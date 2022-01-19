
#include "MoistureSensorWrapper.h"

MoistureSensorWrapper::MoistureSensorWrapper(uint8_t inputPin, uint16_t airCalibration, uint16_t waterCalibration): mInputPin(inputPin), mAirCalibration(airCalibration), mWaterCalibration(waterCalibration) {}

MoistureSensorValue * MoistureSensorWrapper::getValue() {
  auto rawValue = analogRead(mInputPin);
  auto percentValue = map(rawValue, mAirCalibration, mWaterCalibration, 0, 100);
  return new MoistureSensorValue(rawValue, percentValue);
}
