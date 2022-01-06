#include <SoftwareSerial.h>
#define ESP_RX_PIN 10
#define ESP_TX_PIN 11

#define WIFI_SSID ""
#define WIFI_PASSWORD ""

SoftwareSerial esp8266(ESP_RX_PIN, ESP_TX_PIN);

void setup() {
  Serial.begin(9600);
  esp8266.begin(115200);
   
  sendCommand("AT", "OK");
}

void loop() {
  
  delay(1000);

  while(esp8266.available()) {
    Serial.print((char)esp8266.read());
  }
  Serial.print("available=");
  Serial.println(esp8266.available());
}

void sendCommand(String command) {
  Serial.print("Request command=");
  Serial.print(command);
  Serial.print(" wrote bytes=");
  Serial.println(esp8266.println(command));
}
