import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = 'http://localhost:8082/';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  createNewPost(data:any):Observable<any>{
    return this.http.post(BASIC_URL+ `blog/posts` , data);
  }
  getAllPosts():Observable<any>{
    return this.http.get(BASIC_URL+ `blog/posts` );
  }
  getPostById(postId:number):Observable<any>{
    return this.http.get(BASIC_URL+ `blog/posts/${postId}` );
  }
  deletePostById(postId:number):Observable<void>{
    return this.http.delete<void>(BASIC_URL+ `blog/posts/${postId}` );
  }
}
