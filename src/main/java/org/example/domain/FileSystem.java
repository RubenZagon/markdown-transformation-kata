package org.example.domain;

import org.example.domain.valueObjects.MarkdownText;

public interface FileSystem {
    MarkdownText readMarkdownContent();

    void persist(MarkdownText markdownText);
}
