package org.example.Service;

import java.math.BigDecimal;
import java.util.Scanner;

public class Calculate {

    public static void calculateChangeValue() {
        try {
            System.out.println("Podaj reszty do przeliczenia, możesz przerwać wpisywanie wciskając przycisk 'c': ");
            Scanner scanner = new Scanner(System.in);
            String change = scanner.nextLine();

            // Użyłem tu abstrakcji (PLN) by zgodnie z litera O akronimu SOLID
            // można było ewentualnie podać inna klasę implementującą intrfejs Register z innymi monetami poza polskimi
            // i by zgodnie z litera I tego akronimu zamiast obiektów używać abstrakcji (register) by obiekty wyższego poziomu nie zależały od tych niższego poziomu

            Register register = new CashRegister(new PLN());
            MoneyService moneyService = new MoneyService(register);

            while (!change.equals("c")) {
                moneyService.calculateChange(new BigDecimal(change));
                change = scanner.nextLine();
            }
        } catch (NumberFormatException e) {
            System.out.println("Podano nieprawidłowy format liczby.");
        }

    }
}
