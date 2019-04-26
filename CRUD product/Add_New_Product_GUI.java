﻿/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Common.CopyFile_resizeImg;
import Controller.Manage_Product_CONTROLER;
import Model.Type_Of_Product;
import Model.Supplier;
import Model.Product;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import static java.awt.SystemColor.text;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;

/**
 *
 * 
 */
public class Add_New_Product_GUI extends javax.swing.JFrame {

    /**
     * Creates new form Add_New_Product
     */
    static String Working_Directory = System.getProperty("user.dir");
    static String dst = Working_Directory + "\\src\\images\\uploads\\";
    private File from;
    private Object up;
    private ArrayList<Type_Of_Product> list_loai_Thiet_bi;
    private ArrayList<Supplier> list_NSX;

    Manage_Product_CONTROLER control = new Manage_Product_CONTROLER();

    public Add_New_Product_GUI() {
        initComponents();
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);

// add avatar mậc định:
        String deafult_ava = Working_Directory + "\\src\\images\\product.png";
        CopyFile_resizeImg up = new CopyFile_resizeImg();
        product_ava.setIcon(up.ResizeImage(product_ava, deafult_ava, null));

// hiển thị danh sách nhà sản xuất và loại sản phẩm :
        display_NSX();
        display_loai_Sp();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        product_ava = new javax.swing.JLabel(){

        };
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JText_Ten_Thiet_Bi = new javax.swing.JTextField();
        Jbox_List_Loai_Thiet_Bi = new javax.swing.JComboBox();
        Jbox_List_Nha_San_Xuat = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel(){
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/add.png")));
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel8 = new javax.swing.JLabel(){
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/add.png")));
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Thêm sản phẩm mới");

        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/images/upload_image.png")); // NOI18N
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newimg );
        jButton2.setIcon(icon);
        jButton2.setText("Choose file");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên sản phẩm: ");

        jLabel3.setText("Loại sản phẩm:");

        jLabel4.setText("Nhà sản xuất:");

        Jbox_List_Loai_Thiet_Bi.setModel(new javax.swing.DefaultComboBoxModel());
        Jbox_List_Loai_Thiet_Bi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbox_List_Loai_Thiet_BiActionPerformed(evt);
            }
        });

        Jbox_List_Nha_San_Xuat.setModel(new javax.swing.DefaultComboBoxModel());

        jButton1.setText("Tạo mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setPreferredSize(new java.awt.Dimension(30, 30));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setPreferredSize(new java.awt.Dimension(30, 30));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 33, Short.MAX_VALUE)
                        .addComponent(product_ava, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(165, 165, 165))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(109, 109, 109)
                                    .addComponent(jButton1))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(JText_Ten_Thiet_Bi, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Jbox_List_Loai_Thiet_Bi, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Jbox_List_Nha_San_Xuat, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(product_ava, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JText_Ten_Thiet_Bi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Jbox_List_Loai_Thiet_Bi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Jbox_List_Nha_San_Xuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jButton1))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        //filter the files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png", "jpeg", "gif");
        file.addChoosableFileFilter(filter);

        CopyFile_resizeImg up = new CopyFile_resizeImg();
        int result = file.showSaveDialog(null);
        //if the user click on save in JFilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();

            //copy path and name file to cliboard
            String path = selectedFile.getAbsolutePath();
            this.from = selectedFile;
//            
            //Thay avata tạm thời
            product_ava.setIcon(null);
            product_ava.setIcon(up.ResizeImage(product_ava, path, null));

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No file select");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        //Lấy dữ liệu từ các trường:
        String Ten_Thiet_Bi = this.JText_Ten_Thiet_Bi.getText();

        int selected_nxs = this.Jbox_List_Nha_San_Xuat.getSelectedIndex();
        String Ma_NSX = this.list_NSX.get(selected_nxs).getCode_Supplier();

        int selected_loai_thiet_bi = this.Jbox_List_Loai_Thiet_Bi.getSelectedIndex();
        String Ma_Loai = this.list_loai_Thiet_bi.get(selected_loai_thiet_bi).getCodeType();

        if (Ten_Thiet_Bi.equals("")) {
            JOptionPane.showMessageDialog(null, "Tất cả các trường là bắt buộc", "sorry", JOptionPane.ERROR_MESSAGE);
        } else if (control.is_Available_pro(Ten_Thiet_Bi, Ma_NSX)) {
            JOptionPane.showMessageDialog(null, "Thiết bị đã tồn tại", "sorry", JOptionPane.ERROR_MESSAGE);
        } else {
            Product temp = new Product();
            if (this.from != null) {
            //kiểm tra tên có hợp lẹ:

                //Copy file ava gửi lên vào thư mục project
                String newName = this.from.getName().replaceAll("[^\\w .-]+", "_");
                File dest = new File(dst + newName);
                CopyFile_resizeImg up = new CopyFile_resizeImg();
                try {
                    up.copyFileUsingStream(this.from, dest);
                } catch (IOException ex) {
                    Logger.getLogger(Add_New_Product_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                String Anh_SP = newName;
                temp = new Product(Ten_Thiet_Bi, Ma_NSX, Ma_Loai, Anh_SP);
            } else {
                temp = new Product(Ten_Thiet_Bi, Ma_NSX, Ma_Loai);
            }
            if (control.insert(temp)) {
                JOptionPane.showMessageDialog(null, "Thêm thành công", "congratulations", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "có gì đó sai sai", "sorry", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Jbox_List_Loai_Thiet_BiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbox_List_Loai_Thiet_BiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_Jbox_List_Loai_Thiet_BiActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:

        Add_New_Supplier_GUI child = new Add_New_Supplier_GUI();
        child.pack();
        child.setVisible(true);
        child.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                display_NSX();
            }
        });
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        Add_New_TypeOfProduct_GUI child = new Add_New_TypeOfProduct_GUI();
        child.pack();
        child.setVisible(true);
        child.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                display_loai_Sp();
            }
        });
    }//GEN-LAST:event_jLabel8MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Add_New_Product_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_New_Product_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_New_Product_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_New_Product_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_New_Product_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JText_Ten_Thiet_Bi;
    private javax.swing.JComboBox Jbox_List_Loai_Thiet_Bi;
    private javax.swing.JComboBox Jbox_List_Nha_San_Xuat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel product_ava;
    // End of variables declaration//GEN-END:variables

    public void display_loai_Sp() {
        // add loại sản phẩm cho combo box:
        this.Jbox_List_Loai_Thiet_Bi.removeAllItems();
        this.list_loai_Thiet_bi = control.get_List_Type();
        for (Type_Of_Product temp : this.list_loai_Thiet_bi) {
            Jbox_List_Loai_Thiet_Bi.addItem(temp.getNameType());
        }
    }

    public void display_NSX() {
        // add Nhà sản xuất cho bombo box:
        this.Jbox_List_Nha_San_Xuat.removeAllItems();
        this.list_NSX = control.get_List_Supplier();
        for (Supplier temp : this.list_NSX) {
            Jbox_List_Nha_San_Xuat.addItem(temp.getName_Supplier());
        }
    }

}