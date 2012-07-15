package gui;


import al.RomajiToKana;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alaix
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {}
        initComponents();
        opciones = new ArrayList<String>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JWordPad");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.setEnabled(false);

        jMenu1.setText("File");

        jMenuItem1.setText("New");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Open...");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Save");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem3MousePressed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Save as...");
        jMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem4MousePressed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Exit");
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Cut");
        jMenuItem6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem6MousePressed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Copy");
        jMenuItem7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem7MousePressed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Paste");
        jMenuItem8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem8MousePressed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Utilities");

        jMenuItem9.setText("Dictionary");
        jMenuItem9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem9MousePressed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextArea1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyTyped
        // TODO add your handling code here:
        KeyTyped(evt);
        //capslock();
    }//GEN-LAST:event_jTextArea1KeyTyped

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar() == 8)
            if(jTextField1.getText().length() > 0)
                evt.consume();
        //System.out.println(this.getToolkit().getLockingKeyState(evt.VK_CAPS_LOCK));
    }//GEN-LAST:event_jTextArea1KeyPressed

    private void jMenuItem6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem6MousePressed
        // TODO add your handling code here:
        jTextArea1.cut();
    }//GEN-LAST:event_jMenuItem6MousePressed

    private void jMenuItem7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem7MousePressed
        // TODO add your handling code here:
        jTextArea1.copy();
    }//GEN-LAST:event_jMenuItem7MousePressed

    private void jMenuItem8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem8MousePressed
        // TODO add your handling code here:
        jTextArea1.paste();
    }//GEN-LAST:event_jMenuItem8MousePressed

    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
        // TODO add your handling code here:
        abrir();
    }//GEN-LAST:event_jMenuItem2MousePressed

    private void jMenuItem3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MousePressed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_jMenuItem3MousePressed

    private void jMenuItem4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MousePressed
        // TODO add your handling code here:
        guardarComo();
    }//GEN-LAST:event_jMenuItem4MousePressed

    private void jMenuItem9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem9MousePressed
        // TODO add your handling code here:
        Dictionary d = new Dictionary();
        d.SetVisible(true);
    }//GEN-LAST:event_jMenuItem9MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    
    ArrayList<String> opciones;
    String archivo;
    
    void KeyTyped(java.awt.event.KeyEvent evt)
    {
        char c = evt.getKeyChar();
        boolean mayus = evt.isAltDown();
        String s = jTextField1.getText();
        if(c == 8)
        {
            if(s.length() > 0)
            {
                s = s.substring(0, s.length()-1);
                genOpciones(s, mayus);
            }
        }
        else if(c == 27)
        {
            s = new String();
            genOpciones(s, mayus);
        }
        else if(Character.isLetter(c))
        {
            s = s+c;
            if(!genOpciones(s, mayus))
            {
                evt.consume();
                return;
            }
        }
        else if('1' <= c && c <= '9')
        {
            if(opciones.isEmpty())
                insertText(String.valueOf(c));
            else
            {
                int o = c-'0'-1;
                if(o < opciones.size())
                {
                    insertText(opciones.get(o));
                    s = new String();
                    genOpciones(s, mayus);
                }
            }
        }
        else if(c == ',')
        {
            if(opciones.isEmpty())
                insertText("、");
        }
        else if(c == '.')
        {
            if(opciones.isEmpty())
                insertText("。");
        }
        else if(c == '-')
        {
            if(opciones.isEmpty())
                insertText("ー");
            else
            {
                s = s+c;
                genOpciones(s, mayus);
            }
        }
        else
        {
            return;
        }
        evt.consume();
        jTextField1.setText(s);
    }
    void insertText(String s)
    {
        jTextArea1.insert(s, jTextArea1.getCaretPosition());
    }
    boolean genOpciones(String s, boolean Mayus)
    {
        RomajiToKana rtk = new RomajiToKana();
        if(rtk.Convert(s, Mayus) == -1)
            return false;
        opciones.clear();
        if(rtk.result.length() > 0)
        {
            opciones.add(rtk.result.toString());
            rtk.Convert(s, !Mayus);
            opciones.add(rtk.result.toString());
        }
            
        int i = 1;
        StringBuffer sb = new StringBuffer();
        for (String o : opciones)
        {
            sb.append(i).append(") ").append(o).append(' ');
            i++;
        }
        jTextField2.setText(sb.toString());
        return true;
    }
    public boolean capslock()
    {
        try
        {
            return Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        }
        catch(UnsupportedOperationException e)
        {
            System.out.println("Nel");
            return false;
        }
    }
    
    void abrir()
    {
        JFileChooser fc = new JFileChooser();
        if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            archivo = fc.getSelectedFile().toString();
            jTextArea1.setText(leerYRegresar(archivo));
        }
    }
    String leerYRegresar(String Name)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            FileReader fr = new FileReader(Name);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            while(str != null)
            {
                sb.append(str).append("\n");
                str = br.readLine();
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
    void guardar()
    {
        if(archivo != null)
            guardar(archivo);
        else
            guardarComo();
    }
    void guardar(String Name)
    {
        try
        {
            BufferedWriter salida = new BufferedWriter(new FileWriter(Name));
            salida.write(jTextArea1.getText());
            salida.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void guardarComo()
    {
        JFileChooser fc = new JFileChooser();
        if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            archivo = fc.getSelectedFile().toString();
            guardar(archivo);
        }
    }
}