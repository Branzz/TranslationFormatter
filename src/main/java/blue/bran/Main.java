package blue.bran;

import blue.bran.json.JSONTranslator;
import blue.bran.json.PaliFormatter;
import blue.bran.json.Phrase;

public class Main {

	public static void main(String[] args) {

		translate("Deathless Element Meditation.json", "Transliteration of Deathless Element Meditation.txt", transliteration);

	}

	public static void translate(String jsonFileName, String outputFileName, PaliFormatter formatter) {
		JSONTranslator<Phrase> jt = new JSONTranslator<>(jsonFileName, Phrase.class);
		String translation = translate(jt, formatter);
		System.out.println(translation);
		JSONTranslator.outputFile(outputFileName, translation);
	}

	static String translate(JSONTranslator<Phrase> translator, PaliFormatter formatter) {
		return translator.t().toString(formatter);
	}

	/**
	 * some example formatters
	 * <p>
	 * the numbers on the left is the order of json terms specified in WordTranslation's fields
	 * not all are necessary, and they can go in any order
	 * the second line is the format for the inner word parts. This won't show if there aren't any word parts
	 */
	static final PaliFormatter fullDisplay = new PaliFormatter(
			"132\n45\n", new String[] { "**{}**", " - {}", " {}", "__{}__", " / {}" },
			"14532", new String[] { "{}", " {}", "/ {} ", "{} ", "{}" }
	);
	static final PaliFormatter english = new PaliFormatter(
			"4", new String[] { "{} ", "", "", "", "" },
			"", new String[] { "", "", "", "", "" }
	);
	static final PaliFormatter paliParts = new PaliFormatter(
			"13", new String[] { "{} ", "({}) ", "", "", "" },
			"13", new String[] { "{}", " ({})", "", "", "" }
	);
	static final PaliFormatter transliteration = new PaliFormatter(
			"14\n", new String[] { "{}", " {}", "", "", "" },
			"", new String[] { "", "", "", "", "" }
	);
	static final PaliFormatter pali = new PaliFormatter(
			"1", new String[] { "{} ", "", "", "", "" },
			"", new String[] { "", "", "", "", "" }
	);
	static final PaliFormatter cover = new PaliFormatter( // spoilers on each basic term for memorizing
			"1 ", new String[] { "||{}||", " ", "", "", "" },
			"", new String[] { "", "", "", "", "" }
	);
}
