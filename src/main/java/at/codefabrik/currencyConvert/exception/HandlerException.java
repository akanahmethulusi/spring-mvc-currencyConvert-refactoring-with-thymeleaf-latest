package at.codefabrik.currencyConvert.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException{


    @ExceptionHandler(CurrencyConvertException.class)
    public ResponseEntity<String> handleCurrencyConvertException(CurrencyConvertException cce){
        cce.getMessage();
        return new ResponseEntity<>(cce.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
}
