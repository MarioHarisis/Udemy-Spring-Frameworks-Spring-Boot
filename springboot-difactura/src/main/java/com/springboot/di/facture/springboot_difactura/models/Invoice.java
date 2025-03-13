package com.springboot.di.facture.springboot_difactura.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Invoice {

    @Autowired
    private Client client;

    @Value("${invoice.description}")
    private String description;

    @Autowired
    @Qualifier("default") // inyectando mediante el bean
    private List<Item> items;

    /*
     * A diferencia del PostConstruct, el constructor de una clase se ejecuta
     * inmediatamente cuando se crea una instancia del objeto
     */
    public Invoice() {
        System.out.println("Creando el componente de la factura desde Constructor");

        // NO podemos acceder a los atributos sin arg en los param ambos devuelven NULL
        System.out.println(client + " desde Constructor");
        System.out.println(description + " desde Constructor");
    }

    /*
     * @PostConstruct se ejecuta después de que Spring haya terminado de inyectar
     * todas las dependencias en el bean
     * Útil si necesitas ejecutar lógica de inicialización que dependa de otros
     * beans inyectados
     */
    @PostConstruct
    public void init() {
        System.out.println("Creando el componente de la factura desde PostConstruct");
        client.setName(client.getName().concat(" PostConstruct"));
        description = description.concat(" del PostConstruct");
    }

    @PreDestroy // Se ejecuta justo antes de cerrar la aplicación
    public void destroy() {
        System.out.println("Destruyendo el bean invoice");
    }

    // No tenemos ningún atributo 'total' pero al usar un método get, se transforma
    // automáticamente a JSON y lo podemos ver
    public int getTotal() {
        /*
         * OPCIÓN CON STREAM
         * int total = items.stream()
         * .map(item -> item.getSubtotal())
         * .reduce(0,(sum, importe) -> sum + importe);
         */

        int total = 0;
        for (Item item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

}
