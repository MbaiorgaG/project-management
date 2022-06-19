package com.example.projectmanagement.repo;

import com.example.projectmanagement.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
