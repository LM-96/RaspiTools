package it.lm96.raspitools.led;

import java.io.IOException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;

public class Pi4JLed extends PiLed {
	
	private GpioPinDigitalOutput pinOut;
	
	public Pi4JLed(GpioController controller, Pin pin) {	
		pinOut = controller.provisionDigitalOutputPin(pin);
		originalOn = isOn();
	}

	@Override
	public void powerOn() {
		pinOut.high();		
	}

	@Override
	public void powerOff() {
		pinOut.low();		
	}

	@Override
	public boolean isOn() {
		return pinOut.isHigh();
	}

	@Override
	public boolean isOff() {
		return pinOut.isLow();
	}

	@Override
	public void close() throws IOException {
		
	}

}
