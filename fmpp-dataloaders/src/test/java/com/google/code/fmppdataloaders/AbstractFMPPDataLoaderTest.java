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
package com.google.code.fmppdataloaders;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fmpp.progresslisteners.ConsoleProgressListener;
import fmpp.setting.Settings;

/**
 * This is the base class for all the fmpp data loader unit tests. 
 * This class initializes the fmpp engine and sets the properties.
 * 
 * <p>This class uses the spring test context framework and has setup and teardown
 * methods coded in which are responsible for initializing the fmpp engine and
 * cleanup all the output created after the tests in the output directory. 
 * 
 * <p>
 * In order to make unit tests for data loaders first extend the class. 
 * Then initialize the <code>cfgFile</code> attribute with the path of the directory 
 * where the fmpp config file is, in the constructor of the class.
 * 
 * <p>
 * Here is a sample code for a Unit Test
 * <pre>
 * public class DataLoaderTests extends AbstractFMPPDataLoaderTest {
 *     	
 *     public DataLoaderTests() {
 *         cfgFile = new File( "src/test/resources/dataloader/" );
 *     }
 *     
 *     &#64;Test
 *     public testDataLoader() {
 *         // execute the fmpp engine
 *         fmpp.execute();
 *         
 *         assertTrue();
 *     }
 * }
 * </pre>
 *  
 * @author Faisal Feroz
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public abstract class AbstractFMPPDataLoaderTest {
	
	public static final File TEMPLATES_DIRECTORY = new File("src/test/resources/fmpp/");
	public static final File OUTPUT_DIRECTORY = new File("target/test/generated-sources/fmpp/");
	
	protected Settings fmpp;
	protected File cfgFile;

	@Before
	public void setUp() throws Exception {
		
		if ( cfgFile == null ) throw new IllegalStateException( "Please initialize the cfgFile location in the Constructor of the Test Case." );
		
		fmpp = new Settings( new File(".") );
		fmpp.set( "sourceRoot", TEMPLATES_DIRECTORY.getAbsolutePath() );
		fmpp.set( "outputRoot", OUTPUT_DIRECTORY.getAbsolutePath() );
		
		fmpp.load( cfgFile );
		fmpp.addProgressListener( new ConsoleProgressListener() );		
	}

	@After
	public void tearDown() throws Exception {
		cleanDirectory(OUTPUT_DIRECTORY);
	}
	
	private void cleanDirectory(final File directory) {
		if (directory != null) {
			File[] files = directory.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						cleanDirectory( file );
					} else {
						file.delete();
					}
				}
			}
			directory.delete();
		}
	}
}
