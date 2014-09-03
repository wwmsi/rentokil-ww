package com.ww.rentokil.processor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.mockito.Mockito;
import org.mule.DefaultMuleContext;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.MuleSession;
import org.mule.api.client.LocalMuleClient;
import org.mule.api.client.MuleClient;
import org.mule.api.lifecycle.Callable;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.tck.junit4.AbstractMuleTestCase;
import org.mule.transport.DefaultMuleMessageFactory;

import junit.framework.TestCase;

public class SampleFileStreamProcessorTest extends AbstractMuleTestCase {
	
	private DefaultMuleContextFactory muleContextFactory = new DefaultMuleContextFactory();
	
	private static final String SFTP_PAYLOAD = "SftpPayload";
	
	private static final String FILE_PATH ="C:/Users/yuppala/Desktop/temp/temp/orders.csv";
	
	private MuleEventContext eventContext = null;
	
	//private MuleMessage muleMessage=new DefaultMuleMessageFactory();
	
	private MuleMessage muleMessage=null;
	
	private MuleSession muleSession=null;
	
	private byte[] csvFile;
	
	MuleClient client;
	
    LocalMuleClient localclient;
    MuleContext ctx;
/*	
	@Override
	protected void setUp() throws Exception {
		eventContext= Mockito.mock(MuleEventContext.class);
		muleMessage= Mockito.mock(MuleMessage.class);
		muleSession = Mockito.mock(MuleSession.class);
		csvFile = FileUtils.readFileToByteArray(new File(FILE_PATH));
	    client = Mockito.mock(MuleClient.class);
	    localclient = Mockito.mock(LocalMuleClient.class);
	    ctx = Mockito.mock(MuleContext.class);
	    
	}*/
	
	@Test
	public void testFileProcessor() throws Exception{
		
		
		 MuleContext context = muleContextFactory.createMuleContext();
		 
		 eventContext= (MuleEventContext) new DefaultMuleContext();
		
		
		Callable callable = new FileStreamProcessor();
		muleMessage.setOutboundProperty(SFTP_PAYLOAD, csvFile);
		Mockito.when(eventContext.getMessage()).thenReturn(muleMessage);
		Mockito.when(eventContext.getMuleContext()).thenReturn(ctx);
		Mockito.when(eventContext.getSession()).thenReturn(muleSession);
		Mockito.when(ctx.getClient()).thenReturn(localclient);
		eventContext.sendEvent(muleMessage);
		
		InputStream inputStream  = (InputStream) callable.onCall(eventContext);
		//assertNotNull(inputStream);
		//assertSame(new ByteArrayInputStream(csvFile), inputStream);
	}
	

}
