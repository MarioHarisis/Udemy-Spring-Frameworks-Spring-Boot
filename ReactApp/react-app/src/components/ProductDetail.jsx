import PropTypes from "prop-types"

export const ProductDetail = ({handlerSelected ,handlerRemove, product = {} }) => {
    return(
                    <tr>
                        <td>{product.name}</td>
                        <td>{product.description}</td>
                        <td>{product.price}</td>
                        <td>{
                        /*Se ejecuta handlerSelected(product), pasando el producto de la fila seleccionada.*/}
                            <button className="btn btn-secondary btn-sm" onClick={() => {handlerSelected(product)}}>
                                Update
                            </button>
                        </td>
                        <td>{
                            /*Se ejecuta handlerSelected(product), pasando el id producto de la fila seleccionada.*/}
                            <button className="btn btn-danger btn-sm" onClick={() => {handlerRemove(product.id)}}>
                                Remove
                            </button>
                        </td>
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
    product: PropTypes.object.isRequired,
    handlerRemove: PropTypes.func.isRequired,
    handlerSelected: PropTypes.func.isRequired
}