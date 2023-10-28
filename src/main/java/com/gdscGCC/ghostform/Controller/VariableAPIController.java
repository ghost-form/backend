package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Dto.ProjectRequestDto;
import com.gdscGCC.ghostform.Dto.ProjectResponseDto;
import com.gdscGCC.ghostform.Dto.VariableRequestDto;
import com.gdscGCC.ghostform.Dto.VariableResponseDto;
import com.gdscGCC.ghostform.Service.ProjectService;
import com.gdscGCC.ghostform.Service.VariableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class VariableAPIController {
    private final VariableService variableService;

    /** Project의 모든 변수 조회
     * RequestParameter로 project_id를 받아옴*/
    @GetMapping("/variables")
        public ResponseEntity<HashMap<String, Object>> getAllVariables(@RequestParam Long project_id){
        return ResponseEntity.status(HttpStatus.OK).body(variableService.getAllVariables(project_id));
    }

    /** Project의 특정 변수 하나 조회
     * RequestParameter로 project_id 및 variable_key를 받아옴*/
    @GetMapping("/variable")
    public ResponseEntity<Object> getOneVariables(@RequestParam Long project_id, @RequestParam String variable_key){
        return ResponseEntity.status(HttpStatus.OK).body(variableService.getOneVariable(project_id, variable_key));
    }

    /** 한 개의 변수 생성
     * RequestParameter로 project_id를 받아옴
     * RequestBody로 생성할 JSON을 받아옴 */
    @PostMapping("/variables")
    public HashMap<String, Object> createOneVariable(@RequestParam Long project_id, @RequestBody HashMap<String, Object> map){
        return variableService.create(project_id, map);
    }

    /** 한 개의 변수 삭제
     * RequestParameter로 project_id를 받아옴
     * RequestParameter로 삭제할 변수 key를 받아옴 */
    @DeleteMapping("/variables")
    public void deleteOneVariable(@RequestParam Long project_id, @RequestParam String deleteKey){
        variableService.deleteOne(project_id, deleteKey);
    }

    /** 한 개의 변수 수정
     * RequestParameter로 project_id를 받아옴
     * RequestBody로 수정할 변수 key와 value를 받아옴*/
    @PutMapping("/variables")
    public void updateOneVariable(@RequestParam Long project_id, @RequestBody VariableRequestDto variableRequestDto){
        variableService.updateOneVariable(project_id, variableRequestDto);
    }
    
}
