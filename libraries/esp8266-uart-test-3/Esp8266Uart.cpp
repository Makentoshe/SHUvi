#include "Esp8266Uart.h"

Esp8266Uart::Esp8266Uart(uint8_t rxPin, uint8_t txPin) {
  this->mSerial = new SoftwareSerial(rxPin, txPin);
  this->mSerial->begin(115200);
}

Esp8266Uart::~Esp8266Uart() {
  delete mSerial;
}

boolean Esp8266Uart::command(Esp8266Request * command) {
  if (mIsBusy) return false;

  this->mIsBusy = true;
  this->mCurrentRequest = command;
  mSerial->println(command->getCommand());
  return true;
}

Esp8266Response * Esp8266Uart::collect() {
  int availableBytes = mSerial->available();
  Serial.print("availableBytes=");
  Serial.println(availableBytes);
  
  boolean isSuccess = mSerial->find("OK");
  Serial.print("isSuccess=");
  Serial.println(isSuccess);


  //  if (availableBytes <= 0) {
  //    mIsBusy = false;
  //    return NULL;
  //  }
  //
  //  char * array = (char*)malloc(mSerial->available());
  //
  //  int i = 0;
  //  while (mSerial->available()) {
  //    array[i++] = mSerial->read();
  //  }
  //
  //  Serial.println(String(array));
  //
  mIsBusy = false;
  return NULL;
}
