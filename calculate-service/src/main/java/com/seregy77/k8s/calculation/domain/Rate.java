package com.seregy77.k8s.calculation.domain;


import java.math.BigDecimal;

public record Rate(String currencyCode, BigDecimal value) {
}
