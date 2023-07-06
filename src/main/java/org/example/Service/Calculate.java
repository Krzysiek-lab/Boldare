package org.example.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Calculate {
    private final List<String> changesList = new ArrayList<>();

    public void calculateChangeValue() {
        try {
            System.out.println("Podaj reszty do przeliczenia, możesz przerwać wpisywanie wciskając przycisk 'c': ");
            Scanner scanner = new Scanner(System.in);
            String change = scanner.nextLine();
            Register register = new CashRegister(new PLN());
            MoneyService moneyService = new MoneyService(register);

            changesList.add(change);
            while (!change.equals("c")) {
                change = scanner.nextLine();
                if (!Objects.equals(change, "c")) {
                    changesList.add(change);
                }
            }

            for (var changes : changesList) {
                changes = changes.replace(" zł", "");
                moneyService.calculateChange(new BigDecimal(changes));
            }
        } catch (NumberFormatException e) {
            System.out.println("Podano nieprawidłowy format liczby.");
        }
    }
}

// Użyłem tu abstrakcji (PLN) by zgodnie z litera O akronimu SOLID
// można było ewentualnie podać inna klasę implementującą intrfejs Register z innymi monetami poza polskimi
// i by zgodnie z litera I tego akronimu zamiast obiektów używać abstrakcji (register) by obiekty wyższego poziomu nie zależały od tych niższego poziomu