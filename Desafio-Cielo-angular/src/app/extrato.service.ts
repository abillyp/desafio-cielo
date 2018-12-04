import { ListaControleLancamento } from './Model/listaControleLancamento';
import { environment } from './../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class ExtratoService {

  extratoUrl: string;

  constructor(private http: HttpClient) {
      this.extratoUrl = `${environment.apiUrl}/cielo/extrato/lancamentos`;
  }

  getLancamento() {
      return this.http.get<ListaControleLancamento[]>(this.extratoUrl);
  }



}
