package com.seregy77.k8s.calculation.service.rate.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@Service
public class RateClient {
    private final Logger log = LoggerFactory.getLogger(RateClient.class);

    private final String ratesProviderBaseUrl;
    private final RestTemplate restTemplate;

    public RateClient(@Value("${app.rates-provider.base-url}") String ratesProviderBaseUrl) {
        this.ratesProviderBaseUrl = ratesProviderBaseUrl;
        this.restTemplate = new RestTemplate();
    }

    public Map<String, BigDecimal> getRates(String baseCurrency) {
        ResponseEntity<Map<String, BigDecimal>> ratesResponse;
        try {
            ratesResponse = restTemplate.exchange(ratesProviderBaseUrl + "/rates/" + baseCurrency, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
            });
        } catch (RestClientException restClientException) {
            log.info("Failed to fetch rates from rates provider for '{}' currency", baseCurrency, restClientException);
            return Collections.emptyMap();
        }

        if (ratesResponse.getStatusCode().is2xxSuccessful() && ratesResponse.getBody() != null) {
            return ratesResponse.getBody();
        }

        return Collections.emptyMap();
    }
}
