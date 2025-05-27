import files.Files;
import unrelated.Menu;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Files.createFiles();

        Scanner sc = new Scanner(System.in);
            while(true)
                Menu.menu();
    }
}