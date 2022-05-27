package com.CoinPerso.CPAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movement {
    private String type;
    private String date;
    private float price;
    private float quantity;
}
