import { useEffect, useState } from "react"
import { findAll, listProduct } from "../services/ProductService"; // importamos la lista de productos
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


    const [productSelected, setProductSelected] = useState({
        /*
        Aquí estamos usando el hook useState para crear una variable de estado y su función para actualizarla.
        - productSelected almacena el producto actualmente seleccionado.
        - setProductSelected es la función que se usa para actualizar el estado.
        */
        id: 0,
        name: '',
        description: '',
        price: ''
    });

    // obtener la lista de productos
    const getProducts = async () => {

        const result = await findAll(); // findAll() definido en el ProductService

        /*
        setProducts actualizará el estado del componente con el valor de result, 
        que es la lista de productos que se obtuvo. 

        result.data accede al cuerpo de la respuesta, que generalmente contiene 
        los datos reales que estamos buscando.

        Dentro del objeto _embedded, se asume que existe una propiedad llamada 
        products que contiene la lista de productos.
        
        Así que result.data._embedded.products accede a esa lista específica de productos 
        que queremos utilizar.
        */
        setProducts(result.data._embedded.products); // prefijos del JSON
    }

    useEffect(() => {
        getProducts(); // simplemente obtenemos el resultado de la petición de GetProducts();
        /* 
        En este caso, estamos utilizando useEffect para hacer una llamada a 
        una función que recupera los productos y luego actualiza el estado de 
        products con el resultado. 

        CODIGO PREVIO SIMULACIÓN DE DB----------
        const result = listProduct();
        setProducts(result);
        -----------------------------------------
        Dependencias vacías []: Hace que el efecto se ejecute solo una vez, 
        justo cuando el componente se monta.
        */
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

        if (product.id > 0) { // si el producto existe

            setProducts(products.map(prod => {

                if(prod.id == product.id){ // si contienne el mismo id
                    return {...product}; // Actualiza el producto
                }
                return prod; // devuelve el mismo
            }))

        }else {
            // Agrega el nuevo producto con id único
            setProducts([...products, {...product, id: new Date().getTime()}]);
        }
    }

    const handlerRemoveProduct = (id) => { // parámetro name producto que queremos eliminar
        console.log('Product ID "'+ id + '" removed');
        // recorre products elimina el name que sí coincida.
        setProducts(products.filter(prod=> prod.id != id));
        
    }

    const handlerProductSelected = (product) => {
        /*
            El estado productSelected cambia en el momento en que se ejecuta setProductSelected({...product}), 
            es decir, cuando se llama a handlerProductSelected(product)

            Al hacer { ...product }, creamos un nuevo objeto en memoria, evitando modificar el objeto original.
        */
        setProductSelected({...product});
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
        <div className="container">
            <h2>{title}</h2>
            <div className="row">
                <div className="col">
                    <ProductForm handlerAdd = {handlerAddProduct} productSelected={productSelected}/>
                </div>
                <div>
                    {
                        products.length > 0 ? <ProductGrid products={products} handlerRemove={handlerRemoveProduct} handlerSelected={handlerProductSelected}/> :
                        <div className="alert alert-warning">No hay productos en el sistema!</div>
                    }
                </div>
            </div>

        </div>
        </>
    )
}

// Define qué tipo de datos debe recibir ProductApp en sus props.
ProductApp.propTypes = {
    // Indica que products debe ser un string y es obligatorio (isRequired)
    title: PropTypes.string.isRequired
}