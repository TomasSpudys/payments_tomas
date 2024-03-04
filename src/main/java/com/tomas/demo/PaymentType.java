package com.tomas.demo;

public enum PaymentType {
    TYPE1(1),
    TYPE2(2),
    TYPE3(3);

    private final int intValue;

    private PaymentType(int intValue) {
        this.intValue = intValue;
    }

    public int getIntValue() {
        return intValue;
    }
    }
