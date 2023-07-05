package org.example.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CashRegister implements Register {
    private final Denomination PLN;

    public CashRegister(Denomination PLN) {
        this.PLN = PLN;
    }

    @Override
    public List<BigDecimal> populateChangeList() {
        List<BigDecimal> changeList = new ArrayList<>();
        var coinAmounts = PLN.coinDenominationAndAmount();

        coinAmounts.forEach((key, value) -> {
            for (int i = 0; i < value; i++) {
                if (key.endsWith("zł")) {
                    changeList.add(new BigDecimal(key.replaceAll("[^0-9]", "")).multiply(new BigDecimal("100")));
                } else {
                    changeList.add(new BigDecimal(key.replaceAll("[^0-9]", "")));
                }
            }
        });
        return changeList;
    }
}