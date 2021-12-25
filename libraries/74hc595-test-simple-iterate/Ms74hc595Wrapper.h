#ifndef Ms74hc595Wrapper_h
#define Ms74hc595Wrapper_h

#include <ArxSmartPtr.h>
#include "Ms74hc595Value.h"

class Ms74hc595Wrapper {

  public:
    Ms74hc595Wrapper(
      uint8_t stCpPin, uint8_t shCpPin, uint8_t dsPin,
      const std::shared_ptr<Ms74hc595Wrapper> &cascade = std::shared_ptr<Ms74hc595Wrapper>(nullptr)
    ) {
      this->mLatchPin = stCpPin;
      pinMode(mLatchPin, OUTPUT);

      this->mClockPin = shCpPin;
      pinMode(mClockPin, OUTPUT);

      this->mDataPin = dsPin;
      pinMode(mDataPin, OUTPUT);

      this->mCascade = cascade;
    }

    void switchValue(std::shared_ptr<Ms74hc595Value> value) {
      auto current = value;
      internalSwitchValue(current->getValue());

      auto nextValue = current->getNext();
      if (mCascade != nullptr && nextValue != nullptr) {
        mCascade->switchValue(nextValue);
      }
    }

  private:
    uint8_t mLatchPin;
    uint8_t mClockPin;
    uint8_t mDataPin;
    std::shared_ptr<Ms74hc595Wrapper> mCascade;

    void internalSwitchValue(uint8_t value) {
      //      std::cout << static_cast<int>(value) << "\n";
      digitalWrite(mLatchPin, LOW);
      shiftOut(mDataPin, mClockPin, MSBFIRST, value);
      digitalWrite(mLatchPin, HIGH);
    }
};

#endif //Ms74hc595Wrapper_h
