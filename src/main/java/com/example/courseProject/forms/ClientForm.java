package com.example.courseProject.forms;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//заполненная форма загружается в объект этого класса
public class ClientForm {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String secondName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String birthDay;
    private String phone;
    private String email;

    private String telegram;

    private String meta;

    private String vk;
}
