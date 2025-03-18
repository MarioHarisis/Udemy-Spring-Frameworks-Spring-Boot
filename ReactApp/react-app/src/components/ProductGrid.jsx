import PropTypes from "prop-types" // importación dependencia para poder usar propTypes
import { ProductDetail } from "./ProductDetail"
export const ProductGrid = ( {products = []} ) => {

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
    return(

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
                    /* 
                    Se usa key={product.name} para que React 
                    identifique cada ProductDetail de manera única. 
                    (lo mejor sería usar el id, no el name)

                    ❌ React NO pasa key como una prop, porque solo la usa internamente.
                     Puedes pasar el mismo valor como una prop adicional (ej. id) 
                     para identificar cada ProductDetail dentro del componente.

                     Cuando colocamos key en <ProductDetail />, React no asocia la key a los <tr> individuales dentro del componente, sino a todo el componente ProductDetail en sí.

                    Esto significa que cada instancia del componente ProductDetail es única 
                    dentro del map(), y React usa esa key para identificar el bloque completo 
                    de elementos que devuelve ProductDetail (incluyendo todos sus <tr>).
                    */
                    return (
                        <ProductDetail product = {product} key={product.name}/>
                    )
                })}
            </tbody>
        </table>
    )
}
/*
¿Por qué usar PropTypes?
- Ayuda a detectar errores en tiempo de desarrollo.
- Hace que el código sea más claro sobre qué tipo de datos espera un componente.
- Evita bugs al pasar props incorrectas.
*/

// Define qué tipo de datos debe recibir ProductGrid en sus props.
ProductGrid.propTypes = {
    // Indica que products debe ser un array y es obligatorio (isRequired)
    products: PropTypes.array.isRequired
}