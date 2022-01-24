package com.example.courseProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "numcode")
    private int numCode;

    @Column(name = "charcode")
    private String charCode;

    @Column(name = "nominal")
    private int nominal;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private double value;

    @Column(name = "previous")
    private double previous;

    @OneToMany(mappedBy = "currency")
    private List<CreditCard> creditCardList;
}
