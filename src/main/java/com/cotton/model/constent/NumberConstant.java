package com.cotton.model.constent;

public enum NumberConstant {
    ONE(1);
    private int number;

    NumberConstant(int i) {
        this.number=i;
    }
    public Integer getValue(){
        return number;
    }
}
