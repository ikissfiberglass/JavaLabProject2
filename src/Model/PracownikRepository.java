package Model;

import View.ViewImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PracownikRepository implements Serializable {
    ViewImpl view = new ViewImpl();
    private static final long serialVersionUID = 1L;
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

    public void serialize() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("pracownicy"))) {
            outputStream.writeObject(pracownicy);
        } catch (IOException e) {
            view.displayMessageNewLine(("Błąd serializacji: " + e.getMessage()));
        }
    }

    public List<Pracownik> deserialize() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pracownicy"))) {
            List<Pracownik> deserializedPracownicy = (List<Pracownik>) inputStream.readObject();
            return deserializedPracownicy;
        } catch (IOException | ClassNotFoundException e) {
            view.displayMessageNewLine(("Błąd deserializacji: " + e.getMessage()));
            return null;
        }
    }


    //huinia ne pracuje
//    public void archiveToZip(String inputFileName, String zipFileName) {
//        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFileName));
//             FileInputStream fileIn = new FileInputStream(inputFileName)) {
//
//            ZipEntry zipEntry = new ZipEntry(new File(inputFileName).getName());
//            zipOut.putNextEntry(zipEntry);
//
//            byte[] buffer = new byte[1024];
//            int length;
//
//    }

}



