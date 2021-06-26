package com.chaitanya.demoshell.sql;

import org.jline.terminal.Terminal;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SQLContext {

	private StringBuffer sql;

	private static final String NEW_LINE = System.getProperty("line.separator");

	public SQLContext append(String s) {
		if (sql == null)
			sql = new StringBuffer();
		sql.append(s);
		return this;
	}

	public SQLContext newLine() {
		if (sql == null)
			sql = new StringBuffer();
		sql.append(NEW_LINE);
		return this;
	}

	public void clear() {
		sql.setLength(0);
	}

	public void display(Terminal console) {
		console.writer().print(sql);
		this.clear();
	}

}
