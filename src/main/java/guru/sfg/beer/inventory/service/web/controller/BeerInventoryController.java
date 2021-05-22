package guru.sfg.beer.inventory.service.web.controller;

import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.beer.inventory.service.web.mappers.BeerInventoryMapper;
import guru.sfg.beer.inventory.service.web.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerInventoryController {

    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerInventoryMapper beerInventoryMapper;

    @GetMapping("api/v1/beer/{beerId}/inventory")
    List<BeerInventoryDto> listBeersById(@PathVariable UUID beerId){
        log.info("Finding Inventory for beerId:" + beerId);

        List<BeerInventory> inventoryList = beerInventoryRepository.findAllByBeerId(beerId);
        List<BeerInventoryDto> inventoryDtoList = new ArrayList<>();

        for (BeerInventory inv :  inventoryList) {
            BeerInventoryDto beerInventoryDto = beerInventoryMapper.beerInventoryToBeerInventoryDto(inv);
            inventoryDtoList.add(beerInventoryDto);
        }

        /*
        inventoryList
                .stream()
                .map(beerInventoryMapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
           */
        return inventoryDtoList;
    }
}