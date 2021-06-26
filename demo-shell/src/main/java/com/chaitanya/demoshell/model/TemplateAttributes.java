package com.chaitanya.demoshell.model;

import com.chaitanya.demoshell.annotation.Column;
import com.chaitanya.demoshell.annotation.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(schema = "SAMPLE", table = "TEMPLATE_ATTR")
public class TemplateAttributes implements Entity {

	@Column(name = "TEMPALTE_ATTR_PK", isId = true)
	private String id;
	
	@Column(name = "TEMPLATE_ID_FK", isId = true)
	private String templateId;
	
	@Column(name = "TEMPLATE_ATTR_NAME")
	private String name;
	
	@Override
	public String getIdentifier() {
		return String.format("(SELECT TEMPALTE_ATTR_PK FROM SAMPLE.TEMPLATE_ATTR WHERE TEMPLATE_ID_FK='%s')", this.templateId);
	}

	@Override
	public String treeTitle() {
		return this.getClass().getSimpleName()+"-"+this.name;
	}
}
