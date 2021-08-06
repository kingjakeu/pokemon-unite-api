package com.example.pokemon.unite.api.crawl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/crawl")
@RequiredArgsConstructor
public class DataCrawlController {
    private final PokemonDataCrawlService pokemonDataCrawlService;

    @PostMapping(value = "/pokemon")
    public void crawlAllPokemonData() throws IOException {
        this.pokemonDataCrawlService.getAllPokemonInfo();
    }
}
