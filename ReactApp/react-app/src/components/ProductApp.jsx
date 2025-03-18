import { useEffect, useState } from "react"
import { listProduct } from "../services/ProductService"; // importamos la lista de productos
import { ProductGrid } from "./ProductGrid";
/*
export const → Exporta el componente para poder usarlo en otros archivos.
ProductApp → Es una función que representa un componente en React.
*/
export const ProductApp = () => {

    /* products es el estado, y su valor inicial es un array vacío [] 

    setProducts es la función que nos permite actualizar el estado de products. 
    Cuando llamemos a setProducts, React actualizará el valor de products y 
    re-renderizará el componente.
    */
    const [products, setProducts] = useState([]);


    /* 
    En este caso, estamos utilizando useEffect para hacer una llamada a 
    una función que recupera los productos y luego actualiza el estado de 
    products con el resultado. 
    
    Dependencias vacías []: Hace que el efecto se ejecute solo una vez, 
    justo cuando el componente se monta.
    */
    useEffect(() => {
        const result = listProduct();
        setProducts(result);
    }, []);

    /* 

    ¿Qué es una "prop" en React?

    En React, "props" (abreviatura de "properties") son la forma en que los componentes 
    padres pueden pasar datos a los componentes hijos.

    - Son inmutables (no se pueden modificar dentro del componente que las recibe).
    - Sirven para hacer componentes reutilizables al permitir personalizar su contenido.
    - Se pasan como atributos de HTML en los componentes.

    ¿Qué está pasando aquí?
    
    <ProductGrid /> es un componente hijo que estamos utilizando dentro de otro componente.

    products={products} es una prop que se está pasando al componente ProductGrid.
    - La primera products es el nombre de la prop.
    - La segunda products es el estado que hemos definido en el componente padre con useState.

    Es decir, ProductGrid está recibiendo la lista de productos como una prop, 
    para luego mostrarla en su propio contenido. 
    */
    return (
        <>
        <h1>Hola mundo React!</h1>
        <ProductGrid products={products}></ProductGrid>
        </>
    )
}