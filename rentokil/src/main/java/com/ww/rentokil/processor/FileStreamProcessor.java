package com.ww.rentokil.processor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

/**
 * @author yuppala
 *
 */
public class FileStreamProcessor implements Callable {
	
	/**
	 * 
	 */
	private static final String SFTP_PAYLOAD = "SftpPayload";

	/* (non-Javadoc)
	 * @see org.mule.api.lifecycle.Callable#onCall(org.mule.api.MuleEventContext)
	 */
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		byte[] sftpPayload = (byte[]) eventContext.getMessage().getOutboundProperty(SFTP_PAYLOAD);
		InputStream inputStream = new ByteArrayInputStream(sftpPayload); 
		return inputStream;
	}
}
