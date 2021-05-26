package guru.sfg.beer.inventory.service.services;

import sfg.brewery.model.BeerOrderDto;

public interface AllocationService {
    Boolean allocateOrder(BeerOrderDto beerOrderDto);
}
