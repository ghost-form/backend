package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Dto.ProjectRequestDto;
import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public void savePost(ProjectRequestDto projectRequestDto) {
        projectRepository.save(projectRequestDto.ToEntity());
    }

    @Transactional
    @Override
    public List<ProjectRequestDto> getProjectList(Integer pageNum) {
        List<Project> all = projectRepository.findAll();
        List<ProjectRequestDto> ProjectDtoList = new ArrayList<>();

        for(Project project : all){
            ProjectRequestDto projectDto = ProjectRequestDto.builder()
                    .id(project.getId())
                    .title(project.getTitle())
                    .description(project.getDescription())
                    .lastModifiedDate(project.getLastModifiedDate())
                    .build();
            ProjectDtoList.add(projectDto);
        }

        return ProjectDtoList;
    }

    @Transactional
    @Override
    public ProjectRequestDto getPost(Long id) {
        Optional<Project> projectWrapper = projectRepository.findById(id);
        Project project = projectWrapper.get();

        return ProjectRequestDto.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .lastModifiedDate(project.getLastModifiedDate())
                .build();
    }

    @Transactional
    @Override
    public void deletePost(Long id){
        projectRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<ProjectRequestDto> searchPosts(String keyword){
        List<Project> projects = projectRepository.findByTitleContaining(keyword);
        List<ProjectRequestDto> boardList = new ArrayList<>();

        for(Project project : projects){
            ProjectRequestDto build = ProjectRequestDto.builder()
                    .id(project.getId())
                    .title(project.getTitle())
                    .description(project.getDescription())
                    .lastModifiedDate(project.getLastModifiedDate())
                    .build();

            boardList.add(build);
        }

        return boardList;
    }

    @Transactional
    @Override
    public void update(Long id, ProjectRequestDto dto) {
        Optional<Project> byId = projectRepository.findById(id);
        Project project = byId.get();

        project.updateProject(dto.getId(), dto.getDescription(), dto.getTitle(), dto.getLastModifiedDate());
    }
}
