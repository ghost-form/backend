package com.gdscGCC.ghostform.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;


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

    /** 템플릿 전체 내용 */
    private String content;

    /** 템플릿 변수들
     * key로 Long id, String name, String type이 존재 */
    private String variables;

    /** 사용자 id */
    private Long user_id;

    /** 최종 수정일 */
    private LocalDateTime lastModifiedDate;

    /** 프로젝트 STAR */
    private Long star;

    /** 실행 테이블 */
    @OneToMany
    @JoinColumn(name = "run_id")
    private List<Run> run = new ArrayList<>();

    public LocalDateTime setLastModifiedDate() {
        return this.lastModifiedDate = LocalDateTime.now();
    }

    public void updateProject(String title, String description, String content, String variables, Long user_id, LocalDateTime lastModifiedDate, Long star) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.variables = variables;
        this.user_id = user_id;
        this.lastModifiedDate = setLastModifiedDate();
        this.star = star;
    }

    @Builder
    public Project(String title, String description, LocalDateTime lastModifiedDate, String content, String variables, Long user_id, Long star) {
        this.title = title;
        this.description = description;
        this.lastModifiedDate = setLastModifiedDate();
        this.content = content;
        this.variables = variables;
        this.user_id = user_id;
        this.star = star;
    }

    public void addRun(Run run) {
        this.run.add(run);
    }
}
