package com.cotton.model.constent;

public enum DiscountConstant {
    ONE(1.0);
    private double discount;
    DiscountConstant(double v) {
        this.discount = v;
    }
    public Double getValue(){
        return discount;
    }
}
