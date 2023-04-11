package org.example.domain;

import org.example.domain.valueObjects.MarkdownText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MarkdownFormatter {
    public MarkdownText turnsLinksIntoFootnote(MarkdownText markdownText) {
        if (markdownText.isEmpty()){
            return new MarkdownText("");
        }
        Anchor anchors = getAnchors(markdownText);
        return new MarkdownText("%s [^anchor1]\n\n[^anchor1]: %s\n".formatted(anchors.text(), anchors.link()));
    }

    private Anchor getAnchors(MarkdownText markdownText) {
        //language=RegExp
        Pattern pattern = Pattern.compile("\\[(?<text>[^]]+)]\\((?<url>[^)]+)\\)");
        Matcher matcher = pattern.matcher(markdownText.value());
        Anchor anchor = null;
        while (matcher.find()) {
            anchor = new Anchor(matcher.group("text"), matcher.group("url"));
        }
        return anchor;
    }
}
