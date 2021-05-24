package it.lm96.raspitools.sonar;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.Pin;

public interface PiSonar {
	
	public static PiSonar getSonar(GpioController controller, Pin trig, Pin echo) {
		return new Pi4JSonar(controller, trig, echo);
	}
	
	public double readDistanceInCm() throws InterruptedException;

}
