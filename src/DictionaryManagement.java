import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
public class DictionaryManagement {

    public static Word[] dic;
    public static  void insertFromFile() {
        System.out.println("No      |English        |Vietnamese");
        try {
            Path path = Path.of("D:\\OOP\\TuDien.txt");
            List<String> dictionary_data = Files.readAllLines(path);
            for (String word_data : dictionary_data) {
                String[] data = word_data.split("\\s", 2);
                Word word = new Word();
                word.word_target = data[0];
                word.word_explain = data[1];

                System.out.println("        |" + word.word_target + "        |" + word.word_explain);
            }
        } catch (IOException e) {}
    }
    public static void insertFromCommanline() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap so tu: ");

        int num = Integer.parseInt(scan.nextLine());
        Word[] dic = new Word[num];
        int pos = 0;
        while (num != 0) {
            dic[pos] = new Word();
            String x, y;
            System.out.println("nhap tu TA so "+(pos+1)+": ");
            x = scan.nextLine();
            System.out.println("Nhap tu TV so "+(pos+1)+": ");
            y = scan.nextLine();
            dic[pos].setData(x, y);
            num--;
            pos++;
        }
    }
    public static void showAllWord() {
        System.out.println("No      |English        |Vietnamese");

        for(int i=0; i<dic.length; i++){
            System.out.format("%-7s %-15s %-15s",
                    i+1,
                    "|" + dic[i].word_target,
                    "|" + dic[i].word_explain);
            System.out.println();
        }
    }

}
