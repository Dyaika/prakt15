package com.company;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class TextEditor extends JFrame {
    private Component mainContent;
    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem[] jMenuItems;
    private JScrollPane jScrollPane;
    private ICreateDocument createDocument;
    private IDocument document;
    public TextEditor() throws HeadlessException {
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
        jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        add(jMenuBar, BorderLayout.NORTH);

        //контент
        mainContent = new JTextArea();
        jScrollPane = new JScrollPane(mainContent);
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
                    document = (TextDocument)createDocument.createNew();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.out.println("HELLO FROM NEW");
                ((JTextArea)mainContent).setText("");
                repaint();
            }
        });

        //open
        jMenuItems[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    document = (TextDocument)createDocument.createOpen();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ((JTextArea)mainContent).setText((String) document.getContent());
                repaint();
                System.out.println("HELLO FROM OPEN");
            }
        });

        //save
        jMenuItems[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (document != null){
                    document.setContent(((JTextArea)mainContent).getText());
                    try {
                        document.save();
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
                document.exit();
                ((JTextArea)mainContent).setText("");
                System.out.println("HELLO FROM EXIT");
            }
        });

    }
}
