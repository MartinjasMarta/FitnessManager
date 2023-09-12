package hr.tvz.java.projekt.iznimke;

public class NeispravnaVrijednostUnesenogBroja extends RuntimeException{
    public NeispravnaVrijednostUnesenogBroja() {
    }

    public NeispravnaVrijednostUnesenogBroja(String message) {
        super(message);
    }

    public NeispravnaVrijednostUnesenogBroja(String message, Throwable cause) {
        super(message, cause);
    }

    public NeispravnaVrijednostUnesenogBroja(Throwable cause) {
        super(cause);
    }

    public NeispravnaVrijednostUnesenogBroja(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
