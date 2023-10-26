package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Dto.ProjectRequestDto;
import com.gdscGCC.ghostform.Dto.ProjectResponseDto;
import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    // DB에 save
    @Transactional
    public Long save(ProjectRequestDto requestDto){
        // dto를 entity화 해서 repository의 save 메소드를 통해 db에 저장.
        // 저장 후 생성한 id를 반환해 줌.
        return projectRepository.save(requestDto.toEntity()).getId();
    }

    // DB에서 하나의 row 조회
    @Transactional
    public ProjectResponseDto findById(Long id){
        Project project = projectRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 프로젝트가 없습니다. id=" + id));
        System.out.println("project id : " + project.getId());
        System.out.println("project title : " + project.getTitle());
        System.out.println("project description : " + project.getDescription());
        System.out.println("project lastModifiedDate : " + project.getLastModifiedDate());
        return new ProjectResponseDto(project);
    }

    // DB에서 하나의 row 수정
    @Transactional
    public Long update(Long id, ProjectRequestDto requestDto){
        Project project = projectRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 프로젝트가 없습니다. id=" + id));

        project.update(requestDto.getId(), requestDto.getTitle(), requestDto.getDescription(), requestDto.getLastModifiedDate(), requestDto.getTemplates());
        System.out.println("project id : " + project.getId());
        System.out.println("project title : " + project.getTitle());
        System.out.println("project content : " + project.getDescription());
        System.out.println("project content : " + project.getLastModifiedDate());

        return id;
    }

    // DB에서 하나의 row 삭제
    @Transactional
    public void delete(Long id){
        Project project = projectRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 프로젝트가 없습니다. id=" + id));
        projectRepository.deleteById(id);
        System.out.println("project id : " + project.getId() + "project was deleted.");
    }
}
