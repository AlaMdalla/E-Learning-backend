import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const BASIC_URL = 'http://localhost:8082/';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  

  constructor(private http: HttpClient) { }

  createNewPost(formData: FormData): Observable<any> {
    return this.http.post(BASIC_URL + `blog/posts`, formData, { observe: 'response' });
  }
  

  

  getAllPosts(): Observable<any> {
    return this.http.get(BASIC_URL + `blog/posts`);
  }

  getPostById(postId: number): Observable<any> {
    return this.http.get(BASIC_URL + `blog/posts/${postId}`);
  }
  reactPost(postId: number): Observable<any> {
    return this.http.put(BASIC_URL + `blog/posts/${postId}/react`,{});
  }

  deletePostById(postId: number): Observable<void> {
    return this.http.delete<void>(BASIC_URL + `blog/posts/${postId}`);
  }
  updatePost(postId: number, formData: FormData): Observable<any> {
    return this.http.put(BASIC_URL + `blog/posts/${postId}`, formData, { observe: 'response' });
  }
  

}
