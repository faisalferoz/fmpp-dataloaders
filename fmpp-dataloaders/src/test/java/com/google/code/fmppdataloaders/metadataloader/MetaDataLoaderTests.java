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

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static junit.framework.Assert.*;

import com.google.code.fmppdataloaders.AbstractFMPPDataLoaderTest;

public class MetaDataLoaderTests extends AbstractFMPPDataLoaderTest {
	
	public MetaDataLoaderTests() {
		cfgFile = new File( "src/test/resources/metadataloader/" );
	}

	@Test
	public void testMetaDataLoader() throws Exception {
		
		// execute the template engine
		fmpp.execute();
		
		assertTrue( OUTPUT_DIRECTORY.exists() );

		List<String> files = Arrays.asList( OUTPUT_DIRECTORY.list() );
		assertTrue( files.size() > 0);
		
		assertTrue( "Output file [AllNumeric.java] not created.", files.contains( "AllNumeric.java" ) );
		assertTrue( "Output file [AllChar.java] not created.", files.contains( "AllChar.java" ) );
		assertTrue( "Output file [AllLob.java] not created.", files.contains( "AllLob.java" ) );
		assertTrue( "Output file [AllDate.java] not created.", files.contains( "AllDate.java" ) );
		assertTrue( "Output file [AllDatatype.java] not created.", files.contains( "AllDatatype.java" ) );
		assertTrue( "Output file [ArchiveDatat.java] not created.", files.contains( "ArchiveData.java" ) );
	}
}
