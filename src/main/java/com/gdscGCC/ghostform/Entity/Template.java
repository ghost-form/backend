package com.gdscGCC.ghostform.Entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.HashMap;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/** 템플릿 전체 내용 중 {변수} 중괄호 내부에 있는 값을 프롬프트 변수로 치환할 예정 */
public class Template {
    @Id
    @GeneratedValue
    /** 템플릿 번호 */
    private Long template_id;

    /** 템플릿 이름 */
    private String name;

    /** 템플릿 전체 내용 */
    private String content;

    /** 템플릿 변수들
     * key로 Long id, String name, String type이 존재 */
    @Type(JsonType.class)
    @Column(name = "variables", columnDefinition = "text")
    private HashMap<String, Object> variables = new HashMap<>();

    /** 프로젝트 */
    @OneToOne(mappedBy = "template")
    private Project project;

    @Builder
    public Template(Long template_id, String name, String content, Project project, HashMap<String, Object> variables){
        this.template_id = template_id;
        this.name = name;
        this.content = content;
        this.project = project;
        this.variables = variables;
    }

    public void updateTemplate(Long template_id, String name, String content, Project project, HashMap<String, Object> variables){
        this.template_id = template_id;
        this.name = name;
        this.content = content;
        this.project = project;
        this.variables = variables;
    }

    public void setProject(Project project) {
        this.project = project;
        this.project.setTemplate(this);
    }
}
