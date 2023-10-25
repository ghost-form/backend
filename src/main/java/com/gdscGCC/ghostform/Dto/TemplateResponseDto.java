package com.gdscGCC.ghostform.Dto;

import com.gdscGCC.ghostform.Entity.Template;
import lombok.Getter;

@Getter
public class TemplateResponseDto {
    /** 템플릿 번호 */
    private Long id;
    /** 템플릿 이름 */
    private String name;
    /** 템플릿 전체 내용 */
    private String content;

    // repository를 통해 조회한 entity를 Dto로 변환하는 용도
    public TemplateResponseDto(Template template){
        this.id = template.getId();
        this.name = template.getName();
        this.content = template.getContent();
    }
}
