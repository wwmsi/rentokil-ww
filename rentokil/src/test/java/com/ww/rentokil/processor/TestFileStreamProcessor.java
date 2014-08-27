package com.ww.rentokil.processor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.tck.junit4.AbstractMuleContextTestCase;

public class TestFileStreamProcessor extends AbstractMuleContextTestCase {
	private static Logger logger = Logger.getLogger(TestFileStreamProcessor.class);
	private FileStreamProcessor fsp;
	private MuleMessage muleMessage;
	private byte[] sftpPayload;
	private static final String SFTP_PAYLOAD = "SftpPayload";

	@Before
	public void setUp() throws Exception {
		fsp = new FileStreamProcessor();
		muleMessage = testMuleMessageConstruct();
		sftpPayload=getSftpPayloadAsBytes();
		//muleMessage.setOutboundProperty(SFTP_PAYLOAD, getSftpPayloadAsBytes());
		muleMessage.setOutboundProperty(SFTP_PAYLOAD, sftpPayload);
	}

	public MuleMessage testMuleMessageConstruct() throws Exception {
		MuleEvent event = getTestEvent("Event to Read Orders");
		MuleMessage message = event.getMessage();
		return message;
	}

	public byte[] getSftpPayloadAsBytes() {
		File file = new File("src/test/resources/orders.csv");
		long length = file.length();
		byte[] payloadBytes = new byte[(int) length];
		try {
			InputStream insputStream = new FileInputStream(file);
			insputStream.read(payloadBytes);
			insputStream.close();
		} catch (Exception e) {
			logger.error("Error is:" + e.getMessage());
		}
		return payloadBytes;
	}

	@Test
	public void testOnCall() throws Exception {
		MuleEventContext eventContext = mock(MuleEventContext.class);
		// setting a condition
		when(eventContext.getMessage()).thenReturn(muleMessage);
		// actual call
		InputStream resStream = (InputStream) fsp.onCall(eventContext);
		Assert.assertNotNull(resStream);
		MuleMessage res = eventContext.getMessage();
		assertEquals("Event to Read Orders", res.getOriginalPayload());
		assertEquals(sftpPayload.length, IOUtils.toByteArray(resStream).length);
	}

}
