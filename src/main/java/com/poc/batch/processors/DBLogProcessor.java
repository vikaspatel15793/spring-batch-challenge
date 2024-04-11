package com.poc.batch.processors;

import com.poc.batch.entities.StoreOrder;
import org.springframework.batch.item.ItemProcessor;

public class DBLogProcessor implements ItemProcessor<StoreOrder, StoreOrder> {
    public StoreOrder process(StoreOrder storeOrder) throws Exception
    {
        System.out.println("Inserting store order : " + storeOrder);
        return storeOrder;
    }
}
