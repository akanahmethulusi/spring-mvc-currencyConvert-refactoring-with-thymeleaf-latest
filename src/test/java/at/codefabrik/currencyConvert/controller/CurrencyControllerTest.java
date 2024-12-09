package at.codefabrik.currencyConvert.controller;

import at.codefabrik.currencyConvert.model.CurrencyEntity;
import at.codefabrik.currencyConvert.model.CurrencyWrapper;
import at.codefabrik.currencyConvert.service.CurrencyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyControllerTest {
    private CurrencyController currencyController;
    private CurrencyService currencyService;

    @BeforeEach
    public void setUp(){
        currencyService = Mockito.mock(CurrencyService.class);
        currencyController = new CurrencyController(currencyService);
    }

    @Test
    void getAllCurrency() {
        CurrencyEntity usdEntity = new CurrencyEntity(new BigDecimal("1.0"), new BigDecimal("0.95"),"USD","+0.01");
        CurrencyEntity eurEntity = new CurrencyEntity(new BigDecimal("0.85"), new BigDecimal("0.83"), "EUR", "-0.02");

        CurrencyWrapper mockWrapper = new CurrencyWrapper();
        mockWrapper.setUpdateDate("2024-12-03");
        mockWrapper.setCurrencies("USD", usdEntity);
        mockWrapper.setCurrencies("EUR", eurEntity);

        when(currencyService.getAllCurrency()).thenReturn(mockWrapper);

        ResponseEntity<CurrencyWrapper> response =currencyController.getAllCurrency();

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("2024-12-03", response.getBody().getUpdateDate());
        Assertions.assertEquals(new BigDecimal("1.0"), response.getBody().getCurrencies().get("USD").getBuying());
    }

    @Test
    void getUpdateDate() {

        when(currencyService.getUpdateDate()).thenReturn("2024-12-03");

        ResponseEntity<String> response = currencyController.getUpdateDate();

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("2024-12-03", response.getBody());
    }

    @Test
    void getCurrencyConvert() {
        when(currencyService.getCurrencyConvert("USD", "EUR", new BigDecimal("100"))).thenReturn(new BigDecimal("85.00"));

        ResponseEntity<BigDecimal> response = currencyController.getCurrencyConvert("USD", "EUR", new BigDecimal("100"));

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(new BigDecimal("85.00"), response.getBody());
    }
}