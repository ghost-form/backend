package com.gdscGCC.ghostform.Entity;

import jakarta.persistence.*;
import lombok.*;

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

    /** 프로젝트 번호 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @Setter(value = AccessLevel.NONE)
    private Project project;

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.getTemplates().add(this);
        }
        this.project = project;

        // this : 나 자신의 인스턴스를 넣어준다.
        project.getTemplates().add(this);
    }

    @Builder
    public Template(Long template_id, String name, String content, Project project){
        this.template_id = template_id;
        this.name = name;
        this.content = content;
        this.project = project;
    }

    public void update(Long template_id, String name, String content, Project project){
        this.template_id = template_id;
        this.name = name;
        this.content = content;
        this.project = project;
    }
}
