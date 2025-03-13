package com.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.jpa.springboot_jpa.entities.Person;
import com.springboot.jpa.springboot_jpa.repositories.IPersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private IPersonRepository personRepository;

	private static final Logger logger = LoggerFactory.getLogger(SpringbootJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mostrarMaxMin();
	}

	@Transactional
	public void mostrarMaxMin() {
		Integer idMax = personRepository.maxId();
		Integer idMin = personRepository.minId();
		System.out.println("El ID más alto es: " + idMax);
		System.out.println("El ID más bajo es: " + idMin);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("============== Consultas personalizadas por id ==============");
		System.out.println("Ingrese el id para el nombre: ");
		Integer id = scanner.nextInt();
		scanner.close();

		String name = personRepository.findNameById(id);
		String fullname = personRepository.getFullnameById(id);

		if (name == null) {
			System.out.println("No se encontró el usuario");
		} else {

			System.out.println("findNameById:" + name);
			System.out.println("getFullnameById" + fullname);
		}

	}

	@Transactional(readOnly = true)
	public void mostrarSinDuplicados() {
		List<String> listaSinRepetidos = personRepository.findDistinct();
		listaSinRepetidos.forEach(p -> System.out.println(p));
		Integer totalPersonsDistinct = personRepository.findDistinctCount();
		System.out.println(" Total de personas encontradas: " + totalPersonsDistinct);
	}

	@Transactional(readOnly = true)
	public void mostrarLista() {
		List<String> fullnameList = personRepository.findAllFullname();
		fullnameList.forEach(fullname -> System.out.println(fullname));

		List<String> fullnameListUpper = personRepository.findAllFullnameUpper();
		System.out.println("LISTA EN UPPER");
		fullnameListUpper.forEach(fullnameUpp -> System.out.println(fullnameUpp));

		List<String> fullnameListLower = personRepository.findAllFullnameLower();
		System.out.println("LISTA EN LOWER");
		fullnameListLower.forEach(fullnameLow -> System.out.println(fullnameLow));
	}

	@Transactional
	public void mostrarBetween() {
		System.out.println("LISTA BETWEEN ID");
		List<Person> listaBetween = personRepository.findByIdBetween(1, 2);
		listaBetween.forEach(System.out::println);

		System.out.println("LISTA BETWEEN LETRAS");
		List<Person> listaBetweenLetras = personRepository.findBetweenChar("P", "Z");
		listaBetweenLetras.forEach(System.out::println);
	}

	// anotamos solo con @Transactional cuando son operaciones CRUD
	@Transactional
	public void create() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce nombre: ");
		String name = scanner.next();

		System.out.println("Introduce apellido: ");
		String lastname = scanner.next();

		System.out.println("Introduce lenguaje preferido: ");
		String language = scanner.next();
		scanner.close();

		Person newUser = personRepository.save(new Person(null, name, lastname, language));
		System.out.println(newUser);
	}

	// anotamos solo con @Transactional cuando son operaciones CRUD
	@Transactional
	public void update() {
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("Ingrese el ID de la persona a editar");
			Integer id = scanner.nextInt();
			Optional<Person> optionalPerson = personRepository.findById(id);
			if (optionalPerson.isPresent()) {

				optionalPerson.ifPresent(person -> {
					System.out.println("Ingrese el lenguaje de programación");
					String programmingLanguage = scanner.next();
					person.setProgrammingLanguage(programmingLanguage);
					personRepository.save(person);
					System.out.println(person);

				});
				break;
			} else {
				System.out.println("El usuario no existe en la DB");
			}

		} while (true);
		scanner.close();
	}

	@Transactional
	public void delete() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingresa el ID del usuario a eliminar");
		int id = sc.nextInt();

		Optional<Person> optionalPerson = personRepository.findById(id);

		// si esta lo elimina, sino devuelve un mensaje
		optionalPerson.ifPresentOrElse(p -> personRepository.delete(p),
				() -> System.out.println("El usuario no existe en la DB"));

		/*
		 * Optional<Person> optionalPerson = personRepository.findById(id);
		 * 
		 * if (optionalPerson.isPresent()) {
		 * 
		 * optionalPerson.ifPresent(person -> {
		 * personRepository.delete(person);
		 * System.out.println("Persona con ID : " + id + " eliminada correctamente");
		 * });
		 * }
		 */
		sc.close();

	}

	public void delete2(Integer id) {
		personRepository.deleteById(id);
	}

	// anotamos con @Transactional(readOnly = true) cuando son consultas(que no
	// modifique la tabla)
	@Transactional(readOnly = true)
	public void findOne() {

		Person person = null;
		// findById devolverá un tipo Person que quedara como Optional
		Optional<Person> optionalPerson = personRepository.findById(1);

		// isPresent(): If a value is present, returns true, otherwise false.
		if (optionalPerson.isPresent()) {
			person = optionalPerson.get(); // get(): If a value is present, returns the
			// value
			System.out.println(person + " está en la lista");
		} else {
			System.out.println(" No está en la lista");
		}

		/*
		 * // Version simplificada
		 * personRepository.findById(1).ifPresent(p -> System.out.println(p));
		 * Al repetirse el objeto en el lambda, podemos omitirlo e imprimirlo con '::'
		 * personRepository.findById(1).ifPresent(System.out::println);
		 */
	}

	// anotamos con @Transactional(readOnly = true) cuando son consultas(que no
	// modifique la tabla)
	@Transactional(readOnly = true)
	public void list() {

		/* List<Person> persons = (List<Person>) personRepository.findAll(); */
		List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguageAndName("Java", "Mario");

		if (persons.isEmpty()) {
			logger.warn("Usuario no encontrado");
		} else {
			persons.stream().forEach(person -> System.out.println(person));
		}

		// Trae los datos name y programmingLanguage de los usuarios
		System.out.println("\nLista de personsData");
		List<Object[]> personsData = personRepository.obtenerPersonData(); // trae todos
		personsData.stream().forEach(person -> System.out.println(person[0] + " es experto en: " + person[1]));

		// trae el que tenga el mismo nombre
		List<Object[]> personsDataName = personRepository.obtenerPersonData("Mario");
		personsDataName.stream().forEach(person -> System.out.println(person[0]));

		// trae el que tenga el mismo nombre y language
		List<Object[]> personsDataFilter = personRepository.obtenerPersonData("Mario", "Java");
		personsDataFilter.stream()
				.forEach(person -> System.out.println(person[0] + " su lenguaje favorito es: " + person[1]));

		// trae el nombre comleto de la persona en base al lenguaje
		List<Object[]> personsLanguage = personRepository.obtenerPersonDataByProgrammingLanguage("Python");
		personsLanguage.stream().forEach(
				person -> System.out.printf("%s %s ha coincidido con el lenguaje %s", person[0], person[1], person[2]));

	}

}
