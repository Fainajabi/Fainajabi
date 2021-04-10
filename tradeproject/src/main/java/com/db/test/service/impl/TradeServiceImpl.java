package com.db.test.service.impl;

import com.db.test.model.Trade;
import com.db.test.service.TradeService;
import com.db.test.service.TradeStratergy;

public class TradeServiceImpl implements TradeService {

    public void addTradeInStore(Trade trade, TradeStratergy stratergy) {
        stratergy.storeTrade(trade);
    }
}
