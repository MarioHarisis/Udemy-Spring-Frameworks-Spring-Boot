import { useEffect, useState } from "react"
import { listProduct } from "../services/ProductService"; // importamos la lista de productos
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
    ¿ Por qué usar map()?
    En React, usamos .map() en lugar de .forEach() porque .map() devuelve un nuevo array, 
    mientras que .forEach() no devuelve nada.

    ¿ Por qué especificamos la key? 

    Cuando el estado cambia, React re-renderiza solo los elementos que han cambiado, 
    en lugar de volver a dibujar toda la lista. Si no especificamos una key, React 
    no sabe cuál elemento es cuál, lo que puede causar problemas de rendimiento y errores inesperados.

    Si React detecta que un id ya existe, mantendrá el mismo elemento en el DOM.
    Si se agrega o elimina un producto, React solo actualizará los elementos necesarios 
    en lugar de renderizar toda la lista nuevamente.

    */
    return (
        <>
        <h1>Hola mundo React!</h1>
        <table>
            <thead>
                <tr>
                    <th>name</th>
                    <th>description</th>
                    <th>price</th>
                </tr>
            </thead>
            <tbody>
                {products.map(product => {
                    return (
                        /* Se usa key={product.name} para que React 
                        identifique cada fila de manera única. 
                        (lo mejor sería usar el id)*/
                    <tr key={product.name}>

                        <td>{product.name}</td>
                        <td>{product.description}</td>
                        <td>{product.price}</td>

                    </tr>
                    )
                })}
            </tbody>
        </table>
        </>
    )
}