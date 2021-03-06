/*
 * Copyright 2015 Beryl Nene <lc0641046@lambtoncollege.ca>.
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

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

/**
 *
 * @author Beryl Nene <c0641046@lambtoncollege.ca>
 */
public class OrderQueue {
    Queue<Order> orderQueue = new ArrayDeque<>();
    
    public void add(Order order) {
        if (order.getCustomerId().isEmpty() &&order.getCustomerName().isEmpty()){
            throw new RuntimeException("Customer Id or Name Does not Exist");
        }
        if (order.getListOfPurchases().isEmpty()){
            throw new RuntimeException ("No Purchase has been made");
        }
        
        orderQueue.add(order);
        order.setTimeReceived(new Date());
    }
    
   public Order nextOrder(){
        if (orderQueue.isEmpty()){
            return null;
    
        }
        return orderQueue.peek();
    }
   
     public void processed(Order order){
       if (order.getTimeReceived()!=null)
       {
           order.setTimeProcessed(new Date());
       }
       else 
           throw new RuntimeException("This order does not have time processed");
                  
   } 
 
   
    public void fulfill(Order order){
        if (order.getTimeProcessed()==null){
            throw new RuntimeException("This order does not have time processed");
        }
        
        if (order.getTimeReceived()==null){
            throw new RuntimeException("This order does not have time received");
        }
        
    }
    
    public String report(){
        if (orderQueue.isEmpty()){
            return "";
        }else 
            return "JSON ";
    }
}


