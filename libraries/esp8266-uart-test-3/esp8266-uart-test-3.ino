#include "Esp8266Wrapper.h"

#define ESP_RX_PIN 10
#define ESP_TX_PIN 11

#define WIFI_SSID ""
#define WIFI_PASSWORD ""

Esp8266Wrapper * espWrapper;
Esp8266Uart * espUart;

/* AT+CWLAP="Makentoshe","9219547112"
 * WIFI CONNECTED
 * WIFI GOT IP
 * 
 * OK
 */

void setup() {
  Serial.begin(9600);

  espUart = new Esp8266Uart(ESP_RX_PIN, ESP_TX_PIN);
  espUart->command(new Esp8266Request("AT", "OK"));
}

void loop() {
  auto response = espUart->collect();
  Serial.print("Status=");
  if (response == NULL) {
    Serial.println("null");
  } else {
    Serial.println(response->isSuccess());
  }

  delay(1000);

}
