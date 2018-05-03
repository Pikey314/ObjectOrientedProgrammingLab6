package prSimpleWordCounterWithFiles;

public class WordInText {

    private String word;
    private int occurrences;

    public WordInText(String word){
        this.word = word.toUpperCase();
        this.occurrences = 1;

    }

    public void increase(){
        this.occurrences++;
    }

    @Override
    public String toString() {
        return this.word + ":  " + occurrences;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordInText){
            if (this.word.equals(((WordInText) obj).word))
                return true;
        }
        return false;

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}



