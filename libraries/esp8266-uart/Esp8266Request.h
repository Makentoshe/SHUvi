#ifndef Esp8266Request_h
#define Esp8266Request_h

#include "Arduino.h"

struct Esp8266Request {
  public:
    explicit Esp8266Request(String command, String match): mCommand(command), mMatch(match) {}

    String getCommand() { return mCommand; }

    String getResponseMatch() { return mMatch; }

  private:
    String mCommand;
    String mMatch;
};

// "AT+CWLAP=\"Makentoshe\",\"9219547112\""
struct Esp8266WifiConnectionRequest: public Esp8266Request {
  public:
    explicit Esp8266WifiConnectionRequest(String ssid, String password): Esp8266Request(command(ssid, password), "OK") {}

  private:
    // Builds a command string using concatenation
    static String command(String ssid, String password) {
      return String("AT+CWLAP=\"") + ssid + String("\",\"") + password + String("\"");
    }
};

#endif //Esp8266Request_h
