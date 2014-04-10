package org.igels.asciidoc;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.asciidoctor.Asciidoctor;
import org.igels.asciidoc.impl.JRubyAsciidoctor;

@ManagedBean(eager=true)
@SessionScoped
public class Converter {

	static Asciidoctor asciidoctor = JRubyAsciidoctor.create();
/*
This is a simple cloud-enabled http://www.methods.co.nz/asciidoc[AsciiDoc] viewer.

  - Type some AsciiDoc text in the left window
  - See the HTML in the right

mailto:asciidocigels@gmail.com[Email me if any comments]

Here is a few examples of AsciiDoc format:

== Headers

== Level 1
Text.

=== Level 2
Text.

==== Level 3
Text.

===== Level 4
Text.

== Blocks

.Optional Title
[source,perl]
----
# *Source* block
# Use: highlight code listings
# (require `source-highlight` or `pygmentize`)
use DBI;
my $dbh = DBI->connect('...',$u,$p)
    or die "connect: $dbh->errstr";
----

== Text

normal, _italic_, *bold*, +mono+.

``double quoted'', `single quoted'.

normal, ^super^, ~sub~.
 */

	private static String DEFAULT = "This is a simple cloud-enabled http://www.methods.co.nz/asciidoc[AsciiDoc] viewer.\n\n" + 
			"- Type some AsciiDoc text in the left window\n\n" + 
			"- See the HTML in the right\n\n" +
			"mailto:asciidocigels@gmail.com[Email me if any comments]\n\n" +
			"Here is a few examples of AsciiDoc format:\n\n" +
			"== Headers\n\n" +
			"== Level 1\n" +
			"Text.\n\n" +
			"=== Level 2\n" +
			"Text.\n\n" +
			"==== Level 3\n" +
			"Text.\n\n" +
			"===== Level 4\n" +
			"Text.\n\n" +
			"== Blocks\n\n" +
			".Optional Title\n" +
			"[source,perl]\n" +
			"----\n" +
			"# *Source* block\n" + 
			"# Use: highlight code listings\n" +
			"# (require `source-highlight` or `pygmentize`)\n" +
			"use DBI;\n" +
			"my $dbh = DBI->connect('...',$u,$p)\n" +
			"    or die \"connect: $dbh->errstr\";\n" +
			"----\n\n" +
			"== Text\n\n" +
			"normal, _italic_, *bold*, +mono+, ``double quoted'', `single quoted', normal, ^super^, ~sub~.";
	private String text = DEFAULT;
	private String html = "";

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if(text!=null) {
			this.text = text;
		} else {
			this.text = DEFAULT;
		}
	}

	public String getHtml() {
		html = asciidoctor.render(text, new HashMap<String, Object>());
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String convert() {
		return "preview";
	}
}