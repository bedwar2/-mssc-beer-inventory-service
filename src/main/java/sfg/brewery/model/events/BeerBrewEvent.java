package sfg.brewery.model.events;

import lombok.NoArgsConstructor;
import sfg.brewery.model.BeerDto;

@NoArgsConstructor
public class BeerBrewEvent extends BeerEvent {

    private static final long serialVersionUID = 7026110324849173085L;



    public BeerBrewEvent(BeerDto beerDto) {
        super(beerDto);
    }

}
