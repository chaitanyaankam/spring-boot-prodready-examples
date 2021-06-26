package com.chaitanya.demoshell.commands;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;

@Component
public class BooleanValueProvider implements ValueProvider {

	@Override
	public boolean supports(MethodParameter parameter, CompletionContext completionContext) {
		return true;
	}

	@Override
	public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext,
			String[] hints) {
		return Stream.of("true", "false")
		.map(s -> new CompletionProposal(s))
		.collect(Collectors.toList());
	}

}
