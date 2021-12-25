#include "Ms74hc595Wrapper.h"

//Пин подключен к ST_CP входу 74HC595
int latchPin = 8;
//Пин подключен к SH_CP входу 74HC595
int clockPin = 12;
//Пин подключен к DS входу 74HC595
int dataPin = 11;

std::shared_ptr<Ms74hc595Wrapper> wrapper2 (new Ms74hc595Wrapper(latchPin, clockPin, dataPin));
std::shared_ptr<Ms74hc595Wrapper> wrapper1 (new Ms74hc595Wrapper(latchPin, clockPin, dataPin, wrapper2));

void setup() {
  Serial.begin(9600);
}

void loop() {
  std::shared_ptr<Ms74hc595Value> value2 (new Ms74hc595Value(10));
  std::shared_ptr<Ms74hc595Value> value1 (new Ms74hc595Value(39, value2));

  
  // отсчитываем от 0 до 255  и отображаем значение на светодиоде
  for (int numberToDisplay = 0; numberToDisplay < 256; numberToDisplay++) {
    auto value = Ms74hc595Value(numberToDisplay, value2);
    wrapper1 -> switchValue(value1);
    // пауза перед следующей итерацией
    delay(100);
  }
}
