import java.nio.file.*;
import java.util.*;
import java.io.*;

public class DictionaryManagement {
    public static Word[] dic;
    public static void insertFromFile() {
        try {
            Path path = Path.of("D:\\OOP\\TuDien.txt");
            List<String> dictionary_data = Files.readAllLines(path);
            int num = dictionary_data.size();
            dic = new Word[num];
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
            for (int i = 0; i < dic.length; i++) {
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
        int pos = 0;
        while (num != 0) {
            String x, y;
            System.out.println("nhap tu TA so "+(pos+1)+": ");
            x = scan.nextLine();
            System.out.println("Nhap tu TV so "+(pos+1)+": ");
            y = scan.nextLine();
            dic[pos] = new Word(x, y);
            num--;
            pos++;
        }
    }
    public static void showAllWord() {
        System.out.println("No      |English        |Vietnamese");
        for(int i = 0; i< dic.length; i++){
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

}
