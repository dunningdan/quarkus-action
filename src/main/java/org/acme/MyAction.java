package org.acme;

import io.quarkiverse.githubaction.Action;
import io.quarkiverse.githubaction.Context;
import io.quarkiverse.githubaction.Inputs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class MyAction {

//    @Action
//    void test() {
//        System.out.println("Hello from Quarkus GitHub Action");
//    }
//
//    @Action
//    void action(Context context) {
//        String runnerOs = context.getRunnerOs();
//
//        context.print();
//    }


    @Action
    void action(Inputs inputs) {
        System.out.println("Hello from Quarkus GitHub Action");
        System.out.println("Size: " + inputs.all().size());
        // Iterate through the map and print each entry
        for (Map.Entry<String, String> entry : inputs.all().entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // Specify the directory path you want to list
        String directoryPath = "artifacts/"; // Replace with your directory path

        // Create a File object representing the directory
        File directory = new File(directoryPath);
        System.out.println("Directory " + directory.isDirectory());
        // Check if the path is a directory
        if (directory.isDirectory()) {
            // Get a list of files and subdirectories in the directory
            File[] files = directory.listFiles();

            if (files != null) {
                // Loop through the list and print the names of files and directories
                for (File file : files) {
                    System.out.println(file.getName());
                }
            } else {
                System.err.println("Failed to list files in the directory.");
            }
        } else {
            System.err.println("The specified path is not a directory.");
        }

        try {
            // Get the current working directory
            Path currentDirectory = Paths.get(System.getProperty("user.dir"));

            System.out.println("Current Directory: " + currentDirectory);

            // List files in the current directory
            try (Stream<Path> files = Files.list(currentDirectory)) {
                files.forEach(path -> System.out.println(path.getFileName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("End from Quarkus GitHub Action");

    }
}