/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.Manage_Product_CONTROLER;
import Controller.Manage_Staff_CONTROLLER;
import Controller.Manage_storage_CONTROLLER;
import Model.Item;
import Model.Product;
import Model.Staff;
import Model.Supplier;
import Model.Type_Of_Product;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Nguyen Hiep
 */
public class Add_Items_Input_GUI extends javax.swing.JFrame {

    /**
     * Creates new form Add_Items_Input_GUI
     */
    Manage_Product_CONTROLER control_prod = new Manage_Product_CONTROLER();
    Manage_Staff_CONTROLLER control_staff = new Manage_Staff_CONTROLLER();
    Manage_storage_CONTROLLER control_store = new Manage_storage_CONTROLLER();
    private String code_bill;

    public Add_Items_Input_GUI(String code_bill) {
        initComponents();
        setLocationRelativeTo(null);
        this.code_bill = code_bill;

        display_AllProduct();
        display_AllSupplier();
        display_AllTypeOfProd();
        ArrayList<Product> list_prod = control_prod.get_List_Product();
        int default_profit = 0;
        if (list_prod.size() != 0) {
            Product prod = list_prod.get(0);
            Supplier sup = new Supplier();
            Type_Of_Product type = new Type_Of_Product();
            sup.getDetail(prod.getCode_supplier());
            type.getDetail(prod.getCode_type());

            this.jComboBox_Name.setSelectedItem(prod.getName());
            this.jComboBox_supplier.setSelectedItem(sup.getName_Supplier());
            this.jComboBox_type.setSelectedItem(type.getNameType());
            this.jLabel_unit.setText(type.getUnit());
            this.jSpinner_price.setValue(prod.getPrice());
            default_profit = prod.getProfit();
        }
//set number only for jspinner:
        JFormattedTextField txt_price = ((JSpinner.NumberEditor) jSpinner_price.getEditor()).getTextField();
        ((NumberFormatter) txt_price.getFormatter()).setAllowsInvalid(false);
        JFormattedTextField txt_quantity = ((JSpinner.NumberEditor) jSpinner_quantity.getEditor()).getTextField();
        ((NumberFormatter) txt_quantity.getFormatter()).setAllowsInvalid(false);
        JFormattedTextField txt_profit = ((JSpinner.NumberEditor) jSpinner_profit.getEditor()).getTextField();
        ((NumberFormatter) txt_profit.getFormatter()).setAllowsInvalid(false);
        this.jSpinner_profit.setModel(new SpinnerNumberModel(default_profit, 0, 100, 1));

    }

    public Add_Items_Input_GUI() {
        {
            initComponents();
            setLocationRelativeTo(null);

            display_AllProduct();
            display_AllSupplier();
            display_AllTypeOfProd();
            ArrayList<Product> list_prod = control_prod.get_List_Product();
            if (list_prod.size() != 0) {
                Product prod = list_prod.get(0);
                System.out.println(prod.toString());
                Supplier sup = new Supplier();
                Type_Of_Product type = new Type_Of_Product();
                sup.getDetail(prod.getCode_supplier());
                type.getDetail(prod.getCode_type());

                this.jComboBox_Name.setSelectedItem(prod.getName());
                this.jComboBox_supplier.setSelectedItem(sup.getName_Supplier());
                this.jComboBox_type.setSelectedItem(type.getNameType());
                this.jLabel_unit.setText(type.getUnit());
                this.jSpinner_price.setValue(prod.getPrice());
                this.jSpinner_profit.setValue(prod.getProfit());
            }
//set number only for jspinner:
            JFormattedTextField txt_price = ((JSpinner.NumberEditor) jSpinner_price.getEditor()).getTextField();
            ((NumberFormatter) txt_price.getFormatter()).setAllowsInvalid(false);
            JFormattedTextField txt_quantity = ((JSpinner.NumberEditor) jSpinner_quantity.getEditor()).getTextField();
            ((NumberFormatter) txt_quantity.getFormatter()).setAllowsInvalid(false);
            JFormattedTextField txt_profit = ((JSpinner.NumberEditor) jSpinner_profit.getEditor()).getTextField();
            ((NumberFormatter) txt_quantity.getFormatter()).setAllowsInvalid(false);

        }
    }

    public void display_AllProduct() {
        jComboBox_Name.removeAllItems();;
        ArrayList<Product> list = control_prod.get_List_Product();
        for (Product list1 : list) {
            jComboBox_Name.addItem(list1.getName());
        }
    }

    public void display_AllSupplier() {
        jComboBox_supplier.removeAllItems();;
        ArrayList<Supplier> list = control_prod.get_List_Supplier();
        for (Supplier list1 : list) {
            jComboBox_supplier.addItem(list1.getName_Supplier());
        }
    }

    public void display_AllTypeOfProd() {
        jComboBox_type.removeAllItems();;
        ArrayList<Type_Of_Product> list = control_prod.get_List_Type();
        for (Type_Of_Product list1 : list) {
            jComboBox_type.addItem(list1.getNameType());
        }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_Name = new javax.swing.JComboBox();
        jLabel_unit = new javax.swing.JLabel();
        jSpinner_quantity = new javax.swing.JSpinner();
        jSpinner_price = new javax.swing.JSpinner();
        jButton_submit = new javax.swing.JButton();
        jComboBox_supplier = new javax.swing.JComboBox();
        jComboBox_type = new javax.swing.JComboBox();
        jLabel_NameProd = new javax.swing.JLabel(){
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/add.png")));
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel_sup = new javax.swing.JLabel(){
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/add.png")));
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel_type = new javax.swing.JLabel(){
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/add.png")));
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel8 = new javax.swing.JLabel();
        jSpinner_profit = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("SẢN PHẨM");

        jLabel2.setText("Tên thiết bị:");

        jLabel3.setText("Nhà sản xuất");

        jLabel4.setText("Loại");

        jLabel5.setText("Đơn vị:");

        jLabel6.setText("Số lượng:");

        jLabel7.setText("Đơn giá;");

        jComboBox_Name.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));
        jComboBox_Name.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_NameItemStateChanged(evt);
            }
        });
        jComboBox_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_NameActionPerformed(evt);
            }
        });

        jLabel_unit.setPreferredSize(new java.awt.Dimension(20, 10));

        jSpinner_quantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jSpinner_price.setModel(new javax.swing.SpinnerNumberModel(0, 0, 999999999, 100));

        jButton_submit.setText("Thêm");
        jButton_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_submitActionPerformed(evt);
            }
        });

        jComboBox_supplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jComboBox_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        jComboBox_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_typeActionPerformed(evt);
            }
        });

        jLabel_NameProd.setPreferredSize(new java.awt.Dimension(30, 30));
        jLabel_NameProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_NameProdMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_NameProdMouseReleased(evt);
            }
        });

        jLabel_sup.setPreferredSize(new java.awt.Dimension(30, 30));
        jLabel_sup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_supMouseClicked(evt);
            }
        });

        jLabel_type.setPreferredSize(new java.awt.Dimension(30, 30));
        jLabel_type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_typeMouseClicked(evt);
            }
        });

        jLabel8.setText("Lợi nhuận:");

        jLabel9.setText("%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jComboBox_type, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jComboBox_supplier, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(23, 23, 23)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel_type, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel_sup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jSpinner_quantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton_submit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSpinner_price, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jSpinner_profit, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel9)))
                                    .addComponent(jComboBox_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(jLabel_NameProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_NameProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jComboBox_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jComboBox_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jSpinner_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jSpinner_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jSpinner_profit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jButton_submit)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_NameActionPerformed
        // TODO add your handling code here:
        ArrayList<Product> list_prod = control_prod.get_List_Product();
        if (list_prod.size() != 0 && this.jComboBox_Name.getSelectedIndex() != -1) {

            Product prod = list_prod.get(this.jComboBox_Name.getSelectedIndex());
            Supplier sup = new Supplier();
            Type_Of_Product type = new Type_Of_Product();
            sup.getDetail(prod.getCode_supplier());
            type.getDetail(prod.getCode_type());

            this.jComboBox_Name.setSelectedItem(prod.getName());
            this.jComboBox_supplier.setSelectedItem(sup.getName_Supplier());
            this.jComboBox_type.setSelectedItem(type.getNameType());
            this.jLabel_unit.setText(type.getUnit());
            this.jSpinner_price.setValue(prod.getPrice());
            this.jSpinner_profit.setValue(prod.getProfit());

        }
    }//GEN-LAST:event_jComboBox_NameActionPerformed

    private void jButton_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_submitActionPerformed
        // TODO add your handling code here:

        ArrayList<Product> list_prod = control_prod.get_List_Product();
        ArrayList<Supplier> list_sup = control_prod.get_List_Supplier();
        ArrayList<Type_Of_Product> list_type = control_prod.get_List_Type();

        Product prod = list_prod.get(this.jComboBox_Name.getSelectedIndex());
        Supplier sup = list_sup.get(this.jComboBox_supplier.getSelectedIndex());
        Type_Of_Product type = list_type.get(this.jComboBox_type.getSelectedIndex());

        int profit = (int) this.jSpinner_profit.getValue();
        prod.setProfit(profit);
        prod.setPrice((int) this.jSpinner_price.getValue());

        Item item = new Item(this.code_bill, prod.getCode_product(), sup.getCode_Supplier(), type.getCodeType(), (int) this.jSpinner_quantity.getValue());

        prod.setQuantity(prod.getQuantity() + (int) this.jSpinner_quantity.getValue());

        if (!prod.getCode_supplier().equals(sup.getCode_Supplier()) || !prod.getCode_type().equals(type.getCodeType())) {

            prod.setCode_product(prod.generate_CODE());
            prod.setCode_supplier(sup.getCode_Supplier());
            prod.setCode_type(type.getCodeType());
            if (control_store.insert_input(prod, item)) {
                JOptionPane.showMessageDialog(null, "Thêm thành công", "congratulations", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "có gì đó sai sai", "sorry", JOptionPane.ERROR_MESSAGE);
            }
        } else {

            if (control_store.insert_input(item, prod)) {
                JOptionPane.showMessageDialog(null, "Thêm thành công", "congratulations", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "có gì đó sai sai", "sorry", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButton_submitActionPerformed

    private void jComboBox_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_typeActionPerformed
        // TODO add your handling code here:
//        ArrayList<Type_Of_Product> list = control_prod.get_List_Type();
//        this.jLabel_unit.setText(list.get(this.jComboBox_type.getSelectedIndex()).getUnit());
    }//GEN-LAST:event_jComboBox_typeActionPerformed

    private void jLabel_typeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_typeMouseClicked
        // TODO add your handling code here:
        Add_New_TypeOfProduct_GUI child = new Add_New_TypeOfProduct_GUI();
        child.pack();
        child.setVisible(true);
        child.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                //                display_ProductNewest();
                display_AllTypeOfProd();
            }
        });
    }//GEN-LAST:event_jLabel_typeMouseClicked

    private void jLabel_supMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_supMouseClicked
        // TODO add your handling code here:
        Add_New_Supplier_GUI child = new Add_New_Supplier_GUI();
        child.pack();
        child.setVisible(true);
        child.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                //                display_ProductNewest();
                display_AllSupplier();
            }
        });
    }//GEN-LAST:event_jLabel_supMouseClicked

    private void jLabel_NameProdMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NameProdMouseReleased
        // TODO add your handling code here:
        this.jLabel_NameProd.setBackground(Color.blue);
    }//GEN-LAST:event_jLabel_NameProdMouseReleased

    private void jLabel_NameProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NameProdMouseClicked
        // TODO add your handling code here:
        Add_New_Product_GUI child = new Add_New_Product_GUI();
        child.pack();
        child.setVisible(true);
        child.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                display_AllProduct();
                display_AllSupplier();
                display_AllTypeOfProd();

                jComboBox_Name.setSelectedIndex(0);
                ArrayList<Product> list_prod = control_prod.get_List_Product();
                Product prod = list_prod.get(0);
                Supplier sup = new Supplier();
                Type_Of_Product type = new Type_Of_Product();
                sup.getDetail(prod.getCode_supplier());
                type.getDetail(prod.getCode_type());

                jComboBox_supplier.setSelectedItem(sup.getName_Supplier());
                jComboBox_type.setSelectedItem(type.getNameType());
                jLabel_unit.setText(type.getUnit());
                jSpinner_price.setValue(prod.getPrice());

            }
        }
        );

    }//GEN-LAST:event_jLabel_NameProdMouseClicked

    private void jComboBox_NameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_NameItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox_NameItemStateChanged

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
            java.util.logging.Logger.getLogger(Add_Items_Input_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Items_Input_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Items_Input_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Items_Input_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_Items_Input_GUI("aaaaaaaa").setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_submit;
    private javax.swing.JComboBox jComboBox_Name;
    private javax.swing.JComboBox jComboBox_supplier;
    private javax.swing.JComboBox jComboBox_type;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_NameProd;
    private javax.swing.JLabel jLabel_sup;
    private javax.swing.JLabel jLabel_type;
    private javax.swing.JLabel jLabel_unit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner_price;
    private javax.swing.JSpinner jSpinner_profit;
    private javax.swing.JSpinner jSpinner_quantity;
    // End of variables declaration//GEN-END:variables
}
