package com.springboot.di.app.springboot_di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.springboot.di.app.springboot_di.repositories.IProductRepository;
import com.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;

@Configuration // Permite que Spring la reconozca como una fuente de definiciones de beans
@PropertySource("classpath:config.properties") /*
                                                * Especifica la ubicación de un archivo de propiedades que se debe
                                                * cargar en el contexto de Spring Al utilizar esta anotación, todas las
                                                * propiedades definidas en el archivo config.properties estarán
                                                * disponibles para ser inyectadas en otros componentes de Spring, como
                                                * servicios, controladores, etc.
                                                */
public class AppConfig {

    @Bean("productJson")
    IProductRepository productRepositoryJson() {

        return new ProductRepositoryJson(); // podemos retornar un ProductRepositoryJson() porque implemente la interfaz
                                            // que queremos devolver (IProductRepository)
    }

}
