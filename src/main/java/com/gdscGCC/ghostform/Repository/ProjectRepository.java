package com.gdscGCC.ghostform.Repository;

import com.gdscGCC.ghostform.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByTitleContaining(String keyword);
}
