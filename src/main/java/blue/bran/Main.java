package blue.bran;

import blue.bran.json.JSONTranslator;
import blue.bran.json.PaliFormatter;
import blue.bran.json.Phrase;

public class Main {

	public static void main(String[] args) {

		// translate("Deathless Element Meditation.json", "Transliteration of Deathless Element Meditation.txt", transliteration);

		translate("Deathless Element Meditation.json", "Deathless.html", html);
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
	 * the Strings on the right are included if the json field was found
	 * the second line is the format for the inner word parts
	 */
	static final PaliFormatter full = new PaliFormatter(
			"132\n45\n", new String[] { "**{}**", " - {}", " {}", "__{}__", " / {}" },
			"14532", new String[] { "{}", " {}", "/ {} ", "{} ", "{}" }
	);
	static final PaliFormatter english = new PaliFormatter(
			"4", new String[] { "{} ", "", "", "", "" },
			"", new String[] { "", "", "", "", "" }
	);
	static final PaliFormatter parts = new PaliFormatter(
			"13", new String[] { "{} ", "({}) ", "", "", "" },
			"13", new String[] { "{}", " ({})", "", "", "" }
	);
	static final PaliFormatter transliteration = new PaliFormatter(
			"14\n", new String[] { "{}", " {}", "", "", "" },
			"", new String[] { "", "", "", "", "" }
	);
	static final PaliFormatter plain = new PaliFormatter(
			"1", new String[] { "{} ", "", "", "", "" },
			"", new String[] { "", "", "", "", "" }
	);
	static final PaliFormatter spoiler = new PaliFormatter( // tool for memorizing
			"1 ", new String[] { "||{}||", " ", "", "", "" },
			"", new String[] { "", "", "", "", "" }
	);
	static final PaliFormatter html = new PaliFormatter(
			"<div>132<br>45</div><br>\n", new String[] { "<strong>{}</strong>", " - {}", " {}", " <underline>{}</underline>", " / {}" },
			"<small id=\"parts\">14532</small>", new String[] { " {}", " {}", " / {}", " {}", " {}" },
			"<h2>{}</h2>\n<style> *{ font-family: Noto, \"EB Garamond\", \"Source Pro\", \"Merriweather Sans\", serif; } #parts {  } </style>\n<br>\n"
	);
}
