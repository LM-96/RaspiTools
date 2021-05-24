package it.lm96.raspitools.config;

import java.io.IOException;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public interface ConfigReader {
	
	public final static Pin DEFAULT_LED_PIN = RaspiPin.GPIO_06;
	public final static Pin DEFAULT_TRIG_PIN = RaspiPin.GPIO_00;
	public final static Pin DEFAULT_ECHO_PIN = RaspiPin.GPIO_02;
	
	public final static int DEFAULT_DELAY_MILLIS = 500;
	public final static int DEFAULT_CRITICAL_DISTANCE = 10;
	
	public boolean load() throws IOException;
	
	public default Pin getLedPin() {
		return DEFAULT_LED_PIN;
	}
	
	public default Pin getTriggerPin() {
		return DEFAULT_TRIG_PIN;
	}
	
	public default Pin getEchoPin() {
		return DEFAULT_ECHO_PIN;
	}
	
	public default double getCriticalDistance() {
		return DEFAULT_CRITICAL_DISTANCE;
	}
	
	public default long getDelayMillis() {
		return DEFAULT_DELAY_MILLIS;
	}

}
