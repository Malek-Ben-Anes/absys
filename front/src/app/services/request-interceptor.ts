import * as _ from 'lodash';
import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse} from '@angular/common/http';
import {catchError, tap, retry} from 'rxjs/operators';
import {Observable, of} from 'rxjs';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

  readonly CROS_ORIGIN_KEY = 'Access-Control-Allow-Origin';
  readonly CONTENT_TYPE_KEY = 'Content-Type';
  readonly ACCEPT_KEY = 'Accept';

  constructor() { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('http interceptor');
    if (!request.headers.has(this.CONTENT_TYPE_KEY)) {
      request = request.clone({headers: request.headers.set(this.CONTENT_TYPE_KEY, 'application/json')});
    } 
    if (!request.headers.has(this.CROS_ORIGIN_KEY)) {
      request = request.clone({headers: request.headers.set(this.CROS_ORIGIN_KEY, '*')});
    }
    request = request.clone({headers: request.headers.set(this.ACCEPT_KEY, '*/*')});
    return next.handle(request).pipe(
        tap(),
        retry(2),
        catchError((err: any) => {
          if (err instanceof HttpErrorResponse) {
          }
          return of(err);
        }));
  }
}

/*
    ERROR HANDLER
  private errorHandler(err: HttpErrorResponse) {
    if (err.error instanceof Error) {
      console.log(err.error);
      console.log("Client-side error occured.");
    } else {
      console.log(err.error);
      console.log("Server-side error occured.");
    }
  }
*/

/* TOASTER
if (evt instanceof HttpResponse) {
    if(evt.body && evt.body.success)
        this.toasterService.success(evt.body.success.message, evt.body.success.title, { positionClass: 'toast-bottom-center' });
    }
 if(err instanceof HttpErrorResponse) {
    try {
            this.toasterService.error(err.error.message, err.error.title, { positionClass: 'toast-bottom-center' });
        } catch(e) {
            this.toasterService.error('An error occurred', '', { positionClass: 'toast-bottom-center' });
        }
}


userAPI(data): Observable<any> {
  return this.http.post(this.baseurl, data, httpOptions)
    .pipe(
      tap((result) => console.log('result-->',result)),
      catchError(this.handleError('error', []))
    );
}*/
