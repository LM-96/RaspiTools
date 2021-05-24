package it.lm96.raspitools.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;

public class Pi4JLed implements PiLed {
	
	private GpioPinDigitalOutput pinOut;
	
	public Pi4JLed(GpioController controller, Pin pin) {	
		pinOut = controller.provisionDigitalOutputPin(pin);
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

}
