package org.example.test;

import org.testng.annotations.DataProvider;

public class LexicalAnalyzerDataProvider {

    @DataProvider(name="data-provider")
    public static Object[][] dataProviderMethod()
    {
        return new Object[][] {
                {"MOV", "Reserved word"},
                {"a", "Identifier"},
                {"; a", "Comment"},
                {"5", "Dec number"},
                {"0x10", "Hex number"},
                {"\"Hello\"", "String"},
                {"'a'", "Char"},
                {"#directive", "Preprocessor"},
                {",", "Separator"},
                {"5.5", "Float number"}
        };
    }
}
