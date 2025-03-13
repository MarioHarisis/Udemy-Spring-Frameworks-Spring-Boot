import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { ProductComponent } from './products/components/product/product.component';

@Component({
  selector: 'app-root', // conexion en el index general mediante <app-root></app-root>
  standalone: true,
  imports: [CommonModule, ProductComponent], // importación del componente product
  templateUrl: './app.component.html', // el html que lleva <app-root></app-root> en el index general
  styleUrl: './app.component.css' // el css que lleva <app-root></app-root> en el index general
})

// sus usos están en ./app.component.html
export class AppComponent {
  title: string = 'Hola mundo Angular 17';
  enabled: boolean = false;
  
  courses: string[] = ['Angular', 'React', 'Spring Boot'];

  // método para el botón
  setEnabled(): void {
    /* 
    if (this.enabled) {
  this.enabled = false;
} else {
  this.enabled = true;
}
  este código invierte el valor actual de this.enabled. 
  Si estaba en true, se convierte en false, y viceversa.
 */
    this.enabled = this.enabled? false : true;
  }
}
