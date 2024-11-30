package Controller;

import View.ViewImpl;
import java.util.Scanner;
import Model.Pracownik;

public class ControllerImpl {
    private final ViewImpl view = new ViewImpl();
    private final Scanner userInput = new Scanner(System.in);

    public ControllerImpl(){}

    public void startMenu(){

        boolean startMenuRunning = true;
        while(startMenuRunning) {
            view.displayMessageNewLine("MENU");
            view.displayMessageNewLine("1. Lista pracowników");
            view.displayMessageNewLine("2. Dodaj pracownika");
            view.displayMessageNewLine("3. Usuń pracownika");
            view.displayMessageNewLine("4. Kopia zapasowa");
            view.displayMessageNewLine("q - wyjście");

            view.displayMessage("Wybór> ");

            String startMenuOption = userInput.nextLine();
            switch (startMenuOption) {
                case "1":
                    boolean listaPracownikowRunning = true;
                    int index =0;
                    Pracownik.getPracownikStringRepresentationFromListByIndex(index);
                    while(listaPracownikowRunning){
//                        index++;

                        view.displayMessageNewLine(Pracownik.getPracownikStringRepresentationFromListByIndex(index));

                        view.displayMessageNewLine("[Enter] - następny\n [q] - powrót \n [e] - wyjście");
                        String listaPracownikowOption = userInput.nextLine();
                        if(listaPracownikowOption.length() == 0){
                            index++;
                        }else if(listaPracownikowOption.toLowerCase() == "q"){
                            index--;
                            view.displayMessageNewLine(Pracownik.getPracownikStringRepresentationFromListByIndex(index));
                        }else if(listaPracownikowOption.toLowerCase() == "e"){
                            listaPracownikowRunning = false;
                        }
                        else{
                            view.displayMessageNewLine("Niewłaściwa opcja");
                        }
                    }

                    break;
                case "2":
                    view.displayMessageNewLine("Podaj Pesel pracownika: ");
                    String peselLocal = userInput.nextLine();

                    view.displayMessageNewLine("Podaj imię pracownika: ");
                    String firstName = userInput.nextLine();

                    view.displayMessageNewLine("Podaj nazwisko pracownika: ");
                    String secondName = userInput.nextLine();

                    view.displayMessageNewLine("Podaj stanowisko pracownika: ");
                    String stanowisko = userInput.nextLine();

                    view.displayMessageNewLine("Podaj wynagrodzenie pracownika: ");
                    float wynagrodzenie = Float.parseFloat(userInput.nextLine());

                    view.displayMessageNewLine("Podaj numer telefonu pracownika: ");
                    String phoneNumber = userInput.nextLine();

                    view.displayMessageNewLine("Podaj dodatek służbowy pracownika: ");
                    float dodatekSluzbowy = Float.parseFloat(userInput.nextLine());

                    view.displayMessageNewLine("Podaj kartę służbową pracownika: ");
                    String kartaSluzbowa = userInput.nextLine();

                    view.displayMessageNewLine("Podaj limit kosztów miesięcznych pracownika: ");
                    float limitKosztowMiesiac = Float.parseFloat(userInput.nextLine());

                    Pracownik tempPracownik = new Pracownik(peselLocal, firstName, secondName, wynagrodzenie, phoneNumber, dodatekSluzbowy, kartaSluzbowa, limitKosztowMiesiac );
                    Pracownik.addPracownikToList(tempPracownik);

                    break;
                case "3":
                    view.displayMessageNewLine("Podaj Pesel pracownika: ");
                    String deletingOption = userInput.nextLine();
                    view.displayMessageNewLine("Usuwanie pracownika");
                    Pracownik.deletePracownikFromListByPesel(deletingOption);
                    //view.displayMessageNewLine(Pracownik.getPracownikArrayList());
                    break;
                    //case 4:
                    //KOPIA

                case "q":
                    startMenuRunning = false;
                    break;

                default:
                    view.displayMessageNewLine("Wpisz ponownie");
            }
        }



    }

}
