package com.CoinPerso.CPAPI.payload.request;

import com.CoinPerso.CPAPI.models.Portfolio;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PortfolioRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String userId;

    private Portfolio portfolio;
}
