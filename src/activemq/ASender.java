package activemq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ASender extends Thread
{
	Connection connecttion;
	Session session;
	Destination destination;
	MessageProducer messageproduceer;
	
	boolean running=false;
	String msgString;
	int perid;
	
	public ASender(String url,String topic)
	{		 
		try 
		{
			connecttion = new ActiveMQConnectionFactory(url).createConnection();
			connecttion.start();
			session=connecttion.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination=session.createTopic(topic);
			messageproduceer=session.createProducer(destination);
		} catch (JMSException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void Send(String msg,int perid)
	{
		this.msgString=msg;
		this.perid=perid;
		running=true;
		start();
	}
	
	public void Stop()
	{
		running=false;
		try {
			connecttion.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void run()
	{
		while (running) 
		{
			
			try {
				TextMessage   ms=session.createTextMessage();
				ms.setText(msgString);
				messageproduceer.send(destination,ms);
			} catch (JMSException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				Thread.sleep(perid);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
