import { useEffect, useState } from "react"


const initialDataForm = {
    id:0,
    name: '',
    description: '',
    price: ''
}

export const ProductForm = ({ productSelected ,handlerAdd }) => {

    const [form, setForm] = useState(initialDataForm);

    /* 
    --Desestructuración--
    JavaScript extrae las propiedades name, description y price del objeto form 
    y crea variables individuales con esos nombres. Esto es lo mismo que hacer:

    const name = form.name;
    const description = form.description;
    const price = form.price; 
    */
    const {id, name, description, price} = form;


    useEffect(() => {
        /*
        usamos un useEffect para sincronizar el formulario con el producto seleccionado
        */
        setForm(productSelected);
    }, [productSelected]); // Cuando productSelected cambia, se ejecuta el useEffect()

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
            className="form-control my-3 w-75"
            name="name"
            value={name} 
            onChange={(event) => setForm({
                ...form, 
                name: event.target.value
            })}
            />
            <input
            className="form-control my-3 w-75"
            placeholder="Description"
            name="description"
            value={description} 
            onChange={(event) => setForm({
                ...form, 
                description: event.target.value
            })}
            />
            <input
            className="form-control my-3 w-75"
            placeholder="Price"
            name="price"
            value={price}
            onChange={(event) => setForm({
                ...form, 
                price: event.target.value
            })}
            />
            <button className="btn btn-primary" type="submit">
                {id> 0 ? 'Update' : 'Create'}
            </button>
        </form>
    )
}