import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TextParserTest {

    TextParser textParser = new TextParser();

    /**
     * This method tests non-header text
     */
    @Test
    void parseInputNonHeaderText() {
        textParser.parseInputText("Testing non header text");
        Assert.assertArrayEquals(new String[]{"<p>Testing non header text</p>"}, new String[]{textParser.textToAddList.get(0)});
    }


    /**
     * This method tests empty text
     */
    @Test
    void parseInputEmptyText() {
        textParser.parseInputText("");
        Assert.assertArrayEquals(new String[]{""}, new String[]{textParser.textToAddList.get(0)});
    }


    /**
     * This method tests different header text
     */
    @Test
    void parseInputHeaderText() {
        textParser.parseInputText("# Testing h1 header text");
        textParser.parseInputText("## Testing h2 header text");
        textParser.parseInputText("### Testing h3 header text");
        textParser.parseInputText("#### Testing h4 header text");
        textParser.parseInputText("##### Testing h5 header text");
        textParser.parseInputText("###### Testing h6 header text");
        textParser.parseInputText("###This is not a header text");
        Assert.assertArrayEquals(new String[]{"<h1>Testing h1 header text</h1>"}, new String[]{textParser.textToAddList.get(0)});
        Assert.assertArrayEquals(new String[]{"<h2>Testing h2 header text</h2>"}, new String[]{textParser.textToAddList.get(1)});
        Assert.assertArrayEquals(new String[]{"<h3>Testing h3 header text</h3>"}, new String[]{textParser.textToAddList.get(2)});
        Assert.assertArrayEquals(new String[]{"<h4>Testing h4 header text</h4>"}, new String[]{textParser.textToAddList.get(3)});
        Assert.assertArrayEquals(new String[]{"<h5>Testing h5 header text</h5>"}, new String[]{textParser.textToAddList.get(4)});
        Assert.assertArrayEquals(new String[]{"<h6>Testing h6 header text</h6>"}, new String[]{textParser.textToAddList.get(5)});
        Assert.assertArrayEquals(new String[]{"<p>###This is not a header text</p>"}, new String[]{textParser.textToAddList.get(6)});
    }


    /**
     * This method tests text with link
     */
    @Test
    void parseInputTextWithLink() {
        textParser.parseInputText("This is a text [with a link](http://yahoo.com)");
        Assert.assertArrayEquals(new String[]{"<p>This is a text <a href=\"http://yahoo.com\">with a link</a></p>"}, new String[]{textParser.textToAddList.get(0)});
    }

    /**
     * This method tests text with multiple link
     */
    @Test
    void parseInputTextWithMultipleLinks() {
        textParser.parseInputText("This is a [First link](http://first.com) [Second link](http://second.com) [Third link](http://third.com)");
        Assert.assertArrayEquals(new String[]{"<p>This is a <a href=\"http://first.com\">First link</a> <a href=\"http://second.com\">Second link</a> <a href=\"http://third.com\">Third link</a></p>"}, new String[]{textParser.textToAddList.get(0)});
    }

    /**
     * This method tests header text with link
     */
    @Test
    void parseInputTextWithHeaderLink() {
        textParser.parseInputText("# This is a text [with a link](http://yahoo.com)");
        Assert.assertArrayEquals(new String[]{"<h1>This is a text <a href=\"http://yahoo.com\">with a link</a></h1>"}, new String[]{textParser.textToAddList.get(0)});
    }

    /**
     * This method tests text with multiple lines
     */
    @Test
    void parseInputTextWithMultipleLines() {
        textParser.parseInputText("# This is first line");
        textParser.parseInputText("");
        textParser.parseInputText("This is a line [with a link](http://yahoo.com) ");
        Assert.assertArrayEquals(new String[]{"<h1>This is first line</h1>"}, new String[]{textParser.textToAddList.get(0)});
        Assert.assertArrayEquals(new String[]{""}, new String[]{textParser.textToAddList.get(1)});
        Assert.assertArrayEquals(new String[]{"<p>This is a line <a href=\"http://yahoo.com\">with a link</a> </p>"}, new String[]{textParser.textToAddList.get(2)});
    }

    /**
     * This method tests text with different edge cases
     */
    @Test
    void parseInputTextEdgeCases() {
        textParser.parseInputText("This is not# a header");
        textParser.parseInputText("This is not (a link)[https://yahoo.com]");
        textParser.parseInputText("Text with #$Ym|30L$. !!");
        textParser.parseInputText("Text with [Empty Link]()");
        Assert.assertArrayEquals(new String[]{"<p>This is not# a header</p>"}, new String[]{textParser.textToAddList.get(0)});
        Assert.assertArrayEquals(new String[]{"<p>This is not (a link)[https://yahoo.com]</p>"}, new String[]{textParser.textToAddList.get(1)});
        Assert.assertArrayEquals(new String[]{"<p>Text with #$Ym|30L$. !!</p>"}, new String[]{textParser.textToAddList.get(2)});
        Assert.assertArrayEquals(new String[]{"<p>Text with <a href=\"\">Empty Link</a></p>"}, new String[]{textParser.textToAddList.get(3)});

    }
}