package com.chaitanya.demoshell.commands;

import java.util.ArrayList;
import java.util.List;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.chaitanya.demoshell.model.Question;
import com.chaitanya.demoshell.model.Template;
import com.chaitanya.demoshell.model.TemplateAttributes;
import com.chaitanya.demoshell.sql.InsertBuilder;
import com.chaitanya.demoshell.sql.SQLContext;
import com.chaitanya.demoshell.sql.TreeBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ShellComponent
public class EchoCommand {
	
	@Autowired
	private Terminal terminal;
	
	@Autowired
	private TreeBuilder treeBuilder;
	
	@Autowired
	private SQLContext sqlContext;
	
	@Autowired
	private InsertBuilder insertBuilder;
	
	@ShellMethod(key = {"echo"}, value = "echo command")
	public void echoCommand(@ShellOption(valueProvider = StringValueProvider.class) String value) {
		log.info("value is {}",value);
	}
	
	@ShellMethod(key = {"sql"}, value = "sql command")
	public void sqlCommand(@ShellOption(valueProvider = BooleanValueProvider.class) String cascade) {
		TemplateAttributes templateAttributes = TemplateAttributes.builder()
				.name("chaitanya2").id("(Q1 || (select max(seq+1))").build();
		Question question1 = Question.builder().name("How are you?").id("(Q1 || (select max(seq+1))").build();
		Question question2 = Question.builder().name("chaitanya2").id("(Q1 || (select max(seq+1))").build();
		
		List<Question> questions = new ArrayList<>();
		questions.add(question1);
		questions.add(question2);
		
		Template template = Template.builder().name("chaitanya").id("(Q || (select max(seq+1))")
				.templateAttributes(templateAttributes)
				.questions(questions)
				.build();
		
		templateAttributes.setTemplateId(template.getIdentifier());
		question1.setTemplateId(template.getIdentifier());
		question2.setTemplateId(template.getIdentifier());
		
		insertBuilder.build(template, Boolean.parseBoolean(cascade));
		sqlContext.display(terminal);
		treeBuilder.build(template);
	}

}
