package com.example.projectmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "project")
public class Project extends AbstractEntity{

    @Column(updatable = false, nullable = false)
    private String name;
    @Column(updatable = false, nullable = false)
    private String stage;
    @Column(nullable = false)
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
