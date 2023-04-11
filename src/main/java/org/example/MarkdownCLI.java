package org.example;

import java.io.FileWriter;

public final class MarkdownCLI {
    public MarkdownCLI() {}

    public void execute(String markdownFile, String outputFile) {
        writeFile(outputFile, """
                                               visible text link [^anchor1]
                                                                                              
                                               [^anchor1]: url or text
                                               """);

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
