import prSimpleWordCounterWithFiles.WordInText;


public class WordInTextTest {

    public static void main(String[] args){

        WordInText word1 = new WordInText("cap");
        WordInText word2 = new WordInText("Cap");
        WordInText word3 = new WordInText("hat");

        word1.increase();

        System.out.println(word1);
        System.out.println(word2);
        System.out.println(word3);

        if (word1.equals(word2))
            System.out.println("Word 1 and Word 2 are equal");
        else
            System.out.println("Word 1 and Word 2 are NOT equal");

        if (word1.equals(word3))
            System.out.println("Word 1 and Word 3 are equal");
        else
            System.out.println("Word 1 and Word 3 are NOT equal");


        //MOJ TEST

       /* File file = new File("a.txt");

        String a = "ala;ma;kota;1;2;3";
        Scanner sc = new Scanner(file);
        sc.useDelimiter(" ");
        while (sc.hasNextLine())
            System.out.println(sc.nextLine());
        sc.close();*/


        //KONIEC
    }
}
