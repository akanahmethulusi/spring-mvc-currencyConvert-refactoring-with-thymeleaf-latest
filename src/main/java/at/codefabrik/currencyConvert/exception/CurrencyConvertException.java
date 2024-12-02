package at.codefabrik.currencyConvert.exception;

public class CurrencyConvertException extends RuntimeException {

    public CurrencyConvertException(String expWaehrungCode) {
        super(expWaehrungCode);
    }
}
