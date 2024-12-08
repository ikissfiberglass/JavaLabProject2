package Model;

import View.ViewImpl;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

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


    public void serializeToZip(String fileName) {
        if (!fileName.endsWith(".zip")) {
            fileName += ".zip";
        }

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File("megafolder", fileName)));
             ObjectOutputStream oos = new ObjectOutputStream(zos)) {
            zos.putNextEntry(new ZipEntry("pracownicy"));
            oos.writeObject(pracownicy);
            zos.closeEntry();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println(ioe.getMessage());
        }
    }

    public void serializeToGzip(String fileName) {
        try (ObjectOutputStream objectStream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("megafolder" + File.separator + fileName)))) {
            objectStream.writeObject(pracownicy);
        } catch (IOException e) {
            view.displayMessageNewLine("Błąd serializacji do GZIP: " + e.getMessage());
        }
    }


    private List<Pracownik> deserializeFromZip(String fileName) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream("megafolder" + File.separator + fileName));
             ObjectInputStream ois = new ObjectInputStream(zis)) {
             zis.getNextEntry();
            return (List<Pracownik>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Pracownik> deserializeFromGzip(String fileName) {
        try (ObjectInputStream objectStream = new ObjectInputStream(new GZIPInputStream(new FileInputStream("megafolder" + File.separator + fileName)))) {
            return (List<Pracownik>) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            view.displayMessageNewLine("Błąd deserializacji z GZIP: " + e.getMessage());
            return null;
        }
    }

    public List<Pracownik> deserialize(String fileName){
        if (fileName.endsWith(".gz")) {
            return deserializeFromGzip(fileName);
        } else if (fileName.endsWith(".zip")) {
            return deserializeFromZip(fileName);
        } else {
            view.displayMessageNewLine("Nieobsługiwany format pliku: " + fileName);
            return null;
        }
    }



}



