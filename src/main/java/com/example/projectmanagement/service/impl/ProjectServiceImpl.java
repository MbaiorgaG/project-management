package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.project.NewProjectDTO;
import com.example.projectmanagement.entities.Project;
import com.example.projectmanagement.repo.ProjectRepository;
import com.example.projectmanagement.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

//    private final ProjectRepository projectRepository;

    public ProjectServiceImpl() {
//        this.projectRepository = projectRepository;
    }

    @Override
    public void saveProjectToDB(NewProjectDTO dto) {
        Project project = new Project();
        project.setName(dto.getProjectName());
        project.setStage(dto.getProjectStage());
        project.setDescription(dto.getProjectDesc());
//        projectRepository.save(project);

    }
}
