package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Lab task: code the analyzer to divide the code into lexemes and determine their type");
        System.out.println("Chernenko Yevhenii, TTP-32");

        Scanner scanner = new Scanner(System.in);
        LexicalAnalyzer analyzer = new LexicalAnalyzer();

        while (true) {
            System.out.print("Enter a lexeme (type 'exit' to quit): ");
            String code = scanner.nextLine();

            if (code.equals("exit")) {
                break;
            }

            List<Pair<String, String>> lexemes = analyzer.analyze(code);

            System.out.println("Lexical analysis result:");
            for (Pair<String, String> lexeme : lexemes) {
                System.out.println("< " + lexeme.getFirst() + " - " + lexeme.getSecond() + " >");
            }
        }

        scanner.close();
    }
}
