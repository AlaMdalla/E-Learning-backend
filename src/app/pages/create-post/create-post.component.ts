import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { PostService } from 'src/app/service/post.service';
import { HttpClient, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {

  postForm!: FormGroup;
  selectedFile!: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message!: string;
  img: any;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private postService: PostService,
    private httpClient: HttpClient
  ) {}

  ngOnInit() {
    this.postForm = this.fb.group({
      title: [null, Validators.required],
      content: [null, [Validators.required, Validators.maxLength(5000)]],
      postedBy: [null, Validators.required],
      img: [null]
      
    });
  }

  createPost() {
    if (!this.selectedFile) {
      this.snackBar.open("Veuillez sélectionner une image", "Fermer", { duration: 3000 });
      return;
    }
    
  
    const formData = new FormData();
    formData.append('title', this.postForm.value.title);
    formData.append('content', this.postForm.value.content);
    formData.append('postedBy', this.postForm.value.postedBy);
    formData.append('img', this.postForm.value.img);
    formData.append('imageFile', this.selectedFile, this.selectedFile.name); 
  
    this.postService.createNewPost(formData).subscribe(
      res => {
        this.snackBar.open("Post créé avec succès !", "Fermer", { duration: 3000 });
        this.router.navigateByUrl("/");
      },
      error => {
        this.snackBar.open("Une erreur est survenue !", "Fermer", { duration: 3000 });
      }
    );
  }
  
  public onFileChanged(event: Event) {
    const input = event.target as HTMLInputElement;
  
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }
  

  //Gets called when the user clicks on submit to upload the image
  onUpload() {
    if (!this.selectedFile) {
        console.log("Aucune image sélectionnée !");
        this.message = "Aucune image sélectionnée !";
        return;
    }

    console.log("Fichier à uploader :", this.selectedFile);

    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);

    this.httpClient.post('http://localhost:8082/blog/posts/upload', uploadImageData, { observe: 'response' })
      .subscribe({
        next: (response) => {
          console.log("Réponse de l'API :", response);
          if (response.status === 200) {
            this.message = 'Image uploaded successfully';
          } else {
            this.message = 'Image not uploaded successfully';
          }
        },
        error: (error) => {
          console.error("Erreur lors de l'upload :", error);
          this.message = 'Erreur lors de l\'upload de l\'image';
        }
      });
}


  //Gets called when the user clicks on retieve image button to get the image from back end
  getImage() {
    this.img=this.postForm.get('img')?.value;
    //Make a call to Sprinf Boot to get the Image Bytes.
    this.httpClient.get('http://localhost:8082/blog/posts/get/' + this.img)
      .subscribe(
        res => {
          this.retrieveResonse = res;
          this.base64Data = this.retrieveResonse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        }
      );
  }

  

  
}
