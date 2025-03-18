import PropTypes from "prop-types"

export const ProductDetail = ( {product = {}} ) => {
    return(
                    <tr>
                        <td>{product.name}</td>
                        <td>{product.description}</td>
                        <td>{product.price}</td>
                    </tr>
    )
}

/*
¿Por qué usar PropTypes?
- Ayuda a detectar errores en tiempo de desarrollo.
- Hace que el código sea más claro sobre qué tipo de datos espera un componente.
- Evita bugs al pasar props incorrectas.
*/

// Define qué tipo de datos debe recibir ProductDetail en sus props.
ProductDetail.propTypes = {
    // Indica que product debe ser un object y es obligatorio (isRequired)
    product: PropTypes.object.isRequired
}