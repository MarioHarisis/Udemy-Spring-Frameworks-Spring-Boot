import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../../models/product';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'product-form',
  imports: [FormsModule, CommonModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
  /* 
  @Input() permite que un hijo reciba datos de su padre.
  Se usa @Input() product: Product para recibir un objeto de tipo Product.
  El padre envía el producto al hijo con [product]="selectedProduct" desde el product-form
  Si no se envía nada, el hijo (este compnente) usa el valor por defecto 
  { id: 0, name: "", description: "", price: 0 }. 
  */
  @Input() product: Product = {
    id: 0,
    name: '',
    description: '',
    price: 0,
  }

  /* 
  - @Output(): Es un decorador de Angular que permite que un componente envíe eventos a su componente padre.

  - newProductEvent: Es una variable pública del componente que almacena un EventEmitter.
  Su propósito es emitir eventos personalizados con datos asociados.

  - new EventEmitter() | EventEmitter es una clase de Angular utilizada para emitir eventos personalizados.
  En este caso, newProductEvent se inicializa como una instancia de EventEmitter, lo que permite al componente 
  emitir eventos cuando sea necesario.
  */
  @Output() newProductEvent = new EventEmitter();

  // recibe los datos del formulario
  onSubmit(): void {
    // Llama al método emit() de EventEmitter, enviando el valor de this.product al padre
    this.newProductEvent.emit(this.product);
    console.log(this.product);
  }

  cleanProduct() {
    this.product = {
      id: 0,
      name: '',
      description: '',
      price: 0,
    }
    }
}
