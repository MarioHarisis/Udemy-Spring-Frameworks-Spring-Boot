import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';
import { FormComponent } from '../form/form.component';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [FormComponent], // importar el formulario
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit{

  title: string = 'Lista de productos';
  products: Product[] = [];

  selectedProduct: Product = new Product();

  /* es lo mismo que definir el atributo fuera de constructor
   y luego inyectarlo 
   
   private service: ProductService;*/
  constructor(private service: ProductService){}

  ngOnInit(): void {
    /* 
    - Asincronía: Los datos no se obtienen de inmediato, por ejemplo, si estamos haciendo una llamada a una API, 
    debemos esperar a que se reciban los datos. La suscripción es cómo le decimos al Observable que ejecute 
    una acción CUANDO LOS DATOS ESTEN LISTOS.

    - Múltiples valores: Un Observable puede emitir múltiples valores a lo largo del tiempo, 
    y suscribirse a un Observable significa que estamos apuntando a recibir los datos que el Observable emitirá.

    - Una vez suscritos, el Observable empieza a emitir los valores, y cada vez que eso ocurre, se ejecuta 
    la función de callback que proporcionamos en la suscripción.

    En ngOnInit() asignamos los productos que llegarían desde una API hasta el service, y después aquí
    pasamos ese observable a la lista de Productos[] 'products' suscribiéndonos 
    */
    this.service.findAll().subscribe(products => this.products = products);
  }

  addProduct(product: Product): void {
    if(product.id > 0){ //si el producto existe
      /* 
      map() es un método de los arrays en JavaScript que recorre y crea un nuevo array transformando cada elemento.
      Se reemplaza this.products con una nueva lista en la que:
      */
      this.products = this.products.map( prod => {
        if (prod.id == product.id) { // Si el producto ya existe, se reemplaza con la versión actualizada.
          return { ...product }; // return { ...product }; Crea una copia del objeto product en lugar de modificarlo directamente
        }
        return prod; // Si el producto no coincide, se mantiene sin cambios
      })
    }else {

      this.products = [... this.products, { ...product, id: new Date().getTime() }];
      /* 
      Otra opcion:
      this.products = [... this.products, {... product}] 
      modificar la lista existente añadiendo los productos que ya existían más el que recibe el método
      */
    }
    this.selectedProduct = new Product();
  }

  // eliminar producto por id
  onRemoveProduct(id:number): void {
    // crear una nueva lista filtrando por id y excluyendo el que es igual
    this.products = this.products.filter(product => product.id != id);
  }

  // editar producto seleccionado
  onUpdateProduct(productRow: Product): void {
    this.selectedProduct = {... productRow}; // clonar una instancia de producto(spread operator)
    console.log('Selected Product:', this.selectedProduct); // Verifica que el producto está correcto
  }

}