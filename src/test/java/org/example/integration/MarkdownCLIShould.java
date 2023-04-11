package org.example.integration;

import org.example.MarkdownCLI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;

import static org.assertj.core.api.Assertions.assertThat;

final class MarkdownCLIShould {

    @Test
    void read_the_content_from_file_and_apply_a_transformation() {
        String RESOURCES_DIRECTORY = "src/test/resources/";
        String INPUT_MARKDOWN_FILE = RESOURCES_DIRECTORY + "source.md";
        String OUTPUT_FILE = RESOURCES_DIRECTORY + "destination.md";
        MarkdownCLI markdownCLI = new MarkdownCLI();
        writeFile(INPUT_MARKDOWN_FILE, "[visible text link](url)");

        markdownCLI.execute(INPUT_MARKDOWN_FILE, OUTPUT_FILE);

        String expected = readFile(OUTPUT_FILE);
        assertThat(expected).isEqualTo("""
                                               visible text link [^anchor1]
                                                                                              
                                               [^anchor1]: url or text
                                               """);
    }

    @AfterEach
    void clean(){
        String RESOURCES_DIRECTORY = "src/test/resources/";
        String INPUT_MARKDOWN_FILE = RESOURCES_DIRECTORY + "source.md";
        String OUTPUT_FILE = RESOURCES_DIRECTORY + "destination.md";
        java.nio.file.Path path = java.nio.file.Paths.get(INPUT_MARKDOWN_FILE);
        java.nio.file.Path path2 = java.nio.file.Paths.get(OUTPUT_FILE);
        try {
            java.nio.file.Files.deleteIfExists(path);
            java.nio.file.Files.deleteIfExists(path2);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting the file: " + e.getMessage());
        }
    }

    private String readFile(String path) {
        try {
            return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(path)));
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
