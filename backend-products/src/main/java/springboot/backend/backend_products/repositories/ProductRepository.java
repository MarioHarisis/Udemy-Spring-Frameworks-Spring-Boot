package springboot.backend.backend_products.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import springboot.backend.backend_products.entities.Product;

// CrudRepository proporciona los métodos básicos (crear, leer, actualizar y eliminar).
@RepositoryRestResource(path = "products")
/*
 * Al utilizar @RepositoryRestResource sobre esta interface, Spring Boot genera
 * automáticamente los siguientes endpoints REST:
 * 
 * GET /products: Para obtener todos los productos.
 * GET /products/{id}: Para obtener un producto por su ID.
 * POST /products: Para crear un nuevo producto.
 * PUT /products/{id}: Para actualizar un producto por su ID.
 * DELETE /products/{id}: Para eliminar un producto por su ID.
 */
@CrossOrigin(origins = "http://localhost:5173") // conexion a Recat
public interface ProductRepository extends CrudRepository<Product, Long> {

}
