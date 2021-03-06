import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeViewComponent} from './views/home-view/home-view.component';
import {LoginComponent} from './login/login.component';
import {ConfigurationComponent} from './admin-configuration-panel/configuration.component';
import {AuthGuardService} from './authentication/auth-guard.service';
import {BookingViewComponent} from './views/booking-view/booking-view.component';
import {RegisterComponent} from './register/register.component';
import {TeamViewComponent} from './views/team-view/team-view.component';
import {ServiceViewComponent} from './views/service-view/service-view.component';
import {ContactViewComponent} from './views/contact-view/contact-view.component';
import {StoreComponent} from './views/store-view/store.component';


const routes: Routes = [
  { path: '', component: HomeViewComponent },

  { path: 'team', component: TeamViewComponent },
  { path: 'services', component: ServiceViewComponent },
  { path: 'contacts', component: ContactViewComponent },
  { path: 'store', component: StoreComponent },
  { path: 'book-now', component: BookingViewComponent },
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'configuration', component: ConfigurationComponent, canActivate: [AuthGuardService]},

  { path: '**', redirectTo: '/'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
