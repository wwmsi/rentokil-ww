/**
 * 
 */
package com.ww.rentokil.processor.junit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.tck.junit4.AbstractMuleContextTestCase;

/**
 * @author skambhammettu
 * 
 */
public class RentokilTestCase extends AbstractMuleContextTestCase {
	protected static Logger logger = Logger
			.getLogger(TestFileStreamProcessor.class);
	protected MuleMessage muleMessage;
	protected static final String SFTP_PAYLOAD = "SftpPayload";
	byte[] sftpPayload;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		muleMessage = getMuleMessageConstruct();
		sftpPayload = getSftpPayloadAsBytes();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	public MuleMessage getMuleMessageConstruct() throws Exception {
		MuleEvent event = getTestEvent("Event to Read Orders");
		MuleMessage message = event.getMessage();
		return message;
	}

	public byte[] getSftpPayloadAsBytes() throws FileNotFoundException,
			IOException {
		File file = new File("src/test/resources/orders.csv");
		long length = file.length();
		byte[] payloadBytes = new byte[(int) length];

		InputStream insputStream = new FileInputStream(file);
		insputStream.read(payloadBytes);
		insputStream.close();
		return payloadBytes;
	}

	@Test
	public void test() {
		logger.info("Base class for Test framework in rentokil");
	}

}
