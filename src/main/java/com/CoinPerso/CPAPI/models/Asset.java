package com.CoinPerso.CPAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
    private String name;
    private String symbol;
    private List<Movement> movements;
}
