import { useEffect, useState } from "react"
import { listProduct } from "../services/ProductService"; // importamos la lista de productos
import { ProductGrid } from "./ProductGrid";
import PropTypes from "prop-types";
import { ProductForm } from "./ProductForm";
/*
export const → Exporta el componente para poder usarlo en otros archivos.
ProductApp → Es una función que representa un componente en React.
*/
export const ProductApp = ({title}) => {

    /* products es el estado, y su valor inicial es un array vacío [] 

    setProducts es la función que nos permite actualizar el estado de products. 
    Cuando llamemos a setProducts, React actualizará el valor de products y 
    re-renderizará el componente.
    */
    const [products, setProducts] = useState([]);

    useEffect(() => {
        /* 
        En este caso, estamos utilizando useEffect para hacer una llamada a 
        una función que recupera los productos y luego actualiza el estado de 
        products con el resultado. 
        
        Dependencias vacías []: Hace que el efecto se ejecute solo una vez, 
        justo cuando el componente se monta.
        */
        const result = listProduct();
        setProducts(result);
    }, []);


    const handlerAddProduct = (product) => {
        /* 
        setProducts: Esta es la función que actualiza el estado products. 
        Usamos setProducts porque products es una variable de estado en un componente React, 
        y en React nunca se debe modificar el estado directamente (esto es una regla de inmutabilidad).

        [...products, {...product}]: Este es un operador de propagación (spread operator) y se usa para 
        crear una nueva lista de productos. Aquí es donde estamos actualizando el estado de los productos 
        de manera inmutable.

        ...products: Esto copia todos los elementos que ya están en el estado products. 
        El operador de propagación (...) toma todos los elementos del array products y 
        los agrega a la nueva lista.

        {...product}: Aquí estamos creando una nueva copia del objeto product. 
        Esto asegura que el objeto product no se modifique directamente, sino que se cree un nuevo objeto 
        para que React pueda detectar el cambio de estado correctamente. 
        Es un paso para garantizar la inmutabilidad del estado.

        El resultado es una nueva lista de productos que incluye todos los productos anteriores 
        (usando el operador ...products) y el nuevo producto (pasado como argumento a la función) al final. 
        */
        setProducts([...products, {...product}]);
    }

    const handlerRemoveProduct = (name) => {
        console.log(name);
        setProducts(products.filter(prod=> prod.name != name));
        
    }



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
            <h1>{title}</h1>
            <ProductForm handlerAdd = {handlerAddProduct}/>
            <ProductGrid products={products} handlerRemove={handlerRemoveProduct}/>
        </>
    )
}

// Define qué tipo de datos debe recibir ProductApp en sus props.
ProductApp.propTypes = {
    // Indica que products debe ser un string y es obligatorio (isRequired)
    title: PropTypes.string.isRequired
}