package com.xjjlearning.java.util.stream.chap11.service;


import static com.xjjlearning.java.util.stream.chap11.Util.delay;
import static com.xjjlearning.java.util.stream.chap11.Util.format;

public class Discount {

    public enum Code {
        NONE(100), SILVER(95), GOLD(90), PLATINUM(85), DIAMOND(80);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        delay();
        return format(price * code.percentage / 100);
    }
}
