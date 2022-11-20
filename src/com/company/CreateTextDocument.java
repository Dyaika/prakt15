package com.company;

import java.io.FileWriter;
import java.io.IOException;

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
        return new TextDocument("test.txt");
    }
}
