package DictionaryApplication.DictionaryCommandLine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class DictionaryManagement {
    //doc du lieu tu file - final
    public void insertFromFile(Dictionary dictionary, String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String string, word_explain = "", word_target = "";
            while ((string = bufferedReader.readLine()) != null) {
                if (string.startsWith("|")) {
                    if (!word_explain.isEmpty()) {
                        Word word = new Word();
                        word.setWord_target(word_target.trim());
                        word.setWord_explain(word_explain.trim());
                        dictionary.add(word);
                        word_explain = "";
                    }
                    word_target = string.replace("|", "").trim();

                } else {
                    word_explain = word_explain + string + "\n";
                }
            }
            Word word = new Word();
            word.setWord_target(word_target.trim());
            word.setWord_explain(word_explain.trim());
            dictionary.add(word);
        } catch (IOException e) {
            System.out.println("insertFromCommandline");
            System.out.println(e);
        }
    }


    public void exportToFile(Dictionary dictionary, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : dictionary) {
                bufferedWriter.write("|" + word.getWord_target() + "\n" + word.getWord_explain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addWord(Word word, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("|" + word.getWord_target() + "\n" + word.getWord_explain() + "\n");
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteWord(Dictionary dictionary, int index, String path) {
        try {
            dictionary.remove(index);
            exportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public void changeWord(Dictionary dictionary, int index, String path, String mean) {
        try {
            dictionary.get(index).setWord_explain(mean);
            exportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public int searchWord(Dictionary dictionary, String key) {
        try {
            for (int i = 0; i < dictionary.size(); ++i) {
                if (dictionary.get(i).getWord_target().equals(key)) {
                    return i;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public void addupdateWord(Dictionary dictionary, int index, String path, String mean) {
        try {
            String tam = dictionary.get(index).getWord_explain();
            dictionary.get(index).setWord_explain(tam + "\n" + mean);
            exportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public ObservableList<String> lookupWord(Dictionary dictionary, String key) {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (int i = 0; i < dictionary.size(); ++i) {
            if (dictionary.get(i).getWord_target().contains(key)) {
                list.add(String.valueOf(dictionary.get(i).getWord_target()));
            }
        }
        return list;
    }
}
