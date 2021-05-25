#include <iostream>
#include <wiringPi.h>
#include <fstream>
#include <cmath>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>


#define PIN 6
using namespace std;
/*
g++  LedAlone.c -l wiringPi -o  LedAlone
 */
 
 int pin = PIN;
 
void setup() {
	wiringPiSetup();
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

void parseArgs(int argc, char* argv[], bool* s_flag, bool* s_on, bool* c_flag) {
	int c, num;
	bool set = false;
	while((c = getopt(argc, argv, "p:s:c")) != -1) {
		switch(c) {
			case 'p':
				num = verifyUint(optarg);
				if(num != -1) pin = num;
				
				break;
				
			case 's':
				if(strcmp(optarg, "on") == 0 || strcmp(optarg, "ON") == 0)
					*s_on = true;
				else if(strcmp(optarg, "off") == 0 || strcmp(optarg, "OFF") == 0)
					*s_on = false;
				else {
					fprintf(stderr, "Invalid argument \'%s\' for option -s\n", optarg);
					exit(1);
				}
				
				*s_flag = true;
				break;
				
			case 'c':
				*c_flag = true;

			default:
				break;
		}
	}
	
	if(!(*s_flag || *c_flag)) {
		fprintf(stderr, "At least one of \'-s\' or \'-c\' option must be present\n");
		exit(2);
	}
}

int main(int argc, char* argv[]) {
	
	bool s_flag = false, s_on, c_flag = false;
	parseArgs(argc, argv, &s_flag, &s_on, &c_flag);
	
	setup();
	
	
	if(s_flag) {
		pinMode(pin, OUTPUT);
		if(s_on) {
			digitalWrite(pin, HIGH);
			cout << " LedAlone | Led is powered ON " << endl;
		}
		else {
			digitalWrite(pin, LOW);
			cout << " LedAlone | Led is powered OFF " << endl;
		}
	}
	
	if(c_flag) {
		if(digitalRead(pin) == HIGH) cout << " LedAlone | Led is ON " << endl;
		else cout << " LedAlone | Led is OFF " << endl;
	}
	
 	return 0;
}
