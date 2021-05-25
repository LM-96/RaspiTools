package it.lm96.raspitools.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.spf4j.io.PipedOutputStream;
import org.spf4j.io.PipedOutputStream.PipedInputStream;

public class ObservedBufferedReader {
	
	public static BufferedReader observe(InputStream inputStream) throws IOException {
		return (new ObservedBufferedReader(inputStream)).getBufferedReader();
	}
	
	public static BufferedReader observe(BufferedReader reader) throws IOException {
		return (new ObservedBufferedReader(reader)).getBufferedReader();
	}
	
	private BufferedReader original;
	private BufferedReader pipedIn;
	private PrintStream pipedOut;;
	
	public ObservedBufferedReader(BufferedReader original) throws IOException {
		this.original = original;
		
		PipedOutputStream pos = new PipedOutputStream();
		
		pipedIn = new BufferedReader(new InputStreamReader(pos.getInputStream()));
		pipedOut = new PrintStream(pos);
		
		startRedirect();
	}
	
	public ObservedBufferedReader(InputStream original) throws IOException {
		this(new BufferedReader(new InputStreamReader(original)));
	}
	
	public BufferedReader getBufferedReader() {
		return pipedIn;
	}
	
	private void startRedirect() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String line;
				try {
					while((line = original.readLine()) != null) {
						System.out.println(line);
						
						pipedOut.println(line);
						pipedOut.flush();
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}

}
