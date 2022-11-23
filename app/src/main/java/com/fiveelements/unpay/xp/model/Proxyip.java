package com.fiveelements.unpay.xp.model;

public class Proxyip {
    public int port;
    public String url;

    public String toString() {
        return this.url + ":" + this.port;
    }
}
