package Model;

import java.util.ArrayList;
import java.util.List;

public class PracownikRepository {
    private List<Pracownik> pracownicy = new ArrayList<>();

    public PracownikRepository() {
    }

    public List<Pracownik> getAllPracownicy() {
        return new ArrayList<>(pracownicy);
    }

    public String getPracownicyStringRepresentation() {
        return pracownicy.toString();
    }

    public int getPracownicySize() {
        return pracownicy.size();
    }

    public void addPracownik(Pracownik pracownik) {
        pracownicy.add(pracownik);
    }

    public void removePracownik(Pracownik pracownik) {
        pracownicy.remove(pracownik);
    }

    public Pracownik findPracownikByPesel(String pesel) {
        for (Pracownik pracownik : pracownicy) {
            if (pracownik.getPesel().equals(pesel)) {
                return pracownik;
            }
        }
        return null; 
    }

    public String removePracownikByPesel(String pesel) {
        Pracownik pracownik = findPracownikByPesel(pesel);
        if (pracownik != null && pracownicy.remove(pracownik)) {
            return "Pracownik z numerem PESEL: [" + pesel + "] został usunięty";
        }
        return "Nie udało się usunąć pracownika (brak w liście lub błąd)";
    }

    public Pracownik getPracownikByIndex(int index) {
        if (index >= 0 && index < pracownicy.size()) {
            return pracownicy.get(index);
        }
        throw new IndexOutOfBoundsException("Indeks poza zakresem listy.");
    }

    public String getPracownikStringRepresentationByIndex(int index) {
        return pracownicy.get(index).toString();
    }

}



