package com.example.pokemon.unite.api.pokemon.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DamageType implements StyleTypeCode{
    SPECIAL("Special"),
    PHYSICAL("Physical"),
    ;
    private String name;

    DamageType(String name){
        this.name = name;
    }

    public static DamageType getDamageTypeByName(String name){
        return Arrays.stream(DamageType.values())
                .filter(damageType -> damageType.getName().equals(name))
                .findAny()
                .orElse(null);
    }
}
