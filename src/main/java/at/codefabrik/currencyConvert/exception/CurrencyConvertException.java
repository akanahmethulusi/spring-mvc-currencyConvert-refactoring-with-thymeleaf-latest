package at.codefabrik.currencyConvert.exception;

public class CurrencyConvertException extends RuntimeException {
    public CurrencyConvertException(String ungültiges_waehrungscode) {
        super(ungültiges_waehrungscode);
    }
}
