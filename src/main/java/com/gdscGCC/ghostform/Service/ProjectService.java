package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Dto.Project.ProjectRequestDto;
import com.gdscGCC.ghostform.Dto.Project.ProjectResponseDto;
import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    // DB에 save
    @Transactional
    public Long save(ProjectRequestDto requestDto){
        // dto를 entity화 해서 repository의 save 메소드를 통해 db에 저장.
        // 저장 후 생성한 id를 반환해 줌.
        return projectRepository.save(requestDto.toEntity()).getProject_id();
    }

    // DB에서 모든 row 조회
    @Transactional
    public List<Project> findAll(){
        List<Project> projectList = projectRepository.findAll();
        if (projectList.isEmpty()) {
            throw new IllegalArgumentException("해당 프로젝트 리스트가 없습니다.");
        }
        return projectList;
    }
    // DB에서 하나의 row 조회
    @Transactional
    public ProjectResponseDto findById(Long project_id){
        Project project = projectRepository.findById(project_id).orElseThrow(()-> new IllegalArgumentException("해당 프로젝트가 없습니다. id=" + project_id));
        System.out.println("project id : " + project.getProject_id());
        System.out.println("project title : " + project.getTitle());
        System.out.println("project description : " + project.getDescription());
        System.out.println("project user_id : " + project.getUser_id());
        System.out.println("project lastModifiedDate : " + project.getLastModifiedDate());
        return new ProjectResponseDto(project);
    }

    // DB에서 하나의 row 수정
    @Transactional
    public ProjectResponseDto update(Long project_id, ProjectRequestDto requestDto){
        Project project = projectRepository.findById(project_id).orElseThrow(()-> new IllegalArgumentException("해당 프로젝트가 없습니다. id=" + project_id));
        project.updateProject(requestDto.getProject_id(), requestDto.getTitle(), requestDto.getDescription(), requestDto.getContent(), requestDto.getVariables(), requestDto.getUser_id(), requestDto.getLastModifiedDate(), requestDto.getStar());
        System.out.println("project id : " + project.getProject_id());
        System.out.println("project title : " + project.getTitle());
        System.out.println("project description : " + project.getDescription());
        System.out.println("project user_id : " + project.getUser_id());
        System.out.println("project lastModifiedDate : " + project.getLastModifiedDate());

        return new ProjectResponseDto(project);
    }

    // DB에서 하나의 row 삭제
    @Transactional
    public void delete(Long project_id){
        Project project = projectRepository.findById(project_id).orElseThrow(()-> new IllegalArgumentException("해당 프로젝트가 없습니다. id=" + project_id));
        projectRepository.deleteById(project_id);
        System.out.println("project id : " + project.getProject_id() + "project was deleted.");
    }
}
