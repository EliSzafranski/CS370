package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    @Test
    public void testCountNumbers1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(7, mycustomstring.countNumbers());
    }

    /**
     * Test to check if empty string returns 0
     */
    @Test
    public void testCountNumbers2() {
        mycustomstring.setString("");
        assertEquals(0, mycustomstring.countNumbers());
    }

    /**
     * Test to check if NullPointerException is thrown when string is null
     */
    @Test (expected = NullPointerException.class)
    public void testCountNumbers3() {
        mycustomstring.setString(null);
        mycustomstring.countNumbers();
    }

    /**
     * Test to check if one long continuous number returns 1
     */
    @Test
    public void testCountNumbers4() {
        mycustomstring.setString("123445678910111213");
        assertEquals(1, mycustomstring.countNumbers());
    }

    /**
     * Test if escape characters confuse method
     */
    @Test
    public void testCountNumbers5() {
        mycustomstring.setString("6\n45 h3ll0\t9");
        assertEquals(5, mycustomstring.countNumbers());
    }

    /**
     * Test if there are floating point numbers, the method will will count each portion of the decimal separately
     */
    @Test
    public void testCountNumbers6() {
        mycustomstring.setString("Always look on the bright side of life, Monty Python 2.7.3");
        assertEquals(3, mycustomstring.countNumbers());
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("d33p md1  i51,it", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd2() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'bt t0 6snh r6rh", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }

    /**
     * Test to make sure IllegalArgumentException is thrown
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd3() {
        mycustomstring.setString("Th1s 2th3 j4");
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(-1, false);
    }

    /**
     * Test to make sure NullPointerException is thrown
     */
    @Test (expected = NullPointerException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd4() {
        mycustomstring.setString(null);
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, false);
    }

    /**
     * Test for escape characters starting from beginning
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd5() {
        mycustomstring.setString("She1l\tu2\nel3\nhe4io");
        assertEquals("1234", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, false));
    }

    /**
     * Test for ending on last character which is escape character
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd6() {
        mycustomstring.setString("Finally some peace and quie\n");
        assertEquals("ypn\n", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(7, false));
    }

    /**
     * Test for ending on first character which is an escape character
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd7() {
        mycustomstring.setString("\ninally some peace and quiet");
        assertEquals("\n ed", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(7, true));

    }

    /**
     * Test with input as empty string to ensure no errors are thrown and empty string is returned
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd8() {
        mycustomstring.setString("");
        assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, false));
    }

    /**
     * Test with input as non-empty string but n > string length
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd9() {
        mycustomstring.setString("Oh hello there");
        assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(15, false));
    }

    /**
     * Test when n = 1 and startFromEnd is true
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd10() {
        mycustomstring.setString("Hello! My name is Elder Price And I would like to share with you The most amazing book");
        assertEquals("Hello! My name is Elder Price And I would like to share with you The most amazing book", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, true));
    }

    /**
     * Test for only special characters
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd11() {
        mycustomstring.setString("\t\n\'\"\\\n\b\n");
        assertEquals("\"\n", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, false));
    }

    /**
     * Test for an even longer string and startFromEnd is true
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd12() {
        mycustomstring.setString("I am going to quickly compare and contrast these 3 ‘build’ tools (for the Java world). The reason i am going to outline this comparison is a lot of people seem not to understand the difference between these 3.\n" +
                "I put ‘build’ in inverted commas is because these 3 individually are unique in their own ways. Maven, is more than just a ‘build’ tool. But most people refer to them as ‘build’ tool and use them for that particular purpose.\n" +
                "There are many more java oriented ‘build’ tools available out there like Ivy and Buildr. Also, many more other related build tools which target the whole JVM-based ecosystem such as sbt (Scala), Grape(Groovy), Leiningen (Clojure).\n" +
                "I particular highlight these 3 tools is because over the years, they are the most widely used as time gone past. There are already numerous Ant vs Maven vs Gradle comparisons out there exists in existing blog posts, tech articles, stackoverflow questions and i am not re-inventing the wheel.\n" +
                "But rather, i just want to get this off my chest and offer my own personal perspective on this. Put simply, i want to share my thoughts on this topic based on my personal experience and learning gained throughout the years.");
        assertEquals("imt aetotn e n iuwsaufuea.mdveimdcly r t   tsplnaoiplw itwoooolr  xru", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(17, true));
    }

    /**
     * Test on an even longer string and startFromEnd is true
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd13() {
        mycustomstring.setString("I am going to quickly compare and contrast these 3 ‘build’ tools (for the Java world). The reason i am going to outline this comparison is a lot of people seem not to understand the difference between these 3.\n" +
                "I put ‘build’ in inverted commas is because these 3 individually are unique in their own ways. Maven, is more than just a ‘build’ tool. But most people refer to them as ‘build’ tool and use them for that particular purpose.\n" +
                "There are many more java oriented ‘build’ tools available out there like Ivy and Buildr. Also, many more other related build tools which target the whole JVM-based ecosystem such as sbt (Scala), Grape(Groovy), Leiningen (Clojure).\n" +
                "I particular highlight these 3 tools is because over the years, they are the most widely used as time gone past. There are already numerous Ant vs Maven vs Gradle comparisons out there exists in existing blog posts, tech articles, stackoverflow questions and i am not re-inventing the wheel.\n" +
                "But rather, i just want to get this off my chest and offer my own personal perspective on this. Put simply, i want to share my thoughts on this topic based on my personal experience and learning gained throughout the years.\n" +
                "Perhaps quite an old-fashioned build tool nowadays (2018) and i certainly remember very well during my university days way back in 2006 my lecturers were well advocating this tool. And even when i started my career nearly 10 years ago now, the company i was working for, the project i was in was using this tool massively. And then i was told it was mainstream.\n" +
                "This tool builds upon the idea of “Configuration’”unlike other tools such as Maven which you’ll see later is by “Convention”. To work it you need to write/code the ‘actions’ you want the ‘build’ process to take. i.e. configuration.\n" +
                "Now, lets switch our focus to Maven before jumping on Gradle. Without checking on the actual history of Maven and Gradle, as far as i know Maven existed before Gradle. Not long after Ant, Maven came so popular. So popular that nowadays it is considered the de-facto build tool for all Java projects.\n" +
                "Although, it is exactly not just a build tool but a complete software ‘project management system’. In a nutshell, the way it works is it helps you manage your dependencies by storing all artifacts on a repository (Maven Central Repository). You specify your ‘build’ file (it is called `pom.xml`).\n"+
                "In your pom.xml file you list the dependencies you need. It system features a ‘plugin’ architecture that allows you to put in place plugins that customizes the build lifecycle process. Not like Ant, Maven uses a convention build lifecycle process. If you want to change aspects of it, you plugin in plugins and specify the configuration details.\n"+
                "Yet still, it is quite rigid and it follows a convention rather than giving you the flexibility to configure things. Hence, Maven is so-called “Convention over Configuration”.\n" +
                "I see it is as the totally opposite to Ant.");
        assertEquals(" erleibs  ttlalAd-au ,  ostte ewtn a)la eershT“ t noerkne  no bata vf y.cihki iisv Hii", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(34, true));
    }

    /**
     * Test on an even longer string and startFromEnd is false
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd14() {
        mycustomstring.setString("I am going to quickly compare and contrast these 3 ‘build’ tools (for the Java world). The reason i am going to outline this comparison is a lot of people seem not to understand the difference between these 3.\n" +
                "I put ‘build’ in inverted commas is because these 3 individually are unique in their own ways. Maven, is more than just a ‘build’ tool. But most people refer to them as ‘build’ tool and use them for that particular purpose.\n" +
                "There are many more java oriented ‘build’ tools available out there like Ivy and Buildr. Also, many more other related build tools which target the whole JVM-based ecosystem such as sbt (Scala), Grape(Groovy), Leiningen (Clojure).\n" +
                "I particular highlight these 3 tools is because over the years, they are the most widely used as time gone past. There are already numerous Ant vs Maven vs Gradle comparisons out there exists in existing blog posts, tech articles, stackoverflow questions and i am not re-inventing the wheel.\n" +
                "But rather, i just want to get this off my chest and offer my own personal perspective on this. Put simply, i want to share my thoughts on this topic based on my personal experience and learning gained throughout the years.\n" +
                "Perhaps quite an old-fashioned build tool nowadays (2018) and i certainly remember very well during my university days way back in 2006 my lecturers were well advocating this tool. And even when i started my career nearly 10 years ago now, the company i was working for, the project i was in was using this tool massively. And then i was told it was mainstream.\n" +
                "This tool builds upon the idea of “Configuration’”unlike other tools such as Maven which you’ll see later is by “Convention”. To work it you need to write/code the ‘actions’ you want the ‘build’ process to take. i.e. configuration.\n" +
                "Now, lets switch our focus to Maven before jumping on Gradle. Without checking on the actual history of Maven and Gradle, as far as i know Maven existed before Gradle. Not long after Ant, Maven came so popular. So popular that nowadays it is considered the de-facto build tool for all Java projects.\n" +
                "Although, it is exactly not just a build tool but a complete software ‘project management system’. In a nutshell, the way it works is it helps you manage your dependencies by storing all artifacts on a repository (Maven Central Repository). You specify your ‘build’ file (it is called `pom.xml`).\n"+
                "In your pom.xml file you list the dependencies you need. It system features a ‘plugin’ architecture that allows you to put in place plugins that customizes the build lifecycle process. Not like Ant, Maven uses a convention build lifecycle process. If you want to change aspects of it, you plugin in plugins and specify the configuration details.\n"+
                "Yet still, it is quite rigid and it follows a convention rather than giving you the flexibility to configure things. Hence, Maven is so-called “Convention over Configuration”.\n" +
                "I see it is as the totally opposite to Ant.\n"+
                "Finally we are onto Gradle.\n" +
                "From what i can remember, Gradle slowly became popular over the last 3–4 years. Some people are starting to favor Gradle simply because in my own words:\n" +
                "“Gradle has the benefits of both words in that it follows Maven’s convention model but yet offers the flexibility of configuration that Ant has”\n" +
                "So, in other words, Gradle is both Convention & Configuration.\n" +
                "In the past, people use Ant because it is highly flexible with its configuration capabilities (in that you can manually define the build steps to the way you want it to). And people choose Maven because of the simplicity model of following a convention without spending so much time in writing so-called build scripts in Ant.\n" +
                "But what about if you want to follow a convention but yet there are some small parts you want to customize fully.");
        assertEquals("n o fdeo‘oo siu- Iih.aerirystsrrom ee kiabnhoepNeknstt-ta‘sognulealten ugi tirprlt  onhAs itnweesaais", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(37, false));
    }

    @Test
    public void testConvertDigitsToNamesInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
    }

    /**
     * Test to make sure method throws IllegalArgumentException
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConvertDigitsToNamesInSubstring2() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(23, 17);
    }
    /**
     * Test to make sure method throws MyIndexOutOfBoundsException
     */
    @Test (expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring3() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(0, 1000);
    }
    /**
     * Test to make sure method throws NullPointerException
     */
    @Test (expected = NullPointerException.class)
    public void testConvertDigitsToNamesInSubstring4() {
        mycustomstring.setString(null);
        mycustomstring.convertDigitsToNamesInSubstring(5, 10);
    }

    /**
     * Test to make sure first and last characters are converted
     */
    @Test
    public void testConvertDigitsToNamesInSubstring5() {
        mycustomstring.setString("1 two 3");
        mycustomstring.convertDigitsToNamesInSubstring(1,7);
        assertEquals("One two Three", mycustomstring.getString());

    }

    /**
     * Test when all characters are digits and try to convert whole string
     */
    @Test
    public void testConvertDigitsToNamesInSubstring6() {
        mycustomstring.setString("0123456789");
        mycustomstring.convertDigitsToNamesInSubstring(1,10);
        assertEquals("ZeroOneTwoThreeFourFiveSixSevenEightNine", mycustomstring.getString());
    }

    /**
     * Test where string contains escape characters
     */
    @Test
    public void testConvertDigitsToNamesInSubstring7() {
        mycustomstring.setString("One\ttwo\t3\n4\t");
        mycustomstring.convertDigitsToNamesInSubstring(5,12);
        assertEquals("One\ttwo\tThree\nFour\t", mycustomstring.getString());
    }

    /**
     * Test when startPosition is equal to endPosition
     */
    @Test
    public void testConvertDigitsToNamesInSubstring8() {
        mycustomstring.setString("12");
        mycustomstring.convertDigitsToNamesInSubstring(1,1);
        assertEquals("One2", mycustomstring.getString());
    }

}