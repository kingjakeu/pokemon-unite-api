package com.example.pokemon.unite.api.item.repository;

import com.example.pokemon.unite.api.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
