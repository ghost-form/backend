package com.gdscGCC.ghostform.Repository;

import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Entity.StaredProject;
import com.gdscGCC.ghostform.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaredProjectRepository extends JpaRepository<StaredProject, Long> {
    StaredProject findByProjectAndUser(Project board, User user);
    Page<StaredProject> findAllByStaredIsAndUserIs(Pageable pageable, boolean stared, User user);
}
