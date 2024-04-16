import java.io.*;

public class FileDetails {
    public static void main(String[] args) {
        //
        // To-do: Add code here
        //
        if(args.length != 1) {
            System.out.println("Usage: java FileDetails FileName");
            return;
        }
        String fileName = args[0];

        // read the file that passed from parameter of main method
        File file = new File(fileName);
        
        try (FileInputStream stream = new FileInputStream(file)) {
            char[] contents = new char[(int)file.length()];
            for (int i=0; i<contents.length; i++) {
                contents[i] = (char)stream.read();
            }

            for(char c: contents) {
                System.out.print(c);
            }

            summrize(contents);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void summrize(char[] contents) {
        int mouem =0 , jauem=0, lines = 0;
        for(char c:contents) {
            if(Character.isLetter(c)) {
                if("AEIOUaeiou".indexOf(c) != -1) {
                    mouem++;
                }
                else {
                    jauem++;
                }
            }
            else if(c == '\n') {
                lines++;
            }

        }
        System.out.println("\n총 문자 수 : " + contents.length);
        System.out.println("모음 수 : "+ mouem);
        System.out.println("자음 수 : "+jauem);
        System.out.println("줄 수 : "+lines);
    }
}
