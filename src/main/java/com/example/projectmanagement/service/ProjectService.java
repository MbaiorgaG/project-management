package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.project.NewProjectDTO;


public interface ProjectService {

    void saveProjectToDB(NewProjectDTO dto);
}
