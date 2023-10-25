package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Dto.ProjectRequestDto;

import java.util.List;

public interface ProjectService {

    public void savePost(ProjectRequestDto boardDto);

    public List<ProjectRequestDto> getProjectList(Integer pageNum);

    public ProjectRequestDto getPost(Long id);

    public void deletePost(Long id);

    public List<ProjectRequestDto> searchPosts(String keyword);

    public void update(Long id, ProjectRequestDto dto);

}
