#ifndef MoistureSensorWrapper_h
#define MoistureSensorWrapper_h

#include "Arduino.h"
#include "MoistureSensorValue.h"

class MoistureSensorWrapper {

  public:
    // airCalibration - mean sensor value on the air
    // waterCalibration - mean sensor value in the water
    explicit MoistureSensorWrapper(uint8_t inputPin, uint16_t airCalibration, uint16_t waterCalibration);

    MoistureSensorValue * getValue();

  private:
    uint8_t mInputPin;
    uint16_t mAirCalibration;
    uint16_t mWaterCalibration;

};

#endif //MoistureSensorWrapper_h
