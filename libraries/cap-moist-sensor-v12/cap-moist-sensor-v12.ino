#include "MoistureSensorWrapper.h"

MoistureSensorWrapper sensor = MoistureSensorWrapper(A0, 470, 217);

void setup() {
  Serial.begin(9600); // open serial port, set the baud rate to 9600 bps
}

void loop() {
  auto value = sensor.getValue();
  Serial.print("Raw="); Serial.print(value->getRaw()); Serial.print("; Percent="); Serial.println(value->getPercent()); 
  delete value;
  
  delay(250);
}
