package blue.bran.json;

import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class JSONTranslator<T> {

	final T t;

	public JSONTranslator(String jsonFile, Class<T> type0) {
		this.t = new GsonBuilder().create().fromJson(getFileFromResource(jsonFile), type0);
	}

	public T t() {
		return t;
	}


	public static String getFileFromResource(String fileName) {
		try {
			return getFile(new File(JSONTranslator.class.getResource("/" + fileName).toURI()));
		} catch (URISyntaxException e) {
			throw new RuntimeException("Couldn't find that file / resources");
		}
	}

	public static void outputFile(String fileName, String contents) {
		try {
			String path = JSONTranslator.class.getResource("/output/").getPath();
			File file = new File(path + fileName);
			if (file.exists()) {
				System.out.println("> You may be overriding another file with the same name <");
			}
			file.createNewFile();
			try (OutputStream fos = Files.newOutputStream(file.toPath());
				 OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				 BufferedWriter writer = new BufferedWriter(osw)) {
				writer.write(contents);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static String getFile(File file) {
		try (FileInputStream is = new FileInputStream(file);
			 Reader in = new InputStreamReader(is, StandardCharsets.UTF_8);
			 BufferedReader br = new BufferedReader(in)) {
			return br.lines().collect(Collectors.joining("\n"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
