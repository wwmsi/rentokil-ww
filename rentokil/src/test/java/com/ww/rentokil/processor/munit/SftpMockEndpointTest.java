package com.ww.rentokil.processor.munit;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

public class SftpMockEndpointTest extends FunctionalMunitSuite{
	
	private static final String SFTP_PAYLOAD = "SftpPayload";
	
	protected String getConfigResources(){
		return "src/main/app/SftpToDbflow.xml,src/main/app/configurations.xml";
	}
	
	public boolean haveToMockMuleConnectors() {
		return true;
	}
	
	@Test
	public void validateSftpToDbflow() throws Exception {
		
		whenEndpointWithAddress( "sftp:inbound-endpoint" ).thenReturn( muleMessageWithPayload(getMockSftpPayload()));
		MuleEvent resultEvent = runFlow( "SftpToDbflow",testEvent(getMockSftpPayload()));
		MuleMessage resMessage = resultEvent.getMessage();
		assertNotNull(resMessage);
		assertNotNull(resMessage.getOutboundProperty(SFTP_PAYLOAD));
	}
	private InputStream getMockSftpPayload() throws FileNotFoundException{
		InputStream inputStream = new FileInputStream(new File("src/test/resources/orders.csv"));
		return inputStream;
	}

}
