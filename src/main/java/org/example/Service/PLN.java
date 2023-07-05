package org.example.Service;

import java.util.HashMap;
import java.util.Map;

public class PLN implements Denomination {
    @Override
    public Map<String, Integer> coinDenominationAndAmount() {
        return new HashMap<>(Map.of(
                "5 zł", 1,
                "2 zł", 3,
                "1 zł", 5,
                "50 gr", 10,
                "20 gr", 20,
                "10 gr", 200,
                "5 gr", 100,
                "2 gr", 100,
                "1 gr", 10000));
    }
}