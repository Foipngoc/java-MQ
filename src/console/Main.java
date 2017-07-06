package console;

import javax.jms.JMSException;
import mqtt.TReceiver;
import activemq.ASender;

public class Main 
{
	public static void main(String[] args) throws JMSException
	{
		ASender as=new ASender("tcp://localhost:61616","op");
		as.Send("test", 1000);
		//AReceiver receiver=new AReceiver("tcp://localhost:61616","op");
		//receiver.Receive();
		TReceiver receiver=new TReceiver("tcp://localhost:1883","op");
		receiver.Receive();
	}
}
