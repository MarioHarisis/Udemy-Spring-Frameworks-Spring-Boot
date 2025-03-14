import { useState } from 'react'
import './App.css'

// Export permite que el componente principal pueda ser utilizado en otros archivos.
export const App = () => {
  /* 
  - useState: Es un hook de React que permite manejar el estado dentro de un componente funcional.

  - Un Hook es una función especial en React que te permite usar características 
  como el estado (useState) o efectos (useEffect) en componentes funcionales.
  */
  const [count, setCount] = useState(0);
  /*
  count: Es una variable de estado que almacena un número (inicialmente 0).
  setCount: Es la función que se usa para actualizar el valor de count.
  useState(0): Establece el valor inicial de count en 0.
  */

  return (
    /* <> ... </>: Fragmento de React (React.Fragment), usado para agrupar 
    elementos sin agregar un div innecesario. */
    <>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
      </div>
    </>
    /* 
    ¿Cómo funciona la actualización del estado?
    Cada vez que el usuario haga clic en el botón:

    Se ejecuta setCount((count) => count + 1), que aumenta count en 1.
    React detecta el cambio y vuelve a renderizar el componente con el nuevo valor de count.
    El nuevo valor de count se muestra en el botón.
    */
  )
}
