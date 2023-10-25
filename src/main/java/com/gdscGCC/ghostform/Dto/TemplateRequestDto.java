package com.gdscGCC.ghostform.Dto;

import com.gdscGCC.ghostform.Entity.Template;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TemplateRequestDto {
    /** 템플릿 번호 */
    private Long id;
    /** 템플릿 이름 */
    private String name;
    /** 템플릿 전체 내용 */
    private String content;

    @Builder
    public TemplateRequestDto(Long id, String name, String content){
        this.id = id;
        this.name = name;
        this.content = content;
    }

    // request Dto로 받은 Template 객체를 entity화 해서 저장하는 용도
    public Template toEntity(){
        return Template.builder().id(id).name(name).content(content).build();
    }
}