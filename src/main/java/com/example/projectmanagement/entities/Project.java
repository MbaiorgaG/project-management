package com.example.projectmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project extends AbstractEntity{

    private String name;

    private String stage;

    private String description;

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", stage='" + stage + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
