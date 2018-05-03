import prSimpleWordCounterWithFiles.WordCounter;

public class WordCounterTest {

    public static void main(String[] args){

        WordCounter wc = new WordCounter(5);

        String [] data = {"This is the first sentence in the example",
                "and this is the second one"};

        /*wc.addAll(data, " ");*/

        //TEST
        wc.addAllFile("pig.txt", " ");
        //END TEST

        System.out.println(wc.toString());
    }
}
