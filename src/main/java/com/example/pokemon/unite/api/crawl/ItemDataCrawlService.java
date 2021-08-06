package com.example.pokemon.unite.api.crawl;

import com.example.pokemon.unite.api.item.constant.ItemType;
import com.example.pokemon.unite.api.item.domain.Item;
import com.example.pokemon.unite.api.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemDataCrawlService {

    private final ItemRepository itemRepository;

    public void crawlAllItems() throws IOException {
        this.crawHeldItem();
        this.crawlBattleItem();
    }

    private void crawHeldItem() throws IOException {
        Document document = Jsoup.connect("https://www.serebii.net/pokemonunite/holditems.shtml").get();
        Element tableBody = document
                .getElementsByClass("dextable")
                .get(1)
                .getElementsByTag("tbody")
                .get(0);

        List<Item> itemList = new ArrayList<>();
        Elements rows = tableBody.getElementsByTag("tr");
        for (int i = 1; i < rows.size(); i++) {
            Elements cells = rows.get(i).getElementsByTag("td");
            itemList.add(Item.builder()
                    .name(cells.get(1).text())
                    .description(cells.get(2).text())
                    .statBoost(cells.get(3).text())
                    .itemType(ItemType.HELD)
                    .build());
        }
        this.itemRepository.saveAll(itemList);
    }

    private void crawlBattleItem() throws IOException {
        Document document = Jsoup.connect("https://www.serebii.net/pokemonunite/battleitems.shtml").get();
        Element tableBody = document
                .getElementsByClass("dextable")
                .get(0)
                .getElementsByTag("tbody")
                .get(0);

        List<Item> itemList = new ArrayList<>();
        Elements rows = tableBody.getElementsByTag("tr");
        for (int i = 1; i < rows.size(); i++) {
            Elements cells = rows.get(i).getElementsByTag("td");
            itemList.add(Item.builder()
                    .name(cells.get(1).text())
                    .description(cells.get(2).text())
                    .coolDown(Double.valueOf(cells.get(3).text().split(" Seconds")[0]))
                    .itemType(ItemType.BATTLE)
                    .build());
        }
        this.itemRepository.saveAll(itemList);
    }
}
