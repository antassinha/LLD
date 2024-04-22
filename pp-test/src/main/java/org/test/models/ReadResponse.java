package org.test.models;

public class ReadResponse<Value> {
    Value value;

    public Value getValue() {
        return value;
    }

    public ReadResponse(Value value) {
        this.value = value;
    }
}
