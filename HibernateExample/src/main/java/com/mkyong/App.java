package com.mkyong;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;

import com.mkyong.stock.Stock;
import com.mkyong.stock.StockDailyRecord;
import com.mkyong.util.HibernateUtil;

public class App {
	public static void main(String[] args) {
		System.out.println("Hibernate one to many (XML Mapping)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();
        stock.setStockCode("705111");
        stock.setStockName("PADINI111");
        session.save(stock);
        
        StockDailyRecord stockDailyRecords = new StockDailyRecord();
        stockDailyRecords.setPriceOpen(new Float("1.211"));
        stockDailyRecords.setPriceClose(new Float("1.111"));
        stockDailyRecords.setPriceChange(new Float("10.101"));
        stockDailyRecords.setVolume(3000000L);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 2);
        stockDailyRecords.setDate(cal.getTime());
        
        stockDailyRecords.setStock(stock);        
        // stock.getStockDailyRecords().add(stockDailyRecords);

        session.save(stockDailyRecords);

		session.getTransaction().commit();
		System.out.println("Done");
	}
}
