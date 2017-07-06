package activemq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AReceiver implements MessageListener
{
	Session session;
	Destination destination;
	
	public AReceiver(String url,String topic)
	{		
		try 
		{
			Connection connecttion = new ActiveMQConnectionFactory(url).createConnection();
			connecttion.start();
			session=connecttion.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination=session.createTopic(topic);
		} catch (JMSException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void Receive()
	{
		try {
			MessageConsumer mc=session.createConsumer(destination);
			mc.setMessageListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void onMessage(Message map) 
	{		
		TextMessage  vmap = (TextMessage )map;
		String string="";
		try {
			string=vmap.getText();
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
		System.out.println(string);	
		
	}
}
