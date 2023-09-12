package hr.tvz.java.projekt.entiteti;

import java.util.Set;

public class Sekcija {
    private String naziv;
    private Trener trener;
    private Set<Clan> clanovi;

    public Sekcija(String naziv, Trener trener, Set<Clan> clanovi) {
        this.naziv = naziv;
        this.trener = trener;
        this.clanovi = clanovi;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Set<Clan> getClanovi() {
        return clanovi;
    }

    public void setClanovi(Set<Clan> clanovi) {
        this.clanovi = clanovi;
    }
}
