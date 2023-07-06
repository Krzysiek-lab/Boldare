package org.example.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class MoneyService implements Money {
    private List<BigDecimal> availableChangeList;

    public MoneyService(Register register) {
        this.availableChangeList = new ArrayList<>(register.populateChangeList()
                .stream().sorted(Comparator.reverseOrder()).toList());
    }

    @Override
    public void calculateChange(BigDecimal change) {
        System.out.println("   Dla reszty " + change + " zł" + "\n");
        List<BigDecimal> changeEndValue = new ArrayList<>();
        String[] val = String.valueOf(change).split("\\.");
        BigDecimal value;
        if (String.valueOf(change).contains(".")) {
            value = new BigDecimal(val[0] + val[1]);
        } else {
            value = change;
        }


        for (var e : availableChangeList) {
            if (value.equals(new BigDecimal("0"))) {
                break;
            }
            if (value.subtract(e).compareTo(BigDecimal.ZERO) > 0 || value.subtract(e).compareTo(BigDecimal.ZERO) == 0) {
                changeEndValue.add(e);
                value = value.subtract(e);
            }
        }
        availableChangeList = subtractOneListFromAnother(availableChangeList, changeEndValue);
        changeEndValue.stream().distinct().toList().forEach(e -> {
            BigDecimal transformedValue = e;
            String denomination;
            if (String.valueOf(e).endsWith("00")) {
                transformedValue = e.divide(new BigDecimal("100"), RoundingMode.CEILING);
                denomination = " zł";
            } else denomination = " gr";
            System.out.println("Wydaj " + changeEndValue.stream().filter(v -> v.equals(e)).count() + " monet " + transformedValue + denomination);
        });
    }


    private List<BigDecimal> subtractOneListFromAnother(Collection<BigDecimal> firstList, Collection<BigDecimal> secondList) {
        List<BigDecimal> endList = new ArrayList<>(firstList);
        secondList.forEach(endList::remove);
        return endList;
    }
}
