package org.example.domain;

import org.example.domain.valueObjects.MarkdownText;

public final class MarkdownFormatter {
    public MarkdownText turnsLinksIntoFootnote(MarkdownText markdownText) {
        return new MarkdownText("visible text link [^anchor1]\n\n[^anchor1]: url or text\n");
    }
}
