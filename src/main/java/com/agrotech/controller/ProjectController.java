package com.agrotech.controller;

import com.agrotech.model.ProjectModel;
import com.agrotech.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String getAllProjects(Model model) {
        List<ProjectModel> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "project/list"; // Assuming you have a Thymeleaf template named "list.html"
    }

    @GetMapping("/{id}")
    public String getProjectById(@PathVariable Long id, Model model) {
        Optional<ProjectModel> project = projectService.getProjectById(id);
        model.addAttribute("project", project.orElse(null));
        return "project/detail"; // Assuming you have a Thymeleaf template named "detail.html"
    }

    @GetMapping("/new")
    public String showProjectForm(Model model) {
        model.addAttribute("project", new ProjectModel());
        return "project/form"; // Assuming you have a Thymeleaf template named "form.html"
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute ProjectModel project) {
        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }
}
