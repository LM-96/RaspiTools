package it.lm96.raspitools.led;

import java.io.Closeable;
import java.io.IOException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.Pin;

public abstract class PiLed implements Closeable, AutoCloseable{
	
	public static PiLed getLed(GpioController controller, Pin pin) {
		return new Pi4JLed(controller, pin);
	}
	
	public static PiLed getLedAlone(String ledAlone, int pin) {
		return new CLed(ledAlone, pin);
	}
	
	protected boolean originalOn;
	
	public abstract void powerOn() throws IOException;
	public abstract void powerOff() throws IOException;
	public abstract boolean isOn() throws IOException;
	public abstract boolean isOff() throws IOException;
	
	@Override
	public void close() throws IOException {
		boolean isOn = isOn();
		if(isOn && !originalOn)
			powerOff();
		
		if(!isOn && originalOn)
			powerOn();
	}

}
