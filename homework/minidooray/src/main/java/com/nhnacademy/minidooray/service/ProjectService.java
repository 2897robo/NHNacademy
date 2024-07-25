package com.nhnacademy.minidooray.service;

import com.nhnacademy.minidooray.jpa.entity.Project;
import com.nhnacademy.minidooray.jpa.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public List<Project> findByAdminId(Long adminId) {
        return projectRepository.findByAdminId(adminId);
    }
}
