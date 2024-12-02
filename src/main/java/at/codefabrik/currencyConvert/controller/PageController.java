package at.codefabrik.currencyConvert.controller;

import at.codefabrik.currencyConvert.model.CurrencyWrapper;
import at.codefabrik.currencyConvert.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/page")
@AllArgsConstructor
public class PageController {

    private final CurrencyService currencyService;

    @GetMapping({"/", "/index"})
    public String showIndex(Model model) {
        CurrencyWrapper allCurrency = currencyService.getAllCurrency();

        model.addAttribute("Update_Date", allCurrency.getUpdateDate());
        model.addAttribute("EUR", allCurrency.getCurrencies().get("EUR"));
        model.addAttribute("USD", allCurrency.getCurrencies().get("USD"));
        model.addAttribute("CHF", allCurrency.getCurrencies().get("CHF"));

        return "index";
    }

    @GetMapping("/convert")
    public String showConvert(Model model) {
        model.addAttribute("Update_Date", currencyService.getUpdateDate());
        return "convert";
    }

    @PostMapping("/convert")
    public String showConvert(@RequestParam("fromCurrency") String fromCurrency, @RequestParam("toCurrency") String toCurrency, @RequestParam("amount") BigDecimal amount, Model model) {
        BigDecimal convertedCurrency = currencyService.getCurrencyConvert(fromCurrency, toCurrency, amount);

        model.addAttribute("convertedCurrency", convertedCurrency);
        model.addAttribute("Update_Date", currencyService.getUpdateDate());

        return "convert";
    }

    @GetMapping("/allCurrency")
    public String showAlleCurrency(Model model) {
        model.addAttribute("allCurrency", currencyService.getAllCurrency().getCurrencies());
        return "allCurrency";
    }
}
