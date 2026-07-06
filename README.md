# YouTube-to-MP3 Converter

This YouTube-to-MP3 Converter is a Java project I created that lets users convert the audio from a YouTube video into an MP3 file.

I originally made this project to practice working with Java beyond basic console programs. I wanted to learn how Java could interact with webpages, external command-line tools, and files on the user's computer.

## How It Works

The program starts by asking the user to enter a YouTube URL. Once a URL is provided, it connects to the video's webpage and retrieves the video's title.

The program then uses **yt-dlp** to download the best available audio from the video. After the audio is downloaded, **FFmpeg** converts the file into MP3 format. The temporary audio file is then deleted, leaving only the finished MP3 file.

The final MP3 is automatically named after the YouTube video's title.

The process works like this:

1. The user enters a YouTube URL.
2. The program retrieves the video's title.
3. `yt-dlp` downloads the best available audio.
4. FFmpeg converts the audio into an MP3 file.
5. The temporary file is deleted.
6. The finished MP3 is saved using the video's title.

## How I Made It

I created this converter in **Java** using **Eclipse**.

The main `Driver` class handles the user input and connects to the YouTube webpage to retrieve the video's title. I used Apache Commons Text to decode HTML characters that may appear in video titles.

I created a separate `mp3maker` class to handle the actual downloading and conversion process. The program uses Java's `ProcessBuilder` to run `yt-dlp` and FFmpeg directly from the command line.

One of the more interesting parts of this project was learning how to connect a Java program with external tools instead of trying to handle the entire process inside Java itself. I also added operating-system detection so the program could use the correct command-line shell depending on whether it is running on Windows, macOS, or Linux.

This project helped me learn more about:

* Running external programs from Java
* Working with `ProcessBuilder`
* Reading information from webpages
* Handling files and temporary files
* Organizing code into separate classes
* Working with third-party libraries and command-line tools

## Built With

* Java
* Eclipse IDE
* yt-dlp
* FFmpeg

## Requirements

Before running the project, the following tools need to be installed:

* Java
* yt-dlp
* FFmpeg
* Apache Commons Text
* Apache Commons Lang

`yt-dlp` and `ffmpeg` must also be accessible from the system command line.

## How to Use It

1. Clone or download the repository.
2. Open the project in Eclipse.
3. Make sure the required dependencies and tools are installed.
4. Run `Driver.java`.
5. Paste a YouTube URL when prompted.
6. Wait for the download and conversion process to finish.
7. The completed MP3 file will be saved using the video's title.

## Disclaimer

I created this project for **educational purposes only** as a way to learn more about Java, web requests, process execution, file handling, and working with external command-line tools.

This project is not affiliated with, endorsed by, or sponsored by YouTube or Google.

Users are responsible for making sure they have the right to download or convert any content they access. Only use this project with content you own, content in the public domain, or content you have permission to use. I am not responsible for any misuse of this software.
