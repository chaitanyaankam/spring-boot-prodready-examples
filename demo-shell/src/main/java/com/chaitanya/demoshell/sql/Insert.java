package com.chaitanya.demoshell.sql;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Insert {

	private String table;
	
	private final Set<String> columns;
	
	private final Collection<String> values;
	
	private final SQLContext context;
	
	public void builSQL() {
		this.context.append("INSERT INTO ")
		.append(this.table)
		.append(" (")
		.append(StringUtils.join(columns, ", "))
		.append(")")
		.newLine()
		.append("VALUES (")
		.append(StringUtils.join(values, ", "))
		.append(")")
		.newLine()
		.newLine();
	}
}
