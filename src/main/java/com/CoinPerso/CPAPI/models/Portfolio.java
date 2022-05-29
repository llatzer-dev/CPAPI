package com.CoinPerso.CPAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "portfolios")
public class Portfolio {

    @Id
    private String id;

    /*@DBRef*/
    private String idUser;

    @NotBlank
    @Size(max = 20)
    private String name;

    private List<Asset> assets;

}
