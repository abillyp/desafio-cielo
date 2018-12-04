import { OnInit } from '@angular/core';

export class DadosBancarios implements OnInit {

  nomeBanco: string;
  numeroAgencia: number;
  numeroContaCorrente: number;

  ngOnInit() {

  }

  constructor() {
  this.nomeBanco = '';
  this.numeroAgencia = 0;
  this.numeroContaCorrente = 0;
  }

}
