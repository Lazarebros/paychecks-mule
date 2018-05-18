/**
 * 
 */
package com.d2l2c.mule.paychecks.component;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.mule.api.security.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author dayanlazare
 *
 */
public class GetSecurityPrincipalComponent implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		Authentication auth = eventContext.getSession().getSecurityContext().getAuthentication();
		UserDetails principal = (UserDetails) auth.getPrincipal();
		eventContext.getMessage().setInvocationProperty("userName", principal.getUsername());
		return eventContext.getMessage().getPayload();
	}

}
