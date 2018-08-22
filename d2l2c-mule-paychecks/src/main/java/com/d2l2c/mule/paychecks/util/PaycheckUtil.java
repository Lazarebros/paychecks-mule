/**
 * 
 */
package com.d2l2c.mule.paychecks.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author dlazare
 */
public class PaycheckUtil {
	
	private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

	public static boolean checkPassword(String password, String encodedPassword) {
		return encoder.matches(password, encodedPassword);
	}

	public static String encodePassword(String password) {
		return encoder.encode(password);
	}

}
