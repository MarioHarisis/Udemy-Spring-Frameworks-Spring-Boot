package com.springboot.jpa.springboot_jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persons") // si no se define table se cogerá el mismo nombre de la clase
public class Person {

    @Id // Define que el campo siguiente es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Esta anotación le dice a JPA que el valor del campo marcadose va a generar
    // automáticamente. GenerationType.IDENTITY especifica el método que se
    // utilizará para generar ese valor automático. Concretamente, usa una
    // estrategia de auto-incremento
    private Integer id;

    private String name;
    private String lastname;

    @Column(name = "programming_language")
    private String programmingLanguage;

    @Column(name = "created_at");
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Person() {
    }

    public Person(Integer id, String name, String lastname, String programmingLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programmingLanguage = programmingLanguage;
    }

    @PrePersist
    public void prePersist() {
        System.out.println("Evento del ciclo de vida del objeto entity pre-persist");
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("Evento del ciclo de vida del objeto entity pre-update");
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", lastname=" + lastname + ", programmingLanguage="
                + programmingLanguage + "]";
    }

}
