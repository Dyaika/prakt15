package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextDocument implements IDocument{
    private StringBuilder content;
    private final String fileName;
    private FileReader fr;
    private FileWriter fw;
    public TextDocument(String fileName) throws IOException {
        this.fileName = fileName;
        this.content = new StringBuilder();
        fr = new FileReader(fileName);
        Scanner sc = new Scanner(fr);
        while(sc.hasNext()){
            content.append(sc.nextLine() + "\n");
        }

        fr.close();
    }

    @Override
    public String getContent() {
        return content.toString();
    }

    @Override
    public void setContent(Object content) {
        //может тут
        this.content = new StringBuilder((String)content);
    }

    @Override
    public void save() throws IOException {

        fw = new FileWriter(fileName);
        fw.write(content.toString());
        fw.close();
    }

    @Override
    public void exit() {
        content = new StringBuilder();
    }
}
