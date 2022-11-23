package com.fiveelements.unpay.xp.model;

public enum OrderStatus {
    Exception(5),
    None(0),
    WaitPay(1),
    Doing(2),
    Succecs(3),
    Fail(4);

    OrderStatus(int i) {

    }
}
