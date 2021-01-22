import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {PanelModule} from "primeng/panel";
import {ButtonModule} from "primeng/button";
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import {FormsModule} from "@angular/forms";
import {FieldsetModule} from "primeng/fieldset";
import {CalendarModule} from "primeng/calendar";
import {HttpClientModule} from "@angular/common/http";
import {ToastModule} from "primeng/toast";
import {MessageService} from "primeng/api";
import {InputTextModule} from "primeng/inputtext";
import {TableModule} from "primeng/table";
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    HomeComponent,
    AdminComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    PanelModule,
    ButtonModule,
    FieldsetModule,
    CalendarModule,
    ToastModule,
    InputTextModule,
    TableModule
  ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
