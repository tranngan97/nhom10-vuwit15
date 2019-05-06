/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Common.CopyFile_resizeImg;
import Controller.Manage_Staff_CONTROLLER;
import Controller.Manage_Product_CONTROLER;
import Controller.Manage_storage_CONTROLLER;
import Model.Input_bill;
import Model.Item;
import Model.Output_bill;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import Views.GhostText;
import Model.Type_Of_Product;
import Model.Supplier;
import Model.Staff;
import Model.Product;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Nguyen Hiep
 */
public class Main_Page_GUI extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form main_page
     */
    Thread t = null;
    int hours = 0;
    String timeString = "";
    Manage_Product_CONTROLER control_prod = new Manage_Product_CONTROLER();
    Manage_Staff_CONTROLLER control_staff = new Manage_Staff_CONTROLLER();
    Manage_storage_CONTROLLER control_store = new Manage_storage_CONTROLLER();

    static String Working_Directory = System.getProperty("user.dir");
    private int select = 0;

    public Main_Page_GUI() throws InterruptedException {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Main page");
//set logo:
        //CopyFile_resizeImg up = new CopyFile_resizeImg();
        //logo_design.setIcon(up.ResizeImage(logo_design, Working_Directory + "\\src\\images\\HUST.jpg", null));
        //set digistal clock
        Calendar cal = Calendar.getInstance();
        hours = cal.get(Calendar.HOUR_OF_DAY);
        if (hours < 12) {
            greeting.setText("Good morning, Admin");
        } else if (hours < 18) {
            greeting.setText("Good affternoon, Admin");
        } else {
            greeting.setText("Good evening, Admin");
        }
        Font font = greeting.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        greeting.setFont(font.deriveFont(attributes));
        t = new Thread(this);
        t.start();
//end set digistal clock

///set goshtex for search text field
        GhostText searchGhostText = new GhostText(searchTF, "Type here....");
        GhostText searchGhostText1 = new GhostText(jTextField_searchBill, "yyyy/MM/dd");
//Hiển thị bảng nhân viên:
        display_Staff();
// Hiển thị bảng sản phẩm:
        display_Product();

//hiển thị bảng quản lý cửa hàng:
        if (this.select == 0) {
            Table_Input_Bill();
            display_InputBill();
            jTextField_searchBill.addActionListener(search_inputBill);
        } else if (this.select == 1) {
            Table_Output_Bill();
            display_OutputBill();
            jTextField_searchBill.addActionListener(search_outputBill);
        }

    }
//set evet for enter button
    Action search_inputBill = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String date = jTextField_searchBill.getText();
            ArrayList<Input_bill> list = control_store.search_InputBill(date);
            if (list.size() == 0) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp", "sorry", JOptionPane.ERROR_MESSAGE);
            } else {
                DefaultTableModel model_ = (DefaultTableModel) jTable_manage_storage.getModel();
                model_.setRowCount(0);
                int i = 0;
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                for (Input_bill list1 : list) {
                    i++;
                    Staff staff = new Staff();
                    staff.getDetail(list1.getCODE_STAFF());
                    model_.addRow(new Object[]{i, list1.getCODE_INPUT_BILL(), staff.getName(), dateFormat.format(list1.getDATE())});
                }
                jTable_manage_storage.setModel(model_);

                setJTableColumnsWidth(jTable_manage_storage, 960, 60, 300, 300, 300);
            }
        }

    };
    Action search_outputBill = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String date = jTextField_searchBill.getText();
            ArrayList<Output_bill> result = control_store.search_OutputBill(date);
            if (result.size() == 0) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp", "sorry", JOptionPane.ERROR_MESSAGE);
            } else {
                DefaultTableModel model_ = (DefaultTableModel) jTable_manage_storage.getModel();
                model_.setRowCount(0);
                int i = 0;
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                for (Output_bill list1 : result) {
                    i++;
                    Staff staff = new Staff();
                    staff.getDetail(list1.getCODE_STAFF());
                    model_.addRow(new Object[]{i, list1.getCODE_OUTPUT_BILL(), staff.getName(), list1.getName_Cus(), dateFormat.format(list1.getDATE())});
                }
                jTable_manage_storage.setModel(model_);

                setJTableColumnsWidth(jTable_manage_storage, 960, 60, 225, 225, 225, 225);
            }
        }

    };
//Hiển thị sản phẩm

    public void display_Product() {
        ArrayList<Product> list = control_prod.get_List_Product();
        DefaultTableModel model = (DefaultTableModel) Jtable_product.getModel();
        model.setRowCount(0);
        NumberFormat format = NumberFormat.getInstance();
        for (Product prod : list) {
            Supplier sup = new Supplier();
            Type_Of_Product type = new Type_Of_Product();
            sup.getDetail(prod.getCode_supplier());
            type.getDetail(prod.getCode_type());
            model.addRow(new Object[]{prod.getCode_product(), prod.getName(), sup.getName_Supplier(), type.getNameType(), format.format(prod.getQuantity()), format.format(prod.getPrice()), format.format(prod.Amount_sale())});

        }
        setJTableColumnsWidth(Jtable_product, 960, 150, 300, 150, 100, 60, 100,100);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        Jtable_product.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        Jtable_product.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        Jtable_product.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        Jtable_product.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        Jtable_product.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
    }
//Hiển thị nhân viên

    public void display_Staff() {
        ArrayList<Staff> list = control_staff.get_List_Staff();
        DefaultTableModel model = (DefaultTableModel) JTable_Staff.getModel();
        model.setRowCount(0);
        for (Staff temp : list) {
            if (temp.getRoll_call() == 0) {
                model.addRow(new Object[]{temp.getCode_staff(), temp.getName(), temp.getPhone_number(), "Chưa điểm danh"});
            } else if (temp.getRoll_call() == 1) {
                model.addRow(new Object[]{temp.getCode_staff(), temp.getName(), temp.getPhone_number(), "Đến dúng giờ"});
            } else if (temp.getRoll_call() == 2) {
                model.addRow(new Object[]{temp.getCode_staff(), temp.getName(), temp.getPhone_number(), "Đến muộn"});
            } else if (temp.getRoll_call() == 3) {
                model.addRow(new Object[]{temp.getCode_staff(), temp.getName(), temp.getPhone_number(), "Nghỉ có phép"});
            } else if (temp.getRoll_call() == 4) {
                model.addRow(new Object[]{temp.getCode_staff(), temp.getName(), temp.getPhone_number(), "Nghỉ không phép"});
            }
        }

    }
//Hiển thị quản lý kho hàng
//resize table colum

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

    public void Table_Input_Bill() {
        Object[][] data = {};
        String[] columnNames = {"#", "Mã hóa đơn", "Người nhập", "Ngày nhập"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jTable_manage_storage.setModel(model);
    }

    public void display_InputBill() {
        ArrayList<Input_bill> list = control_store.getListInputBill();
        DefaultTableModel model_ = (DefaultTableModel) jTable_manage_storage.getModel();
        model_.setRowCount(0);
        int i = 0;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Input_bill list1 : list) {
            i++;
            Staff staff = new Staff();
            staff.getDetail(list1.getCODE_STAFF());
            model_.addRow(new Object[]{i, list1.getCODE_INPUT_BILL(), staff.getName(), dateFormat.format(list1.getDATE())});
        }
        jTable_manage_storage.setModel(model_);

        setJTableColumnsWidth(jTable_manage_storage, 960, 60, 300, 300, 300);

    }
//Hiển thị quản lý bán hàng

    public void Table_Output_Bill() {
        Object[][] data = {};
        String[] columnNames = {"#", "Mã hóa đơn", "Người xuất", "Khách hàng", "Ngày xuất"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jTable_manage_storage.setModel(model);
    }

    public void display_OutputBill() {
        ArrayList<Output_bill> list = control_store.getListOutputBill();
        DefaultTableModel model_ = (DefaultTableModel) jTable_manage_storage.getModel();
        model_.setRowCount(0);
        int i = 0;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Output_bill list1 : list) {
            i++;
            Staff staff = new Staff();
            staff.getDetail(list1.getCODE_STAFF());
            model_.addRow(new Object[]{i, list1.getCODE_OUTPUT_BILL(), staff.getName(), list1.getName_Cus(), dateFormat.format(list1.getDATE())});
        }
        jTable_manage_storage.setModel(model_);
        setJTableColumnsWidth(jTable_manage_storage, 960, 60, 225, 225, 225, 225);
    }

    public void run() {
        try {
            while (true) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                timeString = formatter.format(now);

                printTime();
                t.sleep(1000);  // interval given in milliseconds  
            }
        } catch (Exception e) {
        }
    }

    public void printTime() {
        digital_clock.setText(timeString);
        Font font = digital_clock.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        digital_clock.setFont(font.deriveFont(attributes));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel(){

        };
        main_tab = new javax.swing.JTabbedPane();
        quan_ly_cua_hang = new javax.swing.JPanel();
        JcomboBox_selection = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_manage_storage = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
            DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();

            { // initializer block
                renderRight.setHorizontalAlignment(SwingConstants.CENTER);
            }

            @Override
            public TableCellRenderer getCellRenderer (int arg0, int arg1) {
                return renderRight;
            }

        };
        jLabel_addBill = new javax.swing.JLabel(){
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/add.png")));
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jTextField_searchBill = new javax.swing.JTextField();
        quan_ly_nhan_vien = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTable_Staff = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
            DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();

            { // initializer block
                renderRight.setHorizontalAlignment(SwingConstants.CENTER);
            }

            @Override
            public TableCellRenderer getCellRenderer (int arg0, int arg1) {
                return renderRight;
            }
        };
        jLabel3 = new javax.swing.JLabel(){
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/add.png")));
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        quan_ly_san_pham = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jtable_product = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            };

        };
        searchTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel(){
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/delete.png")));
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
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
        jLabel8 = new javax.swing.JLabel(){
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/add.png")));
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jPanel2 = new javax.swing.JPanel(){

        };
        logo_design = new javax.swing.JLabel(){
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Logo.jpg")));
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        greeting = new javax.swing.JLabel();
        digital_clock = new javax.swing.JLabel();

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã HH", "Tên HH", "Nhà SX", "Số lượng", "Giá thành", "Ngày nhập"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(965, 650));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(960, 400));

        main_tab.setPreferredSize(new java.awt.Dimension(960, 558));
        main_tab.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                main_tabMouseDragged(evt);
            }
        });

        quan_ly_cua_hang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        quan_ly_cua_hang.setPreferredSize(new java.awt.Dimension(960, 532));
        quan_ly_cua_hang.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                quan_ly_cua_hangMouseMoved(evt);
            }
        });

        JcomboBox_selection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Quản lý kho hàng", "Quản lý bán hàng"}));
        JcomboBox_selection.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcomboBox_selectionItemStateChanged(evt);
            }
        });
        JcomboBox_selection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcomboBox_selectionActionPerformed(evt);
            }
        });

        jScrollPane4.setPreferredSize(new java.awt.Dimension(960, 400));

        jTable_manage_storage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_manage_storage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_manage_storageMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_manage_storage);

        jLabel_addBill.setPreferredSize(new java.awt.Dimension(30, 30));
        jLabel_addBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_addBillMouseClicked(evt);
            }
        });

        jTextField_searchBill.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_searchBillKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_searchBillKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout quan_ly_cua_hangLayout = new javax.swing.GroupLayout(quan_ly_cua_hang);
        quan_ly_cua_hang.setLayout(quan_ly_cua_hangLayout);
        quan_ly_cua_hangLayout.setHorizontalGroup(
            quan_ly_cua_hangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quan_ly_cua_hangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_addBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField_searchBill, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 598, Short.MAX_VALUE)
                .addComponent(JcomboBox_selection, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(quan_ly_cua_hangLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        quan_ly_cua_hangLayout.setVerticalGroup(
            quan_ly_cua_hangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quan_ly_cua_hangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(quan_ly_cua_hangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JcomboBox_selection, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_addBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_searchBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
        );

        main_tab.addTab("Quản lý cửa hàng", quan_ly_cua_hang);

        quan_ly_nhan_vien.setPreferredSize(new java.awt.Dimension(960, 513));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(960, 400));

        JTable_Staff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Mã NV", "Họ và tên","Số điện thoại","#"
            }
        ));
        JTable_Staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_StaffMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTable_Staff);

        jLabel3.setPreferredSize(new java.awt.Dimension(30, 30));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout quan_ly_nhan_vienLayout = new javax.swing.GroupLayout(quan_ly_nhan_vien);
        quan_ly_nhan_vien.setLayout(quan_ly_nhan_vienLayout);
        quan_ly_nhan_vienLayout.setHorizontalGroup(
            quan_ly_nhan_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quan_ly_nhan_vienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(936, Short.MAX_VALUE))
            .addGroup(quan_ly_nhan_vienLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        quan_ly_nhan_vienLayout.setVerticalGroup(
            quan_ly_nhan_vienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, quan_ly_nhan_vienLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );

        main_tab.addTab("Quản lý nhân viên", quan_ly_nhan_vien);

        quan_ly_san_pham.setPreferredSize(new java.awt.Dimension(960, 532));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(960, 400));

        Jtable_product.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Mã thiết bị","Tên thiết bị", "Nhà sản xuất", "Loại sản phẩm","Số lượng","Giá nhập","Giá bán"
            }
        ));
        Jtable_product.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jtable_productMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Jtable_product);

        searchTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTFActionPerformed(evt);
            }
        });
        searchTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTFKeyTyped(evt);
            }
        });

        jLabel4.setPreferredSize(new java.awt.Dimension(30, 30));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
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

        javax.swing.GroupLayout quan_ly_san_phamLayout = new javax.swing.GroupLayout(quan_ly_san_pham);
        quan_ly_san_pham.setLayout(quan_ly_san_phamLayout);
        quan_ly_san_phamLayout.setHorizontalGroup(
            quan_ly_san_phamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quan_ly_san_phamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(quan_ly_san_phamLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 966, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(quan_ly_san_phamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(quan_ly_san_phamLayout.createSequentialGroup()
                    .addGap(471, 471, 471)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(471, Short.MAX_VALUE)))
        );
        quan_ly_san_phamLayout.setVerticalGroup(
            quan_ly_san_phamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, quan_ly_san_phamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(quan_ly_san_phamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
            .addGroup(quan_ly_san_phamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(quan_ly_san_phamLayout.createSequentialGroup()
                    .addGap(223, 223, 223)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(224, Short.MAX_VALUE)))
        );

        main_tab.addTab("Quản lý sản phẩm", quan_ly_san_pham);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(960, 200));

        jButton1.setText("logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("I Think, therefore I am");

        greeting.setText("Hello admin");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(logo_design, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(505, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(digital_clock, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(greeting))
                            .addComponent(jButton1))
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(greeting, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(digital_clock, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(32, 32, 32)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 62, Short.MAX_VALUE))
            .addComponent(logo_design, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
                    .addComponent(main_tab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(main_tab, javax.swing.GroupLayout.PREFERRED_SIZE, 414, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 966, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void main_tabMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_main_tabMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_main_tabMouseDragged

    private void quan_ly_cua_hangMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quan_ly_cua_hangMouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_quan_ly_cua_hangMouseMoved

    private void JcomboBox_selectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcomboBox_selectionActionPerformed
        // TODO add your handling code here:
        //hiển thị bảng quản lý cửa hàng:
        this.select = this.JcomboBox_selection.getSelectedIndex();
        if (this.select == 0) {
            Table_Input_Bill();
            display_InputBill();
            jTextField_searchBill.removeActionListener(search_outputBill);
            jTextField_searchBill.addActionListener(search_inputBill);
        } else if (this.select == 1) {
            Table_Output_Bill();
            display_OutputBill();
            jTextField_searchBill.removeActionListener(search_inputBill);
            jTextField_searchBill.addActionListener(search_outputBill);
        }
    }//GEN-LAST:event_JcomboBox_selectionActionPerformed

    private void Jtable_productMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jtable_productMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        // int column = source.columnAtPoint(evt.getPoint());
        // "Mã thiết bị", "Tên thiết bị", "Mã NSX", "Mã loại","Giá thành"
        String Ma_Thiet_Bi = source.getModel().getValueAt(row, 0) + "";
        String Ma_NSX = source.getModel().getValueAt(row, 2) + "";
        String Ma_Loai = source.getModel().getValueAt(row, 3) + "";

        Product prod = new Product();
        prod.getDetail(Ma_Thiet_Bi);

        Detail_Product_Admin_GUI show = new Detail_Product_Admin_GUI(prod);
        show.pack();
        show.setVisible(true);
        show.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                display_Product();
            }
        });
        show.setVisible(true);
    }//GEN-LAST:event_Jtable_productMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Login_GUI().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTFKeyTyped
        // TODO add your handling code here:
        if (this.searchTF.getText().equals("")) {
            display_Product();
        } else {
            ArrayList<Product> list_result = control_prod.Search(this.searchTF.getText());
            if (list_result.size() == 0) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp", "sorry", JOptionPane.ERROR_MESSAGE);
            } else {
                DefaultTableModel model = (DefaultTableModel) Jtable_product.getModel();
                model.setRowCount(0);

                for (Product temp : list_result) {
                    Supplier sup = new Supplier();
                    Type_Of_Product type = new Type_Of_Product();
                    sup.getDetail(temp.getCode_supplier());
                    type.getDetail(temp.getCode_type());
                    model.addRow(new Object[]{temp.getCode_product(), temp.getName(), sup.getName_Supplier(), type.getNameType(), temp.getQuantity(), temp.getPrice(),});
                }
            }
        }

    }//GEN-LAST:event_searchTFKeyTyped

    private void JTable_StaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_StaffMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        // int column = source.columnAtPoint(evt.getPoint());
        // "Mã thiết bị", "Tên thiết bị", "Mã NSX", "Mã loại","Giá thành"
        String code = source.getModel().getValueAt(row, 0) + "";

        Staff temp = new Staff();
        temp.getDetail(code);
        Detail_Staff_GUI show = new Detail_Staff_GUI(temp);
        show.pack();
        show.setVisible(true);
        show.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                display_Staff();
            }
        });

    }//GEN-LAST:event_JTable_StaffMouseClicked

    private void JcomboBox_selectionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcomboBox_selectionItemStateChanged
        // TODO add your handling code here:
        int select = this.JcomboBox_selection.getSelectedIndex();
    }//GEN-LAST:event_JcomboBox_selectionItemStateChanged

    private void jTable_manage_storageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_manage_storageMouseClicked
        // TODO add your handling code here:
        if (this.select == 0) {
            JTable source = (JTable) evt.getSource();
            int row = source.rowAtPoint(evt.getPoint());
            String code_bill = source.getModel().getValueAt(row, 1) + "";

            Input_bill bill = new Input_bill();
            bill.getDetail(code_bill);
            Detail_Input_Bill_GUI show;
            try {
                show = new Detail_Input_Bill_GUI(bill);
                show.pack();
                show.setVisible(true);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main_Page_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JTable source = (JTable) evt.getSource();
            int row = source.rowAtPoint(evt.getPoint());
            String code_bill = source.getModel().getValueAt(row, 1) + "";

            Output_bill bill = new Output_bill();
            bill.getDetail(code_bill);
            Detail_Output_Bill_GUI show;
            try {
                show = new Detail_Output_Bill_GUI(bill);
                show.pack();
                show.setVisible(true);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main_Page_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jTable_manage_storageMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        Add_New_Staff_GUI child = new Add_New_Staff_GUI();
        child.pack();
        child.setVisible(true);
        child.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                display_Staff();
            }
        });
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Delete_pst_GUI child = new Delete_pst_GUI();
        child.pack();
        child.setVisible(true);
        child.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                display_Product();
            }
        });
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel_addBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_addBillMouseClicked
        // TODO add your handling code here:
        if (this.select == 0) {
            Add_Input_Bill_GUI child = new Add_Input_Bill_GUI();
            child.pack();
            child.setVisible(true);
            child.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    Table_Input_Bill();
                    display_InputBill();
                    display_Product();
                }
            });
        } else {
            Add_Output_Bill_GUI child = new Add_Output_Bill_GUI();
            child.pack();
            child.setVisible(true);
            child.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    Table_Output_Bill();
                    display_OutputBill();
                    display_Product();
                }
            });
        }
        display_Product();
    }//GEN-LAST:event_jLabel_addBillMouseClicked

    private void searchTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTFActionPerformed

    private void jTextField_searchBillKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchBillKeyTyped
        // TODO add your handling code here:
        if (this.select == 0) {
            if (jTextField_searchBill.getText().equals("")) {
                Table_Input_Bill();
                display_InputBill();
            }
        } else {
            if (jTextField_searchBill.getText().equals("")) {
                Table_Output_Bill();
                display_OutputBill();
            }
        }
    }//GEN-LAST:event_jTextField_searchBillKeyTyped

    private void jTextField_searchBillKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchBillKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField_searchBillKeyPressed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        Add_New_Product_GUI child = new Add_New_Product_GUI();
        child.pack();
        child.setVisible(true);
        child.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                display_Product();
            }
        });
    }//GEN-LAST:event_jLabel8MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main_Page_GUI().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main_Page_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_Staff;
    private javax.swing.JComboBox JcomboBox_selection;
    private javax.swing.JTable Jtable_product;
    private javax.swing.JLabel digital_clock;
    private javax.swing.JLabel greeting;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_addBill;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable_manage_storage;
    private javax.swing.JTextField jTextField_searchBill;
    private javax.swing.JLabel logo_design;
    private javax.swing.JTabbedPane main_tab;
    private javax.swing.JPanel quan_ly_cua_hang;
    private javax.swing.JPanel quan_ly_nhan_vien;
    private javax.swing.JPanel quan_ly_san_pham;
    private javax.swing.JTextField searchTF;
    // End of variables declaration//GEN-END:variables

}
