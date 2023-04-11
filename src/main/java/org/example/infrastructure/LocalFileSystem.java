package org.example.infrastructure;

import org.example.domain.FileSystem;
import org.example.domain.valueObjects.MarkdownText;

import java.io.FileWriter;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

public final class LocalFileSystem implements FileSystem {
    private final String sourceFile;
    private final String outputFile;

    public LocalFileSystem(String sourceFile, String outputFile) {
        this.sourceFile = sourceFile;
        this.outputFile = outputFile;
    }

    public MarkdownText readMarkdownContent() {
        return new MarkdownText(readFile(this.sourceFile));
    }

    public void persist(MarkdownText markdownText) {
        try {
            FileWriter fileWriter = new FileWriter(this.outputFile);
            fileWriter.write(markdownText.value());
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException("Error writing the file: " + e.getMessage());
        }
    }

    private String readFile(String path) {
        try {
            return new String(readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            throw new RuntimeException("Error reading the file: " + e.getMessage());
        }
    }
}
