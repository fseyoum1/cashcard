package example.cashcard;

import com.fasterxml.jackson.core.JsonParser;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CashCardApplicationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnCashCardWhenSave() {

        ResponseEntity<String> responseEntity  = restTemplate.getForEntity("/cashcards/99", String.class);

        DocumentContext documentContext = JsonPath.parse(responseEntity.getBody());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldReturnAllCardTypes() {

        ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/all", String.class);
        assertThat(JsonPath.parse(response.getBody())).isEqualTo(HttpStatus.OK);
    }
}
