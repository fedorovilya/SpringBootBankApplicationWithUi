package com.example.courseProject.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionForm {

    @NotEmpty
    private String sender;
    private String recipient;
    @NotEmpty
    private double money;
    @NotEmpty
    private String operation;

    @Override
    public String toString() {
        return "TransactionForm{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", money=" + money +
                ", operation='" + operation + '\'' +
                '}';
    }
}
