package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class TextEditor extends JFrame {
    private Component mainContent;
    private final JMenu jMenu;
    private final JMenuItem[] jMenuItems;
    private final JScrollPane jScrollPane;
    private ICreateDocument createDocument;
    private IDocument document;
    public TextEditor() {
        super("Text Editor");
        setLayout(new BorderLayout());

        //подпункты File
        jMenuItems = new JMenuItem[4];
        jMenuItems[0] = new JMenuItem("New", SwingConstants.CENTER);
        jMenuItems[1] = new JMenuItem("Open", SwingConstants.CENTER);
        jMenuItems[2] = new JMenuItem("Save", SwingConstants.CENTER);
        jMenuItems[3] = new JMenuItem("Exit", SwingConstants.CENTER);

        //вкладка File
        jMenu = new JMenu("File");
        for (JMenuItem item:
             jMenuItems) {
            jMenu.add(item);
        }

        //само меню
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        add(jMenuBar, BorderLayout.NORTH);

        //контент
        mainContent = null;
        jScrollPane = new JScrollPane();
        add(jScrollPane, BorderLayout.CENTER);

        addActions();

        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void addActions(){
        createDocument = new CreateTextDocument();

        //new
        jMenuItems[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    document = createDocument.createNew();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mainContent = document.getContent();
                jScrollPane.setColumnHeaderView(mainContent);
                System.out.println("HELLO FROM NEW");
            }
        });

        //open
        jMenuItems[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    document = createDocument.createOpen();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mainContent = document.getContent();
                jScrollPane.setColumnHeaderView(mainContent);
                System.out.println("HELLO FROM OPEN");
            }
        });

        //save
        jMenuItems[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (document != null && mainContent != null){
                    try {
                        document.save(mainContent);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println("HELLO FROM SAVE");
            }
        });

        //exit
        jMenuItems[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (document != null){
                    document.exit();
                }
                jScrollPane.setColumnHeaderView(null);
                System.out.println("HELLO FROM EXIT");
            }
        });

    }
}
