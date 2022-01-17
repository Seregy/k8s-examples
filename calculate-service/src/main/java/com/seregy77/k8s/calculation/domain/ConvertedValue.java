package com.seregy77.k8s.calculation.domain;

import java.math.BigDecimal;

public record ConvertedValue(String currency, BigDecimal value) {
}
