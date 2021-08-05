package com.example.pokemon.unite.api.pokemon.domain;

import com.example.pokemon.unite.api.pokemon.domain.id.SkillStatusEffectId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "SKILL_STATUS_EFFECT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SkillStatusEffect {

    @EmbeddedId
    private SkillStatusEffectId skillStatusEffectId;

    @MapsId("skillId")
    @ManyToOne
    @JoinColumn(name = "POKEMON_SKILL_ID")
    private PokemonSkill pokemonSkill;
}
