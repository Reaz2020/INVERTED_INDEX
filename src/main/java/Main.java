import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Index index = new Index();
        String [] files =new String[]{"C:\\INVERTED_INDEX\\src\\main\\files\\file1", "C:\\INVERTED_INDEX\\src\\main\\files\\file2", "C:\\INVERTED_INDEX\\src\\main\\files\\file3"};
        index.buildIndex(files);
        System.out.println("Print search phrase: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String phrase = in.readLine();
        index.find(phrase);
        // index.sortIDFvalues();
       // index.printTF();
        index.IDFc();
        index.TFcalc(files);

    }
}
