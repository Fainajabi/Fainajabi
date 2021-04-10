package com.db.test.service.impl;

import com.db.test.exception.TradeException;
import com.db.test.model.Trade;
import com.db.test.service.TradeStratergy;
import com.db.test.util.TradeUtil;

import java.util.Date;
import java.util.TreeMap;

public class AscendingTradeStratergy implements TradeStratergy {

    public TreeMap<Integer, Trade> tradeMap = new TreeMap<Integer, Trade>();

    public void storeTrade(Trade trade) {

        int version = trade.getVersion();
        Date maturityDate = trade.getMaturityDate();
        boolean isValidMaturityDate = TradeUtil.isMaturityDateValid(maturityDate);
        if (isValidMaturityDate) {
            trade.setExpired(TradeUtil.getUpdatedExpityFlag(maturityDate));
            if (!tradeMap.isEmpty()) {
                int highestVersion = tradeMap.lastKey();
                if (version >= highestVersion) {
                    tradeMap.put(version, trade);
                } else {
                    throw new TradeException(" Lower Trade version is not  acceptable");
                }

            } else {
                tradeMap.put(version, trade);
            }
        } else {
            throw new TradeException(" Maturity Date before Today is not acceptable");
        }

        tradeStoreStatus();
    }

    //added for app testing purpose only
    public TreeMap tradeStoreStatus() {
        System.out.println("tradeMap " + tradeMap);
        return tradeMap;
    }
}
