package hr.tvz.java.projekt.entiteti;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clan extends Osoba implements Mjerenje{
    private Clanarina clanarina;
    private Float visina;
    private Float masa;

    private Float prsa;
    private Float struk;
    private Float bokovi;
    private Float bedro;
    private Float postotakMasnoca;
    private boolean izazov;

    public Clan(String ime, String prezime, LocalDate datumRodjenja, Clanarina clanarina, Float visina, Float masa,
                Float prsa, Float struk, Float bokovi, Float bedro, Float postotakMasnoca, boolean izazov) {
        super(ime, prezime, datumRodjenja);
        this.clanarina = clanarina;
        this.visina = visina;
        this.masa = masa;
        this.prsa = prsa;
        this.struk = struk;
        this.bokovi = bokovi;
        this.bedro = bedro;
        this.postotakMasnoca = postotakMasnoca;
        this.izazov = izazov;
    }

    public Clanarina getClanarina() {
        return clanarina;
    }

    public void setClanarina(Clanarina clanarina) {
        this.clanarina = clanarina;
    }

    public Float getVisina() {
        return visina;
    }

    public void setVisina(Float visina) {
        this.visina = visina;
    }

    public Float getMasa() {
        return masa;
    }

    public void setMasa(Float masa) {
        this.masa = masa;
    }

    public Float getPrsa() {
        return prsa;
    }

    public void setPrsa(Float prsa) {
        this.prsa = prsa;
    }

    public Float getStruk() {
        return struk;
    }

    public void setStruk(Float struk) {
        this.struk = struk;
    }

    public Float getBokovi() {
        return bokovi;
    }

    public void setBokovi(Float bokovi) {
        this.bokovi = bokovi;
    }

    public Float getBedro() {
        return bedro;
    }

    public void setBedro(Float bedro) {
        this.bedro = bedro;
    }

    public Float getPostotakMasnoca() {
        return postotakMasnoca;
    }

    public void setPostotakMasnoca(Float postotakMasnoca) {
        this.postotakMasnoca = postotakMasnoca;
    }

    public boolean isIzazov() {
        return izazov;
    }

    public void setIzazov(boolean izazov) {
        this.izazov = izazov;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clan clan = (Clan) o;
        return ime.equals(clan.ime) && prezime.equals(clan.prezime) && datumRodjenja.equals(clan.datumRodjenja)
                && visina.equals(clan.visina)  && masa.equals(clan.masa) && prsa.equals(clan.prsa)
                && struk.equals(clan.struk) && bokovi.equals(clan.bokovi) && bedro.equals(clan.bedro)
                && postotakMasnoca.equals(clan.postotakMasnoca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, prezime, datumRodjenja, visina, masa, prsa, struk, bokovi, bedro, postotakMasnoca);
    }

    @Override
    public String odrediBMIClana(Float visina, Float masa) {
        Float bmi = masa / (visina * visina);
        if (bmi < 18.5) {
            return "Pothranjenost";
        }
        else if (bmi < 25) {
            return "Normalna tjelesna masa";
        }
        else if (bmi < 30) {
            return "Prekomjerna masa";
        }
        else {
            return "Pretilost";
        }
    }

    @Override
    public Float ukupnoMjerenje(Float prsa, Float struk, Float bokovi, Float bedro) {
        return prsa+struk+bokovi+bedro;
    }

    @Override
    public Float rezultatIzazova(Float ukupnoMjerenjePrije, Float ukupnoMjerenjePoslije,
                                 Float postotakMasnocaPrije, Float postotakMasnocaPoslije,
                                 Float masa, Float masaPoslije)
    {
        Float ukupnoIzgubljenihCm = ukupnoMjerenjePrije - ukupnoMjerenjePoslije;
        Float retultatCm = Float.valueOf((ukupnoIzgubljenihCm / ukupnoMjerenjePrije) * 100);

        Float ukupnoIzgubljenihKg = masa - masaPoslije;
        Float rezultatKg = (ukupnoIzgubljenihKg / masa) * 100;

        Float ukupnoIzgubljeneMasnoce = postotakMasnocaPrije - postotakMasnocaPoslije;
        Float rezultatMasnoce = (ukupnoIzgubljeneMasnoce / postotakMasnocaPrije) * 100;

        Float ukupniRezultat = retultatCm + rezultatKg + rezultatMasnoce;

        return ukupniRezultat;
    }

}
