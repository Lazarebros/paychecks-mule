/**
 * 
 */
package com.d2l2c.mule.paychecks.transformer;

import java.util.List;
import java.util.Map;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import com.d2l2c.mule.paychecks.bean.PaycheckSummary;
import com.d2l2c.mule.paychecks.util.PaycheckMappingUtil;

/**
 * @author dayanlazare
 *
 */
public class JavaToJsonDetailsTransformer extends AbstractMessageTransformer {

	@SuppressWarnings("unchecked")
	@Override
	public Object transformMessage(MuleMessage muleMessage, String outputEncoding) throws TransformerException {
		try {
			List<Map<String, Object>> payload = (List<Map<String, Object>>) muleMessage.getPayload();

			PaycheckSummary paycheckSummary = PaycheckMappingUtil.getDetailledPaychecks(payload);

			muleMessage.setPayload(paycheckSummary);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return muleMessage;
	}

}
