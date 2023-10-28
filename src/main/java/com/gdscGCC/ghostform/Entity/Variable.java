package com.gdscGCC.ghostform.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Variable {
    @Id
    Long id;
    String name;
    String type;
}
