package com.gdscGCC.ghostform.Repository;

import com.gdscGCC.ghostform.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
