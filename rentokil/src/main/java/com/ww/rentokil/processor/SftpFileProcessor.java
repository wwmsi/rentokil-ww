package com.ww.rentokil.processor;

import org.apache.commons.lang.StringUtils;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import com.ww.rentokil.vo.OrderVo;


/**
 * @author yuppala
 *
 */
public class SftpFileProcessor implements Callable {
	
	/* (non-Javadoc)
	 * @see org.mule.api.lifecycle.Callable#onCall(org.mule.api.MuleEventContext)
	 */
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {		
		OrderVo orderVo =(OrderVo) eventContext.getMessage().getPayload();
		String orderNumber = orderVo.getOrderNumber();
		String orderAmount = orderVo.getOrderAmount();
		// sample b.logic
		if (StringUtils.isNotBlank(orderNumber)) {
			orderVo.setOrderNumber("PO_" + orderVo.getOrderNumber());
		}
		Integer actualAmount=0;
		if (StringUtils.isNotBlank(orderAmount)) {
			int amount = Integer.parseInt(orderAmount);
			actualAmount= amount>=2000?amount-200:amount;
			orderVo.setOrderAmount(actualAmount.toString());
		}
		return orderVo;
	}
}
