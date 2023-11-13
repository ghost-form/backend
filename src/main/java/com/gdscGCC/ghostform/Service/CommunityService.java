package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Dto.Project.ProjectResponseDto;
import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Entity.StaredProject;
import com.gdscGCC.ghostform.Entity.User;
import com.gdscGCC.ghostform.Repository.ProjectRepository;
import com.gdscGCC.ghostform.Repository.StaredProjectRepository;
import com.gdscGCC.ghostform.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityService {
    private final ProjectRepository projectRepository;
    private final StaredProjectRepository staredProjectRepository;
    private final UserRepository userRepository;


    @Transactional
    public String setStar(Long project_id, Long user_id){
        Project project = projectRepository.findById(project_id).orElseThrow(()-> new IllegalArgumentException("해당 프로젝트가 없습니다. id=" + project_id));
        User user = userRepository.findById(user_id).orElseThrow(()-> new IllegalArgumentException("해당 프로젝트가 없습니다. id=" + project_id));

        if(staredProjectRepository.findByProjectAndUser(project, user) == null) {
            // 좋아요를 누른적 없다면 StarredProject 생성 후, 좋아요 처리
            project.setStar(project.getStar() + 1);
            StaredProject staredProject = new StaredProject(project, user); // true 처리
            staredProjectRepository.save(staredProject);
            return "좋아요 처리 완료";
        } else {
            // 좋아요를 누른적 있다면 취소 처리 후 테이블 삭제
            StaredProject staredProject = staredProjectRepository.findByProjectAndUser(project, user);
            staredProject.unStarredProject(project);
            staredProjectRepository.deleteById(staredProject.getId());
            return "좋아요 취소";
        }

    }

    @Transactional
    public List<ProjectResponseDto> findByStared(Pageable pageable) {
        Page<StaredProject> staredProjects = staredProjectRepository.findAllByStaredIs(pageable, true);
        List<ProjectResponseDto> projectResponseDtoList = new ArrayList<>();
        staredProjects.stream().forEach(i -> projectResponseDtoList.add(new ProjectResponseDto(i.getProject())));

        return projectResponseDtoList;
    }
}
