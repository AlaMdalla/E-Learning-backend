import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePostComponent } from './pages/create-post/create-post.component';
import { ViewAllComponent } from './pages/view-all/view-all.component';
import { ViewPostComponent } from './pages/view-post/view-post.component';
import { UpdatePostComponent } from './pages/update-post/update-post.component';
import { ReclamationComponent } from './reclamation/reclamation.component';
import { ListreclamationComponent } from './pages/listreclamation/listreclamation.component';


const routes: Routes = [
  {path: 'create-post' , component: CreatePostComponent},
  {path: 'view-all' , component: ViewAllComponent},
  {path: 'view-post/:id' , component: ViewPostComponent},
  {path: 'update-post/:id' , component: UpdatePostComponent},
  { path: 'view-post/:id', component: ViewPostComponent },
  { path: 'reclamation/:id', component: ReclamationComponent },
  { path: 'list-reclamations', component: ListreclamationComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
