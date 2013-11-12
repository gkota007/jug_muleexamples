package org.ordermgmt;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

public class CallableTaxableExample implements Callable{

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage message = eventContext.getMessage();
		
		String payload = (String) message.getPayload();
		
		//message.getInboundProperty( name );
		//message.getOutboundProperty( name );
		
		//message.getInvocationProperty("name");     <---These are the flow Vars in MEL
		//message.setSessionProperty(key, value);      <--- These are session variables in MEL
		
		//message.addOutboundAttachment(name, object, contentType);  <<--Adds an attachment
		
		System.out.println("THE CALLTAXABLE PAYLOAD ----> "+ payload );
		return message;
	}

}
