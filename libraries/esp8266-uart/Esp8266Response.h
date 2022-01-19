#ifndef Esp8266Response_h
#define Esp8266Response_h

#include "Arduino.h"

struct Esp8266Response {
  public:
    explicit Esp8266Response(Esp8266Request * request, bool isSuccess) {
      this->mRequest = request;
      this->mIsSuccess = isSuccess;
    }

    ~Esp8266Response() {
      delete mRequest; // remove pointer to Eps8266Request object
    }

    Esp8266Request * getRequest() {
      return mRequest;
    }

    bool isSuccess() {
      return mIsSuccess;
    }

  private:
    Esp8266Request * mRequest;
    bool mIsSuccess;
};

#endif //Esp8266Request_h
