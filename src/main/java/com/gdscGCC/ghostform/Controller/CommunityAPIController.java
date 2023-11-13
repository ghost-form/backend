package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Dto.Project.ProjectResponseDto;
import com.gdscGCC.ghostform.Entity.Project;

import com.gdscGCC.ghostform.Service.CommunityService;
import com.gdscGCC.ghostform.Service.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;


import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/community")
@Tag(name = "Community", description = "Community API")
public class CommunityAPIController {
    private final ProjectService projectService;
    private final CommunityService communityService;

    /** 모든 프로젝트 조회 - 프로젝트 리스팅 */
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> projectListGet(){
        return projectService.findAll();
    }


    /** 프로젝트 Star 한 번 누르면 star++, 한 번 더 누르면 star-- */
    /** 프로젝트 Star 기입 시 StarredProject Entity에 사용자와 프로젝트간 관계 생성 */
    @PutMapping("/{project_id}/star")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> setStar(@PathVariable Long project_id, @PathVariable Long user_id) { // 로그인 기능 구현하면 user_id로 사용자 정보 받오게 하지 않고 로그인 상태로 받아오게끔
        return ResponseEntity.status(HttpStatus.OK).body(communityService.setStar(project_id, user_id));
    }

    /** 내가 Starred 한 프로젝트만 조회 */
    @GetMapping("/staredProjects")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProjectResponseDto>> staredProjectListGet(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(communityService.findByStared(pageable));
    }

    /** Star 수 10개 넘은 프로젝트 조회 */

    /** 사용자 정보를 바꿔, 프로젝트 Fork 해오기 */


}
