package org.example.test;

import org.testng.annotations.*;
import org.testng.Assert;
import java.util.List;
import org.example.LexicalAnalyzer;
import org.example.Pair;

public class LexicalAnalyzerTest {
    private LexicalAnalyzer analyzer;

    @BeforeTest
    public void setUp() {
        analyzer = new LexicalAnalyzer();
    }

    @Test(groups = { "goodInput", "1" })
    public void testCommentLexeme() {
        String code = "; Example of comment";
        List<Pair<String, String>> result = analyzer.analyze(code);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getFirst(), "; Example of comment");
    }

    @Test(groups = { "goodInput", "2" })
    public void testReservedWordLexeme() {
        String code = "MOV adsa00, 1";
        List<Pair<String, String>> result = analyzer.analyze(code);
        Assert.assertEquals(result.size(), 4);
        Assert.assertEquals(result.get(0).getFirst(), "MOV");
        Assert.assertEquals(result.get(0).getSecond(), "Reserved word");
    }

    @Test(groups = { "goodInput", "1" })
    public void testDecNumberLexeme() {
        String code = "MOV adsa00, 20";
        List<Pair<String, String>> result = analyzer.analyze(code);
        Assert.assertEquals(result.size(), 4);
        Assert.assertEquals(result.get(3).getFirst(), "20");
        Assert.assertEquals(result.get(3).getSecond(), "Dec number");
    }

    @Test(groups = { "goodInput", "2" })
    public void testSeparatorLexeme() {
        String code = "DIV adsa00, 1";
        List<Pair<String, String>> result = analyzer.analyze(code);
        Assert.assertEquals(result.get(2).getSecond(), "Separator");
        Assert.assertEquals(result.get(2).getFirst(), ",");
    }

    @Test(groups = { "goodInput", "1" })
    public void testOperatorLexeme() {
        String code = "AND eax, ebx";
        List<Pair<String, String>> result = analyzer.analyze(code);
        Assert.assertEquals(result.get(0).getFirst(), "AND");
        Assert.assertEquals(result.get(0).getSecond(), "Operator");
    }

    @Test(groups = { "goodInput", "2" })
    public void testHexNumberLexeme() {
        String code = "DIV a, 0x10";
        List<Pair<String, String>> result = analyzer.analyze(code);
        Assert.assertEquals(result.get(3).getSecond(), "Hex number");
        Assert.assertEquals(result.get(3).getFirst(), "0x10");
    }

    @Test(groups = { "goodInput", "1" })
    public void testDirectiveLexeme() {
        String code = "#directive";
        List<Pair<String, String>> result = analyzer.analyze(code);
        Assert.assertEquals(result.get(0).getFirst(), "#directive");
        Assert.assertEquals(result.get(0).getSecond(), "Preprocessor");
    }

    @Test(groups = { "goodInput", "2" })
    public void testFloatNumberLexeme() {
        String code = "SUB c, 21.2";
        List<Pair<String, String>> result = analyzer.analyze(code);
        Assert.assertEquals(Double.parseDouble(result.get(3).getFirst()), 21.2);
        Assert.assertEquals(result.get(3).getSecond(), "Float number");
    }

    @Test(groups = { "goodInput", "1" })
    public void testCharacterConstantLexeme() {
        String code = "MOV c, 'a'";
        List<Pair<String, String>> result = analyzer.analyze(code);
        Assert.assertEquals(result.size(), 4);
        Assert.assertEquals(result.get(3).getFirst(), "'a'");
        Assert.assertEquals(result.get(3).getSecond(), "Char");
    }

    @Test(groups = { "goodInput", "2" })
    public void testStringConstantLexeme() {
        String code = "DIV d, \"asd\"";
        List<Pair<String, String>> result = analyzer.analyze(code);
        Assert.assertEquals(result.get(3).getFirst(), "\"asd\"");
        Assert.assertEquals(result.get(3).getSecond(), "String");
    }

    @Test(groups = { "badInput", "1" })
    public void testIncorrectCodeAnalysis() {
        String code = "<>";
        List<Pair<String, String>> result = analyzer.analyze(code);
        Assert.assertEquals(result.get(0).getFirst(), "<>");
        Assert.assertEquals(result.get(0).getSecond(), "Error");
    }

    @Test(expectedExceptions=IllegalArgumentException.class, groups = { "badInput", "2" })
    public void testEmptyCodeAnalysis() {
        String code = "";
        List<Pair<String, String>> result = analyzer.analyze(code);
        Assert.assertEquals(result.get(0).getFirst(), "<>");
    }
}

