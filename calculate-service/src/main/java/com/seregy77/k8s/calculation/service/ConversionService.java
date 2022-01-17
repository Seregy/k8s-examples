package com.seregy77.k8s.calculation.service;

import com.seregy77.k8s.calculation.domain.ConvertedValue;
import com.seregy77.k8s.calculation.domain.Rate;
import com.seregy77.k8s.calculation.service.rate.RateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConversionService {
    private final RateService rateService;

    public ConversionService(RateService rateService) {
        this.rateService = rateService;
    }

    public List<ConvertedValue> convert(BigDecimal originalValue, String originalCurrency) {
        Set<Rate> ratesByCurrency = rateService.getRatesByCurrency(originalCurrency);

        return ratesByCurrency.stream()
                .map(conversionRate -> convertValue(originalValue, conversionRate))
                .sorted(Comparator.comparing(ConvertedValue::currency))
                .collect(Collectors.toList());
    }

    private ConvertedValue convertValue(BigDecimal originalValue, Rate conversionRate) {
        return new ConvertedValue(conversionRate.currencyCode(), originalValue.multiply(conversionRate.value()));
    }
}
