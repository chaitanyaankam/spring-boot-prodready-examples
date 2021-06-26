package com.chaitanya.demoshell.model;

import java.util.List;


import com.chaitanya.demoshell.annotation.One2One;
import com.chaitanya.demoshell.annotation.Column;
import com.chaitanya.demoshell.annotation.One2Many;
import com.chaitanya.demoshell.annotation.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(schema = "SAMPLE", table = "TEMPLATE")
public class Template implements Entity {

	@Column(name = "TEMPALTE_PK", isId = true)
	private String id;
	
	@Column(name = "TEMPLATE_NAME")
	private String name;
	
	@One2One
	private TemplateAttributes templateAttributes;
	
	@One2Many
	private List<Question> questions;
	
	@Override
	public String getIdentifier() {
		return String.format("(SELECT TEMPALTE_PK FROM SAMPLE.TEMPLATE WHERE TEMPLATE_NAME='%s')", this.name);
	}

	@Override
	public String treeTitle() {
		return this.getClass().getSimpleName()+"-"+this.getName();
	}
}
