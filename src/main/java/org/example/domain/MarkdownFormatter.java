package org.example.domain;

import org.example.domain.valueObjects.MarkdownText;

public final class MarkdownFormatter{
    public MarkdownText turnsLinksIntoFootnote(MarkdownText markdownText) {
        if (markdownText.isEmpty()){
            return new MarkdownText("");
        }
        Anchor anchors = markdownText.getAnchors();
        return new MarkdownText("%s [^anchor1]\n\n[^anchor1]: %s\n".formatted(anchors.text(), anchors.link()));
    }
}
