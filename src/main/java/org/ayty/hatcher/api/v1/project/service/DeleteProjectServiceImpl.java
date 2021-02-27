package org.ayty.hatcher.api.v1.project.service;

import lombok.RequiredArgsConstructor;

import org.ayty.hatcher.api.v1.project.entity.Project;
import org.ayty.hatcher.api.v1.project.exception.ProjectNotFoundException;
import org.ayty.hatcher.api.v1.project.jpa.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeleteProjectServiceImpl implements DeleteProjectService {

	private final ProjectRepository projectRepository;

	@Override
	public void delete(Long id) {
		Optional<Project> obj = projectRepository.findById(id);
		if (obj.isPresent()) {
			projectRepository.delete(obj.get());
		}
		else {
			throw new ProjectNotFoundException();
		}
	}
}