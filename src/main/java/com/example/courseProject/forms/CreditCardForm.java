package com.example.courseProject.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCardForm {
    @NotEmpty
    private String cardNum;
    @NotEmpty
    private Integer userId;
    private double money;
}
