package prSimpleWordCounterWithFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SignificantWordsCounter extends WordCounter {

    private String[] noSignificant;
    private static int SIZE = 20;
    private int numWords;

    public SignificantWordsCounter (int n, String[] wordsNS){
        super(n);
        this.noSignificant = wordsNS;
        this.noSignificant = new String[wordsNS.length];
        for (int i =0; i<wordsNS.length; i++)
            this.noSignificant[i] = wordsNS[i].toUpperCase();
    }

    public SignificantWordsCounter (String[] wordsNS){
        super();
        this.noSignificant = wordsNS;
        this.noSignificant = new String[wordsNS.length];
        for (int i =0; i<wordsNS.length; i++)
            this.noSignificant[i] = wordsNS[i].toUpperCase();
    }

    public SignificantWordsCounter(String noSignificantFile, String sep){
        super();
        readNoSignificantFromFile(noSignificantFile, sep);
    }

    public SignificantWordsCounter(int n,String noSignificantFile, String sep){
        super(n);
        readNoSignificantFromFile(noSignificantFile, sep);
    }




    public void readNoSignificantFromFile(String filename, String del){
        File file = new File(filename);
        try (Scanner sc = new Scanner(file)) {
            readNoSignificantWords(sc, del);
        } catch (FileNotFoundException e){
            e.getMessage();
        }


    }
    private void readNoSignificantWords(Scanner sc, String sep){
        while(sc.hasNextLine())
            addAllNS(sc.nextLine(),sep);
        sc.close();
    }


    public void add(String word){
        if (contains1(word) == -1)
            super.add(word);
    }

    public void addNoSiginificant(String word){
        int indexOfWord = contains1(word);
        if (this.noSignificant==null){
            this.noSignificant = new String[1];
            this.noSignificant[0] = word;
        }
        if (indexOfWord == -1 && this.noSignificant!= null){

            String[] newWords = new String[this.noSignificant.length+1];
            System.arraycopy(this.noSignificant,0,newWords,0,this.noSignificant.length);
            newWords[this.noSignificant.length] = word;
            this.noSignificant = newWords;


        }
    }

    public void addAllNS(String line, String del){

        Scanner sc = new Scanner(line);
        sc.useDelimiter(del);
        while (sc.hasNext())
            addNoSiginificant(sc.next());
        sc.close();
    }



    private int contains1(String word){
        if (this.noSignificant!=null) {
            for (int i = 0; i < this.noSignificant.length; i++) {
                String wordFromArray = this.noSignificant[i];
                if (wordFromArray != null) {
                    String wordToCompare = wordFromArray;
                    if (wordToCompare.equals(word.toUpperCase()))
                        return i;
                }
            }
        }
        return -1;
    }

    public String[] getNoSignificant(){
        return this.noSignificant;
    }
}
