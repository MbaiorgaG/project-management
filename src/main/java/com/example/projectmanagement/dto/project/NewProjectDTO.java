package com.example.projectmanagement.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewProjectDTO {
    private String projectName;
    private String projectStage;
    private String projectDesc;
}

