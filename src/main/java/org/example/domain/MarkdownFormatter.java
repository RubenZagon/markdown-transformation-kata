package org.example.domain;

import org.example.domain.valueObjects.MarkdownText;

public final class MarkdownFormatter{

    private MarkdownText markdownText;

    public MarkdownText turnsLinksIntoFootnote(MarkdownText markdownText) {
        this.markdownText = markdownText;
        if (this.markdownText.isEmpty()){
            return new MarkdownText("");
        }
        String text = replaceMarkdownLink();
        return new MarkdownText(text + footnotes());
    }

    private String replaceMarkdownLink() {
        Anchor anchor = this.markdownText.getAnchor();
        return this.markdownText.value().replace(
                "[%s](%s)".formatted(anchor.text(), anchor.link()),
                "%s [^anchor1]".formatted(anchor.text())
        );
    }

    private String footnotes() {
        Anchor anchor = this.markdownText.getAnchor();
        return "\n\n[^anchor1]: %s\n".formatted(anchor.link());
    }
}
