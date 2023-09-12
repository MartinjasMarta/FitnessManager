package hr.tvz.java.projekt.entiteti;

import java.time.LocalDate;

public class Trener extends Osoba{

    private Trener(String ime, String prezime, LocalDate datumRodjenja) {
        super(ime, prezime, datumRodjenja);
    }

    public static class TrenerBuilder {
        private String ime;
        private String prezime;
        private LocalDate datumRodjenja;

        public TrenerBuilder setIme(String ime) {
            this.ime = ime;
            return this;
        }

        public TrenerBuilder setPrezime(String prezime) {
            this.prezime = prezime;
            return this;
        }

        public TrenerBuilder setDatumRodjenja(LocalDate datumRodjenja) {
            this.datumRodjenja = datumRodjenja;
            return this;
        }

        public Trener build() {
            return new Trener(ime, prezime, datumRodjenja);
        }
    }

}
