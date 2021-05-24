package it.lm96.raspitools;

import java.io.IOException;

import com.pi4j.io.gpio.RaspiPin;

public class Prova {

	public static void main(String[] args) throws IOException {
		System.out.println(RaspiPin.getPinByName("GPIO 6"));

	}

}
