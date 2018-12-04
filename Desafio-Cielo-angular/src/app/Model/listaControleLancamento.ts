import { OnInit } from '@angular/core';
import { LancamentoContaCorrenteCliente } from './lancamentoContaCorrenteCliente';

export class ListaControleLancamento implements OnInit {
  lancamentoContaCorrenteCliente: LancamentoContaCorrenteCliente;
  dataEfetivaLancamento: string;
  dataLancamentoContaCorrenteCliente: string;
  numeroEvento: number;
  descricaoGrupoPagamento: string;
  nomeBanco: string;
  valorLancamentoRemessa: number;

  ngOnInit() {
  }

  constructor() {
  this.dataEfetivaLancamento = '';
  this.dataLancamentoContaCorrenteCliente = '';
  this.numeroEvento = 0;
  this.descricaoGrupoPagamento = '';
  this.nomeBanco = '';
  this.valorLancamentoRemessa = 0;

  }

}
