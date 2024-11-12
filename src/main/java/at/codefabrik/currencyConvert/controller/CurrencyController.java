package at.codefabrik.currencyConvert.controller;

import at.codefabrik.currencyConvert.model.CurrencyWrapper;
import at.codefabrik.currencyConvert.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping
@AllArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/currencies")
    public ResponseEntity<CurrencyWrapper> getAllCurrency(){
        CurrencyWrapper allCurrency = currencyService.getAllCurrency();
        return new ResponseEntity<>(allCurrency, HttpStatus.OK);
    }

    @GetMapping("/update-date")
    public ResponseEntity<String> getUpdateDate(){
        String updated = currencyService.getUpdateDate();
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/convert")
    public ResponseEntity<BigDecimal> getCurrencyConvert(@RequestParam("fromCurrency") String  fromCurrency, @RequestParam("toCurrency") String toCurrency, @RequestParam("amount") BigDecimal amount){
        BigDecimal convertedCurrency = currencyService.getCurrencyConvert(fromCurrency, toCurrency, amount);
        return new ResponseEntity<>(convertedCurrency, HttpStatus.OK);
    }
}
