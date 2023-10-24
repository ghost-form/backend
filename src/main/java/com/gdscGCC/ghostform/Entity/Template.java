package com.gdscGCC.ghostform.Entity;

import java.util.List;

/** 템플릿 전체 내용 중 {변수} 중괄호 내부에 있는 값을 프롬프트 변수로 치환할 예정 */
public class Template {
    /** 템플릿 이름 */
    private String name;
    /** 템플릿 전체 내용 */
    private String content;
    /** 프롬프트 변수들 */
    private List<String> variables;
}
