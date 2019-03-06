
export class ErrorHandler{
    
    handleError(error: any): Promise<Array<any>>{
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}