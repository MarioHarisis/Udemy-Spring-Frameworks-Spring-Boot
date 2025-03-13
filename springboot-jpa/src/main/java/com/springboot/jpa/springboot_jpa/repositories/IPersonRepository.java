package com.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.jpa.springboot_jpa.entities.Person;

// CrudRepository proporciona una implementación de las operaciones CRUD básicas
public interface IPersonRepository extends CrudRepository<Person, Integer> {

    // si cumplimos con las nomenclaturas, podemos crear algunos métodos sin
    // Query
    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    // FORMA SERIALIZADA FINDBY
    Optional<Person> findByNameContaining(String name);

    Optional<Person> findByName(String name);

    // Método findBy de CrudRepository para buscar por lenguaje
    List<Person> findByProgrammingLanguage(String programmingLanguage);

    // FORMA SERIALIZADA BETWEEN
    List<Person> findByIdBetween(Integer id1, Integer id2);

    List<Person> findByNameBetween(String char1, String char2);

    // Encontrar el valor más alto
    @Query("select max(p.id) from Person p ")
    Integer maxId();

    // Encontrar el valor más bajo
    @Query("select min(p.id) from Person p ")
    Integer minId();

    // Query personalizada, devuelve solo el nombre
    @Query("select p.name from Person p where p.id=?1")
    String findNameById(Integer id);

    // Busca por id trae nombre completo concatenado
    // otra forma de concatenar:
    // @Query("select p.name || ' ' || p.lastname as fullname from Person p where
    // p.id= ?1")
    @Query("select CONCAT(p.name, ' ' , p.lastname) as fullname from Person p where p.id= ?1")
    String getFullnameById(Integer id);

    // Trae todos los nombres de la lista sin repetidos
    @Query("select distinct(p.name) from Person p ")
    List<String> findDistinct();

    // Fullname de todos los de la lista
    @Query("select concat(p.name , ' ', p.lastname) from Person p")
    List<String> findAllFullname();

    // Fullname de todos los de la lista en MAYUSCULAS
    @Query("select upper(concat(p.name , ' ', p.lastname)) from Person p")
    List<String> findAllFullnameUpper();

    // Fullname de todos los de la lista en MAYUSCULAS
    @Query("select lower(concat(p.name , ' ', p.lastname)) from Person p")
    List<String> findAllFullnameLower();

    // Personas que su id se encuentre entre dos id
    @Query("select p from Person p where p.id between ?1 and ?2")
    List<Person> findBetweenId(Integer id1, Integer id2);

    // Personas que su id se encuentre entre dos letras
    @Query("select p from Person p where p.name between ?1 and ?2")
    List<Person> findBetweenChar(String char1, String char2);

    // Cuenta los nombres de la lista sin repetidos
    @Query("select count(distinct(p.name)) from Person p ")
    Integer findDistinctCount();

    // Buscar por id método manual
    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Integer id);

    // Encuentra el nombre que contenga lo pasado por param
    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLike(String name);

    // nuestra propia consulta para filtrar por lo que queramos
    @Query("select p from Person p where p.programmingLanguage= ?1 and p.name= ?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

    /* Sobrecarga del método obtenerPersonData */

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p from Person p where p.name= ?1")
    List<Object[]> obtenerPersonData(String name);

    // Solo traerá de la lista lo que coincida con los argumentos pasados
    @Query("select p.name, p.programmingLanguage from Person p where p.name= ?1 and  p.programmingLanguage= ?2")
    List<Object[]> obtenerPersonData(String name, String programmingLanguage);

    @Query("select p.name, p.lastname, p.programmingLanguage from Person p where p.programmingLanguage= ?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguage(String programmingLanguage);

}
