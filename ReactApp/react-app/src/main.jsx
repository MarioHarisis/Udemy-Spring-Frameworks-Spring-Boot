import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { ProductApp } from './components/ProductApp.jsx' // importar componente creado

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <ProductApp title = {'Lista de productos'}/>
  </StrictMode>,
)
