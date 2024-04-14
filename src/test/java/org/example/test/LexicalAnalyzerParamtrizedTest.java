package org.example.test;

import org.example.LexicalAnalyzer;
import org.example.Pair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class LexicalAnalyzerParamtrizedTest {
    private LexicalAnalyzer analyzer;

    @BeforeClass
    public void setUpClass() {
        analyzer = new LexicalAnalyzer();
    }

    @Test(dataProvider = "data-provider", dataProviderClass = LexicalAnalyzerDataProvider.class)
    public void testParameterizedAnalysis(String data, String lexeme) {
        List<Pair<String, String>> result = analyzer.analyze(data);
        Assert.assertEquals(result.get(0).getSecond(), lexeme);
    }
}
