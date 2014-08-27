package com.ww.rentokil.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;

/**
 * @author skambhammettu
 * 
 */

public class TestFileStreamProcessor extends RentokilTestCase {
	protected FileStreamProcessor fsp;
	MuleMessage muleMessageWOpayload;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		fsp = new FileStreamProcessor();
		muleMessage.setOutboundProperty(SFTP_PAYLOAD, getSftpPayloadAsBytes());
		muleMessageWOpayload = getMuleMessageConstruct();

	}

	/**
	 * Test method for
	 * {@link com.ww.rentokil.processor.TestFileStreamProcessor#onCall(org.mule.api.MuleEventContext)}
	 * .
	 */
	@Test
	public void testOnCall() throws Exception {
		MuleEventContext eventContext = mock(MuleEventContext.class);
		// setting a condition
		when(eventContext.getMessage()).thenReturn(muleMessage);
		// actual call
		InputStream resStream = (InputStream) fsp.onCall(eventContext);
		assertNotNull(resStream);
		MuleMessage res = eventContext.getMessage();
		assertEquals("Event to Read Orders", res.getOriginalPayload());
		assertEquals(sftpPayload.length, IOUtils.toByteArray(resStream).length);

	}

	@Test(expected = NullPointerException.class)
	public void testOnCall_NoOdersFile() throws NullPointerException, Exception {
		MuleEventContext eventContext = mock(MuleEventContext.class);
		// setting a condition
		when(eventContext.getMessage()).thenReturn(muleMessageWOpayload);
		// actual call
		InputStream resStream = (InputStream) fsp.onCall(eventContext);
	}

}
