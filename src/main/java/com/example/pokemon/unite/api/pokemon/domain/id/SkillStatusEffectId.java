package com.example.pokemon.unite.api.pokemon.domain.id;

import com.example.pokemon.unite.api.pokemon.constant.StatusEffect;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SkillStatusEffectId implements Serializable {

    @Column(name = "POKEMON_SKILL_ID")
    private Long skillId;

    @Column(name = "STATUS_EFFECT")
    @Enumerated(EnumType.STRING)
    private StatusEffect statusEffect;
}
