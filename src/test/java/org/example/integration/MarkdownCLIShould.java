package org.example.integration;

import org.example.MarkdownCLI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.nio.file.Paths;

import static java.nio.file.Files.deleteIfExists;
import static java.nio.file.Files.readAllBytes;
import static org.assertj.core.api.Assertions.assertThat;

final class MarkdownCLIShould {

    private final String RESOURCES_DIRECTORY = "src/test/resources/";
    private final String OUTPUT_FILE = RESOURCES_DIRECTORY + "destination.md";
    private final String INPUT_MARKDOWN_FILE = RESOURCES_DIRECTORY + "source.md";

    @Test
    void read_the_content_from_file_and_apply_a_transformation() {
        writeFile(INPUT_MARKDOWN_FILE, "[visible text link](url)");

        new MarkdownCLI().execute(INPUT_MARKDOWN_FILE, OUTPUT_FILE);

        assertThat(readFile(OUTPUT_FILE))
                .isEqualTo("""
                                   visible text link [^anchor1]
                                                                                  
                                   [^anchor1]: url or text
                                   """);
    }

    @AfterEach
    void clean(){
        try {
            deleteIfExists(Paths.get(INPUT_MARKDOWN_FILE));
            deleteIfExists(Paths.get(OUTPUT_FILE));
        } catch (Exception e) {
            throw new RuntimeException("Error deleting the file: " + e.getMessage());
        }
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
