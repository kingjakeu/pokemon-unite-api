package com.example.pokemon.unite.api.crawl;

import com.example.pokemon.unite.api.pokemon.constant.*;
import com.example.pokemon.unite.api.pokemon.domain.Pokemon;
import com.example.pokemon.unite.api.pokemon.domain.PokemonSkill;
import com.example.pokemon.unite.api.pokemon.domain.PokemonStat;
import com.example.pokemon.unite.api.pokemon.domain.id.PokemonStatId;
import com.example.pokemon.unite.api.pokemon.repository.PokemonRepository;
import com.example.pokemon.unite.api.pokemon.repository.PokemonSkillRepository;
import com.example.pokemon.unite.api.pokemon.repository.PokemonStatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataCrawlService {

    private final PokemonRepository pokemonRepository;
    private final PokemonSkillRepository pokemonSkillRepository;
    private final PokemonStatRepository pokemonStatRepository;

    public void getAllPokemonInfo() throws IOException {
        this.getAllPokemon();
        this.getAllPokemonDetails();
    }

    private void getAllPokemon() throws IOException {
        Document document =  Jsoup.connect("https://www.serebii.net/pokemonunite/pokemon.shtml").get();
        Elements tables = document.getElementsByClass("dextable");
        Element table = tables.get(0);
        Elements rows = table.getElementsByTag("tbody").get(0).getElementsByTag("tr");

        List<Pokemon> pokemonList = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            Elements cells = rows.get(i).getElementsByTag("td");
            pokemonList.add(Pokemon.builder()
                    .name(cells.get(1).text())
                    .attackType(AttackType.getAttackTypeByName(cells.get(3).text()))
                    .roleStyle(RoleStyle.getRoleStyleByName(cells.get(4).text()))
                    .damageType(DamageType.getDamageTypeByName(cells.get(5).text()))
                    .build());
        }
        pokemonRepository.saveAll(pokemonList);
    }

    private void getAllPokemonDetails() throws IOException {
        List<Pokemon> pokemonList = this.pokemonRepository.findAll();
        for(Pokemon pokemon : pokemonList){
            this.getPokemonDetails(pokemon);
        }
    }

    private void getPokemonDetails(Pokemon pokemon) throws IOException {
        log.info("Details for:: "+ pokemon.getName());
        Document document = Jsoup.connect("https://www.serebii.net/pokemonunite/pokemon/"
                + pokemon.getName().replace(" ", "").toLowerCase()
                +".shtml").get();
        this.getPokemonSkills(document, pokemon);
        this.getPokemonStats(document, pokemon);
    }

    private void getPokemonStats(Document document, Pokemon pokemon){
        Elements tabTables = document.getElementsByClass("tab");
        int t = 0;
        for(Element tabTable : tabTables){
            Elements titleElements = tabTable.getElementsByTag("tbody")
                    .get(0)
                    .getElementsByTag("tr")
                    .get(0)
                    .getElementsByTag("td")
                    .get(0)
                    .getElementsByTag("h2");
            if(titleElements.size() > 0){
                String title = titleElements.get(0).text();
                if(title.equals("Stats")) break;
            }
            t += 1;
        }
        Element table = tabTables.get(t);
        Elements rows = table.getElementsByTag("tbody").get(0).getElementsByTag("tr");

        List<PokemonStat> pokemonStatList = new ArrayList<>();
        Integer speed = Integer.valueOf(rows.get(2).getElementsByTag("td").get(6).text());
        for (int i = 2; i < 17; i++) {
            Elements cells = rows.get(i).getElementsByTag("td");
            pokemonStatList.add(PokemonStat.builder()
                    .pokemonStatId(PokemonStatId.builder()
                            .pokemonId(pokemon.getId())
                            .level(Integer.valueOf(cells.get(0).text().replace("Level ", "")))
                            .build())
                    .pokemon(pokemon)
                    .healthPoint(Integer.valueOf(cells.get(1).text()))
                    .attackDamage(Integer.valueOf(cells.get(2).text()))
                    .defense(Integer.valueOf(cells.get(3).text()))
                    .specialAttackDamage(Integer.valueOf(cells.get(4).text().split("-")[0]))
                    .specialDefense(Integer.valueOf(cells.get(5).text()))
                    .speed(speed)
                    .build()
            );
        }
        this.pokemonStatRepository.saveAll(pokemonStatList);
    }

    private void getPokemonSkills(Document document, Pokemon pokemon) throws IOException {
        Elements tabTables = document.getElementsByClass("tab");
        int t = 0;
        for(Element tabTable : tabTables){
            Elements titleElements = tabTable.getElementsByTag("tbody")
                    .get(0)
                    .getElementsByTag("tr")
                    .get(0)
                    .getElementsByTag("td")
                    .get(0)
                    .getElementsByTag("h2");
            if(titleElements.size() > 0){
                String title = titleElements.get(0).text();
                if(title.equals("Ability")) break;
            }
            t += 1;
        }
        Element table = tabTables.get(t);
        Elements rows = table.getElementsByTag("tbody").get(0).getElementsByTag("tr");

        List<PokemonSkill> pokemonSkillList = new ArrayList<>();
        // passive
        pokemonSkillList.add(PokemonSkill.builder()
                .name(rows.get(1).getElementsByClass("fooinfo").get(0).text())
                .description(rows.get(2).getElementsByClass("fooinfo").get(0).text())
                .skillSlotType(SkillSlotType.PASSIVE)
                .pokemon(pokemon)
                .build());
        // basic
        pokemonSkillList.add(createPokemonSkill(rows, 6, SkillSlotType.BASIC, pokemon));

        // SKills
        for (int i = 0; i < 6; i+=2) {
            pokemonSkillList.add(createPokemonSkill(rows, 9 + i, SkillSlotType.SP_1, pokemon));
        }
        for (int i = 0; i < 6; i+=2) {
            pokemonSkillList.add(createPokemonSkill(rows, 16 + i, SkillSlotType.SP_2, pokemon));
        }
        pokemonSkillList.add(createPokemonSkill(rows, 23, SkillSlotType.ULTIMATE, pokemon));
        this.pokemonSkillRepository.saveAll(pokemonSkillList);
    }

    private PokemonSkill createPokemonSkill(Elements rows, int idx, SkillSlotType skillSlotType, Pokemon pokemon){
        Elements firstCells = rows.get(idx).getElementsByTag("td");
        String description = rows.get(idx+1).getElementsByTag("td").get(0).text();
        String[] descSplit = description.split("Upgrade at Level ");

        String maxDamage = firstCells.get(4).text();
        maxDamage = maxDamage.equals("Varies") ? "" : maxDamage;
        maxDamage = maxDamage.contains("-") ? maxDamage.split("-")[1].replace(" ", "") : maxDamage;
        maxDamage = maxDamage.contains("(Single Hit)") ? maxDamage.split(" ")[3] : maxDamage;

        return PokemonSkill.builder()
                .name(firstCells.get(1).text())
                .description(description)
                .moveStyle(MoveStyle.getMoveStyleByName(firstCells.get(2).text()))
                .damageType(DamageType.getDamageTypeByName(firstCells.get(3).text()))
                .skillSlotType(skillSlotType)
                .upgradeLevel(descSplit.length > 1 ? Double.valueOf(descSplit[1].split(":")[0]).intValue() : null)
                .maxLevelDamage(skillSlotType.equals(SkillSlotType.BASIC)
                        ? null
                        : maxDamage.isBlank() ? null : Integer.parseInt(maxDamage))
                .coolDown(firstCells.get(5).text().length() > 0 ? Double.parseDouble(firstCells.get(5).text()) : null)
                .pokemon(pokemon)
                .build();
    }
}
