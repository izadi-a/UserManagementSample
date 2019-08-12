import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export class User{
  constructor(
    public id:number,
    public name:string,
    public family:string,
    public userName:string,
    public salary:number,
  ) {}
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(
    private httpClient:HttpClient
  ) { 
     }

     getUsers()
  {
    console.log("test call");
    return this.httpClient.get<User[]>('http://localhost:8080/users');
  }

  public deleteUser(user) {
    return this.httpClient.delete<User>("http://localhost:8080/users" + "/"+ user.id);
  }

  public createUser(user) {
    return this.httpClient.post<User>("http://localhost:8080/users", user);
  }
}
