package org.example.domain.valueObjects;

import org.example.domain.Anchor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MarkdownText {
    private final String value;

    public MarkdownText(String text) {this.value = text;}

    public String value() {
        return value;
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    public Anchor getAnchors() {
        //language=RegExp
        Pattern pattern = Pattern.compile("\\[(?<text>[^]]+)]\\((?<url>[^)]+)\\)");
        Matcher matcher = pattern.matcher(this.value());
        Anchor anchor = null;
        while (matcher.find()) {
            anchor = new Anchor(matcher.group("text"), matcher.group("url"));
        }
        return anchor;
    }
}
