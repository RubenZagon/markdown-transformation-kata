package org.example.infrastructure;

import org.example.domain.FileSystem;
import org.example.domain.MarkdownFormatter;
import org.example.domain.valueObjects.MarkdownText;

public final class MarkdownCLI{

    private final MarkdownFormatter formatter;
    private final FileSystem fileSystem;

    public MarkdownCLI(MarkdownFormatter formatter, LocalFileSystem fileSystem) {
        this.formatter = formatter;
        this.fileSystem = fileSystem;
    }

    public void execute() {
        MarkdownText rawMarkdownText = fileSystem.readMarkdownContent();
        MarkdownText markdownText = formatter.turnsLinksIntoFootnote(rawMarkdownText);
        fileSystem.persist(markdownText);
    }
}
