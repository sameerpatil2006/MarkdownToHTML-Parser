import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    List<String> linksMatchList = new ArrayList(); // This is to handle multiple links in one line of text
    List<String> textToAddList = new ArrayList<>(); // Storing all the final formatted text in list ready to put in output file
    FileCreator createFile = new FileCreator();
    String linkText, link;
    boolean flag = false;

    /**
     * This method parses text extracted from input file
     *
     * @param inputText
     * @throws IOException
     */
    public void parseInputText(String inputText) {
        String textAfterRemovingMarkDown, textAfterAddingTag;
        flag = checkLinkPattern(inputText);
        if (inputText.startsWith("###### ")) {
            textAfterRemovingMarkDown = removeMarkDown("###### ", inputText);
            if (flag) {
                //To handle multiple links on same line
                while (!linksMatchList.isEmpty()) {
                    extractLinkText();
                    textAfterRemovingMarkDown = addLinkTag(textAfterRemovingMarkDown);
                }
            }
            textAfterAddingTag = addTag("h6", textAfterRemovingMarkDown);
            addToList(textAfterAddingTag);
        } else if (inputText.startsWith("##### ")) {
            textAfterRemovingMarkDown = removeMarkDown("##### ", inputText);
            if (flag) {
                //To handle multiple links on same line
                while (!linksMatchList.isEmpty()) {
                    extractLinkText();
                    textAfterRemovingMarkDown = addLinkTag(textAfterRemovingMarkDown);
                }
            }
            textAfterAddingTag = addTag("h5", textAfterRemovingMarkDown);
            addToList(textAfterAddingTag);
        } else if (inputText.startsWith("#### ")) {
            textAfterRemovingMarkDown = removeMarkDown("#### ", inputText);
            if (flag) {
                //To handle multiple links on same line
                while (!linksMatchList.isEmpty()) {
                    extractLinkText();
                    textAfterRemovingMarkDown = addLinkTag(textAfterRemovingMarkDown);
                }
            }
            textAfterAddingTag = addTag("h4", textAfterRemovingMarkDown);
            addToList(textAfterAddingTag);
        } else if (inputText.startsWith("### ")) {
            textAfterRemovingMarkDown = removeMarkDown("### ", inputText);
            if (flag) {
                //To handle multiple links on same line
                while (!linksMatchList.isEmpty()) {
                    extractLinkText();
                    textAfterRemovingMarkDown = addLinkTag(textAfterRemovingMarkDown);
                }
            }
            textAfterAddingTag = addTag("h3", textAfterRemovingMarkDown);
            addToList(textAfterAddingTag);
        } else if (inputText.startsWith("## ")) {
            textAfterRemovingMarkDown = removeMarkDown("## ", inputText);
            if (flag) {
                //To handle multiple links on same line
                while (!linksMatchList.isEmpty()) {
                    extractLinkText();
                    textAfterRemovingMarkDown = addLinkTag(textAfterRemovingMarkDown);
                }
            }
            textAfterAddingTag = addTag("h2", textAfterRemovingMarkDown);
            addToList(textAfterAddingTag);
        } else if (inputText.startsWith("# ")) {
            textAfterRemovingMarkDown = removeMarkDown("# ", inputText);
            if (flag) {
                //To handle multiple links on same line
                while (!linksMatchList.isEmpty()) {
                    extractLinkText();
                    textAfterRemovingMarkDown = addLinkTag(textAfterRemovingMarkDown);
                }
            }
            textAfterAddingTag = addTag("h1", textAfterRemovingMarkDown);
            addToList(textAfterAddingTag);
        } else if (!inputText.isEmpty()) {
            if (flag) {
                //To handle multiple links on same line
                while (!linksMatchList.isEmpty()) {
                    extractLinkText();
                    inputText = addLinkTag(inputText);
                }
            }
            textAfterAddingTag = addTag("p", inputText);
            addToList(textAfterAddingTag);
        } else {
            addToList(inputText);
        }
    }

    /**
     * This method adds formatted text to list and
     *
     * @param stringToAdd
     */
    public void addToList(String stringToAdd) {
        textToAddList.add(stringToAdd);
    }

    /**
     * This method converts formetted text list to output file
     *
     * @throws IOException
     */
    public void addFormattedTextListToFile() throws IOException {
        createFile.createFile(textToAddList);
    }

    /**
     * This method adds tag and text
     *
     * @param tagToAdd
     * @param text
     * @return
     */
    public String addTag(String tagToAdd, String text) {
        text = "<" + tagToAdd + ">" + text + "</" + tagToAdd + ">";
        return text;
    }

    /**
     * This method removes markdown symbols and returns plain text
     *
     * @param markDownToRemove
     * @param text
     * @return
     */
    public String removeMarkDown(String markDownToRemove, String text) {
        text = text.replaceFirst(markDownToRemove, "");
        return text;
    }

    /**
     * This method checks if given text has link markdown and returns flag accordingly
     *
     * @param inputText
     * @return
     */
    public boolean checkLinkPattern(String inputText) {
        Pattern pattern = Pattern.compile("\\[.*?\\]\\(.*?\\)");
        Matcher matcher = pattern.matcher(inputText);
        while (matcher.find()) {//Finds Matching Pattern in String
            linksMatchList.add(matcher.group(0));//Fetching Group from String
        }
        if (!linksMatchList.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * This method extracts link label and link and stores in variable
     */
    public void extractLinkText() {
        String text = linksMatchList.get(0);
        linkText = StringUtils.substringBetween(text, "[", "]");
        link = StringUtils.substringBetween(text, "(", ")");
        linksMatchList.remove(0);
    }

    /**
     * This method adds link tags to text
     *
     * @param textAfterRemovingMarkDown
     * @return
     */
    public String addLinkTag(String textAfterRemovingMarkDown) {
        return textAfterRemovingMarkDown.replaceFirst("\\[.*?\\]\\(.*?\\)", "<a href=\"" + link + "\">" + linkText + "</a>");
    }
}
