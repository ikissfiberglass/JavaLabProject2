package Model;

import java.util.ArrayList;

public class Pracownik<T> {

    private String pesel;
    private String firstName;
    private String secondName;
    private T custom;
    private static ArrayList<Pracownik> pracownikArrayList = new ArrayList<>();


    public Pracownik() {}

    public Pracownik(String pesel){
        this.pesel = pesel;
        addPracownikToList(this);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setCustom(T custom) {
        this.custom = custom;
    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

        public void setPesel(String pesel){
            this.pesel = pesel;
        }

        public static ArrayList<Pracownik> getPracownikArrayList(){
            return pracownikArrayList;
        }

        public static int getPracownikArrayListSize(){
            return pracownikArrayList.size();
        }

        public static void addPracownikToList(Pracownik pracownik){
            pracownikArrayList.add(pracownik);
        }

        public static void deletePracownikFromList(Pracownik pracownik){
            pracownikArrayList.remove(pracownik);
        }

        public static Pracownik findPracownikByPesel(String Pesel) throws NullPointerException{
            for( Pracownik localPracownik : pracownikArrayList){
                if(localPracownik.pesel == Pesel){
                    return localPracownik;
                }else {
                    return null;
                }
            }
        }

        public static void deletePracownikFromListByPesel(String pesel) throws NullPointerException{
            pracownikArrayList.remove(findPracownikByPesel(pesel));
        }

    }
}
