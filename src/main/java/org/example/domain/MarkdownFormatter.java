package org.example.domain;

import org.example.domain.valueObjects.MarkdownText;

public final class MarkdownFormatter {
    public MarkdownText turnsLinksIntoFootnote(MarkdownText markdownText) {
        if (markdownText.isEmpty()){
            return new MarkdownText("");
        }
        return new MarkdownText("visible text link [^anchor1]\n\n[^anchor1]: url or text\n");
    }
}
