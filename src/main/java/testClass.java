import java.util.*;  // Provides TreeMap, Iterator, Scanner
import java.io.*;    // Provides FileReader, FileNotFoundException

public class testClass
{
    public static void main(String[ ] args)
    {
        // **THIS CREATES A TREE MAP**
        TreeMap<String, Integer> frequencyData = new TreeMap<String, Integer>( );

        Map[] mapArray = new Map[5];
        mapArray[0] = new HashMap<String, Integer>();

        readWordFile(frequencyData);
        printAllCounts(frequencyData);
    }

    public static int getCount(String word, TreeMap<String, Integer> frequencyData)
    {
        if (frequencyData.containsKey(word))
        {  // The word has occurred before, so get its count from the map
            return frequencyData.get(word); // Auto-unboxed
        }
        else
        {  // No occurrences of this word
            return 0;
        }
    }


    public static void printAllCounts(TreeMap<String, Integer> frequencyData)
    {
        System.out.println("-----------------------------------------------");
        System.out.println("    Occurrences    Word");

        for(String word : frequencyData.keySet( ))
        {
            System.out.printf("%15d    %s\n", frequencyData.get(word), word);
        }

        System.out.println("-----------------------------------------------");
    }


    public static void readWordFile(TreeMap<String, Integer> frequencyData)
    {
        int total = 0;
        Scanner wordFile;
        String word;     // A word read from the file
        Integer count;   // The number of occurrences of the word
        int counter = 0;
        int docs = 0;

        //**FOR LOOP TO READ THE DOCUMENTS**
        for(int x=0; x<Docs.length; x++)
        { //start of for loop [*

            try
            {
                wordFile = new Scanner(new FileReader(Docs[x]));
            }
            catch (FileNotFoundException e)
            {
                System.err.println(e);
                return;
            }

            while (wordFile.hasNext( ))
            {
                // Read the next word and get rid of the end-of-line marker if needed:
                word = wordFile.next( );

                // This makes the Word lower case.
                word = word.toLowerCase();

                word = word.replaceAll("[^a-zA-Z0-9\\s]", "");

                // Get the current count of this word, add one, and then store the new count:
                count = getCount(word, frequencyData) + 1;
                frequencyData.put(word, count);
                total = total + count;
                counter++;
                docs = x + 1;

            }

        } //End of for loop *]
        System.out.println("There are " + total + " terms in the collection.");
        System.out.println("There are " + counter + " unique terms in the collection.");
        System.out.println("There are " + docs + " documents in the collection.");

    }


    // Array of documents
    static String Docs [] = {"C:\\INVERTED_INDEX\\src\\main\\files\\file1", "C:\\INVERTED_INDEX\\src\\main\\files\\file2","C:\\INVERTED_INDEX\\src\\main\\files\\file3"};


}