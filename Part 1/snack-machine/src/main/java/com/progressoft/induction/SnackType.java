package com.progressoft.induction;

import java.math.BigDecimal;

public enum SnackType {
    CHEWING_GUM (new Money(BigDecimal.valueOf(0.5))),
    CHOCOLATE (new Money(BigDecimal.valueOf(2))),
    CHIPS (new Money(BigDecimal.valueOf(1)));

    private Money price;
    private SnackType(Money price) {
        this.price = price;
    }
    public Money getValue() {
        return price;
    }
}
