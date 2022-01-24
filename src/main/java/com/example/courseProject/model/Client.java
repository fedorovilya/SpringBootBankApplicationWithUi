package com.example.courseProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Client")
public class Client implements Comparable<Client>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "birth_date")
    private Date birthDate;

    @OneToMany(mappedBy = "client")
    List<CreditCard> creditCardList;

    public enum Role{ ADMIN, USER}

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(unique = true, name = "username")
    private String userName;

    @Column(name = "password")
    private String hashPassword;

    @OneToOne(mappedBy = "client")
    private Contact contact;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthDate=" + birthDate +
                ", role=" + role +
                ", contact=" + contact +
                '}';
    }

    @Override
    public int compareTo(Client o) {
        return this.firstName.compareTo(o.firstName);
    }
}
