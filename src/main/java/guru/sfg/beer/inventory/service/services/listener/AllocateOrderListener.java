package guru.sfg.beer.inventory.service.services.listener;

import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.beer.inventory.service.services.AllocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sfg.brewery.model.BeerOrderDto;
import sfg.brewery.model.events.AllocateOrderRequest;
import sfg.brewery.model.events.AllocationResult;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllocateOrderListener {

    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER)
    public void listen(@Payload AllocateOrderRequest allocateOrderRequest) {
        BeerOrderDto beerOrderDto = allocateOrderRequest.getBeerOrderDto();
        AllocationResult allocationResult = AllocationResult.builder().beerOrderDto(beerOrderDto).build();
        try {
            Boolean allocated = allocationService.allocateOrder(beerOrderDto);
            allocationResult.setAllocationError(false);
            if (allocated) {
                allocationResult.setPendingInventory(false);
            } else {
                allocationResult.setPendingInventory(true);
            }

        } catch (Exception e) {
            log.error("Allocation failed for " + beerOrderDto.getId());
            allocationResult.setAllocationError(true);
            allocationResult.setPendingInventory(false);
        }

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESULT, allocationResult);

    }
}
