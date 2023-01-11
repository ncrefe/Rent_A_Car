package Models.exception;

public class CustomException extends Exception {
    final private CustomError error;
    final private String message;

    public CustomException(CustomError error, String message) {
        this.error = error;
        this.message = message;
    }

    public CustomException(CustomError error) {
        this.error = error;
        this.message = "unknown error";
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
