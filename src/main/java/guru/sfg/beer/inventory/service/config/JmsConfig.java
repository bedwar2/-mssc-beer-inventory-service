package guru.sfg.beer.inventory.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {

    public static final String BEER_INVENTORY_QUEUE = "beer-inventory-queue";
    public static final String BEER_SERVICE_QUEUE = "beer-service-queue";
    public static final String  ALLOCATE_ORDER = "allocate-order";
    public static final String  ALLOCATE_ORDER_RESULT = "allocate-order-result";
    public static final String  CANCEL_ORDER = "cancel-order";

    @Bean
    public MappingJackson2MessageConverter messageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);

        return converter;
    }
}
