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
package com.google.code.fmppdataloaders.metadataloader.domain;

import com.google.code.fmppdataloaders.utils.StringUtils;

/**
 * This class represents the Attributes present in a database table. It houses information
 * related to the Attribute types (Java Types corresponding to respective database types) and their names.
 * 
 * Apart from that the class also contains some utility methods for getting short types, getting attribute names in camel case
 * and title case.
 * 
 * @author <a href="mailto:faisalferoz@gmail.com">Faisal Feroz</a>
 * @version 1.0
 *
 */
public class Attribute {

	// attribute name
	private String name;
	
	// java type associated with the attribute
	private String type;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
	/**
	 * Returns the short java type name for the associated database type.
	 * For example if the type is <code>java.lang.String</code> then this will return <code>String</code>.
	 * 
	 * @return The short name of java type of the attribute.
	 */
	public String getShortType() {
		return StringUtils.getShortName( type );
	}
	
	/**
	 * Returns the attribute name in came case.
	 * For example if the attribute name is <code>EmployeeAddress</code> it will return <code>employeeAddress</code>
	 * 
	 * @return the attribute name in came case.
	 */
	public String getCamelCaseName() {
		return StringUtils.camelCase( name );
	}

	/**
	 * Returns the attribute name in title case.
	 * For example if the attribute name is <code>employeeAddress</code> it will return <code>EmployeeAddress</code>
	 * 
	 * @return the attribute name in title case.
	 */
	public String getTitleCaseName() {
		return StringUtils.removeUnderScores( name );
	}
}