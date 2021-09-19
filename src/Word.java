public class Word {
    public String word_target;
    public String word_explain;

    public void setData(String word_target, String word_explain) {
        this.word_explain = word_explain;
        this.word_target = word_target;
    }

    public void display(){
        System.out.println(word_target + "         " + word_explain);
        System.out.println();
    }
}