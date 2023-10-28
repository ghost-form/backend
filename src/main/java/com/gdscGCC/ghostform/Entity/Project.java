package com.gdscGCC.ghostform.Entity;

import java.time.LocalDateTime;
import java.util.*;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.cglib.core.Local;


@Getter
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
    @OneToMany(mappedBy = "project")
    private List<Template> templates = new ArrayList<Template>();

    /** 프로젝트 변수들
     * key로 Long id, String name, String type이 존재 */
    @Type(JsonType.class)
    @Column(name = "variables", columnDefinition = "text")
    private HashMap<String, Object> variables = new HashMap<>();

    /** 사용자 id */
    private Long user_id;
    /** 최종 수정일 */
    private LocalDateTime lastModifiedDate;


    public LocalDateTime setLastModifiedDate() {
        return this.lastModifiedDate = LocalDateTime.now();
    }

    public void updateProject(Long project_id, String title, String description, List<Template> templates, HashMap<String, Object> variables, Long user_id) {
        this.project_id = project_id;
        this.title = title;
        this.description = description;
        this.templates = templates;
        this.variables = variables;
        this.user_id = user_id;
        this.lastModifiedDate = setLastModifiedDate();
    }



    @Builder
    public Project(Long project_id, String title, String description, List<Template> templates, HashMap<String, Object> variables, Long user_id) {
        this.project_id = project_id;
        this.title = title;
        this.description = description;
        this.templates = templates;
        this.variables = variables;
        this.user_id = user_id;
        this.lastModifiedDate = setLastModifiedDate();
    }
}
