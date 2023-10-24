package com.gdscGCC.ghostform.Entity;

import java.util.List;

/** 프로젝트 개체 */
public class Project {
    /** 프로젝트 제목 */
    private String title;
    /** 프로젝트 설명 */
    private String description;
    /** 하위 템플릿 */
    private List<Template> templates; // 이렇게 추가해도 되는가? 아니면 Entity set간 관계추가를 해야 하는가? java DB연동 참고
    /** 사용자 */
    private String user;
    /** 최종 수정일 */
    private String lastModifiedDate;
}
