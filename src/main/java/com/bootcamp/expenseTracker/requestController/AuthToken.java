package com.bootcamp.expenseTracker.requestController;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthToken {
    private String id;
    private String key;
    private boolean valid;

    public AuthToken(String id, String key) {
        this.id = id;
        this.key = key;
    }
}
