package rest.markets.utils;

import java.io.*;
import java.net.*;

/**
 * This runnable class downloads the file
 */
public class Download implements Runnable{
	
	private String link;
	private File out;
	
	// Constructor
	public Download(String link, File out) {
		this.link = link;
		this.out = out;
	}

	/**
	 * This method downloads the file when the thread is launched.
	 */
	@Override
	public synchronized void run() {
		
		try {
			
			// Connection to URL and opening an input stream
			URL url = new URL(link);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			BufferedInputStream in = new BufferedInputStream(http.getInputStream());
			
			// Method to read content length 
			double fileSize = (double)http.getContentLengthLong();
			
			// Creating a new output stream whit a 1024 Byte buffer
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(this.out), 1024);
			byte[] buffer = new byte[1024];
			
			// Downloading file 1024 byte each time
			int read = 0;
			while((read = in.read(buffer, 0, 1024)) >= 0) {
				bout.write(buffer, 0, read);
			}
			
			// Download complete message
			if(fileSize != -1) {
				System.out.println("Download complete! " + fileSize + " Byte scaricati");
			}
			else System.out.println("Download complete!");
			
			// Stream close
			in.close();
			bout.close();
			
		}
		catch(IOException e) {
			System.err.println("Download IO Exception");
			e.printStackTrace();
		}
	}
}
