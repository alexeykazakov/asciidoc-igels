package org.igels.asciidoc.impl;

//A copy of org.asciidoctor.internal.EnvironmentInjector used to fix https://github.com/asciidoctor/asciidoctorj/issues/102
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jruby.Ruby;

class EnvironmentInjector {

	private Ruby runtime;
	
	EnvironmentInjector(Ruby runtime) {
		this.runtime = runtime;
	}
	
	void inject(Map<String, Object> environmentVars) {
		
		Set<Entry<String, Object>> environmentVariablesAndValues = environmentVars.entrySet();
		
		for (Entry<String, Object> entry : environmentVariablesAndValues) {
			runtime.evalScriptlet(String.format("ENV['%s']=\"%s\"", entry.getKey(), entry.getValue()));
		}
		
	}
	
}