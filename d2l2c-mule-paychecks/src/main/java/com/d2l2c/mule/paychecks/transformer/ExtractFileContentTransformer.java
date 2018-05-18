/**
 * 
 */
package com.d2l2c.mule.paychecks.transformer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import com.d2l2c.common.util.scanner.ScannerUtil;

/**
 * @author dayanlazare
 *
 */
public class ExtractFileContentTransformer extends AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage muleMessage, String outputEncoding) throws TransformerException {
		byte[] payload = null;
		if (muleMessage.getPayload() instanceof byte[]) {
			payload = (byte[]) muleMessage.getPayload();
		} else if (muleMessage.getPayload() instanceof String) {
			payload = ((String) muleMessage.getPayload()).getBytes();
		}
		try (InputStream inputStream = new ByteArrayInputStream(payload)) {
			String newPayload = ScannerUtil.scanFileToText(inputStream);
			muleMessage.setPayload(newPayload);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return muleMessage;
	}

}
