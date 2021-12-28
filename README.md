# Markdown To HTML

The project MarkdownToHTML is a parser which accepts different markdown text by reading a text file 
and converts it in HTML format and stores it in new output file

### Requirements ###
* Java
* Maven
* idea 

### Setup ###
* Clone this repository: ```git clone https://github.com/sameerpatil2006/MarkdownToHTML-Parser.git```
* Install all dependencies from the root directory: ```mvn install```

### Running Project
* Open cloned project in any idea 
* Update input file (src/main/files/inputFile.txt) with desired text
* Run MarkdownConverter.java class (right click MarkdownConverter and click run)
* After run the text added in input file will be converted to HTML format and stored in output file (src/main/files/outputFile.txt)

### Project Structure and Details
* This is a maven project, all dependencies are in pom.xml
* src directory has 2 directories main and test
* main directory has source code, files (input,output) and test directory has unit tests
* MarkdownConverter class has main method and it reads input file and calls TextParser class
* TextParser class parse text and formats with appropriate html tags
* FileCreator class creates new text file and stores all formatted text by TextParser
* TextParserTest class has unit tests for TextParser class 
* Input File is in directory (src/main/files/inputFile.txt)  
* Output File is in directory (src/main/files/outputFile.txt)
