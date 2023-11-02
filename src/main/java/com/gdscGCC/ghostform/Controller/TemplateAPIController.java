package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Dto.Template.TemplateRequestDto;
import com.gdscGCC.ghostform.Dto.Variable.VariableRequestDto;
import com.gdscGCC.ghostform.Entity.Template;
import com.gdscGCC.ghostform.Service.TemplateService;
import com.gdscGCC.ghostform.Service.VariableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/templates")
public class TemplateAPIController {
    private final TemplateService templateService;
    private final VariableService variableService;

    /** 한 개의 템플릿 조회 */
    @GetMapping("/{id}")
    public void templateGet(@PathVariable Long id){
        templateService.findById(id);
    }

    /** 모든 템플릿 조회 */
    @GetMapping("")
    public List<Template> projectListGet(){
        return templateService.findAll();
    }

    /** 한 개의 템플릿 생성 */
    @PostMapping("")
    public Long templateSave(@RequestBody TemplateRequestDto requestDto){
        return templateService.save(requestDto);
    }


    /** 한 개의 템플릿 삭제 */
    @DeleteMapping("/{id}")
    public void templateDelete(@PathVariable Long id){
        templateService.delete(id);
    }

    /** 한 개의 템플릿 수정 */
    @PutMapping("/{id}")
    public Long templateUpdate(@PathVariable Long id, @RequestBody TemplateRequestDto requestDto){
        System.out.println("id는!!! : " + id);
        return templateService.update(id, requestDto);
    }

    /** Template의 모든 변수 조회
     * RequestParameter로 template_id를 받아옴*/
    @GetMapping("/{template_id}/variables")
    public ResponseEntity<HashMap<String, Object>> getAllVariables(@PathVariable Long template_id){
        return ResponseEntity.status(HttpStatus.OK).body(variableService.getAllVariables(template_id));
    }

    /** Template의 특정 변수 하나 조회
     * RequestParameter로 template_id 및 variable_key를 받아옴*/
    @GetMapping("/{template_id}/variable")
    public ResponseEntity<Object> getOneVariables(@PathVariable Long template_id, @RequestParam String key){
        return ResponseEntity.status(HttpStatus.OK).body(variableService.getOneVariable(template_id, key));
    }

    /** Template에 한 개의 변수 생성
     * RequestParameter로 template_id를 받아옴
     * RequestBody로 생성할 JSON을 받아옴 */
    @PostMapping("/{template_id}/variables")
    public HashMap<String, Object> createOneVariable(@PathVariable Long template_id, @RequestBody HashMap<String, Object> map){
        return variableService.create(template_id, map);
    }

    /** Template에 한 개의 변수 삭제
     * RequestParameter로 template_id를 받아옴
     * RequestParameter로 삭제할 변수 key를 받아옴 */
    @DeleteMapping("/{template_id}/variables")
    public void deleteOneVariable(@PathVariable Long template_id, @RequestParam String deleteKey){
        variableService.deleteOne(template_id, deleteKey);
    }

    /** 한 개의 변수 수정
     * RequestParameter로 project_id를 받아옴
     * RequestBody로 수정할 변수 key와 value를 받아옴*/
    @PutMapping("/{template_id}/variables")
    public void updateOneVariable(@PathVariable Long template_id, @RequestBody VariableRequestDto variableRequestDto){
        variableService.updateOneVariable(template_id, variableRequestDto);
    }
}
