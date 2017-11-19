package com.pragma.brewery.web.controller;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.pragma.brewery.configuration.TestContextConfiguration;
import com.pragma.brewery.configuration.WebConfiguration;
import com.pragma.brewery.model.TemperatureData;
import com.pragma.brewery.service.ReadTemperatureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {WebConfiguration.class, TestContextConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
public class TemperatureControllerTest {

    private String port;

    @Autowired
    Environment environment;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ReadTemperatureService readTemperatureService;


    private String id = "Groot!!";
    private Double temp = 123.45;

    private TemperatureData grootTemparatureData;


    @Before
    public void setUp() {
        port = environment.getProperty("local.server.port");
        grootTemparatureData = new TemperatureData(id, temp);
    }

    /**
     * It should delegate the call to {@link com.pragma.brewery.service.ReadTemperatureService#readCurrentTemperature(String)}
     * and return the Json data of {@link com.pragma.brewery.model.TemperatureData}
     */
    @Test
    public void testGetCurrentTemperature() {
        when(readTemperatureService.readCurrentTemperature(id)).thenReturn(grootTemparatureData);
        ResponseEntity<String> jsonResponse = testRestTemplate.getForEntity("http://localhost:" + port + "/services/temperature/" + id, String.class);

        assertThat(jsonResponse).isNotNull();
        assertThat(jsonResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(jsonResponse.getHeaders()).isNotNull().isNotEmpty().containsKey("Content-Type");
        assertThat(jsonResponse.getHeaders().get("Content-Type")).hasSize(1).contains("application/json;charset=UTF-8");
        assertThat(jsonResponse.getBody()).isNotNull().isNotEqualTo("");
        ReadContext ctx = JsonPath.parse(jsonResponse.getBody());
        assertThat((String) ctx.read("$.id")).isEqualTo(id);
        assertThat((Double) ctx.read("$.currentTemperature")).isEqualTo(temp);
    }

}