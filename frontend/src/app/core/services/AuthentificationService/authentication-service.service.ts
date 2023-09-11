import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private apiUrl = 'http://localhost:8080/api'; // Remplacez par l'URL de votre backend

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/connexion`, { email, password }).pipe(
      // Gérer la réponse ici
      tap((response) => {
        if (response && response.id) {
          // Stocker les détails de l'utilisateur dans le localStorage
          localStorage.setItem('currentUser', JSON.stringify(response));
        }
      }),
    );
  }

  signUp(nom: string, email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/inscription`, { nom, email, password }).pipe(
      // Utilisez l'opérateur tap pour stocker les informations dans le localStorage
      tap((user) => {
        localStorage.setItem('currentUser', JSON.stringify(user));
      }),
    );
  }
}
