package com.gdscGCC.ghostform.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /** 사용자 번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ind_id;

    /** 사용자 이름 */
    private String name;
    /** 사용자 ID */
    private String id;
    /** 사용자 비밀번호 */
    private String password;
    /** 사용자 Email */
    private String email;
}
