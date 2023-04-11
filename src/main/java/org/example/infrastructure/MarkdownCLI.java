package org.example.infrastructure;

import org.example.domain.MarkdownFormatter;
import org.example.domain.valueObjects.MarkdownText;

import java.io.FileWriter;

public final class MarkdownCLI {

    private final MarkdownFormatter formatter;

    public MarkdownCLI(MarkdownFormatter formatter) {
        this.formatter = formatter;
    }

    public void execute(String markdownFile, String outputFile) {
        MarkdownText markdownText = formatter.turnsLinksIntoFootnote(new MarkdownText(markdownFile));
        writeFile(outputFile, markdownText.value());
    }

    private void writeFile(String path, String content) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(content);
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException("Error writing the file: " + e.getMessage());
        }
    }
}
