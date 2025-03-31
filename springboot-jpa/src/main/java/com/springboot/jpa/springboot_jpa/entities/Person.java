package com.springboot.jpa.springboot_jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    @Embedded
    private Audit audit = new Audit();

    public Person() {
    }

    public Person(Integer id, String name, String lastname, String programmingLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", lastname=" + lastname + ", programmingLanguage="
                + programmingLanguage + ", createdAt=" + audit.getCreatedAt() + ", " + audit.getUpdatedAt() + "]";
    }

    // GETTERS N SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

}
