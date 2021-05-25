package it.lm96.raspitools.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputUtils {
	
	public static String readLine(InputStream inputStream) throws IOException {
		return (new BufferedReader(new InputStreamReader(inputStream))).readLine();
	}

}
