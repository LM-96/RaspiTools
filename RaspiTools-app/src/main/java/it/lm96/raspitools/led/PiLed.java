package it.lm96.raspitools.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.Pin;

public interface PiLed {
	
	public static PiLed getLed(GpioController controller, Pin pin) {
		return new Pi4JLed(controller, pin);
	}
	
	public void powerOn();
	public void powerOff();
	public boolean isOn();
	public boolean isOff();

}
