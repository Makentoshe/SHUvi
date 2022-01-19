#ifndef MoistureSensorValue_h
#define MoistureSensorValue_h

#include "Arduino.h"

struct MoistureSensorValue {

    public:
        explicit MoistureSensorValue(uint16_t raw, uint8_t percent): mRaw(raw) {
            if (percent <= 0) mPercent = 0; else if (percent >= 100) mPercent = 100; else mPercent = percent;
        }

        uint16_t getRaw(){ return mRaw; }

        uint8_t getPercent() { return mPercent; }

    private:
        uint16_t mRaw;
        uint8_t mPercent;
};

#endif // MoistureSensorValue_h
