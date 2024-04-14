package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {
    private List<Pair<Pattern, String>> patterns;

    public LexicalAnalyzer() {
        // Define lexical patterns
        patterns = new ArrayList<>();
        patterns.add(new Pair<>(Pattern.compile("^\\d*\\.\\d+$"), "Float number"));
        patterns.add(new Pair<>(Pattern.compile("^\\d+$"), "Dec number"));
        patterns.add(new Pair<>(Pattern.compile("^0[xX][0-9a-fA-F]+$"), "Hex number"));
        patterns.add(new Pair<>(Pattern.compile("\"[^\"]*\""), "String"));
        patterns.add(new Pair<>(Pattern.compile("'.'"), "Char"));
        patterns.add(new Pair<>(Pattern.compile("^#[a-zA-Z_]\\w*$"), "Preprocessor"));
        patterns.add(new Pair<>(Pattern.compile("^;.*$"), "Comment"));
        patterns.add(new Pair<>(Pattern.compile("^(MOV|ADD|SUB|DIV|MUL)$"), "Reserved word"));
        patterns.add(new Pair<>(Pattern.compile("^(AND|OR|XOR)$"), "Operator"));
        patterns.add(new Pair<>(Pattern.compile("^[\\.,;:\\[\\]{}()]$"), "Separator"));
        patterns.add(new Pair<>(Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*$"), "Identifier"));
    }

    public List<Pair<String, String>> analyze(String code) {
        if (code.isEmpty()) {
            throw new IllegalArgumentException("Empty string provided for analysis");
        }

        List<Pair<String, String>> lexemes = new ArrayList<>();

        Pattern wordsPattern = Pattern.compile(";.*|\\d*\\.\\d+|\\w+|[\\.,;:\\[\\]{}()]|\"[^\"]*\"|'.'|#[a-zA-Z_][a-zA-Z0-9_]*\\b|\\<>");
        Matcher matcher = wordsPattern.matcher(code);

        while (matcher.find()) {
            String lexeme = matcher.group();
            String lexemeType = "Error";

            for (Pair<Pattern, String> pattern : patterns) {
                if (pattern.getFirst().matcher(lexeme).matches()) {
                    lexemeType = pattern.getSecond();
                    break;
                }
            }
            lexemes.add(new Pair<>(lexeme, lexemeType));
        }
        return lexemes;
    }
}
