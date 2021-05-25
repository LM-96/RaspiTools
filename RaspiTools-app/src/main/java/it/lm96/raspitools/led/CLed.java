package it.lm96.raspitools.led;

import java.io.IOException;

import it.lm96.raspitools.utils.InputUtils;

public class CLed extends PiLed {
	
	private String ledOn, ledOff, checkStatus;
	
	public CLed(String ledAlone, int pin) {
		this.ledOn = ledAlone.trim() + " -p " + pin + " -s on";
		this.ledOff = ledAlone.trim() + " -p " + pin + " -s off";
		this.checkStatus = ledAlone.trim() + " -c";
		
		try {
			originalOn = isOn();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void powerOn() throws IOException {
		Runtime.getRuntime().exec(ledOn);
	}

	@Override
	public void powerOff() throws IOException {
		Runtime.getRuntime().exec(ledOff);
	}

	@Override
	public boolean isOn() throws IOException {
		String line = InputUtils.readLine(Runtime.getRuntime().exec(checkStatus).getInputStream());
		System.out.println(line);
		
		return line.contains("ON");
	}

	@Override
	public boolean isOff() throws IOException {
		String line = InputUtils.readLine(Runtime.getRuntime().exec(checkStatus).getInputStream());
		System.out.println(line);
		
		return line.contains("OFF");
	}

}
