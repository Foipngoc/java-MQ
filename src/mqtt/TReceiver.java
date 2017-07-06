package mqtt;

import com.ibm.mqtt.MqttClient;
import com.ibm.mqtt.MqttException;
import com.ibm.mqtt.MqttNotConnectedException;
import com.ibm.mqtt.MqttSimpleCallback;

public class TReceiver implements MqttSimpleCallback 
{
	MqttClient mqttClient;
	String topic="";
	
	public  TReceiver(String url,String topic) 
	{
		this.topic=topic;
		try {
			
			mqttClient = new MqttClient(url);			
			mqttClient.connect("fdsf",false,(short) 5);
			mqttClient.subscribe(new String[]{topic},new int[]{1});			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Receive()
	{
		mqttClient.registerSimpleHandler(this);
	}
	
	public void Stop()
	{
		try {
			mqttClient.unsubscribe(new String[]{topic});
		} catch (MqttNotConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void connectionLost() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void publishArrived(String msg, byte[] arg1, int arg2, boolean arg3)
			throws Exception {
		System.out.println(new String(arg1));
		
	}
	
}
