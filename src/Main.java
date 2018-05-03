import java.util.NoSuchElementException;
import prSimpleWordCounterWithFiles.*;
import java.io.*;


public class Main {
    public static void main(String [] args) throws IOException {
        String [] data = {
                "The animal I really dig,",
                "Above all others is the pig.",
                "Pigs are noble. Pigs are clever,",
                "Pigs are courteous. However,",
                "Now and then, to break this rule,",
                "One meets a pig who is a fool."};

        String delimiters = "[ .,:;\\-\\!\\�\\�\\?]+";    // With Scanner
        String [] noSignificant = {"THE", "AND", "THEN", "TO", "THIS", "A"};
        WordCounter counter = null, significantCounter = null;
        // If no numeric parameter is given, the counters are also created
        try {
            int n = Integer.parseInt(args[0]);
            System.out.println("Argument: " + n);
            counter = new WordCounter(n);
            significantCounter = new SignificantWordsCounter(n, noSignificant);
        } catch (RuntimeException e) {
            System.out.println("By default...");
            counter = new WordCounter();
            significantCounter = new SignificantWordsCounter(noSignificant);
        }
        // Each word in data is included
        counter.addAll(data, delimiters);
        significantCounter.addAll(data, delimiters);

        System.out.println(counter + "\n");

        System.out.println(significantCounter + "\n");

        try {
            System.out.println(counter.find("pig"));
            System.out.println(counter.find("big"));
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }

        //The program runs again using I/O operations

        System.out.println("This time the program works with files");

        significantCounter=null;
        //If no parameter a default configuration is used
        try {
            int n = Integer.parseInt(args[0]);
            System.out.println("Argument : " + n);
            counter = new WordCounter(n);
            significantCounter= new SignificantWordsCounter(n,"NoSignificantWordsFile.txt",delimiters);
        } catch (RuntimeException e) {
            System.out.println("By default...");
            counter = new WordCounter();

            significantCounter= new SignificantWordsCounter("NoSignificantWordsFile.txt",delimiters);
        }

        // Each word in the file is included
        counter.addAllFile("pig.txt",delimiters);
        significantCounter.addAllFile("pig.txt", delimiters);
        System.out.println(counter + "\n");
        System.out.println(significantCounter + "\n");
        //methods to print on screen
        PrintWriter pw=new PrintWriter(System.out, true);
        counter.printWords(pw);
        //output sent to a file
        counter.printWords("output.txt");
        System.out.println();
        //methods to print on screen in the case of meaningless words
        significantCounter.printWords(pw);
        //output sent to a file
        significantCounter.printWords("significantCounterOutput.txt");


    }
}
