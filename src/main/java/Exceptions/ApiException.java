package Exceptions;


public class ApiException extends RuntimeException{
    private final int statusCode;

    public ApiException(int statusCode,String errorMessage){
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
