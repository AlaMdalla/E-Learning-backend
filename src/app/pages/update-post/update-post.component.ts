import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'src/app/service/post.service';

@Component({
  selector: 'app-update-post',
  templateUrl: './update-post.component.html',
  styleUrls: ['./update-post.component.scss']
})
export class UpdatePostComponent implements OnInit {
  postForm!: FormGroup;
  postId!: number;
  message: string = '';
  retrievedImage: string | undefined;
  selectedFile!: File;
  base64Data: any;
  retrieveResonse: any;
  img: any;
  imageBase64: string | undefined;

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private httpClient: HttpClient
  ) { }

  ngOnInit() {
    // Récupérer l'id du post à partir de l'URL
    this.route.paramMap.subscribe(params => {
      this.postId = +params.get('id')!; // Assurez-vous que l'id est un nombre
      this.loadPostData();
    });

    // Initialisation du formulaire
    this.postForm = this.fb.group({
      title: ['', Validators.required],
      postedBy: ['', Validators.required],
      content: ['', Validators.required],
      img: ['']
      
    });
  }

  // Charger les données du post à partir du service
  loadPostData() {
    this.postService.getPostById(this.postId).subscribe(post => {
      // Préremplir les champs du formulaire avec les données du post
      this.postForm.patchValue({
        title: post.title,
        postedBy: post.postedBy,
        content: post.content,
        img: post.img,
      });
      // Charger l'image si nécessaire
      if (post.img) {
        this.retrievedImage = post.img; // Vous pouvez adapter cela à votre logique d'image
      }
    }, error => {
      this.snackBar.open("Failed to load post data!", "Close", { duration: 3000 });
    });
  }

  // Fonction pour mettre à jour le post
  updatePost() {
    if (this.postForm.valid) {
      // Si une image est sélectionnée, l'ajouter en base64 dans le formulaire
      if (this.imageBase64) {
        this.postForm.get('img')?.setValue(this.imageBase64);
      }

      this.postService.updatePost(this.postId, this.postForm.value).subscribe(() => {
        this.snackBar.open("Post updated successfully!", "Close", { duration: 3000 });
      }, error => {
        this.snackBar.open("Failed to update post!", "Close", { duration: 3000 });
      });
    }
  }

  // Méthode appelée lorsque l'utilisateur sélectionne un fichier
  public onFileChanged(event: Event) {
    const input = event.target as HTMLInputElement;
  
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];

      // Convertir l'image en base64
      const reader = new FileReader();
      reader.readAsDataURL(this.selectedFile);
      reader.onload = () => {
        this.imageBase64 = reader.result?.toString().split(',')[1]; // Extraire la partie base64
        console.log('Base64 Image:', this.imageBase64);
      };
    }
  }

  // Méthode appelée pour télécharger l'image vers le backend
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

  // Méthode appelée pour récupérer l'image depuis le backend
  getImage() {
    this.img = this.postForm.get('img')?.value;
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
