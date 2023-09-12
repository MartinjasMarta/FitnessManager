package hr.tvz.java.projekt.entiteti;

public enum Clanarina {
    DOLAZAK(1,1, "Po dolasku"),
    TJEDNA(2,7, "Tjedna"),
    MJESECNA(3,30, "Mjesečna"),
    GODISNJA(4,365, "Godišnja");

    private final int sifra;
    private final int brojDana;
    private final String opis;

    Clanarina(int sifra, int brojDana, String opis) {
        this.sifra = sifra;
        this.brojDana = brojDana;
        this.opis = opis;
    }

    public int getSifra() {
        return sifra;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public String getOpis() {
        return opis;
    }

    @Override
    public String toString() {
        return sifra + " " + opis;
    }
}
