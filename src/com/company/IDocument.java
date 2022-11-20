package com.company;

import java.io.IOException;

public interface IDocument {
    public Object getContent();
    public void setContent(Object content);
    public void save() throws IOException;
    public void exit();
}
