/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gscinema;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author e-hen
 */
public class CustomerMenu extends javax.swing.JFrame {
    private Customer cus;
    private Database db;
    String theatreid_select;
    String date_select;
    String time_select;
    int price_select;
    int foodid;
    int foodprice;
    int movie_sum;
    int selectedshowid;
    int numofticket;
    double wholeTotal;
    double afterdiscountprice;
    
    ArrayList<Seat> seatlist = new ArrayList<>();

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
        this.cus = cus;
        this.db = db;
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Welcome Text
        welcometext.setText("Welcome " + cus.getName());
        
        
        //List out all the shows (METHOD)
        displayShows();
        
        //List out all the booked tickets (Record)
        try{
            Statement stm1 = db.getConnection().createStatement();
            String sql1 = "SELECT *\n" +
                          "FROM ((((((bookdetail\n" +
                          "INNER JOIN booking ON bookdetail.bookid = booking.bookid )\n" +
                          "INNER JOIN seat ON bookdetail.seatid = seat.seatid)\n" +
                          "INNER JOIN food ON booking.fbid = food.fbid)\n" +
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
                String price = String.valueOf(rs1.getInt("totalprice"));
                String tbData[] = {bookid, title, theatre, seatnum, sdate, stime, price};
                DefaultTableModel tblModel1 = (DefaultTableModel)tickettable.getModel();
                tblModel1.addRow(tbData);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        //MovieList
        try{
            ArrayList<String> movieItems = new ArrayList<>();
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT * FROM movie ORDER BY title";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                movieItems.add(rs.getString("title"));
            }
            for (String i : movieItems) {
                MovieList.addItem(i);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        //TimeList
        try{
            ArrayList<String> timeList = new ArrayList<>();
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT DISTINCT showtime FROM shows ORDER BY showtime DESC";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                timeList.add(rs.getString("showtime"));
            }
            for (String i : timeList) {
                TimeList.addItem(i);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Default Date
        try {
            Date temp = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
            String tempdate = sdf.format(temp);
            Date current = new SimpleDateFormat("dd-MM-yyy").parse(tempdate);
            DateChooser.setDate(current);
        } catch (ParseException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //F&B combo box list
        try{
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
        displayProfile(cus);
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
        jButton2 = new javax.swing.JButton();
        welcometext = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowTable = new javax.swing.JTable();
        MovieList = new javax.swing.JComboBox<>();
        TimeList = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Search = new javax.swing.JButton();
        DateChooser = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        displaytitle = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        displaydate = new javax.swing.JLabel();
        displaytime = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        displaytype = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        displayprice = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        displaytheatre = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        displayreleasedate = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        displaysynopsis = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        displayduration = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        displaycasts = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
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
        jButton4 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        fbunitprice = new javax.swing.JTextField();
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
        jButton5 = new javax.swing.JButton();
        PaymentType = new javax.swing.JComboBox<>();
        studentcheckbox = new javax.swing.JCheckBox();
        studentemail = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        studentid = new javax.swing.JTextField();
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
        jButton6 = new javax.swing.JButton();
        EwalletPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tickettable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        pfic = new javax.swing.JTextField();
        pfname = new javax.swing.JTextField();
        pfemail = new javax.swing.JTextField();
        pfphone = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        updateProfile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Golden Screen Cinema");

        jPanel1.setBackground(new java.awt.Color(255, 255, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("GSCinema");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        welcometext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcometext.setText("Welcome");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcometext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(welcometext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(1110, 500));

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
        MovieList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MovieListActionPerformed(evt);
            }
        });

        TimeList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a time" }));
        TimeList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimeListActionPerformed(evt);
            }
        });

        jLabel2.setText("Movies:");

        jLabel3.setText("Date:");

        jLabel4.setText("Time:");

        Search.setText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        DateChooser.setBackground(new java.awt.Color(255, 255, 255));
        DateChooser.setDateFormatString("dd-MM-yyyy");
        DateChooser.setMinSelectableDate(new java.util.Date(-62135794722000L));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DISPLAY");

        jLabel6.setText("Title:");

        displaytitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        displaytitle.setText("Title");

        jLabel8.setText("Date:");

        jLabel9.setText("Time:");

        displaydate.setText("Date");

        displaytime.setText("Time");

        jLabel12.setText("Type:");

        displaytype.setText("Type");

        jLabel14.setText("Price:");

        displayprice.setText("Price");

        jLabel17.setText("Theatre:");

        displaytheatre.setText("Theatre");

        jLabel7.setText("Release Date:");

        displayreleasedate.setText("Release Date");

        jLabel10.setText("Casts:");

        jLabel11.setText("Synopsis:");

        displaysynopsis.setEditable(false);
        displaysynopsis.setColumns(20);
        displaysynopsis.setLineWrap(true);
        displaysynopsis.setRows(4);
        displaysynopsis.setWrapStyleWord(true);
        displaysynopsis.setAutoscrolls(false);
        jScrollPane3.setViewportView(displaysynopsis);

        jLabel13.setText("Duration:");

        displayduration.setText("Duration");

        displaycasts.setEditable(false);
        displaycasts.setColumns(20);
        displaycasts.setLineWrap(true);
        displaycasts.setRows(2);
        displaycasts.setTabSize(3);
        displaycasts.setWrapStyleWord(true);
        jScrollPane4.setViewportView(displaycasts);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton3.setText("Display All Shows");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        DisplayPanel.setLayout(new java.awt.CardLayout());

        seatpanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout seatpanelLayout = new javax.swing.GroupLayout(seatpanel);
        seatpanel.setLayout(seatpanelLayout);
        seatpanelLayout.setHorizontalGroup(
            seatpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );
        seatpanelLayout.setVerticalGroup(
            seatpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        DisplayPanel.add(seatpanel, "card2");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Food & Beverage");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Set:");

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

        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });

        jButton4.setText("Proceed to Confirmation");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel26.setText("Unit Price:");

        fbunitprice.setEditable(false);
        fbunitprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fbunitpriceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FBPanelLayout = new javax.swing.GroupLayout(FBPanel);
        FBPanel.setLayout(FBPanelLayout);
        FBPanelLayout.setHorizontalGroup(
            FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FBPanelLayout.createSequentialGroup()
                .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FBPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(FBPanelLayout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(FBPanelLayout.createSequentialGroup()
                                .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane5)
                                    .addComponent(fbsetlist, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(quantity)
                                    .addComponent(fbunitprice)))
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 230, Short.MAX_VALUE)))
                .addContainerGap())
        );
        FBPanelLayout.setVerticalGroup(
            FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FBPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(33, 33, 33)
                .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fbsetlist))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FBPanelLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fbunitprice, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quantity))
                .addGap(32, 32, 32)
                .addComponent(jButton4)
                .addGap(36, 36, 36))
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

        jButton5.setText("Proceed To Payment");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        PaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Debit/Credit", "e-Wallet" }));
        PaymentType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentTypeActionPerformed(evt);
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

        javax.swing.GroupLayout ConfirmationPanelLayout = new javax.swing.GroupLayout(ConfirmationPanel);
        ConfirmationPanel.setLayout(ConfirmationPanelLayout);
        ConfirmationPanelLayout.setHorizontalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConfirmationPanelLayout.createSequentialGroup()
                        .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ConfirmationPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(PaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ConfirmationPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(studentcheckbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentemail, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentid, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(totalpricedisplay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titledisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(fbsetdisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(seatsdisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(movietypedisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(unitpricedisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
                        .addComponent(showtimedisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showdatedisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(theatreiddisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(fbquantitydisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        ConfirmationPanelLayout.setVerticalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmationPanelLayout.createSequentialGroup()
                .addContainerGap()
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
                .addComponent(movietypedisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seatsdisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fbsetdisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fbquantitydisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalpricedisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentcheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(studentemail, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(studentid, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(PaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap())
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

        expdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expdateActionPerformed(evt);
            }
        });

        jButton6.setText("Pay");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CardPaymentPanelLayout = new javax.swing.GroupLayout(CardPaymentPanel);
        CardPaymentPanel.setLayout(CardPaymentPanelLayout);
        CardPaymentPanelLayout.setHorizontalGroup(
            CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CardPaymentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cardname)
                    .addComponent(cardnum)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CardPaymentPanelLayout.createSequentialGroup()
                        .addGroup(CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(expdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(csv, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(265, 265, 265))
            .addGroup(CardPaymentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CardPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
                .addComponent(jButton6)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        DisplayPanel.add(CardPaymentPanel, "card5");

        javax.swing.GroupLayout EwalletPanelLayout = new javax.swing.GroupLayout(EwalletPanel);
        EwalletPanel.setLayout(EwalletPanelLayout);
        EwalletPanelLayout.setHorizontalGroup(
            EwalletPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        EwalletPanelLayout.setVerticalGroup(
            EwalletPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );

        DisplayPanel.add(EwalletPanel, "card6");

        jButton1.setText("Book");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MovieList, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TimeList, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(265, 265, 265)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(displaytitle, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(displaydate, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(52, 52, 52)
                                                .addComponent(displaytime, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel14))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(89, 89, 89)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(displayprice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(displaytype, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(displayduration, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(89, 89, 89)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(displayreleasedate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(displaytheatre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(MovieList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(TimeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Search)
                        .addComponent(jLabel5))
                    .addComponent(DateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(346, 346, 346))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(displaytitle)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(displaydate)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(displaytime)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(displaytype)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(displayprice)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(displaytheatre))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(displayreleasedate))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(displayduration)))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(50, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Display Shows", jPanel2);

        tickettable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Movie Title", "Seat Number", "Theatre", "Date", "Time", "Price"
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
        jScrollPane2.setViewportView(tickettable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1499, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(380, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Booked Tickets", jPanel3);

        jLabel27.setText("IC Number:");

        jLabel28.setText("User Name:");

        jLabel29.setText("E-mail Address:");

        jLabel30.setText("Phone Number:");

        pfic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pficActionPerformed(evt);
            }
        });

        jLabel31.setText("Current Password:");

        jLabel32.setText("New Password:");

        jLabel33.setText("Confirm Password:");

        updateProfile.setText("Update");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pfic)
                            .addComponent(pfname, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(pfemail, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(pfphone, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3))))
                .addContainerGap(1169, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(pfic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(pfname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(pfemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(pfphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(updateProfile)
                .addContainerGap(417, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Profile", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Login log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        DisplayPanel.removeAll();
        DisplayPanel.add(FBPanel);
        DisplayPanel.updateUI();
        DisplayPanel.revalidate();
        numofticket = 0;

        for (Seat i : seatlist) {
            if(i.button.isSelected() == true){
                //System.out.println(i.getShowid() + " " + i.getNum() + " selected.");
                numofticket++;
            }
        }
        int counter = 0;
        String[] tempNum = new String[numofticket];
        for(Seat i : seatlist){
            if(i.button.isSelected() == true && counter < numofticket){
                //System.out.println(i.getShowid() + " " + i.getNum() + " selected.");
                tempNum[counter] = i.getNum();
                selectedshowid = i.getShowid();
                counter++;
            }
        }
        String displaychosen = "";
        for(int i = 0; i < numofticket; i++){
            displaychosen += tempNum[i] + "  ";
        }
        try{
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT movie.title, shows.theatreid, shows.showdate, shows.showtime, movie.price, movie.movietype\n" +
            "FROM ((shows\n" +
            "INNER JOIN movie ON shows.movieid = movie.movieid )\n" +
            "INNER JOIN theatre ON shows.theatreid = theatre.theatreid)\n" +
            "WHERE shows.showid = '" + selectedshowid + "'";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next()){
                //title
                titledisplay.setText(rs.getString("movie.title"));

                theatreiddisplay.setText("Theatre ID: " + String.valueOf(rs.getInt("shows.theatreid")));

                showdatedisplay.setText("Date: " + rs.getString("shows.showdate"));

                showtimedisplay.setText("Time: " + rs.getString("shows.showtime"));

                unitpricedisplay.setText("Unit Price: RM" + rs.getString(String.valueOf("movie.price")));

                movietypedisplay.setText("Type: " + rs.getString("movie.movietype"));   
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        //display chosen seat
        seatsdisplay.setText("Seats: " + displaychosen);      
        movie_sum = numofticket * price_select;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //DESELECT ALL BUTTONS IF CUSTOMER CHOSE ANOTHER MOVIE
        for (Seat i : seatlist) {
            i.button.setSelected(false);
        }
        seatpanel.removeAll();
        // refresh the panel.
        DisplayPanel.removeAll();
        DisplayPanel.add(seatpanel);
        DisplayPanel.updateUI();
        seatpanel.updateUI();
        DisplayPanel.revalidate();
        
        //clear and display table (METHOD)
        displayShows();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
 
        //clear table
        DefaultTableModel tblModel = (DefaultTableModel)ShowTable.getModel();
        clearShowsTable(tblModel);
        if(DateChooser.getDate() == null){
            JOptionPane.showMessageDialog(this, "Please choose date");
        }
        else{
            try{
                String movietitle = (String) MovieList.getSelectedItem();

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
                String date = sdf.format(DateChooser.getDate());

                String time = (String) TimeList.getSelectedItem();

                Statement stm = db.getConnection().createStatement();
                String sql = "SELECT shows.theatreid, movie.title, shows.showdate, shows.showtime, movie.price, movie.movietype\n" +
                "FROM ((shows\n" +
                "INNER JOIN movie ON shows.movieid = movie.movieid )\n" +
                "INNER JOIN theatre ON shows.theatreid = theatre.theatreid)\n";

                //conditions
                //REMEMBER TO EDIT THIS
                if(movietitle.isEmpty() && date.isEmpty() && time.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please fill in all the blank(s)");
                }
                else if(!movietitle.isEmpty() && date.isEmpty() && time.isEmpty()){
                    sql = sql + "WHERE title = '" + movietitle + "'";
                }
                else if(movietitle.isEmpty() && !date.isEmpty() && time.isEmpty()){
                    sql = sql + "WHERE showdate = '" + date + "'";
                }
                else if(movietitle.isEmpty() && date.isEmpty() && !time.isEmpty()){
                    sql = sql + "WHERE showtime = '" + time + "'";
                }
                else if(!movietitle.isEmpty() && !date.isEmpty() && time.isEmpty()){
                    sql = sql + "WHERE title = '" + movietitle + "' and showdate = '" + date + "'";
                }
                else if(movietitle.isEmpty() && !date.isEmpty() && !time.isEmpty()){
                    sql = sql + "WHERE showdate = '" + date + "' and showtime = '" + time + "'";
                }
                else if(!movietitle.isEmpty() && date.isEmpty() && !time.isEmpty()){
                    sql = sql + "WHERE title = '" + movietitle + "' and showtime = '" + time + "'";
                }
                else{
                    sql = sql + "WHERE showtime = '" + time + "' and showdate = '" + date + "' and title = '" + movietitle + "'";
                }
                ResultSet rs = stm.executeQuery(sql);
                int counter = 0;
                while(rs.next()){
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
                if(counter == 0){
                    JOptionPane.showMessageDialog(this, "No show found");
                    //remove all components in panel.
                    seatpanel.removeAll();
                    // refresh the panel.
                    seatpanel.updateUI();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_SearchActionPerformed

    private void TimeListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimeListActionPerformed

    }//GEN-LAST:event_TimeListActionPerformed

    private void MovieListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MovieListActionPerformed

    }//GEN-LAST:event_MovieListActionPerformed

    private void ShowTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowTableMouseClicked
        
        DisplayPanel.removeAll();
        DisplayPanel.add(seatpanel);
        DisplayPanel.updateUI();
        seatpanel.removeAll();
        seatpanel.updateUI();

        DefaultTableModel model = (DefaultTableModel)ShowTable.getModel();
        int selectedRowIndex = ShowTable.getSelectedRow();
        theatreid_select  = model.getValueAt(selectedRowIndex, 0).toString();
        date_select = model.getValueAt(selectedRowIndex, 2).toString();
        time_select = model.getValueAt(selectedRowIndex, 3).toString();
        String showid = "";
        int size = 0;
        int row = 0;
        char ch = 0;

        System.out.println(theatreid_select);
        System.out.println(date_select);
        System.out.println(time_select);

        try{
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT * FROM shows WHERE theatreid = '" + theatreid_select + "' AND shows.showdate = '" + date_select + "' AND shows.showtime = '" + time_select + "'";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                showid = String.valueOf(rs.getInt("showid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("showid = " + showid);

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

        System.out.println("size = " + size);

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
                    try{
                        Statement stm2 = db.getConnection().createStatement();
                        String sql2 = "SELECT * FROM seat WHERE seatnum = '" + seatnum + "' AND showid = '" + showid + "'";
                        ResultSet rs2 = stm2.executeQuery(sql2);
                        while(rs2.next()){
                            status = rs2.getString("status");
                            selectedseatid = rs2.getInt("seatid");
                        }
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

                    //check availability
                    if(status.equals("Unavailable")){
                        JToggleButton bt = new JToggleButton(seatnum);
                        bt.setFont(new Font("Arial", Font.PLAIN, 6));
                        bt.setPreferredSize(new Dimension(10, 10));
                        bt.setEnabled(false);
                        seatpanel.add(bt);
                        seatlist.add(new Seat(selectedseatid, seatnum, true, Integer.parseInt(showid), bt));
                        counter++;
                    }else{
                        JToggleButton bt = new JToggleButton(seatnum);
                        bt.setFont(new Font("Arial", Font.PLAIN, 7));
                        bt.setPreferredSize(new Dimension(20, 20));
                        bt.setEnabled(true);
                        //bt.setBackground(Color.green);
                        seatpanel.add(bt);
                        seatlist.add(new Seat(selectedseatid, seatnum, false, Integer.parseInt(showid), bt));
                        counter++;
                    }
                }
            }
        }
        
        //Disable seats for SOP purpose
        for(int i = 0; i < seatlist.size(); i++){
           if(seatlist.get(i).isIsBooked() == true){
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
            Statement stm3 = db.getConnection().createStatement();
            String sql3 = "SELECT *\n" +
            "FROM ((shows\n" +
            "INNER JOIN movie ON shows.movieid = movie.movieid )\n" +
            "INNER JOIN theatre ON shows.theatreid = theatre.theatreid)\n" +
            "WHERE shows.showid = '" + showid + "';";
            ResultSet rs3 = stm3.executeQuery(sql3);
            while(rs3.next()){
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
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        //DESELECT ALL BUTTONS IF CUSTOMER CHOSE ANOTHER MOVIE
        for (Seat i : seatlist) {
            i.button.setSelected(false);
        }
    }//GEN-LAST:event_ShowTableMouseClicked

    private void expdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expdateActionPerformed

    }//GEN-LAST:event_expdateActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String selectedfood = (String) fbsetlist.getSelectedItem();
        
        if(!selectedfood.equals("") && quantity.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please Insert Quantity"); 
        }else if(selectedfood.equals("") && !quantity.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please Select a menu"); 
        }
        else if(!selectedfood.equals("") && !quantity.getText().equals("")){
            
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
                int qt = Integer.parseInt(quantity.getText());
                foodprice = Integer.parseInt(fbunitprice.getText().substring(2, 4)) * qt;
                fbsetdisplay.setText("Food & Beverage: " + (String) fbsetlist.getSelectedItem());
                fbquantitydisplay.setText("F&B Quantity: " + qt);
                wholeTotal = (foodprice + movie_sum);
                String displaytotal = String.format("RM%.2f", wholeTotal);
                totalpricedisplay.setText(displaytotal);
                
                DisplayPanel.removeAll();
                DisplayPanel.add(ConfirmationPanel);
                DisplayPanel.updateUI();
                DisplayPanel.revalidate();
                
                afterdiscountprice = wholeTotal;
        }
        else{
            fbsetdisplay.setText("No Beverages Selected");
            fbquantitydisplay.setText("No Quantity");
            wholeTotal = movie_sum;
            String displaytotal = String.format("RM%.2f",wholeTotal);
            totalpricedisplay.setText(displaytotal);
            DisplayPanel.removeAll();
            DisplayPanel.add(ConfirmationPanel);
            DisplayPanel.updateUI();
            DisplayPanel.revalidate();
            afterdiscountprice = wholeTotal;
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void fbsetlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fbsetlistActionPerformed
        try{
            String fbname = (String) fbsetlist.getSelectedItem();
            
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT * FROM  food where fbname = '" + fbname + "'";
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Description.setText(rs.getString("description"));
                fbunitprice.setText(String.format("RM%4.2f", (double)rs.getInt("price")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_fbsetlistActionPerformed

    private void fbunitpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fbunitpriceActionPerformed
        
    }//GEN-LAST:event_fbunitpriceActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        DisplayPanel.removeAll();
        DisplayPanel.add(CardPaymentPanel);
        DisplayPanel.updateUI();
        DisplayPanel.revalidate();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void PaymentTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PaymentTypeActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        if(cardnum.getText().equals("") || cardname.getText().equals("")  || expdate.getText().equals("")  || csv.getText().equals("") ){
            JOptionPane.showMessageDialog(this, "Please fill in the blanks");
        }
        else{
            try {
                
                //RETURN_GENERATED_ID
                String sql = "INSERT INTO booking (ic, fbid, totalprice) VALUES ('" + cus.getIc() + "', '" + foodid +"', '" + afterdiscountprice + "');";
                PreparedStatement ps = db.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                int bookid = 0;
                while(rs.next()){
                    bookid = rs.getInt(1);
                }
                
                for (Seat i : seatlist) {
                    if(i.button.isSelected() == true){
                        Statement stm2 = db.getConnection().createStatement();
                        String sql2 = "INSERT INTO bookdetail VALUES ('" + bookid + "','" + i.getSeatid() + "');";
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
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void pficActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pficActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pficActionPerformed

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
                //new CustomerMenu().setVisible(true);
                
            }
        });
    }
    
    public void displayProfile(Customer cus){
        pfic.setText(cus.getIc());
        pfname.setText(cus.getName());
        pfemail.setText(cus.getEmail());
        pfphone.setText(cus.getPhone());
    }
    
    public void displayShows(){
        //clear table
        DefaultTableModel tblModel = (DefaultTableModel)ShowTable.getModel();
        clearShowsTable(tblModel);
        try{
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT shows.theatreid, movie.title, shows.showdate, shows.showtime, movie.price, movie.movietype\n" +
                         "FROM ((shows\n" +
                         "INNER JOIN movie ON shows.movieid = movie.movieid )\n" +
                         "INNER JOIN theatre ON shows.theatreid = theatre.theatreid)\n" +
                         "ORDER BY shows.showdate ASC, shows.showtime ASC;";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                String theatre = String.valueOf(rs.getInt("theatreid"));
                String title = rs.getString("title");
                String sdate = rs.getString("showdate");
                String stime = rs.getString("showtime");
                String price = String.valueOf(rs.getInt("price"));
                String type = rs.getString("movietype");
                String tbData[] = {theatre, title, sdate, stime, price, type};
                tblModel.addRow(tbData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearShowsTable(DefaultTableModel tblModel){
        int rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CardPaymentPanel;
    private javax.swing.JPanel ConfirmationPanel;
    private com.toedter.calendar.JDateChooser DateChooser;
    private javax.swing.JTextArea Description;
    private javax.swing.JPanel DisplayPanel;
    private javax.swing.JPanel EwalletPanel;
    private javax.swing.JPanel FBPanel;
    private javax.swing.JComboBox<String> MovieList;
    private javax.swing.JComboBox<String> PaymentType;
    private javax.swing.JButton Search;
    private javax.swing.JTable ShowTable;
    private javax.swing.JComboBox<String> TimeList;
    private javax.swing.JTextField cardname;
    private javax.swing.JTextField cardnum;
    private javax.swing.JTextField csv;
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
    private javax.swing.JTextField expdate;
    private javax.swing.JLabel fbquantitydisplay;
    private javax.swing.JLabel fbsetdisplay;
    private javax.swing.JComboBox<String> fbsetlist;
    private javax.swing.JTextField fbunitprice;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel movietypedisplay;
    private javax.swing.JTextField pfemail;
    private javax.swing.JTextField pfic;
    private javax.swing.JTextField pfname;
    private javax.swing.JTextField pfphone;
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
