package eu.psha.etymbrute.wikitext;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.psha.etymbrute.wikitext.TemplateParser.ITemplateHandler;
import eu.psha.etymbrute.wikitext.TemplateParser.Template;

public class EBWikiText {

	private List<String> ex_etym = Arrays.asList(	"From an [[orthographic]] variant of {{term|etc.|lang=en}}, which used the [[ligature]] of ''et'', {{term|&|lang=en}}.",
													"''[[&]]'' + ''lit'', a shortening of ''[[literal]]'' or ''[[literally]]''.",
													"A common shortening of ''[[2000s]]''.",
													"{{suffix|gate|er}}",
													"A representation of the pronunciation of ''[[haircut]]'' by a speaker whose dialect lacks the [[w:Voiceless glottal fricative|voiceless glottal fricative or transition]] ({{IPA|lang=en|[h]}}).",
													"A representation of the pronunciation of ''[[half]]'' by a speaker whose dialect lacks the [[w:Voiceless glottal fricative|voiceless glottal fricative or transition]] ({{IPA|lang=en|[h]}}).");
	
	private List<String> ex_pro = Arrays.asList(	"anglicised pronunciation: lang=en|/kʌŋ/,",
													"lang=en|/kɔz/,UK: lang=en|/kɒz/,US: lang=en|/kʌz/,",
													"ɛləʊ,",
													"/əm/|/m̩/|/ɪm/,");

	public EBWikiText() {
		//String res = TemplateParser.parse(ex_etym.get(0), new TemplateParser.EtymologyTemplateHandler());
		Template t = TemplateParser.parseTemplate(ex_etym.get(0));
		for(Entry<String, String> e: t.getNamedParams()){
			System.out.println(e.getKey() + ": " + e.getValue());
		}
		//System.out.println(res);
	}

	public static void main(String[] args) {
		
		
	}

	public String fixWikitext(String s){
		
		
		Pattern p1 = Pattern.compile("\\G\\[\\[(.*?)]]");
		Pattern p2 = Pattern.compile("\\G{{(.*?)\\|(.*?)\\|(.*?)}}");
		
		//pattern1 [[this type]]
		Matcher m = p1.matcher(s);
		int lastMatchPos = 0;
		while (m.find()) {
		   System.out.println(m.group(1));
		   System.out.println(m.group(2));
		   lastMatchPos = m.end();
		}
		
		return null;
	}
	
	private String replacePattern1(Matcher m){
		return null;
	}
}
