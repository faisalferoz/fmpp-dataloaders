<#list tables as table>
<@pp.changeOutputFile name=table.titleCaseName+".java" />

import java.sql.*;

/**
 * This is an auto generated class. Use the generate-sources phase 
 * to generate the latest code. Never commit these files in version control system.
 * 
 * For testing purposes only.
 * 
 * @author Faisal Feroz
 */
public class ${table.titleCaseName} {
  <#list table.attributes as attribute >
  
	private ${attribute.shortType} ${attribute.camelCaseName};
 
	public void set${attribute.titleCaseName}( ${attribute.type} ${attribute.camelCaseName} ) {
		this.${attribute.camelCaseName} = ${attribute.camelCaseName};
	}
	public ${attribute.type} get${attribute.titleCaseName}() {
		return ${attribute.camelCaseName};
	}
 </#list>
} 
</#list>