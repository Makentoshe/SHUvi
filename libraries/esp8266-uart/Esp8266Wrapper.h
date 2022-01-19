#ifndef Esp8266Wrapper_h
#define Esp8266Wrapper_h

#include "Arduino.h"
#include "Esp8266Uart.h"

class Esp8266Wrapper {

  public:
    explicit Esp8266Wrapper();
    Esp8266Uart * uart(uint8_t rxPin, uint8_t txPin);
};

#endif //Esp8266Wrapper_h
