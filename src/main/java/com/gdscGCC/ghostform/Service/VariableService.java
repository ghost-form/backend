package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Dto.Variable.VariableRequestDto;
import com.gdscGCC.ghostform.Entity.Template;
import com.gdscGCC.ghostform.Entity.Template;
import com.gdscGCC.ghostform.Repository.ProjectRepository;
import com.gdscGCC.ghostform.Repository.TemplateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VariableService {
    private final TemplateRepository templateRepository;

    /** 모든 변수 조회 */
    @Transactional
    public HashMap<String, Object> getAllVariables(Long template_id){
        
        Template template = templateRepository.findById(template_id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id=" + template_id));
        System.out.println(template.getVariables());
        HashMap<String, Object> variable_list = template.getVariables();
        return variable_list;

    }

    /** 변수 하나 조회 */
    @Transactional
    public Object getOneVariable(Long template_id, String key){
        Template template = templateRepository.findById(template_id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id=" + template_id));
        HashMap<String, Object> variable_list = template.getVariables();
        Object value = variable_list.get(key);
        if( value != null){
            return value;
        }
        else{
            System.out.println("해당 변수가 존재하지 않습니다.");
            return null;
        }
    }

    /** 변수 하나 생성 */
    @Transactional
    public HashMap<String, Object> create(Long template_id, HashMap<String, Object> map){
        Template template = templateRepository.findById(template_id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id=" + template_id));
        HashMap<String, Object> variables = template.getVariables();

        Set<String> keys = map.keySet();
        for (String key : keys){
            variables.put(key, map.get(key));
        }


        // 추가 후 업데이트
        template.updateTemplate(template.getTemplate_id(), template.getName(), template.getContent(), template.getProject(), template.getVariables());

        return map;
    }

    /** 변수 하나 삭제 */
    @Transactional
    public void deleteOne(Long template_id, String deleteKey){
        Template template = templateRepository.findById(template_id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id=" + template_id));

        HashMap<String, Object> variables = template.getVariables();


        if(variables.containsKey(deleteKey)){
            variables.remove(deleteKey);
        }
        else{
            System.out.println("해당 key가 존재하지 않습니다.");
        }

        // 삭제 후 업데이트
        template.updateTemplate(template.getTemplate_id(), template.getName(), template.getContent(), template.getProject(), template.getVariables());

    }

    /** 변수 하나 수정 */
    @Transactional
    public void updateOneVariable(Long template_id, VariableRequestDto variableRequestDto){
        Template template = templateRepository.findById(template_id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id=" + template_id));

        String key = variableRequestDto.getKey();
        Object value = variableRequestDto.getValue();

        // 기존 project의 변수들
        HashMap<String, Object> variables = template.getVariables();


        if(variables.containsKey(key)){
            variables.put(key, value);
        }
        else{
            System.out.println("해당 key가 존재하지 않습니다.");
        }

        // 수정 후 업데이트
        template.updateTemplate(template.getTemplate_id(), template.getName(), template.getContent(), template.getProject(), template.getVariables());


    }




}
