package it.lm96.raspitools.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class FileConfigReader implements ConfigReader {
	
	public static ConfigReader newFileConfigReader(String filePath) {
		return new FileConfigReader(filePath);
	}
	
	private static final String CRITICAL_DISTANCE_KEY = "CRITICAL_DISTANCE";
	private static final String DELAY_MILLIS_KEY = "DELAY_MILLIS";
	private static final String LED_PIN_KEY = "LED_PIN";
	private static final String TRIG_PIN_KEY = "TRIG_PIN";
	private static final String ECHO_PIN_KEY = "ECHO_PIN";
	private static final String SONAR_ALONE_KEY = "SONARALONE";
	private static final String LED_ALONE_KEY = "LEDALONE";
	
	private Path file;
	private Map<String, String> configs;
	
	public FileConfigReader(String filePath) {
		this.file = Paths.get(filePath);
		configs = new HashMap<>();
	}
	
	public boolean load() throws IOException {
		if(!Files.exists(file))
			return false;
		
		configs = Files.lines(file)
				.filter(l -> !l.startsWith("//"))
				.map(l -> l.split(":"))
				.collect(Collectors.toMap(l -> l[0].trim(), l -> l[1].trim()));
		
		return true;
	}
	
	@Override
	public Pin getLedPin() {
		if(configs.containsKey(LED_PIN_KEY))
			return RaspiPin.getPinByName("GPIO " + configs.get(LED_PIN_KEY));
		
		return ConfigReader.super.getLedPin();
	}
	
	@Override
	public Pin getTriggerPin() {
		if(configs.containsKey(TRIG_PIN_KEY))
			return RaspiPin.getPinByName("GPIO " + configs.get(TRIG_PIN_KEY));
		
		return ConfigReader.super.getTriggerPin();
	}
	
	@Override
	public Pin getEchoPin() {
		if(configs.containsKey(ECHO_PIN_KEY))
			return RaspiPin.getPinByName("GPIO " + configs.get(ECHO_PIN_KEY));
		
		return ConfigReader.super.getEchoPin();
	}
	
	@Override
	public double getCriticalDistance() {
		if(configs.containsKey(CRITICAL_DISTANCE_KEY))
			return Double.parseDouble(configs.get(CRITICAL_DISTANCE_KEY));
		
		return ConfigReader.super.getCriticalDistance();
	}
	
	@Override
	public long getDelayMillis() {
		if(configs.containsKey(DELAY_MILLIS_KEY))
			return Long.parseLong(configs.get(DELAY_MILLIS_KEY));
		
		return ConfigReader.super.getDelayMillis();
	}
	
	@Override
	public String getLedAlone() {
		if(configs.containsKey(LED_ALONE_KEY))
			return configs.get(LED_ALONE_KEY);
		
		return ConfigReader.super.getLedAlone();
	}
	
	@Override
	public String getSonarAlone() {
		if(configs.containsKey(SONAR_ALONE_KEY))
			return configs.get(SONAR_ALONE_KEY);
		
		return ConfigReader.super.getSonarAlone();
	}

}
