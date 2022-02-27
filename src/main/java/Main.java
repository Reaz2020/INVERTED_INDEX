import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Index index = new Index();
        String [] files =new String[]{"C:\\INVERTED_INDEX\\src\\main\\files\\file2","C:\\INVERTED_INDEX\\src\\main\\files\\file1", "C:\\INVERTED_INDEX\\src\\main\\files\\file3"};
        index.buildIndex(files);
        index.IDFc();
        index.TFcalc(files);
        System.out.println("Write search phrase: ");
        Scanner sc=new Scanner(System.in);
        String a = sc.nextLine();
        index.findParse(a);


    }
}
