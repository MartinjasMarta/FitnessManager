package hr.tvz.java.projekt.entiteti;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClanUIzazovu extends Clan implements Mjerenje{
    private Float masaPoslije;
    private Float prsaPoslije;
    private Float strukPoslije;
    private Float bokoviPoslije;
    private Float bedroPoslije;
    private Float postotakMasnocaPoslije;

    public ClanUIzazovu(String ime, String prezime, LocalDate datumRodjenja, Clanarina clanarina, boolean izazov,
                        Float visina, Float masa, Float prsa, Float struk, Float bokovi, Float bedro,
                        Float postotakMasnoca, Float masaPoslije, Float prsaPoslije, Float strukPoslije,
                        Float bokoviPoslije, Float bedroPoslije, Float postotakMasnocaPoslije)
    {
        super(ime, prezime, datumRodjenja, clanarina, visina, masa, prsa, struk, bokovi, bedro, postotakMasnoca, izazov);
        this.masaPoslije = masaPoslije;
        this.prsaPoslije = prsaPoslije;
        this.strukPoslije = strukPoslije;
        this.bokoviPoslije = bokoviPoslije;
        this.bedroPoslije = bedroPoslije;
        this.postotakMasnocaPoslije = postotakMasnocaPoslije;
    }

    public Float getMasaPoslije() {
        return masaPoslije;
    }

    public void setMasaPoslije(Float masaPoslije) {
        this.masaPoslije = masaPoslije;
    }

    public Float getPrsaPoslije() {
        return prsaPoslije;
    }

    public void setPrsaPoslije(Float prsaPoslije) {
        this.prsaPoslije = prsaPoslije;
    }

    public Float getStrukPoslije() {
        return strukPoslije;
    }

    public void setStrukPoslije(Float strukPoslije) {
        this.strukPoslije = strukPoslije;
    }

    public Float getBokoviPoslije() {
        return bokoviPoslije;
    }

    public void setBokoviPoslije(Float bokoviPoslije) {
        this.bokoviPoslije = bokoviPoslije;
    }

    public Float getBedroPoslije() {
        return bedroPoslije;
    }

    public void setBedroPoslije(Float bedroPoslije) {
        this.bedroPoslije = bedroPoslije;
    }

    public Float getPostotakMasnocaPoslije() {
        return postotakMasnocaPoslije;
    }

    public void setPostotakMasnocaPoslije(Float postotakMasnocaPoslije) {
        this.postotakMasnocaPoslije = postotakMasnocaPoslije;
    }

    @Override
    public String odrediBMIClana(Float visina, Float masa) {
        Float bmi = masa / (visina * visina);
        if (bmi < 18.5) {
            return bmi + " - Pothranjenost";
        }
        else if (bmi < 25) {
            return bmi + " - Normalna tjelesna masa";
        }
        else if (bmi < 30) {
            return bmi + " - Prekomjerna masa";
        }
        else {
            return bmi + " - Pretilost";
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
