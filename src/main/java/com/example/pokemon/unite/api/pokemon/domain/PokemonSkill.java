package com.example.pokemon.unite.api.pokemon.domain;

import com.example.pokemon.unite.api.pokemon.constant.DamageType;
import com.example.pokemon.unite.api.pokemon.constant.MoveStyle;
import com.example.pokemon.unite.api.pokemon.constant.SkillSlotType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@ToString
@Getter
@Builder
@Entity
@Table(name = "POKEMON_SKILL")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PokemonSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Lob
    @Column(name = "DESC")
    private String description;

    @Column(name = "DAMGAGE_TYPE")
    private DamageType damageType;

    @Column(name = "MOVE_STYLE")
    private MoveStyle moveStyle;

    @Column(name = "SKILL_SLOT")
    private SkillSlotType skillSlotType;

    @Column(name = "LEARN_LVL")
    private Integer learnedLevel;

    @Column(name = "UP_LVL")
    private Integer upgradeLevel;

    @Column(name = "MAX_LVL_DAMAGE")
    private Integer maxLevelDamage;

    @Column(name = "COOL_DOWN")
    private Double coolDown;

    @OneToMany(mappedBy = "pokemonSkill")
    private Set<SkillStatusEffect> skillStatusEffectList;

    @ManyToOne
    @JoinColumn(name = "POKEMON_ID")
    private Pokemon pokemon;

}
