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
package com.google.code.fmppdataloaders.metadataloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.code.fmppdataloaders.metadataloader.domain.Attribute;
import com.google.code.fmppdataloaders.metadataloader.domain.Table;
import com.google.code.fmppdataloaders.utils.StringUtils;

import fmpp.Engine;
import fmpp.tdd.DataLoader;

/**
 * Data loader that loads Database MetaData i.e., Tables Names, 
 * their Attributes, Attribute Types in short the schema of tables specified and
 * returns a List of Table objects.
 * 
 * <p>The format of the directive is:
 * <code>MetaDataLoader(<i>driver</i>, <i>url</i>, 
 * <i>username</i>, <i>password</i>, <i>tablenames</i>)</code> where <br> 
 * <i>driver</i> is the name of the JDBC driver that will be used to connect to the database,<br>
 * <i>url</i> is the database connection string,<br>
 * <i>username</i> is the username for database connection,<br>
 * <i>password</i> is the password for database connection and<br>
 * <i>tablenames</i> is comma separated list of table names who's metadata is to be loaded.
 *  
 * @author <a href="mailto:faisalferoz@gmail.com">Faisal Feroz</a>
 * @version 1.0
 * @see Table
 * @see Attribute
 *
 */
public class MetaDataLoader implements DataLoader {

	@SuppressWarnings( "rawtypes" )
    public Object load(final Engine engine, final List args) throws Exception {

		if (args.size() < 5) {  
			throw new IllegalArgumentException(  
			"At least 5 argument (driverClassName, url, username, password, tablenames) needed.");  
		}
		
		String driver = (String) args.get( 0 );
		String url = (String) args.get( 1 );
		String username = (String) args.get( 2 );
		String password = (String) args.get( 3 );
		String tableNames = (String) args.get( 4 );

		List<Table> tables = new ArrayList<Table>();
		Connection con = null;

		try 
		{
			con = getConnection( driver, url, username, password);

			for ( String tableName : tableNames.split( "," )) {
				tables.add ( getMetaData( con, tableName.trim() ) );
			}
		}
		finally 
		{
			closeQuitely( con );
		}

		return tables;
	}

	/**
	 * @param con The database connection.
	 * @param tableName The name of the table.
	 * @return The Table and its attributes with type information.
	 * @throws Exception
	 */
	private Table getMetaData( final Connection con, final String tableName ) throws Exception
	{
		Table table = new Table ();
		List<Attribute> attributes = new ArrayList<Attribute>();

		ResultSetMetaData metaData = con.createStatement().executeQuery( getSql( tableName ) ).getMetaData();

		if ( metaData == null )
		{
			throw new Exception( "Table " + tableName + " does not exist." );
		}

		for (int i = 1; i <= metaData.getColumnCount(); i++) {

			Attribute attribute = new Attribute();

			attribute.setName( metaData.getColumnName( i ) );
			attribute.setType( StringUtils.getShortName( metaData.getColumnClassName( i ) ) );

			attributes.add( attribute );
		}

		table.setName( tableName );
		table.setAttributes( attributes );

		return table;
	}

	/**
	 * Returns the Database connection for the given dbURL.
	 * 
	 * @return Database Connection.
	 * @throws Exception
	 */
	private Connection getConnection(String driver, String url, String username, String password) throws Exception {

		Class.forName(driver);
		Connection con = DriverManager.getConnection( url, username, password);
		return con;
	}

	/**
	 * Returns the MetaData SQL for the given table.
	 * 
	 * @param tableName the table for which the MetaData SQL should be generated.
	 * @return MetaData SQL for the given table.
	 */
	private String getSql(String tableName) {

		return "select * from " + tableName + " where 1=2";
	}
	
	/**
	 * Closes the connection quietly.
	 * 
	 * @param con The database connection to close.
	 */
	private void closeQuitely(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
}
