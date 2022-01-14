#include "Esp8266Wrapper.h"

#define ESP_RX_PIN 10
#define ESP_TX_PIN 11

#define WIFI_SSID ""
#define WIFI_PASSWORD ""

Esp8266Wrapper * espWrapper;
Esp8266Uart * espUart;

void setup() {
  Serial.begin(9600);

  espUart = new Esp8266Uart(ESP_RX_PIN, ESP_TX_PIN);
  espUart->command(new Esp8266Request("AT", "OK"));
}

void loop() {
  auto string = espUart->collect();
//  Serial.print("Collected=");
//  Serial.println(string);

  delay(1000);

}
