package org.example.domain.valueObjects;

public final class MarkdownText {
    private final String value;

    public MarkdownText(String text) {this.value = text;}

    public String value() {
        return value;
    }
}
