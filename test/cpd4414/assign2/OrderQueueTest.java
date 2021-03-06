/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cpd4414.assign2;

import cpd4414.assign2.OrderQueue;
import cpd4414.assign2.Purchase;
import cpd4414.assign2.Order;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Beryl Nene <c0641046@lambtoncollege.ca>
 */
public class OrderQueueTest {
    
    public OrderQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testWhenCustomerExistsAndPurchasesExistThenTimeReceivedIsNow(){
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        orderQueue.add(order);
        
        long expResult = new Date().getTime();
        long result = order.getTimeReceived().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);
    }
    
    @Test
    public void testGivenNewOrderArriveWhenNeitherIdAndNameExistThrowException() throws Exception{
   boolean existThrow = false;
   OrderQueue orderQueue = new OrderQueue();
   Order order = new Order ("", "");
   order.addPurchase(new Purchase("",450));
    order.addPurchase(new Purchase("PROD0006",450));   
      try{
          orderQueue.add(order);
      }
      catch (Exception ex){
          existThrow = true;
      }
      assertTrue(existThrow);
    }
    
    
  @Test
    public void testGivenNewOrderArriveThereIsNoListOfPurchaseThrowException() throws Exception{
   boolean existThrow = false;
   OrderQueue orderQueue = new OrderQueue();
   Order order = new Order ("CUST00001", "ABC Construction");
  
      try{
          orderQueue.add(order);
      }
      catch (Exception ex){
          existThrow = true;
      }
      assertTrue(existThrow);
    }
    
    @Test
    public void testGivenRequestForNextOrderWhenOrderInSystemReturnOrderEarliestTimeReceivedAndDoesNotHaveTimeProcess(){
       OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        orderQueue.add(order);
        
        Order result= orderQueue.nextOrder();
        Order expResult = orderQueue.orderQueue.peek();
        
        
    }
    
 @Test
    public void testGivenRequestForNextOrderWhenThereIsNoOrderInSystemReturnNull() {
       OrderQueue orderQueue = new OrderQueue();
       Order result =orderQueue.nextOrder();
        assertEquals(result, null);   
    }
    
    @Test
    public void testGivenRequestToProcessOrderWhenOrderTimeReceivedAndPurchaseInStockSetTimeProcessToNow(){
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        order.setTimeReceived(new Date());
        orderQueue.processed(order);
        assertEquals(new Date().getTime(), order.getTimeProcessed().getTime());
    }
     
    @Test
    public void testGivenRequestToProcessOrderWhenOrderDoesNotHaveTimeReceivedThrowException() throws Exception{
        boolean existThrow=false;
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        
        try {
            orderQueue.processed(order);
        }
        catch (RuntimeException ex){
            existThrow = true;
        }
        assertTrue(existThrow);
    }
    
    @Test
    public void testGivenRequestToFulfilOrderWhenOrderHasTimeProcessedAndAllPurchaseInStockSetTimeFulfilToNow(){
    
}
    
    
    @Test
    public void testGivenRequestToFulfillOrderWhenOrderDoesNotHaveTimeProcessedThrowException() throws Exception{
        boolean existThrow=false;
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        order.setTimeProcessed(new Date(new Date().getTime()-1234567890));
        try{
          orderQueue.fulfill(order);
      }
      catch (Exception ex){
          existThrow = true;
      }
      assertTrue(existThrow);
    }
    
    @Test
    public void testGivenRequestToFulfillOrderWhenOrderDoesNotHaveTimeReceivedThrowException() throws Exception{
        boolean existThrow =false;
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order ("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        order.setTimeReceived(new Date(new Date().getTime()-1234567890));
        orderQueue.add(order);
        try{
          orderQueue.fulfill(order);
      }
      catch (Exception ex){
          existThrow = true;
      }
      assertTrue(existThrow);
    }
    
    @Test
    public void testGivenRequestForReportWhenThereAreNoOrderInSystemReturnEmptyString(){
        OrderQueue orderQueue = new OrderQueue();
        String result =orderQueue.report();
        assertEquals("",result);
    }
    
    @Test
    public void testGivenRequestForReportWhenThereAreOrdersInSystemThenReturnAJSONOnbject(){
        
    }
    }


