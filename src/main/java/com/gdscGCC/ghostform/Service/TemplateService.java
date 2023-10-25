package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Dto.TemplateRequestDto;
import com.gdscGCC.ghostform.Dto.TemplateResponseDto;
import com.gdscGCC.ghostform.Entity.Template;
import com.gdscGCC.ghostform.Repository.TemplateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TemplateService {
    private final TemplateRepository templateRepository;

    // DB에 save
    @Transactional
    public Long save(TemplateRequestDto requestDto){
        // dto를 entity화 해서 repository의 save 메소드를 통해 db에 저장.
        // 저장 후 생성한 id를 반환해 줌.
        return templateRepository.save(requestDto.toEntity()).getId();
    }

    // DB에서 하나의 row 조회
    @Transactional
    public TemplateResponseDto findById(Long id){
        Template template = templateRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id=" + id));
        System.out.println("template id : " + template.getId());
        System.out.println("template name : " + template.getName());
        System.out.println("template content : " + template.getContent());
        return new TemplateResponseDto(template);
    }

    // DB에서 하나의 row 수정
    @Transactional
    public Long update(Long id, TemplateRequestDto requestDto){
        Template template = templateRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id=" + id));

        template.update(requestDto.getId(), requestDto.getName(), requestDto.getContent());

        return id;
    }

    // DB에서 하나의 row 삭제
    @Transactional
    public void delete(Long id){
        Template template = templateRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id=" + id));
        templateRepository.deleteById(id);
        System.out.println("template id : " + template.getId() + "template was deleted.");
    }
}
