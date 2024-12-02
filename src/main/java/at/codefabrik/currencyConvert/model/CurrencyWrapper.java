package at.codefabrik.currencyConvert.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CurrencyWrapper {

    @JsonProperty("Update_Date")
    private String updateDate;

    private Map<String, CurrencyEntity> currencies = new HashMap<>();

    @JsonAnySetter
    public void setCurrencies(String key, CurrencyEntity value) {
        currencies.put(key, value);
    }
}
