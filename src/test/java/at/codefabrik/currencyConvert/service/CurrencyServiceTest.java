package at.codefabrik.currencyConvert.service;

import at.codefabrik.currencyConvert.exception.CurrencyConvertException;
import at.codefabrik.currencyConvert.model.CurrencyEntity;
import at.codefabrik.currencyConvert.model.CurrencyWrapper;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


class CurrencyServiceTest {
    private CurrencyService currencyService;
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
        currencyService = new CurrencyService(restTemplate);
    }

    @Test
    void getAllCurrency() {
        CurrencyWrapper mockWrapper = new CurrencyWrapper();
        mockWrapper.setUpdateDate("2024-12-03");

        CurrencyEntity usdEntity = new CurrencyEntity(new BigDecimal("1.0"), new BigDecimal("0.95"), "USD", "+0.01");
        mockWrapper.setCurrencies("USD", usdEntity);

        ResponseEntity<CurrencyWrapper> mockResponse = ResponseEntity.ok(mockWrapper);

        when(restTemplate.exchange(
                eq(CurrencyService.webUrl),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(CurrencyWrapper.class))).thenReturn(mockResponse);

        CurrencyWrapper result = currencyService.getAllCurrency();

        assertNotNull(result);
        assertEquals("2024-12-03",result.getUpdateDate());
        assertEquals(new BigDecimal("1.0"),result.getCurrencies().get("USD").getBuying());
    }

    @Test
    void getUpdateDate() {
        CurrencyWrapper mockWrapper = new CurrencyWrapper();
        mockWrapper.setUpdateDate("2024-12-03");

        when(restTemplate.exchange(
                eq(CurrencyService.webUrl),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(CurrencyWrapper.class))).thenReturn(ResponseEntity.ok(mockWrapper));

        String updateDate = currencyService.getUpdateDate();

        assertEquals("2024-12-03", updateDate);
    }

    @Test
    void getCurrencyConvert() {
        CurrencyEntity usdEntity = new CurrencyEntity(new BigDecimal("1.0"), new BigDecimal("0.88"), "USD", "+0.01");
        CurrencyEntity eurEntity = new CurrencyEntity(new BigDecimal("0.98"), new BigDecimal("0.87"), "EUR", "-0.02");

        CurrencyWrapper mockWrapper = new CurrencyWrapper();
        mockWrapper.setCurrencies("USD", usdEntity);
        mockWrapper.setCurrencies("EUR", eurEntity);

        when(restTemplate.exchange(
                eq(CurrencyService.webUrl),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(CurrencyWrapper.class)
        )).thenReturn(ResponseEntity.ok(mockWrapper));

        BigDecimal result = currencyService.getCurrencyConvert("USD", "EUR", new BigDecimal("100"));

        assertNotNull(result);
        assertEquals(new BigDecimal("114.9"), result); // Erwartetes Ergebnis
    }


        @Test
    void getCurrencyConvert_Currency(){
        CurrencyWrapper mockWrapper = new CurrencyWrapper();

        when(restTemplate.exchange(
                eq(CurrencyService.webUrl),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(CurrencyWrapper.class))).thenReturn(ResponseEntity.ok(mockWrapper));
        Assertions.assertThrows(CurrencyConvertException.class, ()->{
            currencyService.getCurrencyConvert("INVALID", "EUR", new BigDecimal("100"));
        });

    }
}