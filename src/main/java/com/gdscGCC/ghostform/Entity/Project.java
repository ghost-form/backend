package com.gdscGCC.ghostform.Entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/** 프로젝트 개체 */
public class Project {
    @Id
    @GeneratedValue
    /** 프로젝트 번호 */
    private Long id;

    /** 프로젝트 제목 */
    private String title;

    /** 프로젝트 설명 */
    private String description;
    /** 하위 템플릿 */
    @OneToMany(mappedBy = "project")
    private List<Template> templates = new ArrayList<Template>();

    /** 프로젝트 변수들 */
    private List<Variable> Variables;

    /** 사용자 */
    private int user_id;
    /** 최종 수정일 */
    private String lastModifiedDate;

    public void updateProject(Long id, String description, String title, String lastModifiedDate, List<Template> templates) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.templates = templates;
        this.lastModifiedDate = lastModifiedDate;
    }

    @Builder
    public Project(Long id, String description, String title, String lastModifiedDate, List<Template> templates) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.templates = templates;
        this.lastModifiedDate = lastModifiedDate;
    }

    public void update(Long id, String description, String title, String lastModifiedDate, List<Template> templates){
        this.id = id;
        this.description = description;
        this.title = title;
        this.templates = templates;
        this.lastModifiedDate = lastModifiedDate;
    }
}
