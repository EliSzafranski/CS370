package edu.qc.seclass.replace;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class MyMainTest {
    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again! How unique!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice");

        fileWriter.close();
        return file1;
    }

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123. How unique!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile4() throws Exception{
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Counting down and down and down the days till graduation");
        fileWriter.close();
        return file1;
    }

    private File createInputFile5() throws Exception{
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("This & that are both euphamisms for french: @#$%!");
        fileWriter.close();
        return file1;
    }

    private File createInputFile6() throws Exception{
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Hyphenated-words have no space between the words and the -dash");
        fileWriter.close();
        return file1;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

//  catpart.txt.tsl Test Case #10
    @Test
    public void myMainTest1() throws Exception {
        File inputFile1 = createInputFile3();
        File inputFile2 = createInputFile2();

        String args[] = {"-b","-f", "-l", "-i" ,"important", "ABCDEFG", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is ABCDEFG to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123. How unique!";

        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //  catpart.txt.tsl Test Case #11
    @Test
    public void myMainTest2() throws Exception {
        File inputFile1 = createInputFile3();

        String args[] = {"-b","-f", "-l","important", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is ABCDEFG to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123. How unique!";

        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //  catpart.txt.tsl Test Case #15
    @Test
    public void myMainTest3() throws Exception {
        File inputFile1 = createInputFile3();

        String args[] = {"-f", "-l","important", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is ABCDEFG to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123. How unique!";

        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //  catpart.txt.tsl Test Case #35
    @Test
    public void myMainTest4() throws Exception {
        File inputFile1 = createInputFile3();

        String args[] = {"-l","important", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is ABCDEFG to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123. How unique!";

        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //  catpart.txt.tsl Test Case #40
    @Test
    public void myMainTest5() throws Exception {
        File inputFile1 = createInputFile3();
        File inputFile2 = createInputFile1();

        String args[] = {"unique", "ABCDEFG", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123. How ABCDEFG!";
        String expected2 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again! How ABCDEFG!";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    //  catpart.txt.tsl Test Case #41
    @Test
    public void myMainTest6() throws Exception {
        File inputFile1 = createInputFile3();

        String args[] = {"-b","-f", "-l", "-i", "abc", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your ABCDEFG and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: ABCDEFG and 123. How unique!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //  catpart.txt.tsl Test Case #57
    @Test
    public void myMainTest7() throws Exception {
        File inputFile1 = createInputFile3();

        String args[] = {"-f", "-l", "-i", "abc", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your ABCDEFG and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: ABCDEFG and 123. How unique!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual1);
    }

    //  catpart.txt.tsl Test Case 58
    @Test
    public void myMainTest8() throws Exception {
        File inputFile1 = createInputFile3();
        File inputFile2 = createInputFile1();

        String args[] = {"-f", "-l", "-i", "Bill", "ABCDEFG", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected = "Howdy ABCDEFG, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123. How unique!";
        String expected2 = "Howdy ABCDEFG,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy ABCDEFG\" again! How unique!";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    //  catpart.txt.tsl Test Case #60
    @Test
    public void myMainTest9() throws Exception {
        File inputFile1 = createInputFile3();
        File inputFile2 = createInputFile1();

        String args[] = {"-f", "-l", "Bill", "ABCDEFG", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected = "Howdy ABCDEFG, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123. How unique!";
        String expected2 = "Howdy ABCDEFG,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again! How unique!";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    //  catpart.txt.tsl Test Case #71
    @Test
    public void myMainTest10() throws Exception {
        File inputFile1 = createInputFile3();

        String args[] = {"abc", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your ABCDEFG and 123?\n" +
                "It is important to know your ABCDEFG and 123," +
                "so you should study it\n" +
                "and then repeat with me: ABCDEFG and 123. How unique!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual1);
    }

    //  Testing no Options with empty character in <TO> slot and space character in <FROM> slot in multiple locations
    @Test
    public void myMainTest11() throws Exception {
        File inputFile1 = createInputFile3();

        String args[] = {"", "OH HELLO THERE", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123. How unique!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual1);
    }

    //  Testing no Options with space character in <TO> slot in multiple locations
    @Test
    public void myMainTest12() throws Exception {
        File inputFile1 = createInputFile3();

        String args[] = {"abc", " ", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your   and 123?\n" +
                "It is important to know your   and 123," +
                "so you should study it\n" +
                "and then repeat with me:   and 123. How unique!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual1);
    }

    //  Testing no Options with space character in <FROM> slot and empty string in <TO> slot
    @Test
    public void myMainTest13() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {" ", "", "--", inputFile1.getPath()};
        Main.main(args);

        String expected = "Countingdownanddownanddownthedaystillgraduation";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual1);
    }

//  Check -i OPT with multiple files
    @Test
    public void myMainTest14() throws Exception {
        File inputFile1 = createInputFile4();
        File inputFile2 = createInputFile1();

        String args[] = {"-i", "THE", "ABCDEFG", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Counting down and down and down ABCDEFG days till graduation";
        String expected2 = "Howdy Bill,\n" +
                "This is a test file for ABCDEFG replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again! How unique!";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

//  Test if file is not given
    @Test
    public void myMainTest15() throws Exception{
        String args[] = {"abc", "ABCDEFG"};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    //  Test if single file is not found
    @Test
    public void myMainTest16() throws Exception{
        File inputFile = createInputFile1();
        inputFile.delete();
        String args[] = {"abc", "ABCDEFG", "--", inputFile.getPath()};
        Main.main(args);
        assertEquals("File " + inputFile.getName() + " not found", errStream.toString().trim());
    }

    //  Test if single file from multiple files is not found
    @Test
    public void myMainTest17() throws Exception{
        File inputFile1 = createInputFile5();
        File inputFile2 = createInputFile2();
        inputFile2.delete();
        String args[] = {"that", "ABCDEFG", "--", inputFile2.getPath(), inputFile1.getPath()};

        Main.main(args);

        String expected1 = "This & ABCDEFG are both euphamisms for french: @#$%!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("File " + inputFile2.getName() + " not found", errStream.toString().trim());
        assertEquals("The files differ!", expected1, actual1);
    }

    //  Test -f OPT with single file where it is the first word
    @Test
    public void myMainTest18() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-f", "Counting", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "ABCDEFG down and down and down the days till graduation";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //  Test <FROM> is a single character
    @Test
    public void myMainTest19() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"a", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is ABCDEFG test file for the replABCDEFGce utility\n" +
                "Let's mABCDEFGke sure it hABCDEFGs ABCDEFGt leABCDEFGst ABCDEFG few lines\n" +
                "so thABCDEFGt we cABCDEFGn creABCDEFGte some interesting test cABCDEFGses...\n" +
                "And let's sABCDEFGy \"howdy bill\" ABCDEFGgABCDEFGin! How unique!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //  Test <TO> is a single character
    @Test
    public void myMainTest20() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"Counting", "a", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "a down and down and down the days till graduation";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //  Test when there are no changes to be made
    @Test
    public void myMainTest21() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"Phytoplankten", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Counting down and down and down the days till graduation";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

// Test when there are no changes to be made and -b OPT
    @Test
    public void myMainTest22() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-b", "Phytoplankten", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Counting down and down and down the days till graduation";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    // Test when <FROM> is a single special character
    @Test
    public void myMainTest23() throws Exception {
        File inputFile1 = createInputFile5();

        String args[] = {"&", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "This ABCDEFG that are both euphamisms for french: @#$%!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    // Test when <FROM> contains multiple special characters
    @Test
    public void myMainTest24() throws Exception {
        File inputFile1 = createInputFile5();

        String args[] = {"@#$%!", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "This & that are both euphamisms for french: ABCDEFG";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    // Test when <TO> contains multiple special characters
    @Test
    public void myMainTest25() throws Exception {
        File inputFile1 = createInputFile5();

        String args[] = {"that", "!@#$%^&*()", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "This & !@#$%^&*() are both euphamisms for french: @#$%!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    // Test when <TO> is a single special character
    @Test
    public void myMainTest26() throws Exception {
        File inputFile1 = createInputFile5();

        String args[] = {"that", "@", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "This & @ are both euphamisms for french: @#$%!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    // Test to make sure only changed files are backed up when -b OPT is called
    @Test
    public void myMainTest27() throws Exception {
        File inputFile1 = createInputFile5();
        File inputFile2 = createInputFile4();

        String args[] = {"-b", "french", "ZEBRA", "--", inputFile2.getPath(), inputFile1.getPath()};
        Main.main(args);

        String expected1 = "This & that are both euphamisms for ZEBRA: @#$%!";
        String expected2 = "Counting down and down and down the days till graduation";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }

    // Test to check if the separator is not given before the files
    @Test
    public void myMainTest28() throws Exception{
        File inputFile1 = createInputFile5();
        String args[] = {"abc", "ABCDEFG", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    // Test to check if anything follows the file separator symbol
    @Test
    public void myMainTest29() throws Exception{
        String args[] = {"abc", "ABCDEFG", "--"};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    // Test to check if empty array is passed
    @Test
    public void myMainTest30() throws Exception {
        String args[] = {};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    // Test for a <FROM> phrase with space in it
    @Test
    public void myMainTest31() throws Exception {
        File inputFile1 = createInputFile5();

        String args[] = {"are both", "ABCDEFG", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "This & that ABCDEFG euphamisms for french: @#$%!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
    // Test for a <TO> phrase with space in it
    @Test
    public void myMainTest32() throws Exception {
        File inputFile1 = createInputFile5();

        String args[] = {"french", "Maginot Line", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "This & that are both euphamisms for Maginot Line: @#$%!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }


    // Test for when <TO> and <FROM> are phrases with spaces in them
    @Test
    public void myMainTest33() throws Exception {
        File inputFile1 = createInputFile5();

        String args[] = {"are both", "Maginot Line", "--", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "This & that Maginot Line euphamisms for french: @#$%!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    //Test for when no <TO> or <FROM> or OTP are given
    @Test
    public void myMainTest34() throws Exception {
        File inputFile1 = createInputFile5();

        String args[] = {"--", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    //Test for when no <TO> or <FROM> are given, but OTP are given
    @Test
    public void myMainTest35() throws Exception {
        File inputFile1 = createInputFile5();

        String args[] = {"-i", "-f", "--", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    // Test for when three words are given as input
    @Test
    public void myMainTest36() throws Exception{
        File inputFile1 = createInputFile5();

        String args[]= {"-i", "and", "now", "for", "--", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    // Testing replacing a dash with a backup
    @Test
    public void myMainTest37() throws Exception{
        File inputFile1 = createInputFile6();
        String args[] = {"-b", "--", "-dash", "something-completely-different", "--" , inputFile1.getPath()};
        Main.main(args);
        String expected = "Hyphenated-words have no space between the words and the something-completely-different";
        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    // Testing replacing a dash with -i, -f, -l,
    @Test
    public void myMainTest38() throws Exception{
        File inputFile1 = createInputFile6();
        String args[] = {"-f", "-i", "-l", "-dash", "something-completely-different", "--" , inputFile1.getPath()};
        Main.main(args);
        String expected = "Hyphenated-words have no space between the words and the something-completely-different";
        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

//  Testing replacing a hyphenated word
    @Test
    public void myMainTest39() throws Exception{
        File inputFile1 = createInputFile6();
        String args[] = {"Hyphenated-words", "something-completely-different", "--" , inputFile1.getPath()};
        Main.main(args);
        String expected = "something-completely-different have no space between the words and the -dash";
        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }

    // Test replacing spaces with hyphens
    @Test
    public void myMainTest40() throws Exception {
        File inputFile1 = createInputFile4();
        String args[] = {" ", "-", "--", inputFile1.getPath()};
        Main.main(args);
        String expected = "Counting-down-and-down-and-down-the-days-till-graduation";
        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
}
