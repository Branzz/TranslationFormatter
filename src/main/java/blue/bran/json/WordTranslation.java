package blue.bran.json;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WordTranslation {

	String word;
	String type;
	List<WordTranslation> parts = new ArrayList<>();
	String translation;
	String synonyms;

	public String toString(PaliFormatter paliFormatter, boolean inner) {
		return paliFormatter.fillFormat(inner, word, type,
										parts.size() == 0 ? null : parts.stream()
																		.map(p -> p.toString(paliFormatter, true))
																		.collect(Collectors.joining(" + ")),
										translation, synonyms);
	}

}
