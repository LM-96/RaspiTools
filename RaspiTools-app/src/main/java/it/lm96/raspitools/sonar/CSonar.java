package it.lm96.raspitools.sonar;

import java.io.BufferedReader;
import java.io.IOException;

import it.lm96.raspitools.utils.ObservedBufferedReader;

public class CSonar implements PiSonar {
	
	private BufferedReader reader;
	
	private long procPid;
	
	public CSonar(String sonarAlone, int trigPin, int echoPin) throws IOException {
		Process proc = Runtime.getRuntime().exec(sonarAlone.trim() + " -t " + trigPin + " -e " + echoPin + " -i");
		procPid = proc.pid();
		
		reader = ObservedBufferedReader.observe(proc.getInputStream());
		
	}

	@Override
	public double readDistanceInCm() throws InterruptedException, IOException {
		
		Runtime.getRuntime().exec("kill -10 " + procPid);
		
		String line;
		while(!(line = reader.readLine()).contains("distance"));
		
		return Double.parseDouble(line.split(":")[1].trim());
	}

}
