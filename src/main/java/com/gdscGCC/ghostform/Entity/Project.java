package com.gdscGCC.ghostform.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/** 프로젝트 개체 */
public class Project {
    @Id
    @GeneratedValue
    /** 프로젝트 번호 */
    private Long project_id;

    /** 프로젝트 제목 */
    private String title;

    /** 프로젝트 설명 */
    private String description;

    /** 하위 템플릿 */
    @OneToOne
    @JoinColumn(name = "template_id")
    private Template template;


    /** 사용자 id */
    private Long user_id;
    /** 최종 수정일 */
    private LocalDateTime lastModifiedDate;


    public LocalDateTime setLastModifiedDate() {
        return this.lastModifiedDate = LocalDateTime.now();
    }

    public void updateProject(Long project_id, String title, String description, Template template, Long user_id) {
        this.project_id = project_id;
        this.title = title;
        this.description = description;
        this.template = template;
        this.user_id = user_id;
        this.lastModifiedDate = setLastModifiedDate();
    }



    @Builder
    public Project(Long project_id, String title, String description, Template template, Long user_id) {
        this.project_id = project_id;
        this.title = title;
        this.description = description;
        this.template = template;
        this.user_id = user_id;
        this.lastModifiedDate = setLastModifiedDate();
    }
}
