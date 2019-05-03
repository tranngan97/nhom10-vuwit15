/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import static Common.Covert_Amount_toWord.readNum;
import Controller.Manage_Product_CONTROLER;
import Controller.Manage_Staff_CONTROLLER;
import Controller.Manage_storage_CONTROLLER;
import Model.Input_bill;
import Model.Item;
import Model.Product;
import Model.Staff;
import Model.Supplier;
import Model.Type_Of_Product;
import static Views.Add_Input_Bill_GUI.setJTableColumnsWidth;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Nguyen Hiep
 */
public class Detail_Input_Bill_GUI extends javax.swing.JFrame {

    /**
     * Creates new form Add_InputBill
     */
    Manage_Staff_CONTROLLER control_staff = new Manage_Staff_CONTROLLER();
    Manage_Product_CONTROLER control_prod = new Manage_Product_CONTROLER();
    Manage_storage_CONTROLLER control_store = new Manage_storage_CONTROLLER();
    private Input_bill bill = new Input_bill();
    ArrayList<Staff> list = control_staff.get_List_Staff();

    public Detail_Input_Bill_GUI(Input_bill bill) throws InterruptedException {
        initComponents();
        setLocationRelativeTo(null);

        this.bill = bill;
// set infor new bill:
        this.jLabel_codeBill.setText(this.bill.getCODE_INPUT_BILL());
        Staff staff = new Staff();
        staff.getDetail(bill.getCODE_STAFF());
        this.jLabel_staff.setText(staff.getName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date = bill.getDATE();
        this.jLabel_date.setText(dateFormat.format(date));
        display_listItems();

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
        jLabel_codeBill = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jtable_product = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        jLabel3 = new javax.swing.JLabel();
        jLabel_amount = new javax.swing.JLabel();
        jLabel_staff = new javax.swing.JLabel();
        jLabel_date = new javax.swing.JLabel();
        jLabel_byword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("THÔNG TIN HÓA ĐƠN");

        jLabel2.setText("Mã hóa đơn:");

        jLabel_codeBill.setText("jLabel3");

        jLabel5.setText("Ngày nhập:");

        jLabel6.setText("Người nhập:");

        jScrollPane1.setPreferredSize(new java.awt.Dimension(960, 403));

        Jtable_product.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {"#","Tên sản phẩm", "Loại sản phẩm", "Nhà sản xuất", "Số lượng", "Đơn vị", "Giá nhập","Thành tiền"}
        ));
        Jtable_product.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jtable_productMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Jtable_product);

        jLabel3.setText("Tổng:");

        jLabel_amount.setText("0");

        jLabel_staff.setText("jLabel4");

        jLabel_date.setText("jLabel8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel_codeBill, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel_date, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_staff, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel_byword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel_codeBill))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel_staff)
                    .addComponent(jLabel_date))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel_amount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_byword, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Jtable_productMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jtable_productMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        ArrayList<Item> list = control_store.getListItems(this.bill.getCODE_INPUT_BILL());
        Item item = list.get(row);
        Product prod = new Product();
        Type_Of_Product type = new Type_Of_Product();
        Supplier sup = new Supplier();
        prod.getDetail(item.getCODE_PRODUCT());
        type.getDetail(item.getCODE_TYPE());
        sup.getDetail(item.getCODE_SUPPLIER());
        Detail_Product_Admin_GUI show = new Detail_Product_Admin_GUI(prod);
        show.pack();
        show.setVisible(true);
    }//GEN-LAST:event_Jtable_productMouseClicked

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
            java.util.logging.Logger.getLogger(Detail_Input_Bill_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Detail_Input_Bill_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Detail_Input_Bill_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Detail_Input_Bill_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Input_bill bill = new Input_bill();
                bill.getDetail("PN-5285-1401");

                try {
                    new Detail_Input_Bill_GUI(bill).setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Detail_Input_Bill_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void display_listItems() throws InterruptedException {
        ArrayList<Item> list = control_store.getListItems(this.bill.getCODE_INPUT_BILL());
        DefaultTableModel model_ = (DefaultTableModel) Jtable_product.getModel();
        int amount = 0;
        model_.setRowCount(0);
        int i = 0;
        NumberFormat format = NumberFormat.getInstance();
        for (Item temp : list) {
            i++;
            Supplier sup = new Supplier();
            Type_Of_Product type = new Type_Of_Product();
            Product prod = new Product();
            prod.getDetail(temp.getCODE_PRODUCT());
            sup.getDetail(prod.getCode_supplier());
            type.getDetail(prod.getCode_type());

            model_.addRow(new Object[]{i, prod.getName(), type.getNameType(), sup.getName_Supplier(), prod.getQuantity(), type.getUnit(), format.format(prod.getPrice()), format.format(prod.Amount_input())});
            amount += prod.Amount_input();
        }

        this.jLabel_amount.setText(String.valueOf(format.format(amount)));
        ArrayList<String> kq = readNum(String.valueOf(amount));
        String word = "";
        for (String kq1 : kq) {
            word += kq1 + " ";
        }
        this.jLabel_byword.setText("Bằng chữ: " + word + "đồng");
        this.jLabel_byword.setHorizontalAlignment(SwingConstants.RIGHT);
        //this.jLabel_byword.s
        setJTableColumnsWidth(Jtable_product, 960, 30, 400, 110, 130, 70, 60, 80, 80);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        Jtable_product.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Jtable_product;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_amount;
    private javax.swing.JLabel jLabel_byword;
    private javax.swing.JLabel jLabel_codeBill;
    private javax.swing.JLabel jLabel_date;
    private javax.swing.JLabel jLabel_staff;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
