package com.wani.pojotutorial.apps.customer;

import com.wani.pojotutorial.components.OrderFailedException;
import com.wani.pojotutorial.components.model.OrderInfo;
import com.wani.pojotutorial.components.model.OrderPlaced;
import java.rmi.RemoteException;
import java.util.UUID;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import org.glassfish.internal.deployment.ApplicationOrderInfo;

public class OrderCounterEJB implements SessionBean {

    private SessionContext ctx;

    private ConnectionFactory connectionFactory;
    private Queue queue;

    public OrderInfo placeOrder(String coffee) {
        OrderInfo orderInfo = new OrderInfo(UUID.randomUUID(), coffee);

        OrderPlaced orderPlaced = OrderPlaced.of(orderInfo);
        // 주문정보를 검증하고 , 저장하는 기능

        try (Connection connection = connectionFactory.createConnection()) {
            try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
                ObjectMessage message = session.createObjectMessage(orderPlaced);
                session.createProducer(queue).send(message);
            }
        } catch (JMSException e) {
            throw new OrderFailedException(e);
        }
        return orderInfo;
    }

    @Override
    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
        this.ctx = ctx;
    }

    public void ejbCreate() throws CreateException {
        connectionFactory = (ConnectionFactory) ctx.lookup("java:comp/DefaultJMSConnectionFactory");
        queue = (Queue) ctx.lookup("jms.orderQueue");
    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {

    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {

    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {

    }
}
