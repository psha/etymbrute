package eu.psha.etymbrute.wikitext;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.psha.etymbrute.wikitext.TemplateParser.ITemplateHandler;
import eu.psha.etymbrute.wikitext.TemplateParser.Template;

public class EBWikiText {

	private static List<String> ex_etym = Arrays.asList(	"From an [[orthographic]] variant of {{term|etc.|lang=en}}, which used the [[ligature]] of ''et'', {{term|&|lang=en}}.",
													"''[[&]]'' + ''lit'', a shortening of ''[[literal]]'' or ''[[literally]]''.",
													"A common shortening of ''[[2000s]]''.",
													"{{suffix|gate|er}}",
													"A representation of the pronunciation of ''[[haircut]]'' by a speaker whose dialect lacks the [[w:Voiceless glottal fricative|voiceless glottal fricative or transition]] ({{IPA|lang=en|[h]}}).",
													"A representation of the pronunciation of ''[[half]]'' by a speaker whose dialect lacks the [[w:Voiceless glottal fricative|voiceless glottal fricative or transition]] ({{IPA|lang=en|[h]}}).");
	
	private List<String> ex_pro = Arrays.asList(	"IPA: /ˈeɪs ɪnˌhɪb.ɪ.tɚ/ {{US}}\r\n",
													"IPA: /ˈɑːɡə kʊkə(ɹ)/ {{}}\r\n",
													"IPA: /ˌeɪkeɪfɔːtiˈsevən/ {{RP}}\r\n",
													"IPA: /ˈɔːlˌbɔː(ɹ)/|/ˈɔːlˌbɔːɡ/ {{UK}}\r\nIPA: /ˈɔlˌbɔɹ/|/ˈɔlˌbɔɹɡ/ {{US}}\r\n",
													"");

	public EBWikiText() {
		//String res = TemplateParser.parse(ex_etym.get(0), new TemplateParser.EtymologyTemplateHandler());
		Template t = TemplateParser.parseTemplate(ex_etym.get(0));
		for(Entry<String, String> e: t.getNamedParams()){
			System.out.println(e.getKey() + ": " + e.getValue());
		}
		//System.out.println(res);
	}

	public static void main(String[] args) {
		for(String i : ex_etym){
			System.out.println("Pre:  " + i);
			System.out.println("Post: " + fixEtym(i));
		}
	}
	
	//match and replace
	private static String mAndR(String input, String pattern, String replacement){
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(input);
		return m.replaceAll(replacement);
	}
	
	public static String fixEtym(String s){
		//[[w:this|that]]
		s = mAndR(s, "\\[\\[w:(.*?)\\|(.*?)]]", "$2");
		
		////[[w:this]]
		s = mAndR(s, "\\[\\[w:(.*?)]]", "$1");
		
		//[[this]]
		s = mAndR(s, "\\[\\[(.*?)]]", "$1");
		
		//{{term|bla|lang=bla}}
		s = mAndR(s, "\\{\\{term\\|(.*?)\\|.*?\\}\\}", "$1");
		
		//{{IPA|lang=en|[h]}}
		s = mAndR(s, "\\{\\{IPA\\|(.*?)\\|lang=.*?\\}\\}", "$1");
		
		return s;
	}
	
	private Pro fixPro(String raw) {
		return null;
	}
	
	private static class Pro {
		String type;
		String pro;
		String gloss;
	}
	
	private String replacePattern1(Matcher m){
		return null;
	}
}
