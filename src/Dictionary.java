public class Dictionary{
    //interface to load used nested method
    interface Cout {
        void displayDic();
    }
    static void createDic() {
        Word[] dic;

        dic = new Word[2];

        dic[0] = new Word("hello", "xin chao");
        dic[1] = new Word("home", "nha");

    }
}