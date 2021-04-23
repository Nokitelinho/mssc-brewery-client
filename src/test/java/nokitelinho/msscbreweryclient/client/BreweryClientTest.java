package nokitelinho.msscbreweryclient.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nokitelinho.msscbreweryclient.web.model.BeerDto;
import nokitelinho.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BreweryClientTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    BreweryClient client;

    @Test
    void testGetBeerById() {
        BeerDto beerDto = client.getBeerById(UUID.randomUUID());
        assertNotNull(beerDto);
    }

    @Test
    @DisplayName("Should save beer")
    void testSaveNewBeer() {
        BeerDto beerDto = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("New Beer")
                .beerStyle("New beer style")
                .build();
        URI uri = client.saveNewBeer(beerDto);
        assertNotNull(uri);
    }

    @Test
    @DisplayName("Should update beer by given Id")
    void testUpdateBeer() {
        UUID uuid = UUID.randomUUID();
        BeerDto beerDto = BeerDto.builder()
                .beerName("New Beer2")
                .beerStyle("New beer style2")
                .build();
        client.updateBeer(uuid, beerDto);
    }

    @Test
    @DisplayName("Should delete beer by Id")
    void testDeleteBeer() {
        UUID uuid = UUID.randomUUID();
        client.deleteBeer(uuid);
    }

    @Test
    @DisplayName("Should return customer by Id")
    void testGetCustomerById() {
        UUID uuid = UUID.randomUUID();
        CustomerDto dto = client.getCustomerById(uuid);
        log.info("IN testGetCustomerById. {}", dto);
        assertNotNull(dto);
    }

    @Test
    @DisplayName("Should save new customer")
    void testSaveNewCustomer() {
        CustomerDto dto = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Igor")
                .build();
        URI uri = client.saveNewCustomer(dto);
        assertNotNull(uri);
    }

    @Test
    @DisplayName("Should update customer by Id")
    void updateCustomer() {
        UUID uuid = UUID.randomUUID();
        CustomerDto dto = CustomerDto.builder()
                .name("Alex")
                .build();
        client.updateCustomer(uuid, dto);
    }

    @Test
    @DisplayName("Should delete customer by Id")
    void deleteCustomer() {
        UUID uuid = UUID.randomUUID();
        client.deleteCustomer(uuid);
    }
}