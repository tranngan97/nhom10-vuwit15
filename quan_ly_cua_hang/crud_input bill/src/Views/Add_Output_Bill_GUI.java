/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.Manage_Product_CONTROLER;
//import Controller.Manage_Staff_CONTROLLER;
import Controller.Manage_storage_CONTROLLER;
import Model.Input_bill;
import Model.Item;
import Model.Output_bill;
import Model.Product;
//import Model.Staff;
import Model.Supplier;
import Model.Type_Of_Product;
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
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Nguyen Hiep
 */
public class Add_Output_Bill_GUI extends javax.swing.JFrame {

    /**
     * Creates new form Add_InputBill
     */
//    Manage_Staff_CONTROLLER control_staff = new Manage_Staff_CONTROLLER();
    Manage_Product_CONTROLER control_prod = new Manage_Product_CONTROLER();
    Manage_storage_CONTROLLER control_store = new Manage_storage_CONTROLLER();
    private Output_bill bill = new Output_bill();
//    ArrayList<Staff> list = control_staff.get_List_Staff();
    
    public Add_Output_Bill_GUI() {
        initComponents();
        setLocationRelativeTo(null);

// make new bill:
        this.jLabel_codeBill.setText(this.bill.getCODE_OUTPUT_BILL());
        display_Staff();

//set number only in year textfield
        ((AbstractDocument) jTextField_year.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d*");
            
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

//set event when closing: 
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu hóa đơn không?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    // yes option
                    save_data();
                } else {
                    // no option
                    control_store.delete(bill);
                    dispose();
                }
            }
        });
// resize colum:
        //{"#","Tên sản phẩm", "Loại sản phẩm", "Nhà sản xuất", "Số lượng", "Đơn vị", "Giá nhập","Thành tiền"}
        setJTableColumnsWidth(Jtable_product, 960, 30, 400, 110, 130, 70, 60, 80, 80);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        
        Jtable_product.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
    }
    
    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
            double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }
        
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
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
        jLabel_codeBill = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox_staff = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jtable_product = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        jLabel7 = new javax.swing.JLabel(){
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/add.png")));
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jComboBox_day = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jComboBox_month = new javax.swing.JComboBox();
        jTextField_year = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel_amount = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_nameCus = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("THÊM HÓA ĐƠN MỚI");

        jLabel2.setText("Mã hóa đơn:");

        jLabel_codeBill.setText("jLabel3");

        jLabel5.setText("Ngày xuất:");

        jLabel6.setText("Người xuất:");

        jComboBox_staff.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_staff.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_staffItemStateChanged(evt);
            }
        });

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

        jLabel7.setPreferredSize(new java.awt.Dimension(30, 30));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jComboBox_day.setModel(new javax.swing.DefaultComboBoxModel(new String[]
            { "1", "2", "3", "4","5","6","7","8","9",
                "10","11", "12", "13", "14","15","16","17","18","19",
                "20","21", "22", "23", "24","25","26","27","28","29",
                "30","31"}));

    jLabel11.setText("/");

    jComboBox_month.setModel(new javax.swing.DefaultComboBoxModel(new String[]
        { "Jan", "Feb", "Mar", "Apr","May","June","July","Aug","Sept","Oct","Nov","Dec" }));

jTextField_year.setText("2019");

jLabel12.setText("/");

jLabel3.setText("Tổng:");

jLabel_amount.setText("0");

jLabel4.setText("Tên khách hàng:");

javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel_codeBill, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(20, 20, 20)
                        .addComponent(jComboBox_day, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_month, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_year, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_staff, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_nameCus, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)))
        .addContainerGap())
    .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(348, 348, 348)
        .addComponent(jLabel1)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(24, 24, 24)
            .addComponent(jLabel1)
            .addGap(18, 18, 18)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(jLabel_codeBill)
                .addComponent(jLabel6)
                .addComponent(jComboBox_staff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(jComboBox_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jComboBox_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel11)
                .addComponent(jLabel12)
                .addComponent(jLabel4)
                .addComponent(jTextField_nameCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(70, 70, 70))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel_amount))
                    .addGap(46, 46, 46))))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 9, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        Add_Items_GUI child = new Add_Items_GUI(this.bill.getCODE_OUTPUT_BILL());
        child.pack();
        child.setVisible(true);
        child.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    display_listItems();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main_Page_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }//GEN-LAST:event_jLabel7MouseClicked

    private void jComboBox_staffItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_staffItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox_staffItemStateChanged

    private void Jtable_productMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jtable_productMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        ArrayList<Item> list = control_store.getListItems(this.bill.getCODE_OUTPUT_BILL());
        Item item = list.get(row);
        Product prod = new Product();
        Type_Of_Product type = new Type_Of_Product();
        Supplier sup = new Supplier();
        prod.getDetail(item.getCODE_PRODUCT());
        type.getDetail(item.getCODE_TYPE());
        sup.getDetail(item.getCODE_SUPPLIER());
        Detail_Product_GUI show = new Detail_Product_GUI(type, sup, prod);
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
            java.util.logging.Logger.getLogger(Add_Output_Bill_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Output_Bill_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Output_Bill_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Output_Bill_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_Output_Bill_GUI().setVisible(true);
            }
        });
    }
    
    public void display_Staff() {
//        this.jComboBox_staff.removeAllItems();
//        
//        for (Staff list1 : list) {
//            jComboBox_staff.addItem(list1.getName());
//        }
    }
    
    public void save_data() {
        if (this.jTextField_nameCus.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Chưa có tên khách hàng", "sorry", JOptionPane.ERROR_MESSAGE);
            this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        } else if (!check_Date_Valid()) {
            JOptionPane.showMessageDialog(null, "Ngày nhập không hợp lệ", "sorry", JOptionPane.ERROR_MESSAGE);
            this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        } else {
            ArrayList<Item> list_item = control_store.getListItems(this.bill.getCODE_OUTPUT_BILL());
            if (list_item.size() == 0) {
                JOptionPane.showMessageDialog(null, "Chưa có sản phẩm nào", "sorry", JOptionPane.ERROR_MESSAGE);
                this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            } else {
                bill.setName_Cus(this.jTextField_nameCus.getText());
//                bill.setCODE_STAFF(list.get(this.jComboBox_staff.getSelectedIndex()).getCode_staff());
                try {
                    bill.setDATE(getDate_selsect());
                } catch (ParseException ex) {
                    Logger.getLogger(Add_Output_Bill_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (bill.insert()) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công", "congratulations", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "có gì đó sai sai", "sorry", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean check_Date_Valid() {
        int day = Integer.parseInt((String) jComboBox_day.getSelectedItem());
        String month_input = (String) jComboBox_month.getSelectedItem();
        int month = 0;
        switch (month_input) {
            case "Jan":
                month = 1;
                break;
            case "Feb":
                month = 2;
                break;
            case "Mar":
                month = 3;
                break;
            case "Apr":
                month = 4;
                break;
            case "May":
                month = 5;
                break;
            case "June":
                month = 6;
                break;
            case "July":
                month = 7;
                break;
            case "Aug":
                month = 8;
                break;
            case "Sept":
                month = 9;
                break;
            case "Oct":
                month = 10;
                break;
            case "Nov":
                month = 11;
                break;
            case "Dec":
                month = 12;
                break;
            default:
                break;
        }
        int year = Integer.parseInt(jTextField_year.getText());
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day >= 31) {
                return false;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day >= 30) {
                return false;
            }
            
        } else if (month == 2) // February check
        {
            if (year % 4 == 0) // Leap year check for February
            {
                if (day >= 29) {
                    return false;
                }
            } else if (year % 4 != 0) {
                if (day >= 28) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public String getDate_selsect() {
        String day = (String) jComboBox_day.getSelectedItem();
        String month_input = (String) jComboBox_month.getSelectedItem();
        String month = "0";
        switch (month_input) {
            case "Jan":
                month = "1";
                break;
            case "Feb":
                month = "2";
                break;
            case "Mar":
                month = "3";
                break;
            case "Apr":
                month = "4";
                break;
            case "May":
                month = "5";
                break;
            case "June":
                month = "6";
                break;
            case "July":
                month = "7";
                break;
            case "Aug":
                month = "8";
                break;
            case "Sept":
                month = "9";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
            case "Dec":
                month = "12";
                break;
            default:
                break;
        }
        String year = jTextField_year.getText();
        String date_add = day + "-" + month + "-" + year;
        return date_add;
    }
    
    public void display_listItems() throws InterruptedException {
        ArrayList<Item> list = control_store.getListItems(this.bill.getCODE_OUTPUT_BILL());
        DefaultTableModel model_ = (DefaultTableModel) Jtable_product.getModel();
        int amount = 0;
        model_.setRowCount(0);
        int i = 0;
        for (Item temp : list) {
            i++;
            Supplier sup = new Supplier();
            Type_Of_Product type = new Type_Of_Product();
            Product prod = new Product();
            prod.getDetail(temp.getCODE_PRODUCT());
            sup.getDetail(prod.getCode_supplier());
            type.getDetail(prod.getCode_type());
            model_.addRow(new Object[]{i, prod.getName(), type.getNameType(), sup.getName_Supplier(), temp.getQUANTITY(), type.getUnit(), temp.getPRICE(), temp.getPRICE() * temp.getQUANTITY()});
            amount += temp.getPRICE() * temp.getQUANTITY();
        }
        NumberFormat format = NumberFormat.getInstance();
        this.jLabel_amount.setText(String.valueOf(format.format(amount)));
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Jtable_product;
    private javax.swing.JComboBox jComboBox_day;
    private javax.swing.JComboBox jComboBox_month;
    private javax.swing.JComboBox jComboBox_staff;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_amount;
    private javax.swing.JLabel jLabel_codeBill;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_nameCus;
    private javax.swing.JTextField jTextField_year;
    // End of variables declaration//GEN-END:variables
}
