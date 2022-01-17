package com.seregy77.k8s.calculation.web;

import com.seregy77.k8s.calculation.domain.ConvertedValue;
import com.seregy77.k8s.calculation.service.ConversionService;
import com.seregy77.k8s.calculation.web.request.ValueConversionRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CalculationController {
    private static final String DEFAULT_CURRENCY = "USD";

    private final ConversionService conversionService;

    public CalculationController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute(TemplateAttribute.VALUE_CONVERSION_REQUEST.value, new ValueConversionRequest(BigDecimal.ZERO));
        return "convert";
    }

    @PostMapping
    public String convertValue(@ModelAttribute ValueConversionRequest valueConversionRequest, Model model) {
        if (valueConversionRequest.value() != null) {
            List<ConvertedValue> convertedValues = conversionService.convert(valueConversionRequest.value(), DEFAULT_CURRENCY);
            model.addAttribute(TemplateAttribute.CONVERTED_VALUES.value, convertedValues);
        }

        return "convert";
    }

    private enum TemplateAttribute {
        CONVERTED_VALUES("convertedValues"),
        VALUE_CONVERSION_REQUEST("valueConversionRequest");

        private final String value;

        TemplateAttribute(String value) {
            this.value = value;
        }
    }
}
