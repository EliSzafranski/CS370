package edu.qc.seclass;

import java.util.HashMap;

public class MyCustomString implements MyCustomStringInterface{

    private String customString;

    /**
     * Returns the current string. If the string is null, it should return null.
     *
     * @return Current string
     */
    @Override
    public String getString() {
        return this.customString;
    }

    /**
     * Sets the value of the current string.
     *
     * @param string The value to be set
     */
    @Override
    public void setString(String string) {
        this.customString = string;

    }

    /**
     * Returns the number of numbers in the current string, where a number is defined as a
     * contiguous sequence of digits.
     * <p>
     * If the current string is empty, the method should return 0.
     * <p>
     * Examples:
     * - countNumbers would return 2 for string "My numbers are 11 and 96".
     *
     * @return Number of numbers in the current string
     * @throws NullPointerException If the current string is null
     */
    @Override
    public int countNumbers() {
        if(this.customString == null ){
            throw new NullPointerException("String is null");
        }
        else if(this.customString.length() == 0){
            return 0;
        }
        int count = 0;
        boolean isNumber = false;

        for(int i = 0; i < this.customString.length(); i++){
            if(Character.isDigit(this.customString.charAt(i)) && !isNumber){
                count++;
                isNumber = true;
            }
            else if(!Character.isDigit(this.customString.charAt(i)) && isNumber){
                isNumber = false;
            }
        }
        return count;
    }

    /**
     * Returns a string that consists of all and only the characters in positions n, 2n, 3n, and
     * so on in the current string, starting either from the beginning or from the end of the
     * string. The characters in the resulting string should be in the same order and with the
     * same case as in the current string.
     * <p>
     * If the current string is empty or has less than n characters, the method should return an
     * empty string.
     * <p>
     * Examples:
     * - For n=2 and a string of one character, the method would return an empty string.
     * - For n=2 and startFromEnd=false, the method would return the 2nd, 4th, 6th, and so on
     * characters in the string.
     * - For n=3 and startFromEnd=true, the method would return the 3rd from the last character
     * in the string, 6th from the last character in the string, and so on (in the order in
     * which they appear in the string).
     * <p>
     * Values n and startFromEnd are passed as parameters. The starting character, whether it is
     * the first one or the last one in the string, is considered to be in Position 1.
     *
     * @param n            Determines the positions of the characters to be returned
     * @param startFromEnd Determines whether to start counting from the end or from the
     *                     beginning when identifying the characters in position n, 2n, 3n, and so
     *                     on. Please note that the characters are always returned in the order in
     *                     which they appear in the string.
     * @return String made of characters at positions n, 2n, and so on in the current string
     * @throws IllegalArgumentException If "n" less than or equal to zero
     * @throws NullPointerException     If the current string is null and "n" is greater than zero
     */
    @Override
    public String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd) {
        if(this.customString == null && (n > 0)){
            throw new NullPointerException("Null pointer");
        }
        else if(n <= 0){
            throw new IllegalArgumentException("Illegal Argument");
        }
        else if(this.customString.length() < n){
            return "";
        }
        StringBuilder result = new StringBuilder();
        if(!startFromEnd){
            for(int i = n - 1; i < this.customString.length(); i+=n){
                result.append(this.customString.charAt(i));
            }
        }
        else{
            for(int i = this.customString.length() - n; i >= 0; i-=n){
                result.append(this.customString.charAt(i));
            }
            result.reverse();
        }

        return result.toString();
    }

    /**
     * Replace the individual digits in the current string, between startPosition and endPosition
     * (included), with the corresponding English names of those digits, with the first letter
     * capitalized. The first character in the string is considered to be in Position 1.
     * Unlike for the previous method, digits are converted individually, even if contiguous.
     * <p>
     * Examples:
     * - String 460 would be converted to FourSixZero
     * - String 416 would be converted to FourOneSix
     *
     * @param startPosition Position of the first character to consider
     * @param endPosition   Position of the last character to consider
     * @throws IllegalArgumentException    If "startPosition" > "endPosition"
     * @throws MyIndexOutOfBoundsException If "startPosition" <= "endPosition", but either
     *                                     "startPosition" or "endPosition" are out of
     *                                     bounds (i.e., either less than 1 or greater than the
     *                                     length of the string)
     * @throws NullPointerException        If "startPosition" <= "endPosition", "startPosition" and
     *                                     "endPosition" are greater than 0, and the current
     *                                     string is null
     */
    @Override
    public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) {
        if(startPosition > endPosition){
            throw new IllegalArgumentException("Bad Argument");
        }
        else if((startPosition <= endPosition) && (startPosition < 1 || endPosition > this.customString.length())){
            throw new MyIndexOutOfBoundsException("Out of bounds and stuff");
        }
        else if(startPosition > 0 && startPosition <= endPosition && this.customString == null){
            throw new NullPointerException("Nulllllll");
        }
        StringBuilder result = new StringBuilder(this.customString.substring(0,startPosition-1));
        HashMap<Character, String> dict = new HashMap<>();
        dict.put('0',"Zero");
        dict.put('1', "One");
        dict.put('2', "Two");
        dict.put('3',"Three");
        dict.put('4',"Four");
        dict.put('5',"Five");
        dict.put('6',"Six");
        dict.put('7',"Seven");
        dict.put('8',"Eight");
        dict.put('9',"Nine");
        for(int i = startPosition - 1; i < endPosition; i++){
            if(Character.isDigit(this.customString.charAt(i))){
                result.append(dict.get(this.customString.charAt(i)));
            }
            else{
                result.append(this.customString.charAt(i));
            }
        }
        if(this.customString.length() - 1 >= endPosition){
            result.append(this.customString.substring(endPosition));
        }
        this.customString = result.toString();
    }
}
