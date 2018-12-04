import { OnInit } from '@angular/core';
export class ExtratoResumido implements OnInit {

  dataLancamento: string;
  descricao: string;
  numero: number;
  situacao: string;
  dataConfirmacao: string;
  dadosBancario: string;
  valorFinal: number;


  ngOnInit() {
  }

  constructor() {
    this.dataLancamento = ' ';
    this.descricao = ' ';
    this.numero = 0;
    this.situacao = ' ';
    this.dataConfirmacao = ' ';
    this.dadosBancario = ' ';
    this.valorFinal = 0;
 }

}
