package com.example.pokemon.unite.api.item.constant;

import com.example.pokemon.unite.api.pokemon.constant.StyleTypeCode;
import lombok.Getter;

@Getter
public enum ItemType implements StyleTypeCode {

    HELD("Held Item"),
    BATTLE("Battle Item")
    ;
    private String name;

    ItemType(String name){
        this.name = name;
    }
}
