package com.db.test.app;

import com.db.test.exception.TradeException;
import com.db.test.model.Trade;
import com.db.test.service.TradeService;
import com.db.test.service.TradeStratergy;
import com.db.test.service.impl.AscendingTradeStratergy;
import com.db.test.service.impl.TradeServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TradeManager {

    public TradeStratergy stratergy;

    public TradeService service = new TradeServiceImpl();

    public TradeManager(TradeStratergy stratergy) {
        this.stratergy = stratergy;
    }

    public void createTradeStore(Trade trade) {
        service.addTradeInStore(trade, stratergy);
    }


    public static void main(String[] args) {
        AscendingTradeStratergy ascendingTradeStratergy = new AscendingTradeStratergy();
        TradeManager tradeManager = new TradeManager(ascendingTradeStratergy);
        Trade trade = new Trade();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date inputDate = dateFormat.parse("20/05/2021");

            trade.setExpired("N");
            trade.setBookId("B1");
            trade.setCounterPartyId("CP-1");
            trade.setCreatedDate(new Date());
            trade.setMaturityDate(inputDate);
            trade.setTradeId("T1");
            trade.setVersion(2);

            Trade trade1 = new Trade();
            trade1.setExpired("N");
            trade1.setBookId("B1");
            trade1.setCounterPartyId("CP-1");
            trade1.setCreatedDate(new Date());
            trade1.setMaturityDate(inputDate);
            trade1.setTradeId("T1");
            trade1.setVersion(3);

            tradeManager.createTradeStore(trade);
            tradeManager.createTradeStore(trade1);
        } catch (Exception e) {
            throw new TradeException("Exception occurred in Trade Manager" + e.getMessage());
        }

    }


}
