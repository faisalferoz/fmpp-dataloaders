/*******************************************************************************
 * The MIT License
 * 
 * Copyright (c) 2010, Faisal Feroz
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.googlecode.fmppdataloaders.utils;

import java.util.StringTokenizer;

/**
 * This class provides some string utility functions. 
 *
 * @author <a href="mailto:faisalferoz@gmail.com">Faisal Feroz</a>
 *
 */
public class StringUtils {

	private static final char PACKAGE_SEPARATOR_CHAR = '.';
	private static final char INNER_CLASS_SEPARATOR_CHAR = '$';
	private static final String CLASS_SEPARATOR_CHAR = "$$";

    /**
     * <p>
     * Makes first letter lower case. Removes underscores from a string and replaces first
     * letters with capitals. Other letters are changed to lower case.
     * </p>
     *
     * <p>
     * For example <code>foo_bar</code> becomes <code>fooBar</code>
     * but <code>foo_barBar</code> becomes <code>fooBarbar</code>.
     * </p>
     *
     * @param data string to make camel case.
     * @return String
     */
	public static final String camelCase(final String data) {
		
		return data.substring(0,1).toLowerCase() + removeUnderScores( data ).substring(1);
	}

	/**
	 * <p>
	 * Returns the short name of the class name passed in.
	 * </p>
	 * 
	 * <p>
	 * For example <code>java.lang.String</code> becomes <code>String</code>
	 * </p>
	 * 
	 * @param className The class name who's short name is required.
	 * @return String
	 */
	public static final String getShortName(final String className) {

		final int lastDotIndex = className.lastIndexOf(PACKAGE_SEPARATOR_CHAR);
		int nameEndIndex = className.indexOf(CLASS_SEPARATOR_CHAR);
		
		if (nameEndIndex == -1) {
			nameEndIndex = className.length();
		}
		String shortName = className.substring(lastDotIndex + 1, nameEndIndex);
		shortName = shortName.replace(INNER_CLASS_SEPARATOR_CHAR, PACKAGE_SEPARATOR_CHAR);
		return shortName;
	}

    /**
     * <p>
     * Checks if the string is empty or not. It also checks for null and
     * compares against an empty string to check if the string is empty or not. 
     * </p>
     *
     * @param string string to check if its empty or not.
     * @return boolean
     */
	public static final boolean isEmpty( final String string ) {
		
		return string == null || "".equals( string );
	}
	
    /**
     * <p>
     * Remove underscores from a string and replaces first
     * letters with capitals.  Other letters are changed to lower case.
     * </p>
     *
     * <p>
     * For example <code>foo_bar</code> becomes <code>FooBar</code>
     * but <code>foo_barBar</code> becomes <code>FooBarbar</code>.
     * </p>
     *
     * @param data string to remove underscores from.
     * @return String
     */
    public static final String removeUnderScores (final String data)
    {
        StringBuffer out = new StringBuffer();
        StringTokenizer st = new StringTokenizer(data, "_");

        while (st.hasMoreTokens())
        {
            String element = (String) st.nextElement();
            out.append ( firstLetterCaps(element));
        }

        return out.toString();
    }
    
    /**
     * <p>
     *  Makes the first letter caps and the rest lower case.
     * </p>
     *
     * <p>
     *  For example <code>fooBar</code> becomes <code>Foobar</code>.
     * </p>
     *
     * @param data capitalize this
     * @return String
     */
    public static final String firstLetterCaps ( final String data )
    {
        String firstLetter = data.substring(0,1).toUpperCase();
        String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }

}
