import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {DialogOverviewExampleDialogComponent, ScheduleComponent} from './schedule/schedule.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import { SocialLoginModule, AuthServiceConfig, FacebookLoginProvider } from 'angularx-social-login';

const config = new AuthServiceConfig([
  {
    id: FacebookLoginProvider.PROVIDER_ID,
    provider: new FacebookLoginProvider('491244421754834')
  }
]);

export function provideConfig() {
  return config;
}

import {
  MatButtonModule, MatButtonToggleModule, MatCardModule, MatDatepickerModule,
  MatDialogModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatProgressSpinnerModule,
  MatSelectModule, MatSidenavModule, MatSlideToggleModule, MatStepperModule, MatTableModule, MatToolbarModule
} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoginComponent} from './login/login.component';
import {TeamStackComponent} from './stacks/team-stack/team-stack.component';
import {ServiceStackComponent} from './stacks/service-stack/service-stack.component';
import {ContactsStackComponent} from './stacks/contacts-stack/contacts-stack.component';
import {BasicAuthHttpInterceptorService} from './interceptors/basic-auth-http.interceptor.service';
import { RegisterComponent } from './register/register.component';
import { LogoutComponent } from './logout/logout.component';
import { ConfigurationPanelComponent } from './configuration-panel/configuration-panel.component';
import { PieChartComponent } from './charts/pie-chart/pie-chart.component';
import {NgxChartsModule} from '@swimlane/ngx-charts';
import { NumberCardChartComponent } from './charts/number-card-chart/number-card-chart.component';
import {ChangePasswordDialogComponent, ProfileComponent, ServiceDialogComponent} from './profile/profile.component';
import { BookedUsersTableComponent } from './booked-users-table/booked-users-table.component';
import { BookingComponent } from './booking/booking.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ScheduleComponent,
    DialogOverviewExampleDialogComponent,
    LoginComponent,
    TeamStackComponent,
    ServiceStackComponent,
    ContactsStackComponent,
    RegisterComponent,
    LogoutComponent,
    ConfigurationPanelComponent,
    PieChartComponent,
    NumberCardChartComponent,
    ProfileComponent,
    BookedUsersTableComponent,
    ChangePasswordDialogComponent,
    ServiceDialogComponent,
    BookingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    MatSelectModule,
    MatIconModule,
    MatCardModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatMenuModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonToggleModule,
    SocialLoginModule,
    MatListModule,
    NgxChartsModule,
    MatTableModule,
    MatSidenavModule,
    MatProgressSpinnerModule,
    MatSlideToggleModule,
    MatStepperModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthHttpInterceptorService, multi: true },
    {
      provide: AuthServiceConfig,
      useFactory: provideConfig
    }
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    DialogOverviewExampleDialogComponent,
    ChangePasswordDialogComponent,
    ServiceDialogComponent
  ]
})
export class AppModule {
}