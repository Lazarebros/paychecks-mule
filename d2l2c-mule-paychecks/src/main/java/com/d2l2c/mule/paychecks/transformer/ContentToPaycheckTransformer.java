/**
 * 
 */
package com.d2l2c.mule.paychecks.transformer;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import com.d2l2c.paycheck.scanner.util.extractor.AbstractPaycheckExtractor;
import com.d2l2c.paycheck.scanner.util.extractor.PaycheckExtractorFactory;

/**
 * @author dayanlazare
 *
 */
public class ContentToPaycheckTransformer extends AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage muleMessage, String outputEncoding) throws TransformerException {
		if (muleMessage.getPayload() instanceof String) {
			try {
				String payload = muleMessage.getPayload(String.class);
				AbstractPaycheckExtractor extractor = PaycheckExtractorFactory.getInstance(payload);
				
				muleMessage.setPayload(extractor.parse(payload));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return muleMessage;
	}

}
