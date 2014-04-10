package org.igels.asciidoc.impl;

import java.io.InputStream;

import org.asciidoctor.internal.AsciidoctorModule;
import org.asciidoctor.internal.IOUtils;
import org.asciidoctor.internal.RubyUtils;
import org.jruby.Ruby;
import org.jruby.RubyRuntimeAdapter;
import org.jruby.javasupport.JavaEmbedUtils;

// A copy of org.asciidoctor.internal.JRubyAsciidoctorModuleFactory used to fix https://github.com/asciidoctor/asciidoctorj/issues/102 
class JRubyAsciidoctorModuleFactory {

	//hack: to fix problem with copycss this should change in future.
	protected RubyRuntimeAdapter evaler;
	private Ruby runtime;
	
	public JRubyAsciidoctorModuleFactory(Ruby runtime) {
		this.runtime = runtime;
		this.evaler = JavaEmbedUtils.newRuntimeAdapter();
	}
	
	public AsciidoctorModule createAsciidoctorModule() {
		//This piece of code will be changed in future when asciidoctor gem implements a class instead of a module.
		String script = loadAsciidoctorRubyClass();
		evaler.eval(runtime, script);
		
		Object rfj = evaler.eval(runtime, "AsciidoctorModule.new()");
		
		return RubyUtils.rubyToJava(runtime, (org.jruby.runtime.builtin.IRubyObject) rfj, AsciidoctorModule.class);
		
	}
	
	private String loadAsciidoctorRubyClass() {
		InputStream inputStream = AsciidoctorModule.class.getResourceAsStream("asciidoctorclass.rb");
		return IOUtils.readFull(inputStream);
	}
	
	public Ruby runtime() {
		return this.runtime;
	}
}