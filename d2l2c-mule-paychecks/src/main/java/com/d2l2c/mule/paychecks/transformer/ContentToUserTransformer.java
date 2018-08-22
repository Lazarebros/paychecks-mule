/**
 * 
 */
package com.d2l2c.mule.paychecks.transformer;

import java.util.LinkedList;
import java.util.Map;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

/**
 * @author dayanlazare
 *
 */
public class ContentToUserTransformer extends AbstractMessageTransformer {

	private static final String SUCCESS_PROP_NAME = "SUCCESS";
	private static final String PASSWORD_PROP_NAME = "PASSWORD";
	private static final String SELECT_RESULT_SET_NAME = "selectResultSet";

	@SuppressWarnings("unchecked")
	@Override
	public Object transformMessage(MuleMessage muleMessage, String outputEncoding) throws TransformerException {
		Object selectResultSet = muleMessage.getInvocationProperty(SELECT_RESULT_SET_NAME);
		if (selectResultSet instanceof LinkedList<?>) {
			try {
				Map<String, Object> userPayload = ((LinkedList<Map<String, Object>>) selectResultSet).get(0);
				userPayload.remove(PASSWORD_PROP_NAME);
				userPayload.put(SUCCESS_PROP_NAME, true);
				muleMessage.setPayload(userPayload);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return muleMessage;
	}

}
