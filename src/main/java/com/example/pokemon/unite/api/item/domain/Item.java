package com.example.pokemon.unite.api.item.domain;

import com.example.pokemon.unite.api.item.constant.ItemType;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@Entity
@Table(name = "ITEM")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Lob
    @Column(name = "DESC")
    private String description;

    @Lob
    @Column(name = "STAT_BOOST")
    private String statBoost;

    @Column(name = "COOL_DOWN")
    private Double coolDown;

    @Column(name = "ITEM_TYPE")
    @Enumerated(EnumType.STRING)
    private ItemType itemType;
}
