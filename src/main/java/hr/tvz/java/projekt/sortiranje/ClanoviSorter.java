package hr.tvz.java.projekt.sortiranje;

import hr.tvz.java.projekt.entiteti.Clan;

import java.util.Comparator;

public class ClanoviSorter implements Comparator<Clan> {

        @Override
        public int compare(Clan a, Clan b) {
            int compareValue = a.getPrezime().compareTo(b.getPrezime());
            return compareValue != 0 ? compareValue : a.getIme().compareTo(b.getIme());
        }
}
