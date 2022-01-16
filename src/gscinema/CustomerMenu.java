/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gscinema;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.sql.*;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author e-hen
 */
public class CustomerMenu extends javax.swing.JFrame {
    private Customer cus;
    private Database db;
    private String theatreid_select;
    private String date_select;
    private String time_select;
    private int price_select;
    private int foodid;
    private int foodprice;
    private int foodquantity;
    private int movie_sum;
    private int selectedshowid;
    private int numofticket;
    private double unitprice;
    private double wholeTotal;
    private double afterdiscountprice;
    private ArrayList<Seat> seatlist = new ArrayList<>();

//    @Override
//    public void setExtendedState(int state) {
//        super.setExtendedState(JFrame.MAXIMIZED_BOTH); //To change body of generated methods, choose Tools | Templates.
//    }
    /**
     * Creates new form CustomerMenu
     * @param cus
     * @param db
     */
    public CustomerMenu(Customer cus, Database db) {
        initComponents();
        // Customer and Database's data from the previous frame (Login page) 
        this.cus = cus;
        this.db = db;
        
        //Maximize the frame (Full screen)
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //GSC Logo
        ImageIcon icon = new ImageIcon("C:\\Users\\e-hen\\Documents\\NetBeansProjects\\GSCinema\\src\\gscinema\\GSC-Cinema-Vector-Logo.png");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(GSCTitle.getWidth(), GSCTitle.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        GSCTitle.setIcon(scaledIcon);
        //Welcome Text
        welcometext.setText("Welcome " + cus.getName());
        
        //List out all the shows (METHOD)
        DefaultTableModel tblModel = (DefaultTableModel)ShowTable.getModel();
        displayShows(tblModel);
        
        //List out all the booked tickets (Record)
        DefaultTableModel tblModel1 = (DefaultTableModel)tickettable.getModel();
        displayRecord(tblModel1);
        
        //MovieList - combo box
        try{
            // to temporary store the movies
            ArrayList<String> movieItems = new ArrayList<>();
            Statement stm = db.getConnection().createStatement();
            // to retrieve all the movies from database
            String sql = "SELECT DISTINCT title FROM movie ORDER BY title";
            //run query and store in rs
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                movieItems.add(rs.getString("title"));
            }
            // add all the available movie into the combo box from the array list (using for loop)
            for (String i : movieItems) {
                MovieList.addItem(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //TimeList
        try{
            // to temporary store the time
            ArrayList<String> timeList = new ArrayList<>();
            Statement stm = db.getConnection().createStatement();
            // to retrieve all the time from database
            String sql = "SELECT DISTINCT showtime FROM shows ORDER BY showtime DESC";
            //run query and store in rs
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                timeList.add(rs.getString("showtime"));
            }
            // add all the time slots into the combo box from the array list (using for loop)
            for (String i : timeList) {
                TimeList.addItem(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Default Date
        try {
            Date temp = new Date();
            // date format ddmmyyyy
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String tempdate = sdf.format(temp);
            Date current = new SimpleDateFormat("dd-MM-yyyy").parse(tempdate);
            
            // set the format of the date from the date chooser to ddmmyyyy
            DateChooser.setDate(current);
        } catch (ParseException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //F&B combo box list
        try{
            //same as above
            ArrayList<String> foodlist = new ArrayList<>();
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT * FROM food ORDER BY fbid ASC";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                foodlist.add(rs.getString("fbname"));
            }
            for (String i : foodlist) {
                fbsetlist.addItem(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //display the profile information at the Profile tab (Method, refer below)
        displayProfile(cus);
        // disable the print receipt button at the profile tab (default) 
        printReceipt2.setEnabled(false);
        
        studentemail.setEnabled(false);
        studentid.setEnabled(false);
        
//        ImageIcon image = new ImageIcon(getClass().getResource("C:\\Users\\e-hen\\Documents\\NetBeansProjects\\GSCinema\\resources\\Payment_Successful_.png"));
//        Image scaledImage = image.getScaledInstance(qrcode.getWidth(),qrcode.getHeight(),Image.SCALE_SMOOTH);
//        qrcode = new JLabel((Icon) scaledImage);

        //scaledImage();
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
        
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainTabbedPanel = new javax.swing.JTabbedPane();
        MoviePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowTable = new javax.swing.JTable();
        MovieList = new javax.swing.JComboBox<>();
        TimeList = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        SearchButton = new javax.swing.JButton();
        DateChooser = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        DisplayAllShowsButton = new javax.swing.JButton();
        DisplayPanel = new javax.swing.JPanel();
        seatpanel = new javax.swing.JPanel();
        FBPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        fbsetlist = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Description = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        ProceedConfirmationButton = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        fbunitprice = new javax.swing.JTextField();
        BackToSeatsButton = new javax.swing.JButton();
        ConfirmationPanel = new javax.swing.JPanel();
        titledisplay = new javax.swing.JLabel();
        theatreiddisplay = new javax.swing.JLabel();
        showdatedisplay = new javax.swing.JLabel();
        showtimedisplay = new javax.swing.JLabel();
        unitpricedisplay = new javax.swing.JLabel();
        movietypedisplay = new javax.swing.JLabel();
        seatsdisplay = new javax.swing.JLabel();
        totalpricedisplay = new javax.swing.JLabel();
        fbsetdisplay = new javax.swing.JLabel();
        fbquantitydisplay = new javax.swing.JLabel();
        ProceedPaymentButton = new javax.swing.JButton();
        studentcheckbox = new javax.swing.JCheckBox();
        studentemail = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        studentid = new javax.swing.JTextField();
        backtofnbbutton = new javax.swing.JButton();
        CardPaymentPanel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cardnum = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        cardname = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        csv = new javax.swing.JTextField();
        expdate = new javax.swing.JTextField();
        PayButton = new javax.swing.JButton();
        CancelPaymentButton = new javax.swing.JButton();
        ReceiptPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        ReceiptText = new javax.swing.JTextArea();
        printReceipt = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        qrcodelabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bookbutton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        imagelabel = new javax.swing.JLabel();
        l1 = new javax.swing.JLabel();
        displaytitle = new javax.swing.JLabel();
        displaydate = new javax.swing.JLabel();
        l2 = new javax.swing.JLabel();
        l3 = new javax.swing.JLabel();
        displaytime = new javax.swing.JLabel();
        displaytype = new javax.swing.JLabel();
        l4 = new javax.swing.JLabel();
        l5 = new javax.swing.JLabel();
        displayprice = new javax.swing.JLabel();
        displaytheatre = new javax.swing.JLabel();
        l6 = new javax.swing.JLabel();
        l7 = new javax.swing.JLabel();
        displayreleasedate = new javax.swing.JLabel();
        displayduration = new javax.swing.JLabel();
        l8 = new javax.swing.JLabel();
        l9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        displaycasts = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        displaysynopsis = new javax.swing.JTextArea();
        l10 = new javax.swing.JLabel();
        BookedTicketsPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tickettable = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        ReceiptText2 = new javax.swing.JTextArea();
        printReceipt2 = new javax.swing.JButton();
        qrcodelabel = new javax.swing.JLabel();
        eticketlabel = new javax.swing.JLabel();
        ProfilePanel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        pfic = new javax.swing.JTextField();
        pfname = new javax.swing.JTextField();
        pfemail = new javax.swing.JTextField();
        pfphone = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        currentpassword = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        newpassword = new javax.swing.JTextField();
        confirmpassword = new javax.swing.JTextField();
        updateProfile = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        welcometext = new javax.swing.JLabel();
        GSCTitle = new javax.swing.JLabel();
        LogoutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Golden Screen Cinema");
        setMaximumSize(new java.awt.Dimension(2000, 1000));
        setMinimumSize(new java.awt.Dimension(1470, 863));
        setPreferredSize(new java.awt.Dimension(1470, 863));

        MainTabbedPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        MainTabbedPanel.setAutoscrolls(true);

        MoviePanel.setPreferredSize(new java.awt.Dimension(1110, 500));

        ShowTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Theatre", "Title", "Date", "Time", "Price (RM)", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ShowTable.setColumnSelectionAllowed(false);
        ShowTable.setMaximumSize(new java.awt.Dimension(630, 307));
        ShowTable.setMinimumSize(new java.awt.Dimension(630, 307));
        ShowTable.setPreferredSize(new java.awt.Dimension(630, 307));
        ShowTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ShowTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ShowTable.getTableHeader().setReorderingAllowed(false);
        ShowTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ShowTable);
        ShowTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (ShowTable.getColumnModel().getColumnCount() > 0) {
            ShowTable.getColumnModel().getColumn(0).setMinWidth(50);
            ShowTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            ShowTable.getColumnModel().getColumn(1).setMinWidth(200);
            ShowTable.getColumnModel().getColumn(1).setPreferredWidth(300);
            ShowTable.getColumnModel().getColumn(2).setMinWidth(70);
            ShowTable.getColumnModel().getColumn(2).setPreferredWidth(70);
            ShowTable.getColumnModel().getColumn(3).setMinWidth(70);
            ShowTable.getColumnModel().getColumn(3).setPreferredWidth(70);
            ShowTable.getColumnModel().getColumn(4).setMinWidth(70);
            ShowTable.getColumnModel().getColumn(4).setPreferredWidth(70);
            ShowTable.getColumnModel().getColumn(5).setMinWidth(70);
            ShowTable.getColumnModel().getColumn(5).setPreferredWidth(70);
        }

        MovieList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a movie" }));

        TimeList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a time" }));

        jLabel2.setText("Movies:");

        jLabel3.setText("Date:");

        jLabel4.setText("Time:");

        SearchButton.setText("Search");
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        DateChooser.setBackground(new java.awt.Color(255, 255, 255));
        DateChooser.setDateFormatString("dd-MM-yyyy");
        DateChooser.setMinSelectableDate(new java.util.Date(-62135794722000L));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DISPLAY");

        DisplayAllShowsButton.setText("Display All Shows");
        DisplayAllShowsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayAllShowsButtonActionPerformed(evt);
            }
        });

        DisplayPanel.setLayout(new java.awt.CardLayout());

        seatpanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout seatpanelLayout = new javax.swing.GroupLayout(seatpanel);
        seatpanel.setLayout(seatpanelLayout);
        seatpanelLayout.setHorizontalGroup(
            seatpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        seatpanelLayout.setVerticalGroup(
            seatpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );

        DisplayPanel.add(seatpanel, "card2");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Food & Beverage");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Set:");

        fbsetlist.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        fbsetlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fbsetlistActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Description:");

        Description.setEditable(false);
        Description.setColumns(20);
        Description.setLineWrap(true);
        Description.setRows(3);
        Description.setTabSize(6);
        Description.setWrapStyleWord(true);
        jScrollPane5.setViewportView(Description);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Quantity:");

        ProceedConfirmationButton.setText("Proceed to Confirmation");
        ProceedConfirmationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProceedConfirmationButtonActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel26.setText("Unit Price:");

        fbunitprice.setEditable(false);

        BackToSeatsButton.setText("Back To Seats");
        BackToSeatsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackToSeatsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FBPanelLayout = new javax.swing.GroupLayout(FBPanel);
        FBPanel.setLayout(FBPanelLayout);
        FBPanelLayout.setHorizontalGroup(
            FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FBPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(FBPanelLayout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(FBPanelLayout.createSequentialGroup()
                        .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5)
                            .addComponent(fbsetlist, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quantity)
                            .addComponent(fbunitprice, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ProceedConfirmationButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BackToSeatsButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FBPanelLayout.setVerticalGroup(
            FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FBPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fbsetlist, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FBPanelLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(7, 7, 7))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fbunitprice, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(quantity))
                .addGap(18, 18, 18)
                .addComponent(ProceedConfirmationButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BackToSeatsButton)
                .addGap(31, 31, 31))
        );

        DisplayPanel.add(FBPanel, "card4");

        titledisplay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        titledisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titledisplay.setText("Title");

        theatreiddisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        theatreiddisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        theatreiddisplay.setText("Theatre");

        showdatedisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        showdatedisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showdatedisplay.setText("Date");

        showtimedisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        showtimedisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showtimedisplay.setText("Time");

        unitpricedisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        unitpricedisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        unitpricedisplay.setText("Unit Price");

        movietypedisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        movietypedisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        movietypedisplay.setText("Type");

        seatsdisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        seatsdisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seatsdisplay.setText("Seats");

        totalpricedisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalpricedisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalpricedisplay.setText("Total Price");

        fbsetdisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fbsetdisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fbsetdisplay.setText("FB Set");

        fbquantitydisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fbquantitydisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fbquantitydisplay.setText("FB Quantity");

        ProceedPaymentButton.setText("Proceed To Payment");
        ProceedPaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProceedPaymentButtonActionPerformed(evt);
            }
        });

        studentcheckbox.setText("Are you a student?");
        studentcheckbox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studentcheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentcheckboxActionPerformed(evt);
            }
        });

        jLabel34.setText("Student E-mail:");

        jLabel35.setText("Student ID:");

        backtofnbbutton.setText("Back to Food and Beverages");
        backtofnbbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backtofnbbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConfirmationPanelLayout = new javax.swing.GroupLayout(ConfirmationPanel);
        ConfirmationPanel.setLayout(ConfirmationPanelLayout);
        ConfirmationPanelLayout.setHorizontalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmationPanelLayout.createSequentialGroup()
                .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(fbquantitydisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                    .addComponent(fbsetdisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(seatsdisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(movietypedisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(unitpricedisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(showtimedisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(showdatedisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(theatreiddisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titledisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConfirmationPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(totalpricedisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ConfirmationPanelLayout.createSequentialGroup()
                        .addComponent(studentcheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProceedPaymentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(studentemail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(backtofnbbutton, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35)
                        .addGap(7, 7, 7)
                        .addComponent(studentid, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        ConfirmationPanelLayout.setVerticalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmationPanelLayout.createSequentialGroup()
                .addComponent(titledisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(theatreiddisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showdatedisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showtimedisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unitpricedisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(movietypedisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seatsdisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fbsetdisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fbquantitydisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalpricedisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(studentemail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34)
                        .addComponent(jLabel35)
                        .addComponent(studentcheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(studentid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(ProceedPaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backtofnbbutton)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        DisplayPanel.add(ConfirmationPanel, "card3");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("INVOICE");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("VISA | MASTER CARD ");

        jLabel22.setText("Card Number:");

        jLabel23.setText("Name on Card:");

        jLabel24.setText("Expiry Date:");

        jLabel25.setText("CSV Code:");

        PayButton.setText("Pay");
        PayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PayButtonActionPerformed(evt);
            }
        });

        CancelPaymentButton.setText("Cancel Payment");
        CancelPaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelPaymentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CardPaymentPanelLayout = new javax.swing.GroupLayout(CardPaymentPanel);
        CardPaymentPanel.setLayout(CardPaymentPanelLayout);
        CardPaymentPanelLayout.setHorizontalGroup(
            CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardPaymentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CardPaymentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cardname)
                    .addComponent(cardnum)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PayButton, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addGroup(CardPaymentPanelLayout.createSequentialGroup()
                        .addGroup(CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(expdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(csv, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(CancelPaymentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(265, 265, 265))
        );
        CardPaymentPanelLayout.setVerticalGroup(
            CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardPaymentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(csv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(PayButton)
                .addGap(18, 18, 18)
                .addComponent(CancelPaymentButton)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        DisplayPanel.add(CardPaymentPanel, "card5");

        ReceiptText.setEditable(false);
        ReceiptText.setColumns(20);
        ReceiptText.setRows(5);
        jScrollPane6.setViewportView(ReceiptText);

        printReceipt.setText("Print Receipt");
        printReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReceiptActionPerformed(evt);
            }
        });

        jButton1.setText("Quit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        qrcodelabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("QR Code E-Ticket");

        javax.swing.GroupLayout ReceiptPanelLayout = new javax.swing.GroupLayout(ReceiptPanel);
        ReceiptPanel.setLayout(ReceiptPanelLayout);
        ReceiptPanelLayout.setHorizontalGroup(
            ReceiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReceiptPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ReceiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(printReceipt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ReceiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(qrcodelabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        ReceiptPanelLayout.setVerticalGroup(
            ReceiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReceiptPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ReceiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ReceiptPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qrcodelabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ReceiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(printReceipt))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        DisplayPanel.add(ReceiptPanel, "card7");

        bookbutton.setText("Book");
        bookbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bookbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookbuttonActionPerformed(evt);
            }
        });

        imagelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagelabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        l1.setText("Title:");

        displaytitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        displaytitle.setText("Title");

        displaydate.setText("Date");

        l2.setText("Date:");

        l3.setText("Time:");

        displaytime.setText("Time");

        displaytype.setText("Type");

        l4.setText("Type:");

        l5.setText("Price:");

        displayprice.setText("Price");

        displaytheatre.setText("Theatre");

        l6.setText("Theatre:");

        l7.setText("Release Date:");

        displayreleasedate.setText("Release Date");

        displayduration.setText("Duration");

        l8.setText("Duration:");

        l9.setText("Casts:");

        displaycasts.setEditable(false);
        displaycasts.setColumns(20);
        displaycasts.setLineWrap(true);
        displaycasts.setRows(2);
        displaycasts.setTabSize(3);
        displaycasts.setWrapStyleWord(true);
        displaycasts.setBorder(null);
        jScrollPane4.setViewportView(displaycasts);

        displaysynopsis.setEditable(false);
        displaysynopsis.setColumns(20);
        displaysynopsis.setLineWrap(true);
        displaysynopsis.setRows(3);
        displaysynopsis.setWrapStyleWord(true);
        displaysynopsis.setAutoscrolls(false);
        displaysynopsis.setBorder(null);
        jScrollPane3.setViewportView(displaysynopsis);

        l10.setText("Synopsis:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(displaydate, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(displaytheatre, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(displaytime, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(l7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(displayreleasedate, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(displayprice, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(displaytitle, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(displaytype, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(305, 305, 305))
                            .addComponent(displayduration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(displaytitle)
                            .addComponent(l1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(displaydate)
                            .addComponent(l3)
                            .addComponent(displaytime)
                            .addComponent(l2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l4)
                            .addComponent(displaytype)
                            .addComponent(l5)
                            .addComponent(displayprice))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l6)
                            .addComponent(displaytheatre)
                            .addComponent(l7)
                            .addComponent(displayreleasedate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l8)
                            .addComponent(displayduration))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l10)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(imagelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MoviePanelLayout = new javax.swing.GroupLayout(MoviePanel);
        MoviePanel.setLayout(MoviePanelLayout);
        MoviePanelLayout.setHorizontalGroup(
            MoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MoviePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MoviePanelLayout.createSequentialGroup()
                        .addGroup(MoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(MoviePanelLayout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(MovieList, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TimeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(MoviePanelLayout.createSequentialGroup()
                                .addGap(194, 194, 194)
                                .addComponent(DisplayAllShowsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(MoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        MoviePanelLayout.setVerticalGroup(
            MoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MoviePanelLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(MoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(MoviePanelLayout.createSequentialGroup()
                        .addGroup(MoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(MoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(MovieList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(TimeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(SearchButton))
                            .addComponent(DateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DisplayAllShowsButton))
                    .addGroup(MoviePanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bookbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        MainTabbedPanel.addTab("Display Shows", MoviePanel);

        tickettable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Movie Title", "Theatre", "Seat Number", "Date", "Time", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tickettable.getTableHeader().setReorderingAllowed(false);
        tickettable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tickettableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tickettable);

        ReceiptText2.setEditable(false);
        ReceiptText2.setColumns(20);
        ReceiptText2.setRows(5);
        jScrollPane7.setViewportView(ReceiptText2);

        printReceipt2.setText("Print Receipt");
        printReceipt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReceipt2ActionPerformed(evt);
            }
        });

        qrcodelabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        qrcodelabel.setFocusCycleRoot(true);

        eticketlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eticketlabel.setText("QR code E-Ticket");

        javax.swing.GroupLayout BookedTicketsPanelLayout = new javax.swing.GroupLayout(BookedTicketsPanel);
        BookedTicketsPanel.setLayout(BookedTicketsPanelLayout);
        BookedTicketsPanelLayout.setHorizontalGroup(
            BookedTicketsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookedTicketsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BookedTicketsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BookedTicketsPanelLayout.createSequentialGroup()
                        .addGroup(BookedTicketsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(eticketlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(qrcodelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                    .addComponent(printReceipt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        BookedTicketsPanelLayout.setVerticalGroup(
            BookedTicketsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookedTicketsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BookedTicketsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BookedTicketsPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printReceipt2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eticketlabel)
                .addGap(9, 9, 9)
                .addComponent(qrcodelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        MainTabbedPanel.addTab("Booked Tickets", BookedTicketsPanel);

        jLabel27.setText("IC Number:");

        jLabel28.setText("User Name:");

        jLabel29.setText("E-mail Address:");

        jLabel30.setText("Phone Number:");

        pfic.setEditable(false);

        pfname.setEditable(false);

        jLabel31.setText("Current Password:");

        currentpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentpasswordActionPerformed(evt);
            }
        });

        jLabel32.setText("New Password:");

        jLabel33.setText("Confirm Password:");

        updateProfile.setText("Update");
        updateProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ProfilePanelLayout = new javax.swing.GroupLayout(ProfilePanel);
        ProfilePanel.setLayout(ProfilePanelLayout);
        ProfilePanelLayout.setHorizontalGroup(
            ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfilePanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ProfilePanelLayout.createSequentialGroup()
                        .addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pfic)
                            .addComponent(pfname, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(pfemail, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(pfphone, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(currentpassword)
                            .addComponent(newpassword)
                            .addComponent(confirmpassword))))
                .addGap(1067, 1067, 1067))
        );
        ProfilePanelLayout.setVerticalGroup(
            ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfilePanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(pfic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(pfname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(pfemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(pfphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(currentpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(newpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(confirmpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(updateProfile)
                .addContainerGap(422, Short.MAX_VALUE))
        );

        MainTabbedPanel.addTab("Profile", ProfilePanel);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));

        welcometext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcometext.setText("Welcome");

        GSCTitle.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N

        LogoutButton.setText("Logout");
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(GSCTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcometext, javax.swing.GroupLayout.PREFERRED_SIZE, 1106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(welcometext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(GSCTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(MainTabbedPanel)
                        .addContainerGap())
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainTabbedPanel)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bookbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookbuttonActionPerformed
        
        //count the number of tickets selected from the seat's toggle buttons
        numofticket = 0;
        for (Seat i : seatlist) {
            if(i.button.isSelected() == true){
                numofticket++;
            }
        }
        
        // if no buttons selected then display "Please..."
        if(numofticket == 0){
            JOptionPane.showMessageDialog(this, "Please Select Your Seat(s) to Proceed");
            //stop the process
            return;
        }
        //disable book button
        bookbutton.setEnabled(false);
        //disable showtable
        //ShowTable.setEnabled(false);
        
        //change the displayPanel to Food and Beverage Panel (after customer choose seats)
        //Method (Refer below)
        displayPanelChange(FBPanel);
        
        
        int counter = 0;
        String[] tempNum = new String[numofticket];
        for(Seat i : seatlist){
            if(i.button.isSelected() == true && counter < numofticket){
                // to get the seat number (e.g. A1, B5, B6)
                tempNum[counter] = i.getNum();
                // to get the Show ID of the show that the customer chooses
                selectedshowid = i.getShowid();
                counter++;
            }
        }
        
        // for displaying the seats that are chosen by cus (e.g. in the format of 'Seats: A1 C3 C4')
        String displaychosen = "";
        for(int i = 0; i < numofticket; i++){
            //concatenate the string(seat number)
            displaychosen += tempNum[i] + "  ";
        }
        
        // to get the information of the chosen show from Database based on 'showid'
        try{
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT *\n" +
                         "FROM ((shows\n" +
                         "INNER JOIN movie ON shows.movieid = movie.movieid )\n" +
                         "INNER JOIN theatre ON shows.theatreid = theatre.theatreid)\n" +
                         "WHERE shows.showid = '" + selectedshowid + "'";
            // run the query(retrieve data) and store in rs
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                // display all the information at the confirmation panel
                titledisplay.setText(rs.getString("movie.title"));
                theatreiddisplay.setText("Theatre ID: " + String.valueOf(rs.getInt("shows.theatreid")));
                showdatedisplay.setText("Date: " + rs.getString("shows.showdate"));
                showtimedisplay.setText("Time: " + rs.getString("shows.showtime"));
                unitpricedisplay.setText("Unit Price: RM" + rs.getString(String.valueOf("movie.price")));
                unitprice = (double) rs.getDouble("movie.price");
                movietypedisplay.setText("Type: " + rs.getString("movie.movietype"));   
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        //display chosen seat
        // this is the string from above that we concatenated
        seatsdisplay.setText("Seats: " + displaychosen);
        //get the sum of the original price of all the ticket/seats
        movie_sum = numofticket * price_select;
    }//GEN-LAST:event_bookbuttonActionPerformed

    private void DisplayAllShowsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayAllShowsButtonActionPerformed
        //back to default view (METHOD)
        backToDefaultView();
        
        //clear and display table (METHOD)
        DefaultTableModel tblModel = (DefaultTableModel)ShowTable.getModel();
        displayShows(tblModel);
    }//GEN-LAST:event_DisplayAllShowsButtonActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        //SEARCHING FOR SPECIFIC MOVIE/DATE/TIME
        
        //clear table
        DefaultTableModel tblModel = (DefaultTableModel)ShowTable.getModel();
        clearTable(tblModel);

        block:{
            try{
                // get the selected movie (for searching)
                String movietitle = (String) MovieList.getSelectedItem();
                
                //get the selectd date (for searching)
                String date = "";
                if (DateChooser.getDate() == null ){}
                else{
                    // format the date to ddmmyyyy
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Date tempdate = DateChooser.getDate();
                    date = sdf.format(tempdate);
                }
                // get the selected time (for searching)
                String time = (String) TimeList.getSelectedItem();
                
                // to filter and get the shows from database
                Statement stm = db.getConnection().createStatement();
                String sql = "SELECT shows.theatreid, movie.title, shows.showdate, shows.showtime, movie.price, movie.movietype\n" +
                             "FROM ((shows\n" +
                             "INNER JOIN movie ON shows.movieid = movie.movieid )\n" +
                             "INNER JOIN theatre ON shows.theatreid = theatre.theatreid)\n";
                
                /* for example
                sql = "SELECT shows.theatreid, movie.title, shows.showdate, shows.showtime, movie.price, movie.movietype\n" +
                      "FROM ((shows\n" +
                      "INNER JOIN movie ON shows.movieid = movie.movieid )\n" +
                      "INNER JOIN theatre ON shows.theatreid = theatre.theatreid)\n" +
  (the condition) --> "WHERE title = '" + movietitle + "' and showdate = '" + date + "'";
                */
                //conditions (to concatenate the string at the bottom of 'String sql')
                if(movietitle.equals("Select a movie") && date.isEmpty() && time.equals("Select a time")){
                    displayShows(tblModel);
                    break block;
                }
                else if(!movietitle.equals("Select a movie") && date.isEmpty() && time.equals("Select a time")){
                    sql = sql + "WHERE title = '" + movietitle + "'";
                }
                else if(movietitle.equals("Select a movie") && !date.isEmpty() && time.equals("Select a time")){
                    sql = sql + "WHERE showdate = '" + date + "'";
                }
                else if(movietitle.equals("Select a movie") && date.isEmpty() && !time.equals("Select a time")){
                    sql = sql + "WHERE showtime = '" + time + "'";
                }
                else if(!movietitle.equals("Select a movie") && !date.isEmpty() && time.equals("Select a time")){
                    sql = sql + "WHERE title = '" + movietitle + "' and showdate = '" + date + "'";
                }
                else if(movietitle.equals("Select a movie") && !date.isEmpty() && !time.equals("Select a time")){
                    sql = sql + "WHERE showdate = '" + date + "' and showtime = '" + time + "'";
                }
                else if(!movietitle.equals("Select a movie") && date.isEmpty() && !time.equals("Select a time")){
                    sql = sql + "WHERE title = '" + movietitle + "' and showtime = '" + time + "'";
                }
                else{
                    sql = sql + "WHERE showtime = '" + time + "' and showdate = '" + date + "' and title = '" + movietitle + "'";
                }
                // run the query (retrieval of data from database)
                ResultSet rs = stm.executeQuery(sql);
                int counter = 0;
                while(rs.next()){
                    // counter is to check whether there is a show based on the conditions
                    counter++;
                    String theatre = String.valueOf(rs.getInt("theatreid"));
                    String title = rs.getString("title");
                    String sdate = rs.getString("showdate");
                    String stime = rs.getString("showtime");
                    String price = String.valueOf(rs.getInt("price"));
                    String type = rs.getString("movietype");
                    
                    String tbData[] = {theatre, title, sdate, stime, price, type};
                    tblModel.addRow(tbData);
                }
                // after searching for specific movie/time/date
                // if counter = 0 means that there are no shows based on the conditions that the cus wanted
                if(counter == 0){
                    // display pop up message 'No show found'
                    JOptionPane.showMessageDialog(this, "No show found");
                    //remove all components in seat panel.
                    seatpanel.removeAll();
                    // refresh the seat panel.
                    seatpanel.updateUI();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void ShowTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowTableMouseClicked
        // WHEN CUSTOMER CLICKED/CHOOSES ONE OF THE MOVIE 
        
        // enable the book button only after the cus chooses a movie
        bookbutton.setEnabled(true);
        
        // Make the seat panel visible (the screen beside the Shows Table)
        displayPanelChange(seatpanel);
        //clean/clear the panel everytime when the customer selected a show to prevent overlaping of the seats (button) at saet panel
        seatpanel.removeAll();
        seatpanel.updateUI();
        
        //to get the information of the selected row
        DefaultTableModel model = (DefaultTableModel)ShowTable.getModel();
        int selectedRowIndex = ShowTable.getSelectedRow();
        
        // to get the 3 data from each column of the selected row
        theatreid_select  = (String) model.getValueAt(selectedRowIndex, 0);
        date_select = (String) model.getValueAt(selectedRowIndex, 2);
        time_select = (String) model.getValueAt(selectedRowIndex, 3);
        
        String showid = "";
        int size = 0;
        int row = 0;
        char ch = 0;
        
        //to retrieve the show's information (showid) from database of the selected show
        try{
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT * FROM shows WHERE theatreid = '" + theatreid_select + "' " + 
                         "AND shows.showdate = '" + date_select + "' AND shows.showtime = '" + time_select + "'";
            //run query
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                showid = String.valueOf(rs.getInt("showid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // get the size of the theatre of the show
        try{
            Statement stm1 = db.getConnection().createStatement();
            String sql1 = "SELECT theatre.size \n" +
            "from (shows\n" +
            "INNER JOIN theatre ON shows.theatreid = theatre.theatreid)\n" +
            "WHERE shows.showid = '" + showid +"';";
            ResultSet rs1 = stm1.executeQuery(sql1);
            while(rs1.next()){
                size = rs1.getInt("size");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // for display the toggle button (seats)
        // e.g. if the size is 70, then there will be 7 row (10 seats each row) (ABCDEFG)
        // first row will be A1 to A10
        // last row will be G1 to G10
        switch(size){
            case 50 -> {
                row = 5;
                ch = 'E';
            }
            case 70 -> {
                row = 7;
                ch = 'G';
            }
            case 90 -> {
                row = 9;
                ch = 'I';
            }
        }
        
        // set the layout of the seat panel the grid layour based on the size (row and 10 columns + 4 disabled columns)
        seatpanel.setLayout(new GridLayout(row,14,5,5));
        for(char a = 'A'; a <= ch; a++){
            int counter = 1;
            for(int x=1; x <= 14; x++){
                if(x == 3 || x == 6 || x == 9 || x == 12){
                    String y = "X";
                    JToggleButton button = new JToggleButton(y);
                    button.setEnabled(false);
                    seatpanel.add(button);
                }else{
                    int selectedseatid = 0;
                    String seatnum = a + Integer.toString(counter);
                    String status = "";
                    // to get the status of the seat (available or unavailable) and the seat id
                    // from database (retrieve data)
                    try{
                        Statement stm2 = db.getConnection().createStatement();
                        String sql2 = "SELECT * FROM seat WHERE seatnum = '" + seatnum + "' AND showid = '" + showid + "'";
                        ResultSet rs2 = stm2.executeQuery(sql2);
                        while(rs2.next()){
                            status = rs2.getString("status");
                            selectedseatid = rs2.getInt("seatid");
                        }
                        
                        // based on the seat id, we get the price of the seat/ticket
                        Statement stm4 = db.getConnection().createStatement();
                        String sql4 = "SELECT movie.price\n" +
                        "FROM (shows\n" +
                        "INNER JOIN movie ON shows.movieid = movie.movieid)\n" +
                        "WHERE showid = '" + showid + "';";
                        ResultSet rs4 = stm4.executeQuery(sql4);
                        while(rs4.next()){
                            price_select = rs4.getInt("movie.price");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //check status/availability (Available or unavailable)
                    if(status.equals("Unavailable")){
                        JToggleButton bt = new JToggleButton(seatnum);
                        bt.setFont(new Font("Arial", Font.PLAIN, 6));
                        bt.setPreferredSize(new Dimension(10, 10));
                        //disable the seat(toggle button) if it is unavailable
                        bt.setEnabled(false);
                        seatpanel.add(bt);
                        seatlist.add(new Seat(selectedseatid, seatnum, true, Integer.parseInt(showid), bt));
                        counter++;
                    }else{
                        JToggleButton bt = new JToggleButton(seatnum);
                        bt.setFont(new Font("Arial", Font.PLAIN, 7));
                        bt.setPreferredSize(new Dimension(20, 20));
                        // enable the seat (toggle button) if it is available
                        bt.setEnabled(true);
                        seatpanel.add(bt);
                        seatlist.add(new Seat(selectedseatid, seatnum, false, Integer.parseInt(showid), bt));
                        counter++;
                    }
                }
            }
        }
        
        //Disable seats for SOP
        for(int i = 0; i < seatlist.size(); i++){
           if(seatlist.get(i).isIsBooked() == true){
               // if one of the two seats are booked then another seat should be disabled
               int tempnum = Integer.parseInt(seatlist.get(i).getNum().substring(1));
               if(tempnum%2 == 0){
                    seatlist.get(i-1).button.setEnabled(false);
               }
               else{
                    seatlist.get(i+1).button.setEnabled(false);
               }
           } 
        }
        setVisible(true);
        
        //DISPLAY ALL INFORMATION ABOUT THE MOVIE/SHOW below the table
        try{
            
            byte[] img;
            Statement stm3 = db.getConnection().createStatement();
            String sql3 = "SELECT *\n" +
                          "FROM ((shows\n" +
                          "INNER JOIN movie ON shows.movieid = movie.movieid )\n" +
                          "INNER JOIN theatre ON shows.theatreid = theatre.theatreid)\n" +
                          "WHERE shows.showid = '" + showid + "';";
            ResultSet rs3 = stm3.executeQuery(sql3);
            while(rs3.next()){
                // put all the information from database to the fields
                displaytitle.setText(rs3.getString("movie.title"));
                displaydate.setText(rs3.getString("shows.showdate"));
                displaytime.setText(rs3.getString("shows.showtime"));
                displaytype.setText(rs3.getString("movie.movietype"));
                displayprice.setText(String.valueOf(rs3.getInt("movie.price")));
                displaytheatre.setText(String.valueOf(rs3.getInt("shows.theatreid")));
                displayreleasedate.setText(rs3.getString("movie.releasedate"));
                displayduration.setText(rs3.getString("movie.duration"));
                displaycasts.setText(rs3.getString("movie.casts"));
                displaysynopsis.setText(rs3.getString("movie.synopsis"));
                //display poster
                img = rs3.getBytes("image");
                ImageIcon image = new ImageIcon(img);
                Image image2 = image.getImage();
                Image image3 = image2.getScaledInstance(imagelabel.getWidth(), imagelabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon poster = new ImageIcon(image3);
                imagelabel.setIcon(poster);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //DESELECT ALL BUTTONS IF CUSTOMER CHOSE ANOTHER MOVIE
        for (Seat i : seatlist) {
            i.button.setSelected(false);
        }
    }//GEN-LAST:event_ShowTableMouseClicked

    private void ProceedConfirmationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProceedConfirmationButtonActionPerformed
        
        // to get the selected food from the food menu combo box
        String selectedfood = (String) fbsetlist.getSelectedItem();
        
        // if cus insert quantity value <= 0
        if(!quantity.getText().equals("")){
            if(Integer.parseInt(quantity.getText()) <= 0 && !quantity.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Invalid quantity value");
                return;
            }
        }
        
        // if customer did not choose menu and quantity
        if (selectedfood.equals("-") && quantity.getText().equals("")){
            fbsetdisplay.setText("No Beverages Selected");
            fbquantitydisplay.setText("No Quantity");
            // the total price of the food + ticket  = ticket (because no food chosen)
            wholeTotal = movie_sum;
            String displaytotal = String.format("RM%.2f",wholeTotal);
            // to display at confirmation page
            totalpricedisplay.setText(displaytotal);
            
            //change to confirmation panel
            displayPanelChange(ConfirmationPanel);
            // initialise the after discount price to current price (to check whether its a student later)
            afterdiscountprice = wholeTotal;
            // if no food chosen then set food id to 0
            foodid = 0;
        }//when only food menu chosen (without quantity)
        else if(!selectedfood.equals("-") && (quantity.getText().equals("") || quantity.getText().equals("0"))){
            JOptionPane.showMessageDialog(this, "Please Insert Quantity"); 
        }// when only quantity inserted (without menu)
        else if(selectedfood.equals("-") && !quantity.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please Select a menu"); 
        }// both quantity and menu selected and inserted
        else if(!selectedfood.equals("-") && !quantity.getText().equals("")){
            // get the food id from database (query)
            try {
                Statement stm = db.getConnection().createStatement();
                String sql = "SELECT * FROM food WHERE fbname = '" + selectedfood + "'";
                ResultSet rs = stm.executeQuery(sql);
                
                while(rs.next()){
                    foodid = rs.getInt("fbid");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            //display the food information at confirmation panel
            foodquantity = Integer.parseInt(quantity.getText());
            foodprice = Integer.parseInt(fbunitprice.getText().substring(2, 4)) * foodquantity;
            fbsetdisplay.setText("Food & Beverage: " + (String) fbsetlist.getSelectedItem());
            fbquantitydisplay.setText("F&B Quantity: " + foodquantity);
            // now the total will be price of food + ticket
            wholeTotal = (foodprice + movie_sum);
            String displaytotal = String.format("RM%.2f", wholeTotal);
            totalpricedisplay.setText(displaytotal);
            
            //change to confirmation panel
            displayPanelChange(ConfirmationPanel);
            
            // initialize the after discount price (if student) as current total
            afterdiscountprice = wholeTotal;
        }
    }//GEN-LAST:event_ProceedConfirmationButtonActionPerformed

    private void fbsetlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fbsetlistActionPerformed
        // when customer choose a menu from the food menu combo box
        // display the description about the food and the unit price at the panel
        try{
            String fbname = (String) fbsetlist.getSelectedItem();
            
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT * FROM  food where fbname = '" + fbname + "'";
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                //display description
                Description.setText(rs.getString("description"));
                //display unit price
                fbunitprice.setText(String.format("RM%4.2f", (double)rs.getInt("price")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_fbsetlistActionPerformed

    private void ProceedPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProceedPaymentButtonActionPerformed
        // before proceed to payment panel
        // check whether the customer is a student
        
        // if the student check box is selected (ticked) but didnt fill in the blanks, then display 'please...'
        if(studentcheckbox.isSelected() && (studentemail.getText().equals("") || studentid.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "Please fill in the blanks");
        }//if information complete then proceed to 
        else if(studentcheckbox.isSelected() && !studentemail.getText().equals("") && !studentid.getText().equals("")){
            if(valEmail(studentemail.getText()) == false){
                JOptionPane.showMessageDialog(this, "Invalid Email");
            }
        }
        else{
                displayPanelChange(CardPaymentPanel);
        }
    }//GEN-LAST:event_ProceedPaymentButtonActionPerformed

    private void PayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PayButtonActionPerformed
        String bookid = "";
        if(cardnum.getText().equals("") || cardname.getText().equals("")  || expdate.getText().equals("")  || csv.getText().equals("") ){
            JOptionPane.showMessageDialog(this, "Please fill in the blanks");
        }
        else{
            
            if(valCardNum(cardnum.getText()) == false){
                JOptionPane.showMessageDialog(this, "Invalid Card Number");
                return;
            }
            
            if(valCsv(csv.getText()) == false){
                JOptionPane.showMessageDialog(this, "Invalid CSV code");
                return;
            }
            
            if(valExp(expdate.getText()) == false){
                JOptionPane.showMessageDialog(this, "Invalid Expiration Date");
                return;
            }
            
            try {
                String sql = "";
                if(foodid == 0){
                    sql = "INSERT INTO booking (ic, totalprice) VALUES ('" + cus.getIc() + "', '" + afterdiscountprice + "');";
                }else{
                    sql = "INSERT INTO booking (ic, fbid, fbquantity, totalprice) " +
                                 "VALUES ('" + cus.getIc() + "', '" + foodid +"', '" + foodquantity + "', '" + afterdiscountprice + "');";
                }
                //RETURN_GENERATED_ID
                PreparedStatement ps = db.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                while(rs.next()){
                    bookid = String.valueOf(rs.getInt(1));
                }
                for (Seat i : seatlist) {
                    if(i.button.isSelected() == true){
                        Statement stm2 = db.getConnection().createStatement();
                        String sql2 = "INSERT INTO bookdetail (bookid, seatid, unitprice) " + 
                                      "VALUES ('" + bookid + "','" + i.getSeatid() + "', '" + unitprice + "');";
                        stm2.executeUpdate(sql2);
                        
                        Statement stm3 = db.getConnection().createStatement();
                        String sql3 = "UPDATE seat\n" +
                                      "SET status = 'Unavailable'\n" +
                                      "WHERE seatid = '" + i.getSeatid() + "';";
                        stm3.executeUpdate(sql3);
                    }
                }
                JOptionPane.showMessageDialog(this, "Payment Succesful");
            } catch (SQLException ex) {
                Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            // refresh record table
            DefaultTableModel tblModel1 = (DefaultTableModel)tickettable.getModel();
            displayRecord(tblModel1);

            seatpanel.removeAll();
            seatpanel.updateUI();

            displayPanelChange(ReceiptPanel);
            
            viewReceipt(ReceiptText, bookid, qrcodelabel2);
        }
    }//GEN-LAST:event_PayButtonActionPerformed

    private void studentcheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentcheckboxActionPerformed
        // TODO add your handling code here:
        if(studentcheckbox.isSelected()){
            afterdiscountprice = wholeTotal * (0.7);
            String displayTotal = String.format("RM%.2f", afterdiscountprice);
            totalpricedisplay.setText(displayTotal);
            studentemail.setEnabled(true);
            studentid.setEnabled(true);
        }
        else{
            afterdiscountprice = wholeTotal;
            String displayTotal = String.format("RM%.2f", afterdiscountprice);
            totalpricedisplay.setText(displayTotal);
            studentemail.setEnabled(false);
            studentid.setEnabled(false);
        }
    }//GEN-LAST:event_studentcheckboxActionPerformed

    private void BackToSeatsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackToSeatsButtonActionPerformed
        displayPanelChange(seatpanel);
        bookbutton.setEnabled(true);
        //ShowTable.setEnabled(true);
    }//GEN-LAST:event_BackToSeatsButtonActionPerformed

    private void backtofnbbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backtofnbbuttonActionPerformed
        displayPanelChange(FBPanel);
    }//GEN-LAST:event_backtofnbbuttonActionPerformed

    private void CancelPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelPaymentButtonActionPerformed
        int yn = JOptionPane.showConfirmDialog(null, "Do you want to cancel your payment?", "Cancel Payment", JOptionPane.YES_NO_OPTION);
        if (yn == JOptionPane.YES_OPTION) {
            backToDefaultView();
            //ShowTable.setEnabled(true);
        } else {}
    }//GEN-LAST:event_CancelPaymentButtonActionPerformed

    private void tickettableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tickettableMouseClicked
        qrcodelabel.setIcon(null);
        DefaultTableModel model = (DefaultTableModel)tickettable.getModel();
        int selectedRowIndex = tickettable.getSelectedRow();
        String bookid = (String)model.getValueAt(selectedRowIndex, 0);
        viewReceipt(ReceiptText2, bookid, qrcodelabel);
        //eticketlabel.setVisible(true);
        printReceipt2.setEnabled(true);
    }//GEN-LAST:event_tickettableMouseClicked

    private void printReceipt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReceipt2ActionPerformed
        try {
            ReceiptText2.print();
        } catch (PrinterException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printReceipt2ActionPerformed

    private void printReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReceiptActionPerformed
        try {
            ReceiptText.print();
        } catch (PrinterException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printReceiptActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        backToDefaultView();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void updateProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProfileActionPerformed
        
        try{
            //update email
            if(!pfemail.getText().isEmpty() && !cus.getEmail().equals(pfemail.getText())){
                if(valEmail(pfemail.getText()) == false){
                    JOptionPane.showMessageDialog(this, "Invalid Email");
                    return;
                }
                cus.setEmail(pfemail.getText());
                Statement stm = db.getConnection().createStatement();
                String sql = "UPDATE customer\n" +
                             "SET email= '" + cus.getEmail() + "'\n" +
                             "WHERE ic = '" + cus.getIc() + "';";
                stm.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Email update successful");
                System.out.println(cus.getEmail());
            }
            //update phone
            if(!pfphone.getText().isEmpty() && !cus.getPhone().equals(pfphone.getText())){
                if(valPhone(pfphone.getText()) == false){
                    JOptionPane.showMessageDialog(this, "Invalid Phone Number");
                    return;
                }
                cus.setPhone(pfphone.getText());
                Statement stm2 = db.getConnection().createStatement();
                String sql2 = "UPDATE customer\n" +
                              "SET mobile_num = '" + cus.getPhone()+ "'\n" +
                              "WHERE ic = '" + cus.getIc() + "';";
                stm2.executeUpdate(sql2);
                JOptionPane.showMessageDialog(this, "Phone number update successful");
                System.out.println(cus.getPhone());
            }
            //update password
            if(!currentpassword.getText().isEmpty() && !newpassword.getText().isEmpty() && !confirmpassword.getText().isEmpty()){
                if(!currentpassword.getText().equals(cus.getPassword())){
                    JOptionPane.showMessageDialog(this, "Wrong current password");
                }
                else if(!newpassword.getText().equals(confirmpassword.getText())){
                    JOptionPane.showMessageDialog(this, "New password does not match");
                }
                else if(currentpassword.getText().equals(newpassword.getText())){
                    JOptionPane.showMessageDialog(this, "New password must not be the same as current password");
                }
                else{
                    cus.setPassword(newpassword.getText());
                    Statement stm3 = db.getConnection().createStatement();
                    String sql3 = "UPDATE customer\n" +
                                  "SET cus_password = '" + cus.getPassword()+ "'\n" +
                                  "WHERE ic = '" + cus.getIc() + "';";
                    stm3.executeUpdate(sql3);
                    JOptionPane.showMessageDialog(this, "Update successful");
                    System.out.println(cus.getPassword());
                }
            }
            else if(!currentpassword.getText().isEmpty() && (newpassword.getText().isEmpty() || confirmpassword.getText().isEmpty())){
                JOptionPane.showMessageDialog(this, "Please fill in your new password and confirm your password");
            }
            else if(!newpassword.getText().isEmpty() && (currentpassword.getText().isEmpty() || confirmpassword.getText().isEmpty())){
                JOptionPane.showMessageDialog(this, "Please fill in your current password and confirm your password");
            }
            else if(!confirmpassword.getText().isEmpty() && (currentpassword.getText().isEmpty() || newpassword.getText().isEmpty())){
                JOptionPane.showMessageDialog(this, "Please fill in your current password and your new password");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        //refresh profile
        displayProfile(cus);
        currentpassword.setText("");
        newpassword.setText("");
        confirmpassword.setText("");
    }//GEN-LAST:event_updateProfileActionPerformed

    private void currentpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentpasswordActionPerformed
    }//GEN-LAST:event_currentpasswordActionPerformed

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        //Make the login page visible
        Login log = new Login();
        log.setVisible(true);
        //dispose(close) the customer menu page
        dispose();
    }//GEN-LAST:event_LogoutButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }
    
    private void displayProfile(Customer cus){
        pfic.setText(cus.getIc());
        pfname.setText(cus.getName());
        pfemail.setText(cus.getEmail());
        pfphone.setText(cus.getPhone());
    }
    
    private void displayShows(DefaultTableModel tblModel){
        //clear table
        clearTable(tblModel);
        try{
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT *\n" +
                         "FROM ((shows\n" +
                         "INNER JOIN movie ON shows.movieid = movie.movieid )\n" +
                         "INNER JOIN theatre ON shows.theatreid = theatre.theatreid)\n" +
                         "ORDER BY shows.showdate ASC, shows.showtime ASC;";
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                String theatre = String.valueOf(rs.getInt("shows.theatreid"));
                String title = rs.getString("movie.title");
                String sdate = rs.getString("shows.showdate");
                String stime = rs.getString("shows.showtime");
                String price = String.valueOf(rs.getInt("movie.price"));
                String type = rs.getString("movie.movietype");
                
                String tbData[] = {theatre, title, sdate, stime, price, type};
                tblModel.addRow(tbData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void clearTable(DefaultTableModel tblModel){
        int rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }
    
    private void displayRecord(DefaultTableModel tblModel){
        clearTable(tblModel);
        try{
            Statement stm1 = db.getConnection().createStatement();
            String sql1 = "SELECT *\n" +
                          "FROM ((((((bookdetail\n" +
                          "INNER JOIN booking ON bookdetail.bookid = booking.bookid )\n" +
                          "INNER JOIN seat ON bookdetail.seatid = seat.seatid)\n" +
                          "LEFT JOIN food ON booking.fbid = food.fbid)\n" +
                          "INNER JOIN customer ON booking.ic = customer.ic)\n" +
                          "INNER JOIN shows ON seat.showid = shows.showid)\n" +
                          "INNER JOIN movie ON shows.movieid = movie.movieid)\n" +
                          "WHERE customer.ic = '" + cus.getIc() +"'\n" +
                          "ORDER BY bookdetail.bookid;";
            
            ResultSet rs1 = stm1.executeQuery(sql1);
            while(rs1.next()){
                String bookid = String.valueOf(rs1.getInt("bookid"));
                String title = rs1.getString("title");
                String theatre = String.valueOf(rs1.getInt("theatreid"));
                String seatnum = rs1.getString("seatnum");
                String sdate = rs1.getString("showdate");
                String stime = rs1.getString("showtime");
                String price = String.valueOf(rs1.getInt("unitprice"));
                
                String tbData[] = {bookid, title, theatre, seatnum, sdate, stime, price};
                tblModel.addRow(tbData);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void displayPanelChange(JPanel panel){
        DisplayPanel.removeAll();
        DisplayPanel.add(panel);
        DisplayPanel.updateUI();
        DisplayPanel.revalidate();
    }
    
    private void backToDefaultView(){
        //DESELECT ALL BUTTONS IF CUSTOMER CHOSE ANOTHER MOVIE
        for (Seat i : seatlist) {
            i.button.setSelected(false);
        }
        seatpanel.removeAll();
        seatpanel.updateUI();
        
        fbsetlist.setSelectedIndex(0);
        quantity.setText("");
        studentcheckbox.setSelected(false);
        studentemail.setText("");
        studentid.setText("");
        cardnum.setText("");
        cardname.setText("");
        expdate.setText("");
        csv.setText("");
        
        displaytitle.setText("");
        displaydate.setText("");
        displaytime.setText("");
        displaytype.setText("");
        displayprice.setText("");
        displaytheatre.setText("");
        displayreleasedate.setText("");
        displayduration.setText("");
        displaycasts.setText("");
        displaysynopsis.setText("");
        imagelabel.setIcon(null);
        
        // refresh the panel.
        displayPanelChange(seatpanel);
    }
    
    private void viewReceipt(JTextArea textArea, String passedbookid, JLabel QR){
       
        String bookid = "";
        String movietitle = "";
        String theatre = "";
        String showdate = "";
        String showtime = "";
        StringBuilder seat = new StringBuilder();
        String fnbname = "";
        String fnbquantity = "";
        double totalprice = 0;
       
        try{
            Statement stm = db.getConnection().createStatement();
            String sql ="SELECT *\n" +
                        "FROM ((((((bookdetail\n" +
                        "INNER JOIN booking ON bookdetail.bookid = booking.bookid )\n" +
                        "INNER JOIN seat ON bookdetail.seatid = seat.seatid)\n" +
                        "LEFT JOIN food ON booking.fbid = food.fbid)\n" +
                        "INNER JOIN customer ON booking.ic = customer.ic)\n" +
                        "INNER JOIN shows ON seat.showid = shows.showid)\n" +
                        "INNER JOIN movie ON shows.movieid = movie.movieid)\n" +
                        "WHERE customer.ic = '" + cus.getIc() +"' AND booking.bookid = '" + passedbookid + "'\n" +
                        "ORDER BY bookdetail.bookid;";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                bookid = String.valueOf(rs.getInt("bookdetail.bookid"));
                movietitle = rs.getString("movie.title");
                theatre = String.valueOf(rs.getInt("shows.theatreid"));
                showdate = rs.getString("shows.showdate");
                showtime = rs.getString("shows.showtime");
                seat.append(rs.getString("seat.seatnum")).append(" ");
                fnbname = rs.getString("food.fbname");
                if(fnbname == null){
                    fnbname = "-";
                }
                fnbquantity = String.valueOf(rs.getInt("booking.fbquantity"));
                if(fnbquantity.equals("0")){
                    fnbquantity = "-";
                }
                totalprice = rs.getDouble("booking.totalprice");
            }
        } catch (SQLException ex) {
             Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        String text = "GSCINEMA\n" +
                      "=========================================\n" +
                      "Customer Name: " + cus.getName() +"\n" +
                      "=========================================\n" +
                      "Book ID: " + bookid + "\n" +
                      "=========================================\n" +
                      "Movie Title: " + movietitle + "\n" +
                      "Theatre: " + theatre + "\n" +
                      "Show Date: " + showdate + "\n" +
                      "Show Time: " + showtime + "\n" +
                      "Seat(s): " + seat + "\n" +
                      "=========================================\n" +
                      "Food and Beverages: " + fnbname + "\n" +
                      "F&B Quantity: " + fnbquantity + "\n" +
                      "=========================================\n" +
                      "TOTAL PRICE: " + String.format("RM%.2f", totalprice) + "\n";
        textArea.setText(text);
        
        //QR Code
        GenerateQRCode qr = new GenerateQRCode(movietitle, cus.getName(), bookid, theatre, showdate, showtime, seat);
        ImageIcon icon = new ImageIcon("C:\\Users\\e-hen\\Pictures\\Java\\qrcode.png");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(QR.getWidth(), QR.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        QR.setIcon(scaledIcon);
    }
    
    private static boolean valEmail(String email){
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(email);
        return matcher.find();
    }
    
    private static boolean valPhone(String phone){
        String phoneRegex = "^\\d{10,11}$";
        Pattern phonePat = Pattern.compile(phoneRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = phonePat.matcher(phone);
        return matcher.find();
    }
    
    private static boolean valCardNum(String cardnum){
        String cardRegex = "^\\d{16}$";
        Pattern cardPat = Pattern.compile(cardRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = cardPat.matcher(cardnum);
        return matcher.find();
    }
    
    private static boolean valCsv(String csv){
        String csvRegex = "^\\d{3}$";
        Pattern csvPat = Pattern.compile(csvRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = csvPat.matcher(csv);
        return matcher.find();
    }
    
    private static boolean valExp(String exp){
        String expRegex = "^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$";
        Pattern expPat = Pattern.compile(expRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = expPat.matcher(exp);
        return matcher.find();
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackToSeatsButton;
    private javax.swing.JPanel BookedTicketsPanel;
    private javax.swing.JButton CancelPaymentButton;
    private javax.swing.JPanel CardPaymentPanel;
    private javax.swing.JPanel ConfirmationPanel;
    private com.toedter.calendar.JDateChooser DateChooser;
    private javax.swing.JTextArea Description;
    private javax.swing.JButton DisplayAllShowsButton;
    private javax.swing.JPanel DisplayPanel;
    private javax.swing.JPanel FBPanel;
    private javax.swing.JLabel GSCTitle;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JTabbedPane MainTabbedPanel;
    private javax.swing.JComboBox<String> MovieList;
    private javax.swing.JPanel MoviePanel;
    private javax.swing.JButton PayButton;
    private javax.swing.JButton ProceedConfirmationButton;
    private javax.swing.JButton ProceedPaymentButton;
    private javax.swing.JPanel ProfilePanel;
    private javax.swing.JPanel ReceiptPanel;
    private javax.swing.JTextArea ReceiptText;
    private javax.swing.JTextArea ReceiptText2;
    private javax.swing.JButton SearchButton;
    private javax.swing.JTable ShowTable;
    private javax.swing.JComboBox<String> TimeList;
    private javax.swing.JButton backtofnbbutton;
    private javax.swing.JButton bookbutton;
    private javax.swing.JTextField cardname;
    private javax.swing.JTextField cardnum;
    private javax.swing.JTextField confirmpassword;
    private javax.swing.JTextField csv;
    private javax.swing.JTextField currentpassword;
    private javax.swing.JTextArea displaycasts;
    private javax.swing.JLabel displaydate;
    private javax.swing.JLabel displayduration;
    private javax.swing.JLabel displayprice;
    private javax.swing.JLabel displayreleasedate;
    private javax.swing.JTextArea displaysynopsis;
    private javax.swing.JLabel displaytheatre;
    private javax.swing.JLabel displaytime;
    private javax.swing.JLabel displaytitle;
    private javax.swing.JLabel displaytype;
    private javax.swing.JLabel eticketlabel;
    private javax.swing.JTextField expdate;
    private javax.swing.JLabel fbquantitydisplay;
    private javax.swing.JLabel fbsetdisplay;
    private javax.swing.JComboBox<String> fbsetlist;
    private javax.swing.JTextField fbunitprice;
    private javax.swing.JLabel imagelabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l10;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l4;
    private javax.swing.JLabel l5;
    private javax.swing.JLabel l6;
    private javax.swing.JLabel l7;
    private javax.swing.JLabel l8;
    private javax.swing.JLabel l9;
    private javax.swing.JLabel movietypedisplay;
    private javax.swing.JTextField newpassword;
    private javax.swing.JTextField pfemail;
    private javax.swing.JTextField pfic;
    private javax.swing.JTextField pfname;
    private javax.swing.JTextField pfphone;
    private javax.swing.JButton printReceipt;
    private javax.swing.JButton printReceipt2;
    private javax.swing.JLabel qrcodelabel;
    private javax.swing.JLabel qrcodelabel2;
    private javax.swing.JTextField quantity;
    private javax.swing.JPanel seatpanel;
    private javax.swing.JLabel seatsdisplay;
    private javax.swing.JLabel showdatedisplay;
    private javax.swing.JLabel showtimedisplay;
    private javax.swing.JCheckBox studentcheckbox;
    private javax.swing.JTextField studentemail;
    private javax.swing.JTextField studentid;
    private javax.swing.JLabel theatreiddisplay;
    private javax.swing.JTable tickettable;
    private javax.swing.JLabel titledisplay;
    private javax.swing.JLabel totalpricedisplay;
    private javax.swing.JLabel unitpricedisplay;
    private javax.swing.JButton updateProfile;
    private javax.swing.JLabel welcometext;
    // End of variables declaration//GEN-END:variables
}
