package hr.tvz.java.projekt.iznimke;

public class NeispravnaVrijednostVisine extends Exception{
    public NeispravnaVrijednostVisine() {
    }

    public NeispravnaVrijednostVisine(String message) {
        super(message);
    }

    public NeispravnaVrijednostVisine(String message, Throwable cause) {
        super(message, cause);
    }

    public NeispravnaVrijednostVisine(Throwable cause) {
        super(cause);
    }

    public NeispravnaVrijednostVisine(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
