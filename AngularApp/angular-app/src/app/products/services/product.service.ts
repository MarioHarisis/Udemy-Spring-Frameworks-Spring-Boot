import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { map, Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  /* 
  immportamos clase product desde '../models/product' 
  creamos una lista de Productos en JSON
  con los atributos definidos en su clase /models/product.ts
  */
  private products: Product[] = [
    // formato JSON
    {
      id: 1,
      name: 'Mesa comedor',
      description: 'Excelente mesa para el comedor',
      price: 60
    },
    {
      id: 2,
      name: 'Teclado mecánico',
      description: 'Excelente teclado para typing',
      price: 80
    }
  ];
  private url: string = 'http://localhost:8080/products';

  constructor() {}
  /* 
  -Este es un método llamado findAll, que no toma ningún parámetro. 
  El propósito de este método es devolver todos los productos almacenados en la variable products 
  
  - Observable es un tipo de flujo de datos asincrónico que es muy común en Angular, especialmente con RxJS. 
  Esto significa que findAll no devuelve directamente los productos, sino que devuelve un observable 
  que emitirá un valor en el futuro (en este caso, el arreglo de productos).

  - La notación Product[] indica que el observable emitirá una lista (arreglo) de objetos de tipo Product.

  - return of(this.products); | of() es un operador de RxJS que toma un valor y lo emite como un observable. 
  En este caso, of(this.products) emite el arreglo products como un observable.

  En Angular, generalmente se usa el async pipe en las plantillas o 
  el método subscribe() en el código para manejar los valores emitidos por los observables.
  */
  findAll(): Observable<Product[]>{
    return of (this.products);
  }

}
