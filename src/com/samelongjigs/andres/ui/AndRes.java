/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samelongjigs.andres.ui;

import com.samelongjigs.andres.controller.StringParseHandler;
import com.samelongjigs.andres.controller.XmlWriter;
import com.samelongjigs.andres.model.StringRes;
import com.samelongjigs.andres.ui.StringsFrame;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author cris
 */
public class AndRes extends javax.swing.JFrame {

    final String INITIAL_PATH = System.getProperty("user.home");
    
    
    /**
     * Creates new form AndRes
     */
    public AndRes() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(desktopPane, java.awt.BorderLayout.CENTER);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        JFileChooser chooser = new JFileChooser(INITIAL_PATH);
//        chooser.setM("Open android project/module");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selFile = chooser.getSelectedFile();
            try {
                
                StringsFrame strFrame = new StringsFrame();
                desktopPane.add(strFrame);
                strFrame.setClosable(true);
                DefaultTableModel strModel = new DefaultTableModel();
                strModel.addColumn("#");
                strModel.addColumn("Category");
                strModel.addColumn("Name");
                strModel.addColumn("Value");
                
                JTable table = strFrame.getStringsTable();
                table.setModel(strModel);
                Font font = table.getFont();
                table.setFont(new Font(font.getFontName(), Font.PLAIN, 16));
                table.getColumn("Name").setCellRenderer(new TableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        String k = ((StringRes) value).getName();
                        System.out.println("key " + k);
                        return new JLabel(k);
                    }
                });
                
                Stream<Path> resPathsStream = Files.walk(selFile.toPath())
                    .filter(path -> path.getFileName().toString().matches("string.*\\.xml"));
                
                resPathsStream.forEach(path -> 
                        processResourceFiles(path.toFile(), strModel));
                strFrame.setVisible(true);
                
                strFrame.getStringsTable().getColumnModel().getColumn(0).setPreferredWidth(100);
                strFrame.getStringsTable().getColumnModel().getColumn(0).setWidth(100);
            } catch (IOException ex) {
                Logger.getLogger("ui").log(Level.WARNING, "open error: ", ex);
            }
        }

    }//GEN-LAST:event_openMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        JInternalFrame frame = desktopPane.getSelectedFrame();
        if (frame instanceof StringsFrame) {
            
            TableModel mdl = ((StringsFrame) frame).getStringsTable().getModel();
            int rows = mdl.getRowCount();
            List<StringRes> contents = new ArrayList<>(rows);
            for (int i = 0; i < rows; i++) {
                Object res = mdl.getValueAt(i, 2);
                if (res instanceof StringRes) {
                    contents.add((StringRes) res);
                }
            }
            String outpath = INITIAL_PATH + File.separator + "strings_" 
                    + System.currentTimeMillis() + ".xml";
            System.out.println("writing " + contents.size() + " strs to " + outpath);
            XmlWriter.writeStrings(outpath, contents);
        } else {
            System.out.println("nothing sel");
        }
    }//GEN-LAST:event_saveMenuItemActionPerformed

    void processResourceFiles(File f, DefaultTableModel strModel) {
        try {
            SAXParser strParser = SAXParserFactory.newInstance().newSAXParser();
            StringParseHandler xmlParseHdl = new StringParseHandler();
            strParser.getXMLReader().setProperty("http://xml.org/sax/properties/lexical-handler", xmlParseHdl);
            strParser.parse(f, xmlParseHdl);

//                    xmlParseHdl.strings
            Object[] data;
            for (StringRes res : xmlParseHdl.parsedStrings) {
                data = new Object[]{strModel.getRowCount(), res.getCategory(), 
                    res, res.getValue()};
//                System.out.println(data[0] + " -> " + data[1]);
                strModel.addRow(data);
            }

            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(AndRes.class.getName()).log(Level.WARNING, null, ex);
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables

    

}
