import java.io.*;

public class KeywordCount {
    static String[] keyword = {
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
        "continue", "default", "do", "double", "else", "enum", "extends", "false", "final", "finally",
        "float", "for", "If", "goto", "Implements", "Import", "Instanceof", "Int", "interface", "long",
        "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static",
        "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "true",
        "try", "void", "volatile", "while"
    };
    static int[] key_count = new int[54];

    //출력문
    public static void printBoard() {
        for(int i=0; i<keyword.length; i++) {
            if(key_count[i] != 0) {
                System.out.println(keyword[i] + ": " + key_count[i]);
            }
        }
    }


    //키워드 개수 세기
    private static int[] countKeywords(String filename) {
        int[] counts = new int[keyword.length];

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < keyword.length; i++) {
                    if (line.contains(keyword[i])) {
                        counts[i]++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return counts;
    }


    public static void main (String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java FileDetails FileName");
            return;
        }
        String fileName = args[0];

        key_count = countKeywords(fileName);
        printBoard();
    }
}