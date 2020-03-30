package nest.hava.edutills.tokenizer;

import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import scala.collection.Seq;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class OpenKoreanTextTokenizer {

	public static List<String> tokenize(String str) {

		HashSet<String> results = new HashSet<String>();

		CharSequence normalized = OpenKoreanTextProcessorJava.normalize(str);
		Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
		List<KoreanPhraseExtractor.KoreanPhrase> phrases = OpenKoreanTextProcessorJava.extractPhrases(tokens, true, true);

		for (KoreanPhraseExtractor.KoreanPhrase a : phrases) {
			results.add(a.text());
		}
		return new ArrayList<String>(results);

	}


	public static void tokenize(String str,HashSet<String> dataList) {

		CharSequence normalized = OpenKoreanTextProcessorJava.normalize(str);
		Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
		/*List<String> tList = TwitterKoreanProcessorJava.tokensToJavaStringList(tokens);

		for (String t : tList) {
			
			if(t.length()>1)
			{
				dataList.add(t.toUpperCase());
			}
			
		}*/

		List<KoreanPhraseExtractor.KoreanPhrase> phrases = OpenKoreanTextProcessorJava.extractPhrases(tokens, true, true);

		for (KoreanPhraseExtractor.KoreanPhrase a : phrases) {

			if(a.text().length()>1)
			{
				if(!dataList.contains(a.text().toLowerCase()))
				{
					dataList.add(a.text().toLowerCase());
				}

			}

		}

	}

}
