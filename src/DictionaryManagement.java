import java.util.Scanner;
import java.util.Arrays;
public class DictionaryManagement {

    public static Word[] dic;
    public static void insertFromCommanline() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap so tu: ");

        int num = Integer.parseInt(scan.nextLine());
        dic = new Word[num];
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
        System.out.println("Nu      |English        |Vietnamese");

        for(int i=0; i<dic.length; i++){
            System.out.println((i+1)+"       |"+dic[i].word_target+"         |"+dic[i].word_explain);
            System.out.println();
        }
    }

}
