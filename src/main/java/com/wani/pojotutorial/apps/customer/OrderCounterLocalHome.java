package com.wani.pojotutorial.apps.customer;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface OrderCounterLocalHome extends EJBLocalHome {

    OrderCounterLocal create() throws CreateException;

}
