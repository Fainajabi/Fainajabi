package com.db.test.util;

import com.db.test.constant.TradeConstant;

import java.util.Date;

public class TradeUtil {

    private TradeUtil() {
        //empty constructor
    }

    public static String getUpdatedExpityFlag(Date maturityDate) {
        return maturityDate.compareTo(new Date()) < TradeConstant.ZERO ? TradeConstant.YES : TradeConstant.NO;
    }

    public static boolean isMaturityDateValid(Date maturityDate) {
        return maturityDate.compareTo(new Date()) > TradeConstant.ZERO;
    }
}
