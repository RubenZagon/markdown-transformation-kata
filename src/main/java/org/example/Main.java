package org.example;

import org.example.domain.MarkdownFormatter;
import org.example.infrastructure.LocalFileSystem;
import org.example.infrastructure.MarkdownCLI;

public class Main {
    public static void main(String[] args) {
        new MarkdownCLI(new MarkdownFormatter(), new LocalFileSystem(args[0], args[1])).execute();
    }
}
