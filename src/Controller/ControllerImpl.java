package Controller;

import View.ViewImpl;
import java.util.Scanner;
import Model.Pracownik;

public class ControllerImpl {
    private final ViewImpl view;
    private final Scanner userInput = new Scanner(System.in);


    public ControllerImpl(ViewImpl view){

        this.view = view;
    }

    public void startMenu(){
        view.displayMessageNewLine("MENU");
        view.displayMessageNewLine("1. Lista pracowników");
        view.displayMessageNewLine("2. Dodaj pracownika");
        view.displayMessageNewLine("3. Usuń pracownika");
        view.displayMessageNewLine("4. Kopia zapasowa");

        view.displayMessage("Wybór> ");
        String startMenuOption = userInput.nextLine();

        switch(startMenuOption){
            case "1":
                view.displayMessageNewLine("Lista pracowników");
                if(Pracownik.getPracownikArrayListSize() == 0){
                    view.displayMessageNewLine("Lista pracowników jest pusta");
                }else{
                    view.displayMessageNewLine(Pracownik.getPracownikArrayList());
                }
            case "2":
                view.displayMessageNewLine("Podaj Pesel pracownika: ");
                String peselLocal = userInput.nextLine();
                Pracownik tempPracownik = new Pracownik(peselLocal);
                Pracownik.addPracownikToList(tempPracownik);

            case "3":
                view.displayMessageNewLine("Usuwanie pracownika");
                Pracownik.deletePracownikFromListByPesel()

        }



    }

}
