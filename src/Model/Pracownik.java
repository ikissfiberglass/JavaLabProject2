package Model;

import java.util.ArrayList;

public class Pracownik<T> {

    private String pesel;
    private String firstName;
    private String secondName;
    private String stanowisko;
    private float wynagrodzenie;
    private String phoneNumber;
    private T custom;
    private float dodatekSluzbowy;
    private String kartaSluzbowa;
    private float limitKosztowMiesiac;
    private static ArrayList<Pracownik> pracownikArrayList = new ArrayList<>();


    public Pracownik() {}

    public Pracownik(String pesel, String firstName, String secondName, float wynagrodzenie, String phoneNumber, float dodatekSluzbowy, String kartaSluzbowa, float limitKosztowMiesiac){
        this.pesel = pesel;
        this.firstName = firstName;
        this.secondName = secondName;
        this.wynagrodzenie = wynagrodzenie;
        this.phoneNumber = phoneNumber;
        this.dodatekSluzbowy = dodatekSluzbowy;
        this.kartaSluzbowa = kartaSluzbowa;
        this.limitKosztowMiesiac = limitKosztowMiesiac;

        this.stanowisko = "pracownik";
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

//        public static ArrayList<Pracownik> getPracownikArrayList(){
//            return pracownikArrayList;
//        }

        public static String getPracownikArrayListStringRepresentation(){
             return pracownikArrayList.toString();
        }

        public static ArrayList<Pracownik> getPracownikArrayList() {return pracownikArrayList;}

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
                }
            }
            return null;
        }

        public static String deletePracownikFromListByPesel(String pesel) throws NullPointerException{
            pracownikArrayList.remove(findPracownikByPesel(pesel));
            if(pracownikArrayList.contains(findPracownikByPesel(pesel))){
                return "Nie udało się usunąć pracownika ( ";
            }else{
                return " Pracownik z numerem PESEL: [" + pesel +  "] został usunięty";
            }
        }

        public static Pracownik getPracownikFromListByIndex(int index){ //indexation starts with 0
            return pracownikArrayList.get(index);
        }

        public static String getPracownikStringRepresentationFromListByIndex( int index){
            return pracownikArrayList.get(index).toString();
        }

        @Override
        public String toString(){
            return String.format(
                    "%-25s %s\n" +
                            "%-25s %s\n" +
                            "%-25s %s\n" +
                            "%-25s %s\n" +
                            "%-25s %.2f\n" +
                            "%-25s %s\n" +
                            "%-25s %.2f\n" +
                            "%-25s %s\n" +
                            "%-25s %.2f",
                    "Identyfikator PESEL:", pesel,
                    "Imię:", firstName,
                    "Nazwisko:", secondName,
                    "Stanowisko:", stanowisko,
                    "Wynagrodzenie:", wynagrodzenie,
                    "Numer telefonu:", phoneNumber,
                    "Dodatek służbowy:", dodatekSluzbowy,
                    "Karta służbowa:", kartaSluzbowa,
                    "Limit kosztów miesięcznych:", limitKosztowMiesiac
            );
        }

    }




