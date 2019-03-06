import {ErrorHandler} from '../platform/errors/errors.handler';

export class AppContext{
  public static API_BASE_URL: string = 'http://localhost:8080';
  private static errorHandler: ErrorHandler = new ErrorHandler();
  
  //Replace with error interceptor
  static getErrorHandler(): ErrorHandler{
    return this.errorHandler;
  }
}
