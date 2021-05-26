package sfg.brewery.model.events;

import io.netty.buffer.ByteBufProcessor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sfg.brewery.model.BeerOrderDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllocationResult {
    private BeerOrderDto beerOrderDto;

    private Boolean allocationError = false ;

    private Boolean pendingInventory = false;

}
