import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.commons.text.StringEscapeUtils;

public class Driver {

	private static final Pattern HTML_ENTITY_PATTERN = Pattern.compile("&[a-zA-Z0-9#]+;");

	public static boolean containsHtmlSymbol(String link) {
		return HTML_ENTITY_PATTERN.matcher(link).find();
	}

	public static void main(String[] args) throws Exception {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter your youtube link.");
			String url = sc.nextLine();
			String title = urlReader(url);
			
			mp3maker m = new mp3maker(title, url);
			m.print();
			
			m.downloadAndConvertToAudio();
		}
	}

	public static String urlReader(String link) throws Exception {
		@SuppressWarnings("deprecation")
		URL url = new URL(link);
		String title = null;

		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		BufferedReader read = new BufferedReader(new InputStreamReader(con.getInputStream() ) );
		boolean found = false;
		String line;
		
		while (!found && (line = read.readLine() ) != null) {

			if (line.contains("<title>") ) {
				found = true;

				title = line.substring(line.indexOf("<title>") + 7, line.indexOf(" - YouTube</title>") );
				
				if (containsHtmlSymbol(title) ) {
					title = StringEscapeUtils.unescapeHtml4(title);
				}
			}
		}
		if (title == null) {
			title = "No title found";
		}
		return title;
	}

}
