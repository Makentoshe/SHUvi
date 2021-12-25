#ifndef Ms74hc595Value_h
#define Ms74hc595Value_h

#include <ArxSmartPtr.h>

struct Ms74hc595Value {

public:
    explicit Ms74hc595Value(uint8_t value, std::shared_ptr<Ms74hc595Value> next) {
        this->mValue = value;
        this->mNext = next;
    }

    explicit Ms74hc595Value(uint8_t value) {
        this->mValue = value;
        this->mNext = std::shared_ptr<Ms74hc595Value>(nullptr);
    }

    uint8_t getValue() const {
        return mValue;
    }

    std::shared_ptr<Ms74hc595Value> getNext() {
        return mNext;
    }

private:
    uint8_t mValue;
    std::shared_ptr<Ms74hc595Value> mNext;
};

#endif //Ms74hc595Value_h
