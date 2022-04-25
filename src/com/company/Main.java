package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here

        MyFile myFile = new MyFile();
        myFile.help();

        while (true){
            Scanner scanner = new Scanner(System.in);
            String fullCommand = scanner.nextLine();
            String[] fullCommandSplit = fullCommand.split(" ");
            String baseCommand = fullCommandSplit[0];
            if (fullCommand.equalsIgnoreCase("exit")){
                myFile.exit();
                break;
            }
            switch (baseCommand){
                case "ls":
                    myFile.listDirectory(fullCommandSplit[1]);
                    break;
                case "ls_py":
                    myFile.listPythonFiles(fullCommandSplit[1]);
                    break;
                case "is_dir":
                    myFile.isDirectory(fullCommandSplit[1]);
                    break;
                case "define":
                    myFile.define(fullCommandSplit[1]);
                    break;
                case "readmod":
                    myFile.printPermissions(fullCommandSplit[1]);
                    break;
                case "setmod":
                    myFile.setPermissions(fullCommandSplit[1], fullCommandSplit[2]);
                    break;
                case "cat":
                    myFile.printContent(fullCommandSplit[1]);
                    break;
                case "append":
                    myFile.appendFooter(fullCommandSplit[1]);
                    break;
                case "bc":
                    myFile.createBackup(fullCommandSplit[1]);
                    break;
                case "greplong":
                    myFile.printLongestWord(fullCommandSplit[1]);
                    break;
                case "help":
                    myFile.help();
                    break;
                default:
                    System.out.println("No such command. Use help to find available commands.");
            }

        }

    }

    static void copySourceToDest(Path fromPath, Path source) {

        LocalDateTime ldt = LocalDateTime.now();
        String newDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(ldt);

        Path destination = Paths.get("/tmp/" + newDate + ".backup", source.toString().substring(fromPath.toString().length()));
        try {
            Files.copy(source, destination);

        } catch (IOException e) {
            System.out.println("Oops! No such file. Use correct one!");
        }
    }
}

