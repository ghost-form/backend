package com.gdscGCC.ghostform.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Ask {
    private String key;
    private String value;

    public Ask(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
