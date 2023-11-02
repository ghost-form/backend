package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Dto.Template.TemplateRequestDto;
import com.gdscGCC.ghostform.Dto.Template.TemplateResponseDto;
import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Entity.Template;
import com.gdscGCC.ghostform.Repository.ProjectRepository;
import com.gdscGCC.ghostform.Repository.TemplateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TemplateService {
    private final TemplateRepository templateRepository;
    private final ProjectRepository projectRepository;

    // DB에 save
    @Transactional
    public Long save(Long project_id, TemplateRequestDto requestDto){
        // dto를 entity화 해서 repository의 save 메소드를 통해 db에 저장.
        // 저장 후 생성한 id를 반환해 줌.
        Template template = requestDto.toEntity();
        Project project = projectRepository.findById(project_id).orElseThrow(()-> new IllegalArgumentException("해당 프로젝트가 없습니다. id=" + project_id));
        template.setProject(project);

        return templateRepository.save(template).getTemplate_id();
    }

    // DB에서 하나의 row 조회
    @Transactional
    public TemplateResponseDto findById(Long id){
        Template template = templateRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id=" + id));
        System.out.println("template id : " + template.getTemplate_id());
        System.out.println("template name : " + template.getName());
        System.out.println("template content : " + template.getContent());
        System.out.println("template project : " + template.getProject());
        return new TemplateResponseDto(template);
    }

    // DB에서 모든 row 조회
    @Transactional
    public List<Template> findAll(){
        List<Template> templateList = templateRepository.findAll();
        return templateList;
    }

    // DB에서 하나의 row 수정
    @Transactional
    public Long update(Long id, TemplateRequestDto requestDto){
        Template template = templateRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id=" + id));

        template.updateTemplate(requestDto.getTemplate_id(), requestDto.getName(), requestDto.getContent(), requestDto.getProject(), requestDto.getVariables());
        System.out.println("template id : " + template.getTemplate_id());
        System.out.println("template name : " + template.getName());
        System.out.println("template content : " + template.getContent());
        System.out.println("template project : " + template.getProject());

        return id;
    }

    // DB에서 하나의 row 삭제
    @Transactional
    public void delete(Long id){
        Template template = templateRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id=" + id));
        templateRepository.deleteById(id);
        System.out.println("template id : " + template.getTemplate_id() + "template was deleted.");
    }
}
