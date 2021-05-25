package it.lm96.raspitools.sonar;

import java.io.IOException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.Pin;

public interface PiSonar {
	
	public static PiSonar getSonar(GpioController controller, Pin trig, Pin echo) {
		return new Pi4JSonar(controller, trig, echo);
	}
	
	public static PiSonar getSonarAlone(String sonarAlone, int trig, int echo) throws IOException {
		return new CSonar(sonarAlone, trig, echo);
	}
	
	public double readDistanceInCm() throws InterruptedException, IOException;

}
