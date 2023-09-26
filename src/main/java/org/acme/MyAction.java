package org.acme;

import io.quarkiverse.githubaction.Action;
import io.quarkiverse.githubaction.Context;
import io.quarkiverse.githubaction.Inputs;
import jakarta.inject.Inject;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    @Inject
    S3Client s3;

    @Action
    void action(Inputs inputs) {
        System.out.println("Hello from Quarkus GitHub Action");
        System.out.println("Size: " + inputs.all().size());
        // Iterate through the map and print each entry
        for (Map.Entry<String, String> entry : inputs.all().entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        String fileName = inputs.getRequired("file-path");
        System.out.println(System.getProperty("user.dir") + "/" + fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/" + fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("END");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Specify the directory path you want to list
        String directoryPath = System.getProperty("user.dir") + "/src"; // Replace with your directory path
        System.out.println(directoryPath);
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

        System.out.println("End from Quarkus GitHub Action");

    }
}