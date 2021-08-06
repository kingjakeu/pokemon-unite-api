package com.example.pokemon.unite.api.pokemon.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AttackType implements StyleTypeCode{
    RANGED("Ranged"),
    MELEE("Melee"),
    ;
    private String name;

    AttackType(String name){
        this.name = name;
    }

    public static AttackType getAttackTypeByName(String name){
        return Arrays.stream(AttackType.values())
                .filter(attackType -> attackType.getName().equals(name))
                .findAny()
                .orElse(null);
    }
}
