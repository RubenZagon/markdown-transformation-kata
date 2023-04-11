package org.example.unit;

import org.example.domain.MarkdownFormatter;
import org.example.domain.valueObjects.MarkdownText;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

final class MarkdownFormatterShould {

    /*
    Z – Zero - No text
    O – One - One link
    M – Many - Many links
    B – Boundary - Empty link, link without text, link without url, repeated links
    I – Interface definition
    E – Exercise Exceptional behavior - null, empty, invalid, etc.
    S – Simple Scenarios, Simple Solutions
     */

    public static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of(
                        "[visible text link](url)",
                        "visible text link [^anchor1]\n\n[^anchor1]: url\n"
                ),
                Arguments.of(
                        "[this book](https://codigosostenible.com)",
                        "this book [^anchor1]\n\n[^anchor1]: https://codigosostenible.com\n"
                ),
                Arguments.of(
                        "[this book](https://codigosostenible.com) and some other text\nand some other text line.",
                        "this book [^anchor1] and some other text\nand some other text line.\n\n[^anchor1]: https://codigosostenible.com\n"
                )
        );
    }
    @ParameterizedTest
    @MethodSource("provideTestData")
    void turns_links_into_footnote(String text, String expected) {
        MarkdownFormatter markdownFormatter = new MarkdownFormatter();

        MarkdownText markdownText = markdownFormatter.turnsLinksIntoFootnote(new MarkdownText(text));

        assertThat(markdownText.value()).isEqualTo(expected);
    }
}
