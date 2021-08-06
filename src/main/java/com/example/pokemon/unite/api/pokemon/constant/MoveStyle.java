package com.example.pokemon.unite.api.pokemon.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MoveStyle implements StyleTypeCode {
    AREA("Area"),
    BUFF("Buff"),
    DEBUFF("Debuff"),
    DASH("Dash"),
    HINDRANCE("Hindrance"),
    MELEE("Melee"),
    RANGED("Ranged"),
    RECOVERY("Recovery"),
    SURE_HIT("Sure Hit"),
    ;
    private String name;

    MoveStyle(String name){
        this.name = name;
    }

    public static MoveStyle getMoveStyleByName(String name){
        return Arrays.stream(MoveStyle.values())
                .filter(moveStyle -> moveStyle.getName().equals(name))
                .findAny()
                .orElse(null);
    }
}

