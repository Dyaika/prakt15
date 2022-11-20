package com.company;

import java.io.IOException;

public interface ICreateDocument {
    public IDocument createNew() throws IOException;
    public IDocument createOpen() throws IOException;
}
