package hr.tvz.java.projekt.main;

import hr.tvz.java.projekt.entiteti.*;
import hr.tvz.java.projekt.iznimke.NeispravanUnosDatuma;
import hr.tvz.java.projekt.iznimke.NeispravnaVrijednostUnesenogBroja;
import hr.tvz.java.projekt.iznimke.NeispravnaVrijednostVisine;
import hr.tvz.java.projekt.sortiranje.ClanoviSorter;
import hr.tvz.java.projekt.util.CustomFormats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    private static final int MIN_TRENERA = 2;
    private static final int MIN_CLANOVA = 2;
    private static final int MIN_SEKCIJA = 2;
    private static final int  BROJ_CLANARINA = 4;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {

        Scanner unos = new Scanner(System.in);

        List<Trener> listaTrenera = new ArrayList<>();
        List<Clan> listaClanova = new ArrayList<>();

        int brojTrenera = 0;
        boolean repeatInput;
        do {
            System.out.print("Koliko trenera želite unijeti (minimalno " + MIN_TRENERA + "): ");
            repeatInput = false;
            try {
                brojTrenera = unesiInt(unos, MIN_TRENERA);
            } catch (InputMismatchException | NeispravnaVrijednostUnesenogBroja ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        for (int i = 0; i < brojTrenera; i++) {
            System.out.println("Unesite podatke za " + (i + 1) + ". trenera:");
            listaTrenera.add(unesiNovogTrenera(unos));
        }

        int brojClanova = 0;
        do {
            System.out.print("Koliko članova želite unijeti (minimalno " + MIN_CLANOVA + "): ");
            repeatInput = false;
            try {
                brojClanova = unesiInt(unos, MIN_CLANOVA);
            } catch (InputMismatchException | NeispravnaVrijednostUnesenogBroja ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        for (int i = 0; i < brojClanova; i++) {
            System.out.println("Unesite podatke za " + (i + 1) + ". člana:");
            listaClanova.add(unesiNovogClana(unos));
        }

        int brojSekcija = 0;
        do {
            System.out.print("Koliko sekcija želite unijeti (minimalno " + MIN_CLANOVA + "): ");
            repeatInput = false;
            try {
                brojSekcija = unesiInt(unos, MIN_SEKCIJA);
            } catch (InputMismatchException | NeispravnaVrijednostUnesenogBroja ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        List<Sekcija> listaSekcija = unesiSekciju(unos, brojSekcija, listaClanova, listaTrenera);

        System.out.println("\n\n");

        for (Sekcija sekcija : listaSekcija) {
            ispisiClanoveUSekciji(sekcija);
            System.out.println();
        }

        System.out.println("\n\n");

        List<ClanUIzazovu> listaClanovaUIzazovu = new ArrayList<>();
        for (Clan listaClanovum : listaClanova) {
            if (listaClanovum.isIzazov()) {
                System.out.println("Unesi podatke o novom mjerenju za člana: " + listaClanovum.getIme() + " "
                        + listaClanovum.getPrezime());
                listaClanovaUIzazovu.add(unesiNovoMjerenjaZaClanaUIzazovu(unos, listaClanovum));
            }
        }

        System.out.println("\n\n");


        for(int i = 0; i < listaClanovaUIzazovu.size(); i++){
            System.out.println("Podaci o članu: " + listaClanovaUIzazovu.get(i).getIme() + " "
                    + listaClanovaUIzazovu.get(i).getPrezime());
            System.out.println("BMI: " + listaClanovaUIzazovu.get(i)
                    .odrediBMIClana(listaClanovaUIzazovu.get(i).getVisina(), listaClanovaUIzazovu.get(i).getMasa()));
            Float mjerenjePrije = listaClanovaUIzazovu.get(i).ukupnoMjerenje(listaClanovaUIzazovu.get(i).getPrsa(),
                            listaClanovaUIzazovu.get(i).getStruk(), listaClanovaUIzazovu.get(i).getBokovi(),
                            listaClanovaUIzazovu.get(i).getBedro());
            Float mjerenjePoslije = listaClanovaUIzazovu.get(i).ukupnoMjerenje(listaClanovaUIzazovu.get(i).getPrsaPoslije(),
                    listaClanovaUIzazovu.get(i).getStrukPoslije(), listaClanovaUIzazovu.get(i).getBokoviPoslije(),
                    listaClanovaUIzazovu.get(i).getBedroPoslije());
            System.out.println("Rezultat izazova: " + listaClanovaUIzazovu.get(i).rezultatIzazova
                    (mjerenjePrije, mjerenjePoslije, listaClanovaUIzazovu.get(i).getPostotakMasnoca(),
                            listaClanovaUIzazovu.get(i).getPostotakMasnocaPoslije(), listaClanovaUIzazovu.get(i).getMasa(),
                            listaClanovaUIzazovu.get(i).getMasaPoslije()));
        }

    }
    static Clan unesiNovogClana(Scanner unos) {

        System.out.print("Unesite ime člana: ");
        String ime = unos.nextLine();

        System.out.print("Unesite prezime člana: ");
        String prezime = unos.nextLine();

        boolean repeatInput;
        LocalDate datumRodjenja = LocalDate.now();
        do {
            System.out.print("Unesite datum rođenja člana (u formatu dd.mm.yyyy.): ");
            repeatInput = false;
            try {
                datumRodjenja = unesiDatum(unos);
            } catch (NeispravanUnosDatuma ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        System.out.println("Odaberi vrstu clanarine (unesi broj ispred naziva): ");
        System.out.println(Clanarina.DOLAZAK.getOpis());
        System.out.println(Clanarina.TJEDNA.getOpis());
        System.out.println(Clanarina.MJESECNA.getOpis());
        System.out.println(Clanarina.GODISNJA.getOpis());
        Clanarina clanarina = unosClanarine(unos);

        System.out.println("Unesite podatke inicijalnog mjerenja: ");

        float visina = 0;
        float masa = 0;
        float prsa = 0;
        float struk = 0;
        float bokovi = 0;
        float bedro = 0;
        float postotakMasnoca = 0;


        do {
            repeatInput = false;
            try {
                System.out.print("visina (u metrima): ");
                visina = unesiVisinu(unos);
            } catch (InputMismatchException | NeispravnaVrijednostUnesenogBroja ex) {
                repeatInput = true;
            } catch (NeispravnaVrijednostVisine ex){
                System.out.println("Visina ne smije biti 0! Pokušajte ponovo!");
                logger.error("Unesena je pogrešna vrijednost visine! Unesena je 0.", ex);
                repeatInput = true;
            }
        }
        while (repeatInput);

        do {
            repeatInput = false;
            try {
                System.out.print("masa (u kg): ");
                masa = unesiFloat(unos);
            } catch (InputMismatchException ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        do {
            repeatInput = false;
            try {
                System.out.print("prsa (u cm): ");
                prsa = unesiFloat(unos);
            } catch (InputMismatchException ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        do {
            repeatInput = false;
            try {
                System.out.print("struk (u cm): ");
                struk = unesiFloat(unos);
            } catch (InputMismatchException ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        do {
            repeatInput = false;
            try {
                System.out.print("bokovi (u cm): ");
                bokovi = unesiFloat(unos);
            } catch (InputMismatchException ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        do {
            repeatInput = false;
            try {
                System.out.print("bedro (u cm): ");
                bedro = unesiFloat(unos);
            } catch (InputMismatchException ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        do {
            repeatInput = false;
            try {
                System.out.print("postotak masnoća (u %): ");
                postotakMasnoca = unesiFloat(unos);
            } catch (InputMismatchException ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        boolean izazov = false;
        System.out.println("Hoće li član sudjelovati u izazovu? (Unesite broj ispred odgovora) : ");
        System.out.println("1. Da\n2. Ne");
        int izazovBroj = unos.nextInt();
        unos.nextLine();
        switch (izazovBroj) {
            case 1 -> izazov = true;
            case 2 -> izazov = false;
            }

        return new Clan(ime, prezime, datumRodjenja, clanarina, visina, masa, prsa, struk, bokovi, bedro,
                postotakMasnoca, izazov);
    }

    static Trener unesiNovogTrenera(Scanner unos) {
        var builder = new Trener.TrenerBuilder();

        System.out.print("Unesite ime trenera: ");
        builder.setIme(unos.nextLine());

        System.out.print("Unesite prezime trenera: ");
        builder.setPrezime(unos.nextLine());

        boolean repeatInput;
        do {
            System.out.print("Unesite datum rođenja trenera (u formatu dd.MM.yyyy.): ");
            repeatInput = false;
            try {
                builder.setDatumRodjenja(unesiDatum(unos));
            } catch (NeispravanUnosDatuma ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        return builder.build();
    }

    private static List<Sekcija> unesiSekciju(Scanner unos, int brojSekcija, List<Clan> clanovi,
                                              List<Trener> treneri)
    {
        while (true) {
            List<Sekcija> sekcije = new ArrayList<>();
            Map<Trener, List<Sekcija>> voditelji = new HashMap<>();

            for (int i = 0; i < brojSekcija; i++) {
                Sekcija sekcija = unesiNovuSekciju(unos, treneri, clanovi);
                sekcije.add(sekcija);
                Trener voditelj = sekcija.getTrener();

                if (voditelji.containsKey(voditelj)) {
                    voditelji.get(voditelj).add(sekcija);
                } else {
                    voditelji.put(voditelj, new ArrayList<>() {{
                        add(sekcija);
                    }});
                }
            }
            if (treneri.stream().allMatch(voditelji::containsKey) && treneri.stream().anyMatch(trener -> {
                List<Sekcija> listaSekcija = voditelji.get(trener);
                return listaSekcija != null;
            })) {
                System.out.println("\n\n");
                ispisiVoditeljeSekcija(voditelji);
                return sekcije;
            } else {
                System.out.println("Greška pri unosu! Svaki trener mora biti voditelj barem jedne sekcije!");
            }
        }
    }

    static Sekcija unesiNovuSekciju(Scanner unos, List<Trener> treneri, List<Clan> clanovi) {

        System.out.print("Unesite naziv sekcije: ");
        String naziv = unos.nextLine();

        Trener trener = odaberiTrenera(unos, treneri);

        System.out.println("Unesite broj članova upisanih u sekciju: ");
        int brojUpisanih = 0;
        boolean repeatInput;
        try {
            brojUpisanih = unesiInt(unos, MIN_CLANOVA);
        } catch (InputMismatchException | NeispravnaVrijednostUnesenogBroja ex) {
            repeatInput = true;
        }
        Set<Clan> upisaniClanovi = odaberiClanove(unos, clanovi, brojUpisanih);

        return new Sekcija(naziv, trener, upisaniClanovi);
    }

    static Clanarina unosClanarine(Scanner unos) {
        boolean repeatInput;
        int clanarina;
        do {
            repeatInput = false;
            try {
                clanarina = unos.nextInt();
                if (clanarina > BROJ_CLANARINA) {
                    throw new NeispravnaVrijednostUnesenogBroja();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Pogrešan unos! Pokušajte ponovo!");
                logger.error("Nije unesen broj!", ex);
                repeatInput = true;
                throw new InputMismatchException();
            } catch (NeispravnaVrijednostUnesenogBroja e) {
                System.out.println("Pogrešan unos! Pokušajte ponovo!");
                logger.error("Unesena je pogrešna vrijednost broja!", e);
                repeatInput = true;
                throw new NeispravnaVrijednostUnesenogBroja(e);
            } finally {
                unos.nextLine();
            }
        }while(repeatInput);

        return switch (clanarina) {
            case 1 -> Clanarina.DOLAZAK;
            case 2 -> Clanarina.TJEDNA;
            case 3 -> Clanarina.MJESECNA;
            case 4 -> Clanarina.GODISNJA;
            default -> {
                String message = "Kritična greška!";
                logger.error(message);
                throw new RuntimeException(message);
            }
        };
    }

    static ClanUIzazovu unesiNovoMjerenjaZaClanaUIzazovu(Scanner unos, Clan clanUIzazovu) {

        float masaPoslije = 0;
        float prsaPoslije = 0;
        float strukPoslije = 0;
        float bokoviPoslije = 0;
        float bedroPoslije = 0;
        float postotakMasnocaPoslije = 0;
        boolean repeatInput;

        do {
            repeatInput = false;
            try {
                System.out.print("masa (u kg): ");
                masaPoslije = unesiFloat(unos);
            } catch (InputMismatchException ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        do {
            repeatInput = false;
            try {
                System.out.print("prsa (u cm): ");
                prsaPoslije = unesiFloat(unos);
            } catch (InputMismatchException ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        do {
            repeatInput = false;
            try {
                System.out.print("struk (u cm): ");
                strukPoslije = unesiFloat(unos);
            } catch (InputMismatchException ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        do {
            repeatInput = false;
            try {
                System.out.print("bokovi (u cm): ");
                bokoviPoslije = unesiFloat(unos);
            } catch (InputMismatchException ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        do {
            repeatInput = false;
            try {
                System.out.print("bedro (u cm): ");
                bedroPoslije = unesiFloat(unos);
            } catch (InputMismatchException ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);

        do {
            repeatInput = false;
            try {
                System.out.print("postotak masnoća (u %): ");
                postotakMasnocaPoslije = unesiFloat(unos);
            } catch (InputMismatchException ex) {
                repeatInput = true;
            }
        }
        while (repeatInput);


        return new ClanUIzazovu(clanUIzazovu.getIme(), clanUIzazovu.getPrezime(), clanUIzazovu.getDatumRodjenja(),
                clanUIzazovu.getClanarina(), clanUIzazovu.isIzazov(), clanUIzazovu.getVisina(), clanUIzazovu.getMasa(), clanUIzazovu.getPrsa(), clanUIzazovu.getStruk(),
                clanUIzazovu.getBokovi(), clanUIzazovu.getBedro(), clanUIzazovu.getPostotakMasnoca(),
                masaPoslije, prsaPoslije, strukPoslije, bokoviPoslije, bedroPoslije, postotakMasnocaPoslije);
    }

    static Trener odaberiTrenera(Scanner scanner, List<Trener> treneri) {
        for (int i = 0; i < treneri.size(); i++) {
            System.out.println((i + 1) + ". " + treneri.get(i).getIme() + " " + treneri.get(i).getPrezime());
        }

        System.out.print("Izaberite trenera (Unesite broj ispred imena): ");
        int index = scanner.nextInt() - 1;
        Trener trener = treneri.get(index);
        scanner.nextLine();

        return trener;
    }

    static Set odaberiClanove(Scanner unos, List<Clan> clanovi, int brojUpisanih) {

        List<Clan> listaUpisanihClanova = new ArrayList<>();

        for (int i = 0; i < clanovi.size(); i++) {
            System.out.println((i + 1) + ". " + clanovi.get(i).getIme() + " " + clanovi.get(i).getPrezime());
        }

        for (int i = 0; i < brojUpisanih; i++) {
            System.out.print("Izaberite člana za upis u sekciju (Unesite broj ispred imena): ");
            int index = unos.nextInt() - 1;
            listaUpisanihClanova.add(clanovi.get(index));
            unos.nextLine();
        }

        return new HashSet<>(listaUpisanihClanova);
    }

    private static void ispisiClanoveUSekciji(Sekcija sekcija) {
        System.out.println("Članovi upisani na sekciju: " + sekcija.getNaziv());

        for (Clan clan : sekcija.getClanovi().stream().sorted(new ClanoviSorter()).toList()) {
            System.out.println(clan.getPrezime() + " " + clan.getIme());
        }
    }

    private static void ispisiClanoveUIzazovu(List<Clan> clanoviUIzazovu) {
        System.out.println("Članovi u izazovu: ");

        for (Clan clan : clanoviUIzazovu) {
            System.out.println(clan.getPrezime() + " " + clan.getIme());
        }
    }

    private static void ispisiVoditeljeSekcija(Map<Trener, List<Sekcija>> voditelji) {
        voditelji.forEach((trener, sekcije) -> {
            System.out.print(trener.getIme() + " " + trener.getPrezime() + " je voditelj sekcije: ");
            for (Sekcija sekcija : sekcije) {
                System.out.println(sekcija.getNaziv());
            }
        });
    }

    public static LocalDate unesiDatum(Scanner unos) {
        LocalDate datumRodjenja;
        try {
            datumRodjenja = LocalDate.parse(unos.nextLine(), CustomFormats.CROATIAN_DATE_FORMAT);
        } catch (DateTimeParseException ex) {
            logger.error("Unešen krivi format datuma.", ex);
            System.out.println("Unijeli ste datum u pogrešnom formatu! Pokušajte ponovo!");
            throw new NeispravanUnosDatuma(ex);
        }
        return datumRodjenja;
    }

    public static int unesiInt(Scanner unos, int limiter) {
        int broj;
        try {
            broj = unos.nextInt();
            if (broj < limiter) {
                throw new NeispravnaVrijednostUnesenogBroja();
            }
        } catch (InputMismatchException ex) {
            System.out.println("Pogrešan unos! Pokušajte ponovo!");
            logger.error("Nije unesen broj!", ex);
            throw new InputMismatchException();
        } catch (NeispravnaVrijednostUnesenogBroja e) {
            System.out.println("Pogrešan unos! Pokušajte ponovo!");
            logger.error("Unesena je pogrešna vrijednost broja!", e);
            throw new NeispravnaVrijednostUnesenogBroja(e);
        } finally {
            unos.nextLine();
        }
        return broj;
    }

    public static float unesiVisinu(Scanner unos) throws NeispravnaVrijednostVisine{
        float broj;
        try {
            broj = unos.nextFloat();
            if(broj == 0){
                throw new NeispravnaVrijednostVisine("Visina ne smije biti 0! Pokušajte ponovo!");
            }else if(broj >= 3){
                throw new NeispravnaVrijednostUnesenogBroja("");
            }
        } catch (InputMismatchException ex) {
            System.out.println("Pogrešan unos! Pokušajte ponovo!");
            logger.error("Nije unesen float!", ex);
            throw new InputMismatchException();
        } catch (NeispravnaVrijednostUnesenogBroja e){
            System.out.println("Pogrešan unos! Visina mora biti manja od 3. Pokušajte ponovo!");
            logger.error("Unesena je neispravna vrijednost!", e);
            throw new NeispravnaVrijednostUnesenogBroja();
        } finally {
            unos.nextLine();
        }

        return broj;
    }

    public static float unesiFloat(Scanner unos) {
        float broj;
        try {
            broj = unos.nextFloat();
        } catch (InputMismatchException ex) {
            System.out.println("Pogrešan unos! Pokušajte ponovo!");
            logger.error("Nije unesen float!", ex);
            throw new InputMismatchException();
        }finally {
            unos.nextLine();
        }
        return broj;
    }
}