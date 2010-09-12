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

import java.util.List;

import com.google.code.fmppdataloaders.utils.StringUtils;

/**
 * This class represents the Table present in a database. It houses information
 * related to the Attribute associated with it.
 * 
 * Apart from that the class also contains some utility methods for getting table names in camel case
 * and title case.
 * 
 * @author <a href="mailto:faisalferoz@gmail.com">Faisal Feroz</a>
 * @version 1.0
 * 
 * @see Attribute
 *
 */
public class Table {

	// database table name
	private String name;
	
	// list of attributes (columns) in the table
	private List<Attribute> attributes;
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public List<Attribute> getAttributes() 
	{
		return attributes;
	}
	
	public void setAttributes(List<Attribute> attributes) 
	{
		this.attributes = attributes;
	}

	/**
	 * Returns the table name in title case.
	 * For example if the table name is <code>employeeAddress</code> it will return <code>EmployeeAddress</code>
	 * 
	 * @return the table name in title case.
	 */
	public String getTitleCaseName() {
		return StringUtils.removeUnderScores ( name );
	}
	
	/**
	 * Returns the table name in came case.
	 * For example if the table name is <code>EmployeeAddress</code> it will return <code>employeeAddress</code>
	 * 
	 * @return the table name in came case.
	 */
	public String getCamelCaseName() {
		return StringUtils.camelCase( name );
	}
}
