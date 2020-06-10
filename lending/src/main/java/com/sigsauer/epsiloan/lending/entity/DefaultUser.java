package com.sigsauer.epsiloan.lending.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Data
@Entity
public class DefaultUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    public DefaultUser() {
        this.username = "System";
    }

    public DefaultUser(String username) {
        this.username = username;
    }
}
