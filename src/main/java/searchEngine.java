import java.util.Scanner;

public class searchEngine {
    public void functionHolder(String files []){

        Index index = new Index();
        //String [] files =new String[]{"C:\\INVERTED_INDEX\\src\\main\\files\\doc1","C:\\INVERTED_INDEX\\src\\main\\files\\doc2","C:\\INVERTED_INDEX\\src\\main\\files\\doc3","C:\\INVERTED_INDEX\\src\\main\\files\\doc4","C:\\INVERTED_INDEX\\src\\main\\files\\doc5"};
        index.indexBuilding(files);
        index.IDFc();
        index.TFcalc(files);
        System.out.println("Write search phrase: ");
        Scanner sc=new Scanner(System.in);
        String a = sc.nextLine();
        index.findParse(a);
    }
}
