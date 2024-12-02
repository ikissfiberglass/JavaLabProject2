package Controller;

import Model.Dyrektor;
import Model.Handlowiec;
import Model.PracownikRepository;
import View.ViewImpl;

import java.math.BigDecimal;
import java.util.Scanner;
import Model.Pracownik;

public class ControllerImpl {
    private final ViewImpl view = new ViewImpl();
    private final Scanner userInput = new Scanner(System.in);
    PracownikRepository pracownicy = new PracownikRepository();

    public ControllerImpl() {
    }

    public void startMenu() {

        boolean startMenuRunning = true;
        while (startMenuRunning) {
            view.displayMessageNewLine("MENU");
            view.displayMessageNewLine("1. Lista pracowników");
            view.displayMessageNewLine("2. Dodaj pracownika");
            view.displayMessageNewLine("3. Usuń pracownika");
            view.displayMessageNewLine("4. Kopia zapasowa");
            view.displayMessageNewLine("q - wyjście");

            view.displayMessage("Wybór> \n");

            String startMenuOption = userInput.nextLine();
            switch (startMenuOption) {
                case "1":
                    boolean listaPracownikowRunning = true;
                    int index = 0;
                    try {
                        pracownicy.getPracownikStringRepresentationByIndex(index);
                    }catch (IndexOutOfBoundsException e){
                        view.displayMessageNewLine("Spróbuj ponownie");
                        break;
                    }
                    while (listaPracownikowRunning) {
                        view.displayMessageNewLine(pracownicy.getPracownikStringRepresentationByIndex(index));
                        view.displayMessageNewLine("Index: " + index);

                        view.displayMessageNewLine("[Enter] - następny\n [q] - powrót \n [e] - wyjście");
                        String listaPracownikowOption = userInput.nextLine();
                        if (listaPracownikowOption.length() == 0) {
                            if (index < pracownicy.getPracownicySize() - 1) {
                                index++;
                            } else {
                                view.displayMessageNewLine("To ostatni pracownik ");
                            }
                        } else if (listaPracownikowOption.equalsIgnoreCase("q")) {
                            if (index > 0) {
                                index--;
                                //view.displayMessageNewLine(Pracownik.getPracownikStringRepresentationFromListByIndex(index-1));
                            } else {
                                view.displayMessageNewLine("Nie można cofnąć się dalej (");
                            }
                        } else if (listaPracownikowOption.equalsIgnoreCase("e")) {
                            listaPracownikowRunning = false;
                        } else {
                            view.displayMessageNewLine("Niewłaściwa opcja");
                        }
                    }
                    break;
                case "2":
                    boolean roleMenuRunning = true;
                    while (roleMenuRunning) {
                        view.displayMessageNewLine( "[D]yrektor/[H]andlowiec:       ");
                        String roleOption = userInput.nextLine();
                        view.displayMessageNewLine("------------------------------------------------------------------");
                        if (roleOption.equalsIgnoreCase("d")) {
                            initializeDyrektor();
                            roleMenuRunning = false;
                        } else if (roleOption.equalsIgnoreCase("h")) {
                            intializeHandlowiec();
                            roleMenuRunning = false;
                        } else {
                            view.displayMessageNewLine("Spróbuj jeszcze raz");
                        }
                        view.displayMessageNewLine("------------------------------------------------------------------");
                    }


                    break;
                case "3":
                    view.displayMessageNewLine("Podaj Pesel pracownika: ");
                    String deletingOption = userInput.nextLine();
                    view.displayMessageNewLine(pracownicy.findPracownikByPesel(deletingOption).toString());

                    view.displayMessageNewLine("[Enter] - potwierdź \n [Q] - porzuć");
                    String validate = userInput.nextLine();
                    if(validate.trim().isEmpty()){
                        pracownicy.removePracownikByPesel(deletingOption);
                    }else if(validate.equalsIgnoreCase("q")){
                        break;
                    }
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

    public void initializeDyrektor() {
        view.displayMessageNewLine("Podaj Pesel dyrektora: ");
        String peselLocal = userInput.nextLine();

        view.displayMessageNewLine("Podaj imię dyrektora: ");
        String firstName = userInput.nextLine();

        view.displayMessageNewLine("Podaj nazwisko dyrektora: ");
        String secondName = userInput.nextLine();

        float wynagrodzenie = readFloat("Podaj wynagrodzenie dyrektora: ");
        view.displayMessageNewLine("Podaj numer telefonu dyrektora: ");
        String phoneNumber = userInput.nextLine();

        BigDecimal dodatekSluzbowy = readBigDecimal("Podaj dodatek służbowy dyrektora: ");
        view.displayMessageNewLine("Podaj numer karty: ");
        String kartaSluzbowa = userInput.nextLine();

        float limitKosztow = readFloat("Podaj limit kosztów dyrektora: ");

        view.displayMessageNewLine("[Enter] - zapisz");
        String validate = userInput.nextLine();
        if (validate.trim().isEmpty()) {
            try {
                Dyrektor dyrektorLocal = new Dyrektor(peselLocal,
                        firstName,
                        secondName,
                        wynagrodzenie,
                        phoneNumber,
                        dodatekSluzbowy,
                        kartaSluzbowa,
                        limitKosztow);
                pracownicy.addPracownik(dyrektorLocal);
            }catch (IllegalArgumentException e){
                view.displayError(e.getMessage());
            }
        }
    }

    public void intializeHandlowiec() {
        view.displayMessageNewLine("Podaj Pesel handlowca: ");
        String peselLocal = userInput.nextLine();

        view.displayMessageNewLine("Podaj imię handlowca: ");
        String firstName = userInput.nextLine();

        view.displayMessageNewLine("Podaj nazwisko handlowca: ");
        String secondName = userInput.nextLine();

        float wynagrodzenie = readFloat("Podaj wynagrodzenie handlowca: ");
        view.displayMessageNewLine("Podaj numer telefonu handlowca: ");
        String phoneNumber = userInput.nextLine();

        BigDecimal stawkaProwizji = readBigDecimal("Podaj stawkę prowizji handlowca: ");
        BigDecimal limitProwizjiMiesiac = readBigDecimal("Podaj limit prowizji miesięczny: ");

        view.displayMessageNewLine("[Enter] - zapisz");
        String validate = userInput.nextLine();
        if (validate.trim().isEmpty()) {
            try{
            Handlowiec handlowiecLocal = new Handlowiec(peselLocal,
                    firstName,
                    secondName,
                    wynagrodzenie,
                    phoneNumber,
                    stawkaProwizji,
                    limitProwizjiMiesiac);
            pracownicy.addPracownik(handlowiecLocal);
            }catch (IllegalArgumentException e){
                view.displayError(e.getMessage());
            }

        }
    }


    private float readFloat(String prompt) {
        while (true) {
            try {
                view.displayMessageNewLine(prompt);
                return Float.parseFloat(userInput.nextLine());
            } catch (NumberFormatException nfe) {
                view.displayMessageNewLine("Nieprawidłowy format. Wprowadź liczbę zmiennoprzecinkową.");
            }
        }
    }

    private BigDecimal readBigDecimal(String prompt) {
        while (true) {
            try {
                view.displayMessageNewLine(prompt);
                return new BigDecimal(userInput.nextLine());
            } catch (NumberFormatException nfe) {
                view.displayMessageNewLine("Nieprawidłowy format. Wprowadź poprawną liczbę.");
            }
        }
    }


}

