import { useState } from "react"


const initialDataForm = {
    name: '',
    description: '',
    price: ''
}

export const ProductForm = ({handlerAdd}) => {

    const [form, setForm] = useState(initialDataForm);

    /* 
    --Desestructuración--
    JavaScript extrae las propiedades name, description y price del objeto form 
    y crea variables individuales con esos nombres. Esto es lo mismo que hacer:

    const name = form.name;
    const description = form.description;
    const price = form.price; 
    */
    const {name, description, price} = form;

    return(
        <form onSubmit={(event) => {
            event.preventDefault();

            // validación de los input
            if (!name || !description || !price) {
                alert('debe completar los datos del formulario');
                return;
            }
            console.log(form); // imprimir el objeto creado  
            handlerAdd(form); // pasamos el producto al handler
            setForm(initialDataForm); // limpiar los input

        }}>
    {       /*
            onChange: Este evento se dispara cada vez que el usuario cambia el valor del campo de entrada 
            (cuando escribe algo). 
            
            setForm es la función que actualiza el estado del formulario.

            { ...form, name: event.target.value }:
            Esto es una actualización del estado de manera inmutable. Usamos el operador de propagación (...) 
            para copiar todo el objeto form y luego sobrescribir solo la propiedad name con el nuevo valor 
            (event.target.value)
            */}
            <input 
            placeholder="Name"
            name="name"
            value={name} 
            onChange={(event) => setForm({
                ...form, 
                name: event.target.value
            })}
            />
            <input 
            placeholder="Description"
            name="description"
            value={description} 
            onChange={(event) => setForm({
                ...form, 
                description: event.target.value
            })}
            />
            <input 
            placeholder="Price"
            name="price"
            value={price}
            onChange={(event) => setForm({
                ...form, 
                price: event.target.value
            })}
            />
            <button type="submit">
                Enviar
            </button>
        </form>
    )
}