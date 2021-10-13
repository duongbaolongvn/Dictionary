import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class DictionaryManagement {
    public static Word[] dic;
    public static int wordNumber = 0;
    public static void insertFromFile() {
        try {
            Path path = Path.of("D:\\OOP\\TuDien.txt");
            List<String> dictionary_data = Files.readAllLines(path);
            wordNumber = dictionary_data.size();
            dic = new Word[wordNumber];
            int pos = 0;
            String x, y;
            for (String word_data : dictionary_data) {
                String[] data = word_data.split("\\s", 2);
                x = data[0];
                y = data[1];
                dic[pos] = new Word(x, y);
                pos++;
            }
        } catch (IOException e) {}
    }
    public static void dictionaryLookup() {
        System.out.println();
        System.out.println("Tu can tim: ");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String search = sc.next();
            for (int i = 0; i < wordNumber; i++) {
                if (dic[i].getWord_target().contains(search)) {
                    System.out.format("%-7s %-15s %-15s",
                            "",
                            "|" + dic[i].getWord_target(),
                            "|" + dic[i].getWord_explain());
                    System.out.println();
                }
            }
        }
    }
    public static void insertFromCommanline() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap so tu: ");

        int num = Integer.parseInt(scan.nextLine());
        dic = new Word[num];
        while (num != 0) {
            String x, y;
            System.out.println("nhap tu TA so "+(wordNumber+1)+": ");
            x = scan.nextLine();
            System.out.println("Nhap tu TV so "+(wordNumber+1)+": ");
            y = scan.nextLine();
            dic[wordNumber] = new Word(x, y);
            num--;
            wordNumber++;
        }
    }
    public static void showAllWord() {
        System.out.println("No      |English        |Vietnamese");
        for(int i = 0; i< wordNumber; i++){
            System.out.format("%-7s %-15s %-15s",
                    i+1,
                    "|" + dic[i].getWord_target(),
                    "|" + dic[i].getWord_explain());
            System.out.println();
        }
    }
    public static void dictionaryExportToFile() {
        try {
            FileWriter fw = new FileWriter("D:\\OOP\\Output.txt");
            for(int i = 0; i< dic.length; i++){
                fw.write(dic[i].getWord_target() + "\t" + dic[i].getWord_explain());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {}
    }
    public static void deleteFromCommanLine() {
        Scanner sc = new Scanner(System.in);
        String delWord;
        int m = 0;
        if (wordNumber == 0) {
            System.out.println("Dictionary is empty.");
        } else {
            System.out.println("Delete word: ");
            delWord = sc.nextLine();
            for (int i = 0; i < wordNumber; i++) {
                if(dic[i].getWord_target().equals(delWord)) {
                    m = 1;
                    for (int j = i; j < wordNumber - 1; j++) {
                        dic[j] = dic[j+1];
                    }
                }
            }
        }
        if (m == 1) {
            System.out.println("Deleted");
        }
        if (m == 0 && wordNumber > 0) {
            System.out.println("Can't find delete word");
        }
        wordNumber--;
    }
    public static void editFromCommanLine() {
        Scanner sc = new Scanner(System.in);
        String edWord;
        String edited = "";
        int m;
        if (wordNumber == 0) {
            System.out.println("Dictionary is empty.");
        } else {
            int t = 0;
            int k = 0;
            int t1 = 0;
            int t2 = 0;
            System.out.println("Select the element you want to edit: ");
            System.out.println("1. Word");
            System.out.println("2. Mean");
            System.out.println("Choice: ");
            sc = new Scanner(System.in);
            m = sc.nextInt();
            sc = new Scanner(System.in);
            System.out.println("Edit word: ");
            edWord = sc.nextLine();
            for (int i = 0; i < wordNumber; i++) {
                if (dic[i].getWord_target().equals(edWord)) {
                    t = 1;
                    if (m == 1) {
                        t1 = 1;
                        k = i;
                    }
                    if (m == 2) {
                        t2 = 1;
                        k = i;
                    }
                }
            }
            if (t == 1 && wordNumber > 0) {
                sc = new Scanner(System.in);
                System.out.println("Edited to: ");
                edited = sc.nextLine();
            }
            if (t1 == 1) {
                dic[k].setWord_target(edited);
            }
            if (t2 == 1) {
                dic[k].setWord_explain(edited);
            }
            if (t == 0 && wordNumber > 0) {
                System.out.println("Can't find edit word");
            }
        }
    }

}
