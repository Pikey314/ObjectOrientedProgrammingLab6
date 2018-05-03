package prSimpleWordCounterWithFiles;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WordCounter {

    private WordInText[] words;
    private int numWords;
    protected static int INIT_SIZE = 10;

    public WordCounter(){
        this.words = new WordInText[INIT_SIZE];
    }

    public WordCounter(int size){
        this.words = new WordInText[size];
    }

    public void add(String word){
        int indexOfWord = contains(word);
        if (indexOfWord != -1){
            this.words[indexOfWord].increase();
        } else {
            /*
            int firstNullIndex = -1;
            for (int i = this.words.length-1; i>=0; i--){
                if (this.words[i] != null)
                    firstNullIndex = i+1;
            }
            if (firstNullIndex != -1)
                this.words[firstNullIndex] = new WordInText(word);
            else { */
                WordInText[] newWords = new WordInText[this.words.length+1];
                System.arraycopy(this.words,0,newWords,0,this.words.length);
                newWords[this.words.length] = new WordInText(word);
                this.words = newWords;
            //}

        }
    }

    public void addAll(String line, String del){

        Scanner sc = new Scanner(line);
        sc.useDelimiter(del);
        while (sc.hasNext())
            add(sc.next());
        sc.close();
    }

    public void addAll(String[] text, String del){
        for (String textLine : text)
            addAll(textLine,del);
    }

    public void addAllFile(String filename, String del){
        File file = new File(filename);
        try (Scanner sc = new Scanner(file)) {
            readFile(sc, del);
        } catch (FileNotFoundException e){
            e.getMessage();
        }


    }



    public WordInText find (String word){
        int index = contains(word);
        if (index == -1)
            throw new NoSuchElementException("");
        else
            return this.words[index];
    }

    public void printWords(String outputFile){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
        for (WordInText word : this.words)
            if (word!=null) {
                writer.write(word.toString() + "\n");
                writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Problem with output file");
        }
    }

    public void printWords(PrintWriter pw){
        for (WordInText word : this.words)
            if (word!=null)
            pw.println(word.toString());
    }

    @Override
    public String toString(){
        StringBuilder stringToReturn = new StringBuilder("[");
        for (WordInText word : this.words)
            if (word != null) {
                stringToReturn.append(word.toString() + ", ");
            }
//        stringToReturn.delete(stringToReturn.length()-2, stringToReturn.length()-1);
        stringToReturn.append("]");
        return stringToReturn.toString();
    }




    private int contains(String word){
        for (int i = 0; i<this.words.length; i++) {
            WordInText wordFromArray = this.words[i];
            if (wordFromArray != null) {
                String wordToCompare = wordFromArray.toString();
                if (wordToCompare.split(":")[0].equals(word.toUpperCase()))
                    return i;
            }
        }
        return -1;
    }

    private void readFile(Scanner sc, String del){
        while(sc.hasNextLine())
            addAll(sc.nextLine(),del);
        sc.close();
    }
}
