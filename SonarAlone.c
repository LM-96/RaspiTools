#include <iostream>
#include <wiringPi.h>
#include <fstream>
#include <cmath>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>


#define TRIG 0
#define ECHO 2

#define DELAY 30

#define CLOSE 18
#define MEDIUM 21
#define FAR 60

#define POS_LEFT 0.055
#define POS_RIGHT 0.24
#define POS_FORWARD 0.14
using namespace std;
/*
g++  SonarAlone.c -l wiringPi -o  SonarAlone
 */
 
 int echo = ECHO;
 int trig = TRIG;
 int delay_t = DELAY;
 
void setup() {
	cout << " SonarAlone | initialiting setup... " << endl;
	wiringPiSetup();
	pinMode(trig, OUTPUT);
	pinMode(echo, INPUT);
	//TRIG pin must start LOW
	digitalWrite(trig, LOW);
	delay(30);
	cout << " SonarAlone | setup ended! " << endl;
}

int getCM() {
	//Send trig pulse
	digitalWrite(trig, HIGH);
	delayMicroseconds(20);
	digitalWrite(trig, LOW);

	//Wait for echo start
	while(digitalRead(echo) == LOW);

	//Wait for echo end
	long startTime = micros();
	while(digitalRead(echo) == HIGH);
	long travelTime = micros() - startTime;

	//Get distance in cm
	int distance = travelTime / 58;

	return distance;
}

int verifyUint(char* str) {
	int pin=0;
	for(int i=0; str[i]!= '\0'; i++){
		if( (str[i] < '0') || (str[i] > '9') ){
			return -1;
		}
	}
	return atoi(str);
}

int main(int argc, char* argv[]) {
	int cm, c;
	int num;
	while((c = getopt(argc, argv, "e:t:d:")) != -1) {
		switch(c) {
			case 'e':
				num = verifyUint(optarg);
				if(num != -1) echo = num;
				
				break;
				
			case 't':
				num = verifyUint(optarg);
				if(num != -1) trig = num;
				
				break;
				
			case 'd':
				num = verifyUint(optarg);
				if(num != -1) delay_t = num;
				
				break;

			default:
				break;
		}
	}
	
	setup();
	while(1) {
 		cm = getCM();
		//cout << "distance(" << cm  << ",forward,front)" << endl;
		cout << " SonarAlone | distance: " << cm   << endl;
		delay(delay_t);
	}
 	return 0;
}
