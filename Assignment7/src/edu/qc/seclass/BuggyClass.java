package edu.qc.seclass;

public class BuggyClass {

    public static int buggyMethod1(int a, int b){
        if(a < 0){
            return (a/b);
        }
        else if(a > 0){
            return (b/a);
        }
        else {
            return (a/b);
        }
    }

    public static int buggyMethod2(int a, int b){
        if(a != 0){
            b = ((b * 7)/b) + 3;
        }
        return b/a;
    }

    public static int buggyMethod3(int a, int b){
        int j = 0;
        if (a <= 0){
           j = (a / 2);
        }
        return(j/b);
    }

    public static void buggyMethod4(){
        /**
         * If we were to say that every test suite that achieves 100% statement coverage reveals the fault,
         * then it means the test suite executes every single line, and a 100% branch coverage suite also will have to execute
         * each line that the statement coverage executes because branch coverage subsumes statement coverage, so it will also
         * cause a fault. This is an unavoidable division by zero fault
         */
    }

    public static void buggyMethod5(int i){
        /**
         * This is impossible because the definition of 100% statement coverage
         * means that it must execute every statement, including "x=i/0" where the fault lies.
         * But the requirements say that none of the test suites can reveal it, which leads to the
         * contradiction. This is an unavoidable division by zero fault
         */

    }
}
