package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;

public class CreateTextDocument implements ICreateDocument{
    @Override
    public TextDocument createNew() throws IOException {
        FileWriter fileWriter = new FileWriter("test.txt");
        fileWriter.write("");
        fileWriter.close();
        return new TextDocument("test.txt");
    }

    @Override
    public TextDocument createOpen() throws IOException {
        File file = new File("test.txt");
        if (!file.exists()){
            FileWriter fileWriter = new FileWriter("test.txt");
            fileWriter.write("this is new file named test.txt");
            fileWriter.close();
        }
        return new TextDocument("test.txt");
    }
}
