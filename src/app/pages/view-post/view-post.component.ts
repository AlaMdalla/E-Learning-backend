import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentService } from 'src/app/service/comment.service';
import { PostService } from 'src/app/service/post.service';
import { ReclamationService } from 'src/app/service/reclamation.service';

@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrls: ['./view-post.component.scss']
})
export class ViewPostComponent implements OnInit {
  postId: number; // Explicitly typed as number
  postData: any;
  CommentForm!: FormGroup;
  comments: any[] = [];
  reclamations: any[] = [];

  constructor(
    private postService: PostService,
    private activatedRoute: ActivatedRoute,
    private matSnackBar: MatSnackBar,
    private fb: FormBuilder,
    private commentService: CommentService,
    private reclamationService: ReclamationService,
    private router: Router
  ) {
    // Convert the string parameter to a number
    this.postId = +this.activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.getPostById();
    
    this.CommentForm = this.fb.group({
      postedBy: [null, Validators.required],
      content: [null, Validators.required],
    });
  }

  publishComment() {
    const postedBy = this.CommentForm.get('postedBy')?.value;
    const content = this.CommentForm.get('content')?.value;
    this.commentService.createComment(this.postId, postedBy, content).subscribe(
      res => {
        this.matSnackBar.open("Comment Published Successfully", "Ok");
        this.CommentForm.reset();
        this.getCommentByPost();
      },
      error => {
        this.matSnackBar.open("Something Went Wrong!!");
      }
    );
  }

  getPostById() {
    this.postService.getPostById(this.postId).subscribe(
      res => {
        this.getCommentByPost();
        this.postData = {
          ...res,
          avatar: res.postedBy ? `assets/img/avatar${res.postedBy}.jpg` : 'assets/img/default-avatar.jpg'
        };
      },
      error => {
        this.matSnackBar.open("Something went wrong!!");
      }
    );
  }

  getCommentByPost() {
    this.commentService.getAllCommentByPost(this.postId).subscribe(
      res => {
        this.comments = res.map((comment: { postedBy: any }) => ({
          ...comment,
          avatar: comment.postedBy ? `assets/img/avatar${comment.postedBy}.jpg` : 'assets/img/default-avatar.jpg',
          showReply: false,
          replyContent: ''
        }));
      },
      error => {
        this.matSnackBar.open("Something went wrong!!");
      }
    );
  }

  

  reactPost() {
    this.postService.reactPost(this.postId).subscribe(
      res => {
        this.matSnackBar.open("Post reacted successfully", "Close", { duration: 3000 });
        if (this.postData) {
          this.postData.likeCount += 1;
        }
      },
      error => {
        this.matSnackBar.open("Something Wrong!!", "Close", { duration: 3000 });
      }
    );
  }

  replyToComment(commentId: number) {
    const comment = this.comments.find(c => c.id === commentId);
    if (comment && comment.replyContent) {
      const postedBy = this.CommentForm.get('postedBy')?.value || 'Anonymous';
      this.commentService.replyToComment(commentId, postedBy, comment.replyContent).subscribe(
        res => {
          this.matSnackBar.open("Reply posted successfully!", "Close", { duration: 3000 });
          comment.replyContent = '';
          comment.showReply = false;
          this.getCommentByPost();
        },
        error => {
          console.error('Reply error:', error);
          this.matSnackBar.open("Error posting reply!", "Close", { duration: 3000 });
        }
      );
    }
  }

  goToReclamationForm() {
    this.router.navigate([`/reclamation/${this.postId}`]);
  }
}