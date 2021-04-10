package com.db.test.app;

import com.db.test.constant.TradeConstant;
import com.db.test.exception.TradeException;
import com.db.test.model.Trade;
import com.db.test.service.impl.AscendingTradeStratergy;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class TradeManagerTest {

    AscendingTradeStratergy ascendingTradeStratergy = new AscendingTradeStratergy();
    TradeManager tradeManager = new TradeManager(ascendingTradeStratergy);

    @Test
    public void testCreateTradeStoreAscending() throws Exception {

        Trade trade = new Trade();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date maturityDate = dateFormat.parse("20/05/2021");
        trade.setExpired("N");
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setCreatedDate(new Date());
        trade.setMaturityDate(maturityDate);
        trade.setTradeId("T1");
        trade.setVersion(2);

        Trade trade1 = new Trade();
        trade1.setExpired("N");
        trade1.setBookId("B1");
        trade1.setCounterPartyId("CP-1");
        trade1.setCreatedDate(new Date());
        trade1.setMaturityDate(maturityDate);
        trade1.setTradeId("T1");
        trade1.setVersion(3);

        tradeManager.createTradeStore(trade);
        tradeManager.createTradeStore(trade1);
        TreeMap resultMap = ascendingTradeStratergy.tradeStoreStatus();

        assertEquals(2, resultMap.size());
    }


    @Test
    public void testCreateTradeStoreOverride() throws Exception {

        Trade trade = new Trade();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date maturityDate = dateFormat.parse("20/05/2021");
        trade.setExpired("N");
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setCreatedDate(new Date());
        trade.setMaturityDate(maturityDate);
        trade.setTradeId("T1");
        trade.setVersion(2);

        Trade trade1 = new Trade();
        trade1.setExpired("N");
        trade1.setBookId("B1");
        trade1.setCounterPartyId("CP-1");
        trade1.setCreatedDate(new Date());
        trade1.setMaturityDate(maturityDate);
        trade1.setTradeId("T1");
        trade1.setVersion(3);

        Trade trade2 = new Trade();
        trade2.setExpired("N");
        trade2.setBookId("B1");
        trade2.setCounterPartyId("CP-1");
        trade2.setCreatedDate(new Date());
        trade2.setMaturityDate(maturityDate);
        trade2.setTradeId("T1");
        trade2.setVersion(3);

        tradeManager.createTradeStore(trade);
        tradeManager.createTradeStore(trade1);
        tradeManager.createTradeStore(trade2);
        TreeMap resultMap = ascendingTradeStratergy.tradeStoreStatus();

        assertEquals(2, resultMap.size());
    }


    @Test(expected = TradeException.class)
    public void testCreateTradeStoreLowerVersion() throws Exception {
        Trade trade = new Trade();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date maturityDate = dateFormat.parse("20/05/2021");
        trade.setExpired("N");
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setCreatedDate(new Date());
        trade.setMaturityDate(maturityDate);
        trade.setTradeId("T1");
        trade.setVersion(2);

        Trade trade1 = new Trade();
        trade1.setExpired("N");
        trade1.setBookId("B1");
        trade1.setCounterPartyId("CP-1");
        trade1.setCreatedDate(new Date());
        trade1.setMaturityDate(maturityDate);
        trade1.setTradeId("T1");
        trade1.setVersion(3);

        Trade trade2 = new Trade();
        trade2.setExpired("N");
        trade2.setBookId("B1");
        trade2.setCounterPartyId("CP-1");
        trade2.setCreatedDate(new Date());
        trade2.setMaturityDate(maturityDate);
        trade2.setTradeId("T1");
        trade2.setVersion(1);

        tradeManager.createTradeStore(trade);
        tradeManager.createTradeStore(trade1);
        tradeManager.createTradeStore(trade2);

    }


    @Test(expected = TradeException.class)
    public void testCreateTradeStoreInvalidMaturityDate() throws Exception {
        Trade trade = new Trade();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date maturityDate = dateFormat.parse("20/05/2020");
        trade.setExpired("N");
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setCreatedDate(new Date());
        trade.setMaturityDate(maturityDate);
        trade.setTradeId("T1");
        trade.setVersion(2);

        tradeManager.createTradeStore(trade);

    }

    @Test(expected = TradeException.class)
    public void testCreateTradeStoreExpiredFlagYES() throws Exception {
        Trade trade = new Trade();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date maturityDate = dateFormat.parse("20/05/2020");
        trade.setExpired("N");
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setCreatedDate(new Date());
        trade.setMaturityDate(maturityDate);
        trade.setTradeId("T1");
        trade.setVersion(2);

        tradeManager.createTradeStore(trade);
        TreeMap<Integer, Trade> resultMap = ascendingTradeStratergy.tradeStoreStatus();

        assertEquals(TradeConstant.YES, resultMap.get(2).getExpired());

    }

    @Test
    public void testCreateTradeStoreExpiredFlagNO() throws Exception {
        Trade trade = new Trade();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date maturityDate = dateFormat.parse("20/05/2021");
        trade.setExpired("Y");
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setCreatedDate(new Date());
        trade.setMaturityDate(maturityDate);
        trade.setTradeId("T1");
        trade.setVersion(2);

        tradeManager.createTradeStore(trade);
        TreeMap<Integer, Trade> resultMap = ascendingTradeStratergy.tradeStoreStatus();

        assertEquals(TradeConstant.NO, resultMap.get(2).getExpired());

    }


}
