import java.io.*;
import java.util.Scanner;

public class CopyFileLower {
    public static void main(String[] args) {
        String stringFrom, stringTo;
        BufferedReader fileStreamFrom;
        BufferedWriter fileStreamTo;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Copy From: ");
        stringFrom = scanner.nextLine();
        System.out.print("Copy To: ");
        stringTo = scanner.nextLine();
        scanner.close();

        try {
            FileReader inputFile = new FileReader(stringFrom);
            File outputFile = new File(stringTo);

            fileStreamFrom = new BufferedReader(inputFile);
            fileStreamTo = new BufferedWriter(new FileWriter(outputFile));

            String stringBuffer = "";
            while ((stringBuffer = fileStreamFrom.readLine()) != null) {
                stringBuffer = stringBuffer.toLowerCase();
                fileStreamTo.write(stringBuffer);
                fileStreamTo.write("\n");
            }

            fileStreamFrom.close();
            fileStreamTo.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File " + stringFrom + " not found");
        }
        catch(IOException e) {
            System.out.println("Problem occured when try to writing file");
        }
    }
}
