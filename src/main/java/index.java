import java.io.*;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

class Index {




    ArrayList<Map<String, Double>> TF_IDF_List = new ArrayList();
    Map<Integer, String> sources;
    HashMap<String, HashSet<Integer>> index;
    Map<String, Double> IDF_forAllWords = new HashMap<>();
    ArrayList<String> allWordsForALLdocuments = new ArrayList();
    int numbeRofDocumentsThatContainThePharseTerm = 0;
    Index() {
        sources = new HashMap<Integer, String>();
        index = new HashMap<String, HashSet<Integer>>();
    }
    public void buildIndex(String[] files) {
        int docNo = 1;
        int i = 0;
        // creating IDF and Inverted Index
        for (String fileName : files) {
            //ArrayList<String> allWords_forEachDocument = new ArrayList();
            //TF(allWords_forEachDocument,i);
            try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
                sources.put(i, fileName);
                String ln;
                while ((ln = file.readLine()) != null) {
                    String[] words = ln.split("\\W+");
                    for (String word : words) {
                        word = word.toLowerCase();
                        //allWords_forEachDocument.add(word);
                        allWordsForALLdocuments.add(word);
                        if (!index.containsKey(word))
                            index.put(word, new HashSet<Integer>());
                        index.get(word).add(i);
                    }
                }
            } catch (IOException e) {
                System.out.println("File " + fileName + " not found. Skip it");
            }
            i++;

        }
       // index.forEach((k, v) -> System.out.println("Inverted list  " + k + " = " + v));

    }
    void TF_value_forEachDocument(String fileName, int i) {

        ArrayList<String> allWords_forEachDocument = new ArrayList();
        try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
            sources.put(i, fileName);
            String ln;
            while ((ln = file.readLine()) != null) {
                String[] words = ln.split("\\W+");
                for (String word : words) {
                    word = word.toLowerCase();
                    allWords_forEachDocument.add(word);
                }
            }
        } catch (IOException e) {
            System.out.println("File " + fileName + " not found. Skip it");
        }
        TF(allWords_forEachDocument, i);

    }
    public void find(String phrase) {

        String[] words = phrase.split("\\W+");
        HashSet<Integer> res = new HashSet<Integer>(index.get(words[0].toLowerCase()));
        for (String word : words) {
            res.retainAll(index.get(word));
        }

        if (res.size() == 0) {
            System.out.println("Not found");
            return;
        }
        System.out.println("Found in: ");
        for (int num : res) {
            numbeRofDocumentsThatContainThePharseTerm++;
            System.out.println("\t" + sources.get(num));
        }
        System.out.println(phrase + " is in " + numbeRofDocumentsThatContainThePharseTerm + " documents ");
    }
    void TF(ArrayList<String> arrayList, int docNo) {
        double tfVlue;
        int totalWordsInTheDocument = arrayList.size();
        HashMap<String, Integer> termFrequencyList = new HashMap<>();
        HashMap<String, Double> TF = new HashMap<>();
        for (int i = 0; i < arrayList.size(); i++) {
            int countA = Collections.frequency(arrayList, arrayList.get(i));
            termFrequencyList.put(arrayList.get(i), countA);
            tfVlue = countA;


            TF.put(arrayList.get(i), tfVlue / totalWordsInTheDocument);
            //TF.put(arrayList.get(i), 0.5+0.5*(tfVlue/ totalWordsInTheDocument));

        }

       // System.out.println("----------------Document " + docNo+"---------------------------------");
      //  TF.forEach((k, v) -> System.out.println("TF Value for word  " + k + " = " + v));
        //System.out.println("                   TF_IDF ");
        TF_IDF(TF);
        System.out.println("  ");
        /*tf(t,d) = n/N
n is the number of times term t appears in the document d.
N is the total number of terms in the document d. (https://medium.com/@adityamdk/tf-idf-implementation-in-java-f6c4d1d97e3b)*/}
    /*idf(t,D) = log (N/( n))
N is the number of documents in the data set.
n is the number of documents that contain the term t among the data set*/
    // tf df printing
    void print() {
        /*for(int i = 0; i < TF_IDF_List.size(); i++) {
            System.out.print(",  tf df list for all words “\\n”"+TF_IDF_List.get(i)+"“\\n”");
        }*///TF_IDF_List.forEach(t -> System.out.println(t));
    }
    void sortTF_IDFvalues(Map<String, Double> tempTF_IDF){
        Object[] a = tempTF_IDF.entrySet().toArray();
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Double>) o2).getValue()
                        .compareTo(((Map.Entry<String, Double>) o1).getValue());
            }
        });//tempTF_IDF.forEach((k, v) -> System.out.println("TF_IDF Value for word  " + k + " = " + v));
       // System.out.println(" ");
       // System.out.println(" ");
        /*for (Object e : a) {
            System.out.println("TF_IDF Value for word :"+((Map.Entry<String, Double>) e).getKey() + "   "
                    + ((Map.Entry<String, Double>) e).getValue());

        }System.out.println(" ");*/
    }
   /* void printTF() {
        System.out.println("TF for all words ");
        TF_forAllWords.forEach((k, v) -> System.out.println("TF :  " + k + "  " + v));
    }*/
    // calculationg tf df for individual doc and add in an arraylist
    void TF_IDF(Map<String, Double> TF) {
        Map<String, Double> TF_IDF = new HashMap<>();
       // System.out.println("IDF size  " + IDF_forAllWords.size());
        int i = 0;
        for (String key : TF.keySet()) {
            if (TF.containsKey(key) == IDF_forAllWords.containsKey(key)) {
                double TF_IDF_value = TF.get(key) * IDF_forAllWords.get(key);
               // System.out.println();
                TF_IDF.put(key, TF_IDF_value);

            }
        }TF_IDF_List.add(i,TF_IDF);



       // System.out.println(" ---------------------------------------------------------- ");
        sortTF_IDFvalues(TF_IDF);
       // TF_IDF.forEach((k, v) -> System.out.println("TF_IDF Value for word  " + k + " = " + v));
    }

    void IDFc() {
        double numberOfDocHaveTheWord = 0;
        double numberOfDoc = sources.size();
        double docuTemp = numberOfDoc;

        for (int i = 0; i < allWordsForALLdocuments.size(); i++) {
            numberOfDocHaveTheWord = index.get(allWordsForALLdocuments.get(i)).size();
            double wordTemp = numberOfDocHaveTheWord;
            double input = docuTemp / wordTemp;
            IDF_forAllWords.put(allWordsForALLdocuments.get(i), 1 + Math.log(input));

        }

       // IDF_forAllWords.forEach((k, v) -> System.out.println("IDF  = " + k + "= " + v));
       // System.out.println("--------------------------------------------------------------");
        //sortIDFvalues();
       // System.out.println("IDF size  " + IDF_forAllWords.size());

       // TF.forEach((k, v) -> System.out.println("TF Value for word  " + k + " = " + v));

    }
    //reversing the arraylist to match list document wise,  matching  documnets + word //saving them as key value,
    // sorting according to value and printing
    void findParse( String a ){
        Collections.reverse(TF_IDF_List);
        HashMap<Integer, Double> TF_IDF_ListNew =new LinkedHashMap<>();
        int docNO;
        for (int i = 0; i < TF_IDF_List.size(); i++) {
            if(TF_IDF_List.get(i).containsKey(a)){
                docNO=i+1;
                TF_IDF_ListNew.put(docNO, TF_IDF_List.get(i).get(a));
            }
        }sortingDocument(TF_IDF_ListNew); // sorting tf_idf value wise


    }
    //need to change codes */

    void sortingDocument(HashMap<Integer, Double> tempHashMap){

        Object[] obj = tempHashMap.entrySet().toArray();
        Arrays.sort(obj, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<Integer, Double>) o2).getValue()
                        .compareTo(((Map.Entry<Integer, Double>) o1).getValue());
            }
        });//tempTF_IDF.forEach((k, v) -> System.out.println("TF_IDF Value for word  " + k + " = " + v));
        // System.out.println(" ");



        for (Object e : obj) {
            System.out.println("Document no :"+((Map.Entry<Integer, Double>) e).getKey() + "  tf.idf value  "
                    + ((Map.Entry<Integer, Double>) e).getValue());

        }

        // printing value of the tf idf in every document
        System.out.println();


        System.out.println("Documents ");
        for (int i = 0; i < TF_IDF_List.size(); i++) {
            System.out.println("Document "+(i+1)+":-----"+TF_IDF_List.get(i));

        }



    }






    public void TFcalc(String[] files) {
        int docNo = 1;
        for (String fileName : files) {
            TF_value_forEachDocument(fileName, docNo);
            docNo++;
        }
    }

}