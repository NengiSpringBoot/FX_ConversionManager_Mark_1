package com.fxconversion.fx_conversionmanager_mark_1.Enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumCurrency {

    CHF(0),
    CNY(1),
    EUR(2),
    GBP(3),
    JPY(4),
    NGN(5),
    SEK(6),
    USD(7),
    ZAR(8);


        private int value;
        private static Map map = new HashMap<>();

        private EnumCurrency(int value) {
            this.value = value;
        }

        static {
            for (EnumCurrency enumCurrency : EnumCurrency.values()) {
                map.put(enumCurrency.value, enumCurrency);
            }
        }

        public static EnumCurrency valueOf(int enumCurrency) {
            return (EnumCurrency) map.get(enumCurrency);
        }

        public int getValue() {
            return value;
        }

}
