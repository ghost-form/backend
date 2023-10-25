package com.gdscGCC.ghostform.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/** 템플릿 전체 내용 중 {변수} 중괄호 내부에 있는 값을 프롬프트 변수로 치환할 예정 */
public class Template {
    @Id
    @GeneratedValue
    /** 템플릿 번호 */
    private Long id;
    /** 템플릿 이름 */
    private String name;
    /** 템플릿 전체 내용 */
    private String content;
//    /** 프롬프트 변수들 */
//    private List<String> variables;

    @Builder
    public Template(Long id, String name, String content){
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public void update(Long id, String name, String content){
        this.id = id;
        this.name = name;
        this.content = content;
    }
}
