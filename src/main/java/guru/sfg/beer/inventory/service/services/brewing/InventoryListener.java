package guru.sfg.beer.inventory.service.services.brewing;

import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import sfg.brewery.model.BeerDto;
import sfg.brewery.model.events.NewInventoryEvent;

import javax.print.attribute.standard.Destination;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @Transactional
    @JmsListener(destination = JmsConfig.BEER_INVENTORY_QUEUE)
    public void listen(@Payload NewInventoryEvent newInventoryEvent) {
        BeerDto beerDto = newInventoryEvent.getBeerDto();

        BeerInventory beerInventory = BeerInventory.builder()
                .beerId(beerDto.getId())
                .upc(beerDto.getUpc())
                .quantityOnHand(beerDto.getQuantityOnHand()).build();

        beerInventoryRepository.save(beerInventory);


    }
}
