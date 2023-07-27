package blue.bran.json;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Phrase {

	String name;
	List<WordTranslation> words = new ArrayList<>();

	public String toString(PaliFormatter paliFormatter) {
		return paliFormatter.formattedName(name) +
			               words.stream()
								.map(w -> w.toString(paliFormatter, false))
								.collect(Collectors.joining(""));
	}

}
