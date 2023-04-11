package org.example.infrastructure;

import org.example.domain.MarkdownFormatter;
import org.example.domain.valueObjects.MarkdownText;

import java.io.FileWriter;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

public final class MarkdownCLI {

    private final MarkdownFormatter formatter;

    public MarkdownCLI(MarkdownFormatter formatter) {
        this.formatter = formatter;
    }

    public void execute(String sourceFile, String outputFile) {
        MarkdownText markdownText = formatter.turnsLinksIntoFootnote(new MarkdownText(readFile(sourceFile)));
        writeFile(outputFile, markdownText.value());
    }

    private String readFile(String path) {
        try {
            return new String(readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            throw new RuntimeException("Error reading the file: " + e.getMessage());
        }
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
