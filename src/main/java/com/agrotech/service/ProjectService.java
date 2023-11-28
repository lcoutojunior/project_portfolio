package com.agrotech.service;

import com.agrotech.model.ProjectModel;
import com.agrotech.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectModel> getAllProjects() {
        return (List<ProjectModel>) projectRepository.findAll();
    }

    public Optional<ProjectModel> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public ProjectModel saveProject(ProjectModel project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}