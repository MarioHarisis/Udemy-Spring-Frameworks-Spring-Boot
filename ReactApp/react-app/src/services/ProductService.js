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
