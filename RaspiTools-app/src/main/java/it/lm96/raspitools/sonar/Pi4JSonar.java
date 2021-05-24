package it.lm96.raspitools.sonar;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;

public class Pi4JSonar implements PiSonar {
	
	private GpioPinDigitalOutput trig;
	private GpioPinDigitalInput echo;
	
	public Pi4JSonar(GpioController controller, Pin trig, Pin echo) {
		this.trig = controller.provisionDigitalOutputPin(trig);
		this.echo = controller.provisionDigitalInputPin(echo, PinPullResistance.PULL_DOWN);
		
		this.trig.low();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double readDistanceInCm() throws InterruptedException {
		trig.high();
		Thread.sleep(30);
		trig.low();
		
		while(echo.isLow());
		
		long startTime = System.nanoTime();
		while(echo.isHigh());
		long duration = System.nanoTime() - startTime;
		
		return (duration * 0.001) / 58.0;
	}

}
