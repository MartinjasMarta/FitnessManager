package hr.tvz.java.projekt.entiteti;

import java.util.ArrayList;
import java.util.List;

public interface Mjerenje {

    String odrediBMIClana(Float visina, Float masa);

    Float ukupnoMjerenje(Float prsa, Float struk, Float bokovi, Float bedro);

    Float rezultatIzazova(Float ukupnoMjerenjePrije, Float ukupnoMjerenjePoslije,
                    Float postotakMasnocaPrije, Float postotakMasnocaPoslije, Float masa, Float masaPoslije);

}
