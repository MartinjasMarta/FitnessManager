package hr.tvz.java.projekt.entiteti;

import java.time.LocalDate;

public abstract class Osoba {
    protected String ime;
    protected String prezime;
    protected LocalDate datumRodjenja;

    public Osoba(String ime, String prezime, LocalDate datumRodjenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }


}
