package com.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.springboot.di.app.springboot_di.controllers.Product;
import com.springboot.di.app.springboot_di.repositories.IProductRepository;

// Aquí entra toda la lógica
@Service
public class ProductServiceImpl implements IProductService {

    // private IProductRepository productRepository = new IProductRepository();
    @Autowired
    @Qualifier("productJson") // seleccionamos por nombre qué bean queremos implementar,
    // de esta manera si quisieramos cambiar de implementación, sólo tendríamos que
    // cambiar el nombre
    private IProductRepository productRepository; // inyección de dependencia mediante interfaz

    /*
     * Environment es una abstracción que proporciona acceso a las propiedades de
     * configuración y a los perfiles activos de la aplicación. Spring usa
     * Environment para cargar y acceder a variables y configuraciones desde
     * diferentes fuentes, como archivos de propiedades (.properties), archivos YAML
     * (.yml), variables de entorno del sistema operativo, argumentos de línea de
     * comandos, entre otros.
     */
    @Autowired
    private Environment environment;

    @Override
    public List<Product> findAll() {
        /*
         * map() para iterar sobre cada producto
         * collect() en Java es una operación terminal de un stream, devuelve
         * una colección o algún tipo de estructura de datos que agrupa los resultados
         * En este caso, estamos diciendo que queremos recolectar los elementos en una
         * lista usando collect(Collectors.toList())
         * Las operaciones terminales son las que activan el procesamiento del stream.
         * 
         * Hasta que se invoca una operación terminal, ninguna operación intermedia
         * (como map() o filter()) realmente se ejecuta.
         * 
         * Un stream no almacena los datos directamente, sino que permite procesarlos de
         * manera eficiente, transformando los elementos a medida que fluyen por una
         * serie de operaciones intermedias
         */
        return productRepository.findAll().stream().map(p -> {
            // Product productTax = new Product(p.getId(), p.getName(), p.getPrice() *
            // 1.21);

            // Forma con clone()
            Product productTax = (Product) p.clone();

            // obtenemos el valor de "config.price.tax", lo recibimos como un String desde
            // config.properties y lo pasamos a Double
            productTax.setPrice(p.getPrice() * environment.getProperty("config.price.tax", Double.class));

            return productTax; // Retorna el producto clonado y modificado al stream.
        }).collect(Collectors.toList()); // recolectar los elementos del stream en una lista
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id);
    }

}
