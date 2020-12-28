package edu.qc.seclass.replace;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Replace {
    private Charset charset = StandardCharsets.UTF_8;
    private String [] args;
    private ArrayList<String> options;
    private ArrayList<String> files = new ArrayList<>();
    private String to, from;
    private boolean createBackup = false, justFirst = false, justLast = false, ignoreCase = false;

    private HashSet<Character> badChars = new HashSet<>(Arrays.asList('\\', '.', '[', ']', '{', '}', '(', ')', '<', '>', '*', '+', '=', '?', '^', '$', '|'));
    private HashSet<String> allowableOptions = new HashSet<>(Arrays.asList("-b", "-f", "-l", "-i"));

    public Replace(String [] args){
        this.args = args;
        this.options = new ArrayList<>(Arrays.asList(args));
    }

    public void replace() throws Exception {
        if(containsObviousErrors()){
            Main.usage();
            return;
        }
        if(!parseArgs()){
            Main.usage();
            return;
        }
        if(createBackup) createBackup();
        if(from.length() == 0){
            Main.usage();
            return;
        }
        for(String fl : files){
            if(isValidFile(fl)) {
                String content = getFileContent(fl);
                String newContent = editFileContent(content);
                writeNewContentToOldFile(newContent, fl);
            }
            else Main.FNF(new File(fl).getName());
        }
    }

    public boolean containsObviousErrors(){
        if(options.indexOf("--") < 0) return true;
        if(options.size() < 4) return true;
        if(options.indexOf("--") == options.size() - 1) return true;
        return false;
    }

    private boolean parseArgs(){
        boolean reachedFiles = false, reachedFrom = false, reachedTo = false;
        for(int i = 0; i < options.size(); i++){
            if(options.get(i).equals("--")){
                if(!containsSecondDD(i+1)){
                    reachedFiles = true;
                    continue;
                }
                if(!reachedFiles){
                    if(!reachedFrom){
                        reachedFrom = true;
                        from = options.get(i + 1);
                        i++;
                        continue;
                    }
                    else if(!reachedTo){
                        reachedTo = true;
                        to = options.get(i + 1);
                        i++;
                        continue;
                    }
                }
            }
            if(reachedFiles){
                files.add(options.get(i));
                continue;
            }
            if(options.get(i).equals("-b")){
                if(reachedFrom && reachedTo) return false;
                else if(reachedFrom){
                    reachedTo = true;
                    to = options.get(i);
                    continue;
                }
                createBackup = true;
            }
            else if(options.get(i).equals("-f")){
                if(reachedFrom && reachedTo) return false;
                else if(reachedFrom){
                    reachedTo = true;
                    to = options.get(i);
                    continue;
                }
                justFirst = true;
            }
            else if(options.get(i).equals("-l")){
                if(reachedFrom && reachedTo) return false;
                else if(reachedFrom){
                    reachedTo = true;
                    to = options.get(i);
                    continue;
                }
                justLast = true;
            }
            else if(options.get(i).equals("-i")){
                if(reachedFrom && reachedTo) return false;
                else if(reachedFrom){
                    reachedTo = true;
                    to = options.get(i);
                    continue;
                }
                ignoreCase = true;
            }
            else if(!reachedFrom){
                from = options.get(i);
                reachedFrom = true;
            }
            else if(!reachedTo){
                to = options.get(i);
                reachedTo = true;
            }
            else return false;
        }
        if(to == null && from == null) return false;
        return true;
    }

    private boolean containsSecondDD(int i) {
        for(; i < options.size();i ++){
            if(options.get(i).equals("--")) return true;
        }
        return false;
    }

    private boolean createBackup(){
        for(String fl : files){
            File tmp = new File(fl);
            Path source = Paths.get(tmp.getPath());
            Path target = Paths.get(tmp.getPath() + ".bck");
            try{
                Files.copy(source, target);
            }
            catch(IOException el){
                el.printStackTrace();
                return false;
            }
        }
        return true;
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


    private String editFileContent(String content){
        String regex = ignoreCase ? "(?i)" : "";
        from = escapeSpecialRegexChars(from);
        if(!justLast && !justFirst){
            content = content.replaceAll(regex+from, Matcher.quoteReplacement(to));
            return content;
        }
        if(justFirst){
            content = content.replaceFirst(regex+from, to);
        }
        if(justLast){
            StringBuilder tmp = new StringBuilder(content);
            StringBuilder fromTmp = new StringBuilder(from);
            StringBuilder toTmp = new StringBuilder(to);
            tmp = tmp.reverse();
            fromTmp = fromTmp.reverse();
            toTmp = toTmp.reverse();
            String revFrom = fromTmp.toString();
            String revContent = tmp.toString();
            String revTo = toTmp.toString();

            revContent = revContent.replaceFirst(regex+revFrom, revTo);

            StringBuilder toReturn = new StringBuilder(revContent);
            toReturn = toReturn.reverse();
            content = toReturn.toString();
        }
        return content;
    }

    private void writeNewContentToOldFile(String content, String file) throws Exception{
        File f = new File(file);
        FileWriter fileWriter = new FileWriter(f, false);
        fileWriter.write(content);
        fileWriter.close();
    }

    private boolean isValidFile(String st) {
        File f = new File(st);
        if(f.isFile()) return true;
        return false;
    }

    String escapeSpecialRegexChars(String str) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < from.length(); i++){
            if(badChars.contains(from.charAt(i))){
                sb.append("\\" + from.charAt(i));
                continue;
            }
            sb.append(from.charAt(i));
        }
        return sb.toString();
    }
}
