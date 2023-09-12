package hr.tvz.java.projekt.iznimke;

public class NeispravanUnosDatuma extends RuntimeException{
    public NeispravanUnosDatuma() {
    }

    public NeispravanUnosDatuma(String message) {
        super(message);
    }

    public NeispravanUnosDatuma(String message, Throwable cause) {
        super(message, cause);
    }

    public NeispravanUnosDatuma(Throwable cause) {
        super(cause);
    }

    public NeispravanUnosDatuma(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
