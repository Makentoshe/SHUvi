#include "Esp8266Wrapper.h"

Esp8266Wrapper::Esp8266Wrapper() {}

Esp8266Uart * Esp8266Wrapper::uart(uint8_t rxPin, uint8_t txPin) {
  return new Esp8266Uart(rxPin, txPin);
}
