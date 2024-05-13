package org.example.constant;

public enum SearchOption {
    FULL_NAME("fullName"),
    PHONE_NUMBER("phoneNumber");

    private final String value;

    SearchOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}