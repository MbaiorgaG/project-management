package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.project.NewProjectDTO;
import com.example.projectmanagement.service.impl.ProjectServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/project/")
public class ProjectController {

private final ProjectServiceImpl projectService;

    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @GetMapping("new")
    public String displayProjForm(Model model){
        NewProjectDTO newProject = new NewProjectDTO();
        model.addAttribute("project",newProject);
        return "project/new_project";
    }

    @PostMapping("/save")
    public String createProj(@Valid NewProjectDTO projectDTO, Model model){
        System.out.println(projectDTO);
        return  "redirect:/project/new";
    }
}
