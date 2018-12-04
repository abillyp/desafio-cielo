import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TabelaLancamentoComponent } from './tabela-lancamento/tabela-lancamento.component';
import { TableModule } from 'primeng/table';
import { ButtonModule} from 'primeng/button';
import { ExtratoService } from './extrato.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    TabelaLancamentoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule,
    HttpClientModule,
    FormsModule,
    ButtonModule
  ],
  providers: [ExtratoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
