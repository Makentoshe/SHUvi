#ifndef Esp8266Request_h
#define Esp8266Request_h

#include "Arduino.h"

struct Esp8266Request {
  public:
    explicit Esp8266Request(char * command, char * match) {
      this->mCommand = command;
      this->mMatch = match;
    }

    String getCommand() {
      return String(mCommand);
    }

    char * getResponseMatch() {
      return mMatch;
    }

  private:
    char * mCommand;
    char * mMatch;
};

#endif //Esp8266Request_h
