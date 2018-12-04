import { OnInit } from '@angular/core';
import { DadosBancarios } from './dadosBancarios';

export class LancamentoContaCorrenteCliente implements OnInit {
  numeroRemessaBanco: number;
  nomeSituacaoRemessa: string;
  dadosDomicilioBancario: DadosBancarios;
  nomeTipoOperacao: string;


  ngOnInit() {

  }

  constructor() {
  this.numeroRemessaBanco = 0;
  this.nomeSituacaoRemessa = '';
  this.nomeTipoOperacao = '';
  }
}
