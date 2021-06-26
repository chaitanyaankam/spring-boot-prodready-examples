package com.chaitanya.demoshell.sql;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chaitanya.demoshell.model.Entity;
import com.chaitanya.demoshell.model.Template;

@Component
public class TreeBuilder {
	
	@Autowired
	private Terminal terminal;

	public void build(Template template) {
		TreeNode<Entity> root = new TreeNode<Entity>(template);
		root.addChild(template.getTemplateAttributes());
		template.getQuestions().forEach(q->root.addChild(q));
		terminal.writer().print(TreeNode.renderDirectoryTree(root));
	}
}
