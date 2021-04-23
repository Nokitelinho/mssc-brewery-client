package nokitelinho.msscbreweryclient.client;

import lombok.extern.slf4j.Slf4j;
import nokitelinho.msscbreweryclient.web.model.BeerDto;
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

    @Autowired
    BreweryClient client;

    @Test
    void testGetBeerById() {
        BeerDto beerDto = client.getBeerById(UUID.randomUUID());
        log.info("IN testGetBeerById. {}", beerDto);
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
        assertNotNull(beerDto);
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
    void deleteBeer() {
        UUID uuid = UUID.randomUUID();
        client.deleteBeer(uuid);
    }
}