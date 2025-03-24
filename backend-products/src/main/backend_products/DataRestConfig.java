package springboot.backend.backend_products;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import springboot.backend.backend_products.entities.Product;

@Configuration // indica a Spring que esta clase contiene configuración para la aplicación.
public class DataRestConfig implements RepositoryRestConfigurer {
    /*
     * implements RepositoryRestConfigurer: Al implementar esta interfaz, puedes
     * sobrescribir métodos para configurar cómo se comporta Spring Data REST, por
     * ejemplo, cómo se exponen los recursos REST, cómo se manejan las entidades,
     * etc.
     */

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        /*
         * config: Es un objeto de tipo RepositoryRestConfiguration que te permite
         * configurar varias propiedades de la exposición del repositorio REST (como qué
         * propiedades se exponen, cómo se exponen, etc.).
         * 
         * cors: Es un objeto de tipo CorsRegistry que te permite configurar las reglas
         * CORS (Cross-Origin Resource Sharing) para las peticiones que provienen de
         * otros orígenes. Esto puede ser útil si estás sirviendo tu aplicación desde un
         * dominio diferente al de tu backend.
         */
        config.exposeIdsFor(Product.class); // exponer el id de los productos en el JSON
    }

}
