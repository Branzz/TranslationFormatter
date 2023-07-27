package blue.bran.json;

public class PaliFormatter {

	final String formatOrder;
	final String[] format;
	final String innerFormatOrder;
	final String[] innerFormat;
	final int[] rearranger;

	public PaliFormatter(String formatOrder, String[] format, String innerFormatOrder, String[] innerFormat) {
		this.formatOrder = formatOrder;
		this.format = format;
		this.innerFormatOrder = innerFormatOrder;
		this.innerFormat = innerFormat;
		rearranger = formatOrder.chars().filter(Character::isDigit).map(c -> c - '0').toArray();
	}

	public String fillFormat(boolean inner, Object... args) {
		String reformatted = inner ? innerFormatOrder : formatOrder;
		for (int i = 0; i < rearranger.length; i++) {
			reformatted = getFormatAt(reformatted, i, inner, args);
		}
		return reformatted;
	}

	public String getFormatAt(String result, int ind, boolean inner, Object... args) {
		int rearrangedInd = rearranger[ind] - 1;
		return result.replace(String.valueOf(rearrangedInd + 1), args[rearrangedInd] == null ? "" : (inner ? innerFormat : format)[ind].replace("{}", args[rearrangedInd].toString()));
	}
}
