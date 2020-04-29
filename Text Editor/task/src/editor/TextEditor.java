package editor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextEditor extends JFrame {

    Deque<Integer> regularSearchIndex = new ArrayDeque<>();

    Deque<Integer> regexSearchIndex = new ArrayDeque<>();

    public TextEditor() {
        initComponents();
    }

    private void initComponents() {

        //Panel for the UI
        JPanel jPanel = new JPanel();

        //Text Area
        JTextArea textArea = new JTextArea();
        textArea.setName("TextArea");

        //ScrollPane for textArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");

        //Open Button
        JButton loadButton = new JButton();
        loadButton.setName("OpenButton");
        loadButton.setIcon(new ImageIcon("/Users/nabin/Desktop/loadFileIcon.png"));

        //Save Button
        JButton saveButton = new JButton();
        saveButton.setName("SaveButton");
        saveButton.setIcon(new ImageIcon("/Users/nabin/Desktop/saveIcon.png"));

        //Search text field
        JTextField textField = new JTextField();
        textField.setName("SearchField");

        //Start Search Button
        JButton startSearchButton = new JButton();
        startSearchButton.setName("StartSearchButton");
        startSearchButton.setIcon(new ImageIcon("/Users/nabin/Desktop/searchIcon.png"));

        //Previous match button
        JButton previousMatchButton = new JButton();
        previousMatchButton.setName("PreviousMatchButton");
        previousMatchButton.setIcon(new ImageIcon("/Users/nabin/Desktop/leftAngleBracket.png"));

        //Next match Button
        JButton nextMatchButton = new JButton();
        nextMatchButton.setName("NextMatchButton");
        nextMatchButton.setIcon(new ImageIcon("/Users/nabin/Desktop/rightAngleBracket.png"));

        //Check box for regular expression option
        JCheckBox regexCheckBox = new JCheckBox();
        regexCheckBox.setText("Use Regex");
        regexCheckBox.setName("UseRegExCheckbox");

        //MenuBar
        JMenuBar jMenuBar = new JMenuBar();

        // File Menu and their items
        JMenu fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");

        JMenuItem loadMenuItem = new JMenuItem("Open");
        loadMenuItem.setName("MenuOpen");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setName("MenuSave");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setName("MenuExit");

        //Add File menu items to the file menu
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        //Add Search Menu and their menu items
        JMenu searchMenu = new JMenu("Search");
        searchMenu.setName("MenuSearch");

        JMenuItem startSearchMenuItem = new JMenuItem("Start Search");
        startSearchMenuItem.setName("MenuStartSearch");
        JMenuItem previousSearchMenuItem = new JMenuItem("PreviousSearch");
        previousSearchMenuItem.setName("MenuPreviousMatch");
        JMenuItem nextSearchMenuItem = new JMenuItem("MenuNextMatch");
        nextSearchMenuItem.setName("MenuNextMatch");
        JMenuItem useRegexSearchMenuItem = new JMenuItem("Use regular expressions");
        useRegexSearchMenuItem.setName("MenuUseRegExp");
        searchMenu.add(startSearchMenuItem);
        searchMenu.add(previousSearchMenuItem);
        searchMenu.add(nextSearchMenuItem);
        searchMenu.add(useRegexSearchMenuItem);

        //Add menu to the menu bar
        jMenuBar.add(fileMenu);
        jMenuBar.add(searchMenu);

        //Title of the JFrame
        setTitle("Text Editor");

        //File Chooser
        JFileChooser fileChooser = new JFileChooser();
        add(fileChooser);
        fileChooser.setFileFilter(
                new FileNameExtensionFilter("Only .txt file allowed", "txt"));
        fileChooser.setName("FileChooser");

        setJMenuBar(jMenuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GroupLayout jPanelLayout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(loadButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startSearchButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(previousMatchButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextMatchButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(regexCheckBox)
                                .addGap(12, 12, 12))
                        .addGroup(jPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane)
                                .addContainerGap())
        );

        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout.createSequentialGroup()
                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(regexCheckBox))
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(nextMatchButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(textField, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(loadButton, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(startSearchButton, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(previousMatchButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();

        loadButton.addActionListener(actionEvent -> {
            loadText(fileChooser, textArea);
        });

        saveButton.addActionListener(actionEvent -> {
            saveText(fileChooser, textArea);
        });

        loadMenuItem.addActionListener(actionEvent -> {
            loadText(fileChooser, textArea);
        });
        saveMenuItem.addActionListener(actionEvent -> {
            saveText(fileChooser, textArea);
        });
        exitMenuItem.addActionListener(actionEvent -> {
            dispose();
        });

        startSearchButton.addActionListener(actionEvent -> {
            if (regexCheckBox.isSelected()) {
                regexSearch(textArea, textField.getText());
            }
            searchText(textArea, textField.getText());
        });

        nextMatchButton.addActionListener(actionEvent -> {
            if (this.regularSearchIndex != null) {
                nextSearch(textArea, textField.getText());
            }
        });

        previousMatchButton.addActionListener(actionEvent -> {
            if (!this.regularSearchIndex.isEmpty()) {
                previousSearch(textArea, textField.getText());
            }
        });
    }

    private void regexSearch(JTextArea textArea, String text) {
        Pattern compile = Pattern.compile(text);
        Matcher matcher = compile.matcher(textArea.getText());

        if (matcher.find()) {
            int start = matcher.start();
            this.regexSearchIndex.offerFirst(start);
            paintText(textArea, regexSearchIndex.peekLast(), matcher.group());
        }

    }


    private void loadText(JFileChooser fileChooser, JTextArea textArea) {
        int dialog = fileChooser.showOpenDialog(null);

        if (dialog == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                FileReader stream = new FileReader(file);
                StringBuilder string = new StringBuilder();
                while (stream.ready()) {
                    string.append((char) stream.read());
                }
                textArea.setText(string.toString());
            } catch (FileNotFoundException e) {
                textArea.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void saveText(JFileChooser fileChooser, JTextArea textArea) {
        int dialog = fileChooser.showSaveDialog(null);
        if (dialog == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(selectedFile)) {
                writer.print(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
                e.getLocalizedMessage();
            }
        }
    }

    private void searchText(JTextArea textArea, String searchQuery) {
        String content = textArea.getText();
        int i = content.indexOf(searchQuery, 0);
        if (i >= 0) {
            this.regularSearchIndex.offerLast(i);
        }
        if (!this.regularSearchIndex.isEmpty()) {
            paintText(textArea, regularSearchIndex.peek(), searchQuery);
        }
    }

    private void nextSearch(JTextArea textArea, String searchQuery) {
        String content = textArea.getText();
        Integer peek = regularSearchIndex.peek();
        int i = content.indexOf(searchQuery, peek + 1);
        this.regularSearchIndex.offerFirst(i);
        if (i >= 0) {
            System.out.println(this.regularSearchIndex);
            paintText(textArea, regularSearchIndex.peekFirst(), searchQuery);
        }
    }

    private void previousSearch(JTextArea textArea, String searchQuery) {
        System.out.println(this.regularSearchIndex);
        if (!this.regularSearchIndex.isEmpty() && this.regularSearchIndex != null) {
            if (this.regularSearchIndex.size() > 1) {
                Integer integer = this.regularSearchIndex.pollFirst();
                paintText(textArea, integer, searchQuery);
            }
        } else {
            return;
        }
    }


    private void paintText(JTextArea textArea, int i, String searchQuery) {
        textArea.setCaretPosition(i + searchQuery.length());
        textArea.select(i, i + searchQuery.length());
        textArea.grabFocus();
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new TextEditor().setVisible(true));
    }

}