package com.example.projectmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends AbstractEntity{

    @Column(updatable = false, nullable = false)
    private String firstname;
    @Column(updatable = false, nullable = false)
    private String lastname;
    @Column(updatable = false, nullable = false)
    private String state;
    @Column(updatable = false, nullable = false)
    private String age;
}
