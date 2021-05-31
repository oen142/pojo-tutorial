package com.wani.pojotutorial.apps.customer;

import com.wani.pojotutorial.components.model.OrderInfo;
import javax.ejb.EJBLocalObject;

public interface OrderCounterLocal extends EJBLocalObject {

    OrderInfo placeOrder(String coffee);

}
