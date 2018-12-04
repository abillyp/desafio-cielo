import { ListaControleLancamento } from './../Model/listaControleLancamento';
import { ExtratoResumido } from './../Model/extratoResumido';
import { Component, OnInit } from '@angular/core';
import { ExtratoService } from '../extrato.service';

@Component({
  selector: 'app-tabela-lancamento',
  templateUrl: './tabela-lancamento.component.html',
  styleUrls: ['./tabela-lancamento.component.css']
})
export class TabelaLancamentoComponent implements OnInit {


  extratosResumidos: ExtratoResumido[] =  [ ];
  listaLancamento: ListaControleLancamento[] = [];


  constructor(private extratoService: ExtratoService) { }

  ngOnInit() {
    this.extratoService.getLancamento()
     .subscribe((resposta: ListaControleLancamento[]) => {
            this.extratosResumidos = JSON.parse(JSON.stringify( resposta ));
            this.geraLancamentoReduzido(resposta);
           });


  }

/*
  Função para transformar pegar somente as informações que serão exibidas na tela
*/
  geraLancamentoReduzido(data) {
    for(let i = 0; i < data.length; i++) {
      this.extratosResumidos[i].dataLancamento = data[i].dataLancamentoContaCorrenteCliente;
      this.extratosResumidos[i].descricao = data[i].lancamentoContaCorrenteCliente.nomeTipoOperacao;
      this.extratosResumidos[i].numero = data[i].lancamentoContaCorrenteCliente.numeroRemessaBanco;
      this.extratosResumidos[i].situacao = data[i].lancamentoContaCorrenteCliente.nomeSituacaoRemessa;
      this.extratosResumidos[i].dataConfirmacao = data[i].dataEfetivaLancamento;
      this.extratosResumidos[i].dadosBancario = data[i].nomeBanco.trim() + ` Ag ` +
              data[i].lancamentoContaCorrenteCliente.dadosDomicilioBancario.numeroAgencia + ` CC ` +
              data[i].lancamentoContaCorrenteCliente.dadosDomicilioBancario.numeroContaCorrente;
      this.extratosResumidos[i].valorFinal = data[i].valorLancamentoRemessa;
    }
  }


}
