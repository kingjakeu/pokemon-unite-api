package com.example.pokemon.unite.api.pokemon.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RoleStyle implements StyleTypeCode{
    ATTACKER("Attacker"),
    ALL_ROUNDER("All-Rounder"),
    SUPPORTER("Supporter"),
    DEFENDER("Defender"),
    SPEEDSTER("Speedster")
    ;

    private String name;

    RoleStyle(String name){
        this.name = name;
    }

    public static RoleStyle getRoleStyleByName(String name){
        return Arrays.stream(RoleStyle.values())
                .filter(roleStyle -> roleStyle.getName().equals(name))
                .findAny()
                .orElse(null);
    }
}
