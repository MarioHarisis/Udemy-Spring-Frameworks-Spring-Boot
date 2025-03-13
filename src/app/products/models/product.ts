export class Product {

    /* Al no estar inicializados, da error, hay dos soluciones:
    - inicializarlos --> id: number = 0;
    - poner '!', esto significa que se asignará el tipado más adelante
    */
    id!: number;
    name!: string;
    description!: string;
    price!: number;
}