package at.codefabrik.currencyConvert.service;

import at.codefabrik.currencyConvert.exception.CurrencyConvertException;
import at.codefabrik.currencyConvert.model.CurrencyEntity;
import at.codefabrik.currencyConvert.model.CurrencyWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@AllArgsConstructor
public class CurrencyService {
    private static final String webUrl="https://finans.truncgil.com/v4/today.json";
    private final RestTemplate restTemplate;

    public CurrencyWrapper getAllCurrency() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<CurrencyWrapper> response = restTemplate.exchange(webUrl, HttpMethod.GET, httpEntity, CurrencyWrapper.class);

        return response.getBody();
    }

    public String getUpdateDate() {
        return getAllCurrency().getUpdateDate();
    }

    public BigDecimal getCurrencyConvert(String fromCurrency, String toCurrency, BigDecimal amount) {
        CurrencyWrapper allCurrency = getAllCurrency();

        CurrencyEntity fromEntity = allCurrency.getCurrencies().get(fromCurrency);
        CurrencyEntity toEntity = allCurrency.getCurrencies().get(toCurrency);

        if(fromEntity == null || toEntity == null){
            throw new CurrencyConvertException("Währungscode ungültig!");
        }

        BigDecimal fromRate = fromEntity.getBuying();
        BigDecimal toRate = toEntity.getSelling();

        BigDecimal result = amount.multiply(fromRate).divide(toRate, RoundingMode.HALF_UP);

        return result;
    }
}
