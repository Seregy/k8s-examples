package com.seregy77.k8s.calculation.service.rate;

import com.seregy77.k8s.calculation.service.rate.client.RateClient;
import com.seregy77.k8s.calculation.domain.Rate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RateService {
    private final RateClient rateClient;

    public RateService(RateClient rateClient) {
        this.rateClient = rateClient;
    }

    public Set<Rate> getRatesByCurrency(String baseCurrency) {
        Map<String, BigDecimal> rates = rateClient.getRates(baseCurrency);

        return rates.entrySet().stream()
                .map(entry -> new Rate(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }
}
