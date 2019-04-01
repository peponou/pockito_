import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UrlAddComponent } from './url-add/url-add.component';


const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},

  {
    path: 'url-add/:id',
    component: UrlAddComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
