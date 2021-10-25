package main.dictionary21;

public class DictionaryCommandline {
    public static void dictionaryBasic(){
        DictionaryManagement.insertFromCommanline();
        DictionaryManagement.showAllWord();
    }
    public static void dictionaryAdvanced() {
        DictionaryManagement.insertFromFile();
        DictionaryManagement.showAllWord();
        DictionaryManagement.dictionaryLookup();
    }
    public static void main(String[] args) {
        DictionaryManagement.insertFromFile();
        DictionaryManagement.dictionaryExportToFile();
    }
}