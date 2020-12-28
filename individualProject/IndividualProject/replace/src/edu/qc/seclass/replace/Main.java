package edu.qc.seclass.replace;

public class Main {

    public static void main(String[] args) throws Exception {
        // TODO: Empty skeleton method
        Replace replace = new Replace(args);
        replace.replace();
    }

    public static void usage() {
        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" );
    }

    public static void FNF(String str){
        System.err.println("File " + str + " not found");
    }

}