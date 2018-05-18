/**
 * 
 */
package com.d2l2c.mule.paychecks.comparator;

import java.io.File;
import java.util.Comparator;

/**
 * @author dayanlazare
 *
 */
public class FilenameComparator implements Comparator<Object> {

	private static final String VALUE_SEPARATOR = "-";
	private static final int FILE_EXTENSION_LENGTH = 4;

	@Override
	public int compare(Object o1, Object o2) {
		String fileName1 = ((File) o1).getName();
		fileName1 = fileName1.substring(0, fileName1.length() - FILE_EXTENSION_LENGTH);

		String fileName2 = ((File) o2).getName();
		fileName2 = fileName2.substring(0, fileName2.length() - FILE_EXTENSION_LENGTH);

		return compare(fileName1, fileName2);
	}

	private int compare(String fileName1, String fileName2) {
		int result = 0;

		String[] values1 = fileName1.split(VALUE_SEPARATOR);
		String[] values2 = fileName2.split(VALUE_SEPARATOR);

		for (int i = 0; i < values1.length; i++) {
			Long value1 = Long.valueOf(values1[i]);
			Long value2 = Long.valueOf(values2[i]);

			if(value1 > value2) {
				result = 1;
				break;
			} else if(value1 < value2) {
				result = -1;
				break;
			}
		}
		return result;
	}

}
