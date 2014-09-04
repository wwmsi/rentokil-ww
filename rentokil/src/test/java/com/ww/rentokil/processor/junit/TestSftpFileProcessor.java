/**
 * 
 */
package com.ww.rentokil.processor.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;

import com.ww.rentokil.processor.SftpFileProcessor;
import com.ww.rentokil.vo.OrderVo;

/**
 * @author skambhammettu
 * 
 */
public class TestSftpFileProcessor extends RentokilTestCase {
	protected SftpFileProcessor sftpFileProcessor = null;
	OrderVo resOrdervo;
	int amount;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		sftpFileProcessor = new SftpFileProcessor();
		amount = Integer.parseInt(getOrderVo().getOrderAmount());

	}

	public OrderVo getOrderVo() {
		/*OrderVo orderVo = null;
		// create mock response
		orderVo = new OrderVo("7867", "MyOrders", "9008172829", "2000",
				"Enable", "Hyd", "US", "SFUser", "SFUser");*/
		OrderVo orderVo = new OrderVo();
		orderVo.setAccountName("MyOrders");
		orderVo.setActivatedBy("SFUser");
		orderVo.setBillingAddress("Hyd");
		orderVo.setContactNumber("9008172829");
		orderVo.setCreatdBy("SFUser");
		orderVo.setOrderAmount("2000");
		orderVo.setOrderNumber("7867");
		orderVo.setOrderStatus("Enable");
		orderVo.setShippingAddress("US");
		return orderVo;
	}

	/**
	 * Test method for
	 * {@link com.ww.rentokil.processor.SftpFileProcessor#onCall(org.mule.api.MuleEventContext)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOnCall() throws Exception {
		MuleEventContext eventContext = mock(MuleEventContext.class);
		muleMessage.setPayload(getOrderVo());
		// setting a condition
		when(eventContext.getMessage()).thenReturn(muleMessage);
		// actual call
		resOrdervo = (OrderVo) sftpFileProcessor.onCall(eventContext);
		MuleMessage res = eventContext.getMessage();
		assertNotNull(res);
		assertEquals("PO_" + getOrderVo().getOrderNumber(),
				resOrdervo.getOrderNumber());
		Integer expectedAmount = amount >= 2000 ? amount - 200 : amount;
		assertEquals(expectedAmount.toString(), resOrdervo.getOrderAmount());
	}

	@Test(expected = ClassCastException.class)
	public void testOnCall_NoOdersFile() throws Exception {
		MuleEventContext eventContext = mock(MuleEventContext.class);
		// setting a condition
		when(eventContext.getMessage()).thenReturn(muleMessage);
		// actual call
		resOrdervo = (OrderVo) sftpFileProcessor.onCall(eventContext);
		MuleMessage res = eventContext.getMessage();
	}
}
