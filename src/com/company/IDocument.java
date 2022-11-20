package com.company;

import java.awt.*;
import java.io.IOException;

public interface IDocument {
    public Component getContent();
    public void setContent(Object content);
    public void save(Component component) throws IOException;
    public void exit();
}
