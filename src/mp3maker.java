import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class mp3maker {

	private String title;
	private String vidUrl;

	public mp3maker(String title, String vidUrl) {
		this.title = title; 
		this.vidUrl = vidUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void downloadAndConvertToAudio() throws IOException {
		String tempAudioName = "audioFile.m4a";

		deleteTempIfExists(tempAudioName);

		downloadAudio(tempAudioName);

		String outputAudioName = title + ".mp3";
		convertToMp3(tempAudioName, outputAudioName);

		deleteTempIfExists(tempAudioName);

		System.out.println("Successfully created MP3: " + outputAudioName);

	}

	public void downloadAudio(String tempAudioFile) throws IOException {
		System.out.println("Downloading MP3 from..." + vidUrl);
		String command = "yt-dlp -f bestaudio --extract-audio --audio-format m4a --output \"" + tempAudioFile + "\" "
				+ vidUrl;

		runCommand(command);
	}

	public void convertToMp3(String inputFileName, String outputFileName) throws IOException {
		
		String command = "ffmpeg -i \"" + inputFileName + "\" -q:a 0 -map a \"" + outputFileName + "\"";
		runCommand(command);
	}

	private static void runCommand(String command) throws IOException {

		String[] cmd = null;

		if (isWindows()) {
			cmd = new String[] { "cmd.exe", "/c", command };
		} else if (isUnix()) {
			cmd = new String[] { "/bin/sh", "-c", command };
		}

		ProcessBuilder processBuilder = new ProcessBuilder(cmd);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

	private static boolean isUnix() {
		String osName = System.getProperty("os.name").toLowerCase();

		return osName.contains("nix") || osName.contains("nux") || osName.contains("mac");
	}

	private static void deleteTempIfExists(String tempFilePath) throws IOException {
		Path path = Paths.get(tempFilePath);
		if (Files.exists(path)) {
			Files.delete(path);
			System.out.println("Deleted temp file");
		}

	}

	public void print() {
		if (title == null || title.isEmpty()) {
			System.out.println("Title could not be retrieved.");

		} else {
			System.out.println("Name of youtube video: " + title);
		}
	}

}
