package springboot.backend.backend_products.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // Indica que esta clase es una entidad JPA, representa una tabla en la db
@Table(name = "products") // esta entidad se mapea a la tabla llamada "products"
public class Product {

    @Id // Indica que id es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
     * Hibernate generará automáticamente el valor del id usando auto-incremento
     * de la base de datos
     */

    private Long id;
    private String name;
    private String description;
    private Long price;

}
