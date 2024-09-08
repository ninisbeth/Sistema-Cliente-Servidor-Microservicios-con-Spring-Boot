import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-contactos',
  templateUrl: './contactos.component.html',
  styleUrl: './contactos.component.css'
})
export class ContactosComponent implements OnInit {
  contactos: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    const url = 'http://localhost:8080/contactos';
    this.http.get<any[]>(url).pipe(
      tap(data => {
        this.contactos = data;
        console.log("Datos cargados:", this.contactos);  // Imprime los datos cargados
      }),
      catchError(error => {
        console.error("Error al cargar datos:", error);
        return of([]);  // Devuelve un array vacío en caso de error
      })
    ).subscribe();
  }

  eliminar(id: number): void {
    console.log("ID recibido para eliminar:", id);  // Imprime el ID en la consola
    if (!id) {
      console.error("ID no definido");
      return;
    }
    const url = `http://localhost:8080/contactos/${id}`;
    this.http.delete(url).pipe(
      tap(res => {
        console.log("Eliminación exitosa:", res);  // Imprime la respuesta en la consola
        this.cargar();  // Vuelve a cargar los datos después de eliminar
      }),
      catchError(error => {
        console.error("Error en la solicitud DELETE:", error);  // Imprime errores en la consola
        return of(null);  // Devuelve null en caso de error
      })
    ).subscribe();
  }
}