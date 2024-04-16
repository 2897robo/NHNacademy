import java.io.*;

public class FileDetails {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java FileDetails FileName");
            return;
        }
        String fileName = args[0];
        File file = new File(fileName);
        try (FileInputStream stream = new FileInputStream(file)) {
            char[] contents = new char[(int)file.length()];
            for (int i = 0; i < contents.length; i++) {
                contents[i] = (char)stream.read();
            }
            summerize(contents);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void summerize(char[] contents) {
        int vowel = 0, consonants = 0, lines = 0;
        for (char c: contents) {
            if (Character.isLetter(c)) {
                if ("AEIOUaeiou".indexOf(c) != -1) {
                    vowel++;
                }
                else {
                    consonants++;
                }
            } else if (c == '\n') {
                lines++;
            }
        }
        System.out.println("총 문자 수: " + contents.length);
        System.out.println("모음 수: " + vowel);
        System.out.println("자음 수: " + consonants);
        System.out.println("줄 수: " + lines);
    }
}

