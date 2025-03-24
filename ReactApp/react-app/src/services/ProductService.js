import axios from "axios";

// creación lista de productos
const initProducts = [
  {
    id: 1,
    name: "Monitor Samsung",
    price: 500,
    description: "El momnitor es genial",
  },
  {
    id: 2,
    name: "iPhone 15",
    price: 1200,
    description: "Telefono increible",
  },
];

const baseUrl = "http://localhost:8080/products";

export const listProduct = () => initProducts; // devolver lista de productos

// devolver todos los resultados
export const findAll = async () => {
  try {
    // consulta asíncrona a la db, devuelve una AxiosPromise
    const response = await axios.get(baseUrl);
    return response;
  } catch (error) {
    console.log(error);
  }
  return null;
};

// CREAR UN PRODUCTO
export const create = async ({ name, description, price }) => {
  try {
    const response = await axios.post(baseUrl, {
      /* 
      ESTO SE PUEDE ABREVIAR CUANDO SE LLAMEN IGUAL
      name: name,
      description: description,
      price: price */

      name,
      description,
      price,
    });
    return response;
  } catch (error) {
    console.log(error);
  }
  return undefined;
};

// UPDATE DE PRODUCTO
export const update = async ({ id, name, description, price }) => {
  try {
    // put() se utiliza para enviar datos a un servidor para actualizar un recurso existente
    const response = await axios.put(`${baseUrl}/${id}`, {
      // direccion referencia
      /* 
      ESTO SE PUEDE ABREVIAR CUANDO SE LLAMEN IGUAL
      name: name,
      description: description,
      price: price */

      name,
      description,
      price,
    });
    return response;
  } catch (error) {
    console.log(error);
  }
  return undefined;
};
