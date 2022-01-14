#ifndef Esp8266Uart_h
#define Esp8266Uart_h

#include "Esp8266Request.h"
#include "Esp8266Response.h"
#include <SoftwareSerial.h>

class Esp8266Uart {

  public:
    explicit Esp8266Uart(uint8_t rxPin, uint8_t txPin);
    
    ~Esp8266Uart();

    // Returns true if command was accepted and started to execute.
    // Return false if class is buzy with other command and current 
    // request was refused
    boolean command(Esp8266Request * command);
    
    Esp8266Response * collect();

  private:
    SoftwareSerial * mSerial;
    Esp8266Request * mCurrentRequest;
    boolean mIsBusy;
};

#endif //Esp8266Uart_h
