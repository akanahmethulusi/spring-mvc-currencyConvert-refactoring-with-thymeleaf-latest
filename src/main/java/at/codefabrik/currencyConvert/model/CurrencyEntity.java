package at.codefabrik.currencyConvert.model;

import at.codefabrik.currencyConvert.utils.JsonDeserializerFile;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyEntity {

    @JsonDeserialize(using = JsonDeserializerFile.class)
    @JsonProperty("Buying")
    private BigDecimal buying;
    @JsonProperty("Selling")
    private BigDecimal selling;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Change")
    private String change;

    @JsonCreator
    public CurrencyEntity(@JsonProperty("Buying") BigDecimal buying, @JsonProperty("Selling") BigDecimal selling, @JsonProperty("Type") String type, @JsonProperty("Change") String change) {
        this.buying = buying;
        this.selling = selling;
        this.type = type;
        this.change = change;
    }
}
