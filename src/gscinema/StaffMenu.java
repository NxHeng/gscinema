/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gscinema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author e-hen
 */
public class StaffMenu extends javax.swing.JFrame {
    private Database db;
    private String movieid_Remove;
    /**
     * Creates new form StaffMenu
     */
    public StaffMenu() {
        try{
            db = new Database();
            db.connect();
        } catch (SQLException e) {}
        initComponents();
        
        //ADD MOVIES TAB
        //show movies
        try{
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT * FROM movie";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                String movieid = String.valueOf(rs.getInt("movieid"));
                String title = rs.getString("title");
                String rdate = rs.getString("releasedate");
                String casts = rs.getString("casts");
                String synopsis = rs.getString("synopsis");
                String duration = rs.getString("duration");
                String price = String.valueOf(rs.getInt("price"));
                String type = rs.getString("movietype");
                String tbData[] = {movieid, title, rdate, casts, synopsis, duration, price, type};
                DefaultTableModel tblModel = (DefaultTableModel)MovieTable.getModel();
                tblModel.addRow(tbData);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        casts1.setLineWrap(true);
        synopsis1.setLineWrap(true);
        
        //set default release date as today's date
        try {
            Date temp = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
            String tempdate = sdf.format(temp);
            Date current = new SimpleDateFormat("dd-MM-yyy").parse(tempdate);
            releasedate1.setDate(current);

        } catch (ParseException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //DELETE MOVIES TAB
        //Show movies
        try{
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT * FROM movie";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                String movieid = String.valueOf(rs.getInt("movieid"));
                String title = rs.getString("title");
                String rdate = rs.getString("releasedate");
                String casts = rs.getString("casts");
                String synopsis = rs.getString("synopsis");
                String duration = rs.getString("duration");
                String price = String.valueOf(rs.getInt("price"));
                String type = rs.getString("movietype");
                String tbData[] = {movieid, title, rdate, casts, synopsis, duration, price, type};
                DefaultTableModel tblModel = (DefaultTableModel)MovieTable2.getModel();
                tblModel.addRow(tbData);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //ADD SHOW TAB
        //Display shows
        try{
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT shows.showid, shows.movieid, movie.title, shows.theatreid, theatre.size, shows.showdate, shows.showtime, movie.price, movie.movietype\n" +
                         "FROM ((shows\n"+
                         "INNER JOIN movie ON shows.movieid = movie.movieid )\n"+
                         "INNER JOIN theatre ON shows.theatreid = theatre.theatreid);";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                String showid = String.valueOf(rs.getInt("shows.showid"));
                String movieid = String.valueOf(rs.getInt("shows.movieid"));
                String title = rs.getString("movie.title");
                String theatre = String.valueOf(rs.getInt("shows.theatreid"));
                String size = String.valueOf(rs.getInt("theatre.size"));
                String sdate = rs.getString("shows.showdate");
                String stime = rs.getString("shows.showtime");
                String price = String.valueOf(rs.getInt("movie.price"));
                String type = rs.getString("movie.movietype");   
                String tbData[] = {showid, movieid, title, theatre, size, sdate, stime, price, type};
                DefaultTableModel tblModel = (DefaultTableModel)AddShow.getModel();
                tblModel.addRow(tbData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Combo Box Movie
        try{
            ArrayList<String> movieid = new ArrayList<>();
            ArrayList<String> title = new ArrayList<>();
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT * FROM movie ORDER BY movieid ASC";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                movieid.add(String.valueOf(rs.getInt("movieid")));
                title.add(rs.getString("title"));
            }
            String[] combine = new String[movieid.size()];
            for(int i = 0; i < movieid.size(); i++){
                combine[i] = movieid.get(i) + " - " + title.get(i);
            }
            for(String item : combine){
                movie3.addItem(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Combo Box Theatre
        try{
            ArrayList<String> theatreid = new ArrayList<>();
            ArrayList<String> tsize = new ArrayList<>();
            Statement stm = db.getConnection().createStatement();
            String sql = "SELECT * FROM theatre ORDER BY theatreid ASC";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                theatreid.add(String.valueOf(rs.getInt("theatreid")));
                tsize.add(rs.getString("size"));
            }
            String[] combine = new String[theatreid.size()];
            for(int i = 0; i < tsize.size(); i++){
                combine[i] = theatreid.get(i) + " (" + tsize.get(i) + ") ";
            }
            for(String item : combine){
                theatre3.addItem(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
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
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MovieTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        title1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        movieid1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        releasedate1 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        casts1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        synopsis1 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        duration1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        price1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        type1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        MovieTable2 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        AddShow = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        movie3 = new javax.swing.JComboBox<>();
        showid3 = new javax.swing.JTextField();
        theatre3 = new javax.swing.JComboBox<>();
        sdate3 = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        stime3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("GSCinema");

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(375, 375, 375)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        MovieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Movie ID", "Title", "Release Date", "Casts", "Synopsis", "Duration", "Price", "Type"
            }
        ));
        MovieTable.setRowHeight(40);
        MovieTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(MovieTable);

        jLabel2.setText("Movie Title:");

        title1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                title1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Movie ID:");

        movieid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieid1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Release Date:");

        releasedate1.setDateFormatString("dd-MM-yyyy");

        jLabel5.setText("Casts:");

        casts1.setColumns(20);
        casts1.setLineWrap(true);
        casts1.setRows(3);
        casts1.setTabSize(4);
        casts1.setWrapStyleWord(true);
        jScrollPane2.setViewportView(casts1);

        jLabel6.setText("Synopsis:");

        synopsis1.setColumns(20);
        synopsis1.setRows(5);
        jScrollPane3.setViewportView(synopsis1);

        jLabel7.setText("Duration:");

        jLabel8.setText("Price:");

        price1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                price1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Type:");

        type1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Premium" }));
        type1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type1ActionPerformed(evt);
            }
        });

        jButton2.setText("Add Movie");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Clear ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Refresh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title1)
                    .addComponent(movieid1)
                    .addComponent(releasedate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addComponent(duration1)
                    .addComponent(price1)
                    .addComponent(type1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(movieid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(title1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(releasedate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(duration1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(price1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(type1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton5))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Add Movies", jPanel2);

        MovieTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Movie ID", "Title", "Release Date", "Casts", "Synopsis", "Duration", "Price", "Type"
            }
        ));
        MovieTable2.setRowHeight(40);
        MovieTable2.getTableHeader().setReorderingAllowed(false);
        MovieTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MovieTable2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(MovieTable2);

        jButton4.setText("Remove Selected Movie");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setText("// Maybe need to delete Show and Seat as well");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(107, 107, 107))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(53, 53, 53))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jButton4)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel10)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Remove Movies", jPanel3);

        AddShow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Show ID", "Movie ID", "Title", "Theatre ID", "Theatre Size", "Show Date", "Show Time", "Price", "Type"
            }
        ));
        AddShow.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(AddShow);

        jLabel11.setText("Movie:");

        jLabel12.setText("Theatre:");

        jLabel13.setText("Show ID:");

        movie3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movie3ActionPerformed(evt);
            }
        });

        showid3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showid3ActionPerformed(evt);
            }
        });

        theatre3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                theatre3ActionPerformed(evt);
            }
        });

        sdate3.setDateFormatString("dd-MM-yyyy");

        jLabel14.setText("Show Date:");

        jLabel15.setText("Show Time:");

        stime3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stime3ActionPerformed(evt);
            }
        });

        jButton6.setText("Add Show");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sdate3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(movie3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(theatre3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(showid3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(stime3))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addGap(191, 191, 191))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(showid3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(movie3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(theatre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sdate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(stime3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addComponent(jButton6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Shows", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void title1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_title1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_title1ActionPerformed

    private void movieid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_movieid1ActionPerformed

    private void price1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_price1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_price1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        
        DefaultTableModel tblModel = (DefaultTableModel)MovieTable.getModel();
        //Remove rows one by one from the end of the table
        int rowCount = tblModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
        
        try{
            String movieid2 = movieid1.getText();
            String title2 = title1.getText();
            SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
            String date2 = s.format(releasedate1.getDate());
            String casts2 = casts1.getText();
            String synopsis2 = synopsis1.getText();
            String duration2 = duration1.getText();
            String price2 = price1.getText();
            String type2 = (String) type1.getSelectedItem();
            
            if(movieid2.equals("")||title2.equals("")||date2.equals("")||casts2.equals("")||synopsis2.equals("")||duration2.equals("")||price2.equals("")||type2.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in the blank(s)");
            }
            else{
                Statement stm = db.getConnection().createStatement();
                String sql2 = "INSERT INTO movie VALUES('" + movieid2 +"', '" + title2 + "', '" + date2 +"', "
                        + "'" + casts2 + "', '" + synopsis2 + "', '" + duration2 + "', '" + price2 + "', '" + type2 + "')";
                stm.executeUpdate(sql2);
                
                JOptionPane.showMessageDialog(this, "The movie added succesfully");
                
                //clear fill
                movieid1.setText("");
                title1.setText("");
                //set to current date (default) after adding a movie
                try {
                    Date temp = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
                    String tempdate = sdf.format(temp);
                    Date current = new SimpleDateFormat("dd-MM-yyy").parse(tempdate);
                    releasedate1.setDate(current);
                } catch (ParseException ex) {
                    Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                casts1.setText("");
                synopsis1.setText("");
                duration1.setText("");
                price1.setText("");
                
                //refresh
                try{
                    Statement stmt = db.getConnection().createStatement();
                    String sql = "SELECT * FROM movie";
                    ResultSet rs = stmt.executeQuery(sql);
                    while(rs.next()){
                        String movieid = String.valueOf(rs.getInt("movieid"));
                        String title = rs.getString("title");
                        String rdate = rs.getString("releasedate");
                        String casts = rs.getString("casts");
                        String synopsis = rs.getString("synopsis");
                        String duration = rs.getString("duration");
                        String price = String.valueOf(rs.getInt("price"));
                        String type = rs.getString("movietype");
                        String tbData[] = {movieid, title, rdate, casts, synopsis, duration, price, type};
                        
                        tblModel.addRow(tbData);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
                movieid1.setText("");
                title1.setText("");
                try {
                    Date temp = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
                    String tempdate = sdf.format(temp);
                    Date current = new SimpleDateFormat("dd-MM-yyy").parse(tempdate);
                    releasedate1.setDate(current);

                } catch (ParseException ex) {
                    Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                casts1.setText("");
                synopsis1.setText("");
                duration1.setText("");
                price1.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Login log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void MovieTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MovieTable2MouseClicked
        
        //Store movie id to a temporary variable
        DefaultTableModel model = (DefaultTableModel)MovieTable2.getModel();
        int selectedRowIndex = MovieTable2.getSelectedRow();
        movieid_Remove  = model.getValueAt(selectedRowIndex, 0).toString();
    }//GEN-LAST:event_MovieTable2MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        DefaultTableModel tblModel = (DefaultTableModel)MovieTable2.getModel();
        
        //For refreshing table after deleting movie
        //Remove rows one by one from the end of the table
        int rowCount = tblModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
        
        //Delete selected movie(row) where movie id is stored in 'movieid_Remove'
        try{
            Statement stm = db.getConnection().createStatement();
            String sql = "DELETE FROM movie WHERE movieid = '" + movieid_Remove + "';";
            stm.executeUpdate(sql);
            //refresh table after delete movie
            try{
                    Statement stmt = db.getConnection().createStatement();
                    String sql1 = "SELECT * FROM movie";
                    ResultSet rs = stmt.executeQuery(sql1);
                    while(rs.next()){
                        String movieid = String.valueOf(rs.getInt("movieid"));
                        String title = rs.getString("title");
                        String rdate = rs.getString("releasedate");
                        String casts = rs.getString("casts");
                        String synopsis = rs.getString("synopsis");
                        String duration = rs.getString("duration");
                        String price = String.valueOf(rs.getInt("price"));
                        String type = rs.getString("movietype");
                        String tbData[] = {movieid, title, rdate, casts, synopsis, duration, price, type};
                        tblModel.addRow(tbData);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(StaffMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        DefaultTableModel tblModel = (DefaultTableModel)MovieTable.getModel(); 
        //For refreshing table
        //Remove rows one by one from the end of the table
        int rowCount = tblModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
        //refresh table 
        try{
            Statement stmt = db.getConnection().createStatement();
            String sql1 = "SELECT * FROM movie";
            ResultSet rs = stmt.executeQuery(sql1);
            while(rs.next()){
                String movieid = String.valueOf(rs.getInt("movieid"));
                String title = rs.getString("title");
                String rdate = rs.getString("releasedate");
                String casts = rs.getString("casts");
                String synopsis = rs.getString("synopsis");
                String duration = rs.getString("duration");
                String price = String.valueOf(rs.getInt("price"));
                String type = rs.getString("movietype");
                String tbData[] = {movieid, title, rdate, casts, synopsis, duration, price, type};
                tblModel.addRow(tbData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void stime3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stime3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stime3ActionPerformed

    private void showid3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showid3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showid3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        //ADD SHOW BUTTON
        String showid = showid3.getText();
        String movieid = (String) movie3.getSelectedItem();
        //to get the movie id at the first position
        movieid = movieid.substring(0, 1);
        String theatreid = (String) theatre3.getSelectedItem();
        //to get the theatre id at the first position
        theatreid = theatreid.substring(0, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        String date = sdf.format(sdate3.getDate());
        String time = stime3.getText();
        try{
            if(showid.equals("")||movieid.equals("")||theatreid.equals("")||date.equals("")||time.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in the blank(s)");
            }
            else{
                Statement stm = db.getConnection().createStatement();
                String sql = "INSERT INTO SHOWS VALUES('" + showid + "', '" + movieid + "', '" + theatreid + "', '" + date + "', '" + time + "');";
                stm.executeUpdate(sql);
                
                JOptionPane.showMessageDialog(this, "The movie added succesfully");
                
                //GENERATE TICKETS FOR ALL SEATS
                //Identify the size of theatre
                String temp = (String) theatre3.getSelectedItem();
                String theatresize;
                //exp -> 1 (30)  //  2 (100)  //  11 (50)    //    13 (100)   
                //       0123456     01234567     01234567         012345678 
                if(temp.substring(2,3).equals(" ") && temp.substring(7, 8).equals(")")){
                    theatresize = temp.substring(4, 7);
                }
                else if(temp.substring(2,3).equals(" ") && temp.substring(6, 7).equals(")")){
                    theatresize = temp.substring(4, 6);
                }
                else if(temp.substring(1, 2).equals(" ") && temp.substring(6, 7).equals(")")){
                    theatresize = temp.substring(3, 6);
                }
                else{
                    theatresize = temp.substring(3, 5);
                }
                //theatresize = temp.substring(3, 5);
                
                
                int size = Integer.parseInt(theatresize);
                char finalrow = 'E';
                String[] seatnum = new String[size];
                switch(size){
                    case 50 -> finalrow = 'E';
                    case 70 -> finalrow = 'G';
                    case 90 -> finalrow = 'I';
                }
                int count = 0; 
                for(char a = 'A'; a <= finalrow; a++){
                    for(int i = 1; i <= 10; i++){
                        if(count == size){
                            break;
                        }
                        seatnum[count] = a + String.valueOf(i);
                        count++;
                    }
                }
                    
                String status = "Available";
                for(int i = 0; i < size; i++){
                     Statement stmt2 = db.getConnection().createStatement();
                     String sql2 = "INSERT INTO seat (showid, seatnum, status) VALUES('" + showid + "', '" + seatnum[i] + "', '" + status + "');";
                     stmt2.executeUpdate(sql2);
                }
                
                
                //CLAER FILLS
                showid3.setText("");
                movie3.setSelectedIndex(0);
                theatre3.setSelectedIndex(0);
                //set to default date after adding a show
                try {
                    Date temp2 = new Date();
                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyy");
                    String tempdate = sdf2.format(temp2);
                    Date current = new SimpleDateFormat("dd-MM-yyy").parse(tempdate);
                    sdate3.setDate(current);
                    } catch (ParseException ex) {
                        Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                stime3.setText("");
                
                //REFRESH TABLE
                DefaultTableModel tblModel = (DefaultTableModel)AddShow.getModel(); 
                //For refreshing table
                //Remove rows one by one from the end of the table
                int rowCount = tblModel.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    tblModel.removeRow(i);
                }
                //refresh table 
                try{
                    Statement stmt = db.getConnection().createStatement();
                    String sql1 = "SELECT shows.showid, shows.movieid, movie.title, shows.theatreid, theatre.size, shows.showdate, shows.showtime, movie.price, movie.movietype\n" +
                                 "FROM ((shows\n"+
                                 "INNER JOIN movie ON shows.movieid = movie.movieid )\n"+
                                 "INNER JOIN theatre ON shows.theatreid = theatre.theatreid)";
                    ResultSet rs = stmt.executeQuery(sql1);
                    while(rs.next()){
                        String showid2 = String.valueOf(rs.getInt("shows.showid"));
                        String movieid2 = String.valueOf(rs.getInt("shows.movieid"));
                        String title = rs.getString("movie.title");
                        String theatre = String.valueOf(rs.getInt("shows.theatreid"));
                        String tsize = String.valueOf(rs.getInt("theatre.size"));
                        String sdate = rs.getString("shows.showdate");
                        String stime = rs.getString("shows.showtime");
                        String price = String.valueOf(rs.getInt("movie.price"));
                        String type = rs.getString("movie.movietype");   
                        String tbData[] = {showid2, movieid2, title, theatre, tsize, sdate, stime, price, type};
                        tblModel.addRow(tbData);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void movie3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movie3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_movie3ActionPerformed

    private void type1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_type1ActionPerformed

    private void theatre3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_theatre3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_theatre3ActionPerformed

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
            java.util.logging.Logger.getLogger(StaffMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AddShow;
    private javax.swing.JTable MovieTable;
    private javax.swing.JTable MovieTable2;
    private javax.swing.JTextArea casts1;
    private javax.swing.JTextField duration1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> movie3;
    private javax.swing.JTextField movieid1;
    private javax.swing.JTextField price1;
    private com.toedter.calendar.JDateChooser releasedate1;
    private com.toedter.calendar.JDateChooser sdate3;
    private javax.swing.JTextField showid3;
    private javax.swing.JTextField stime3;
    private javax.swing.JTextArea synopsis1;
    private javax.swing.JComboBox<String> theatre3;
    private javax.swing.JTextField title1;
    private javax.swing.JComboBox<String> type1;
    // End of variables declaration//GEN-END:variables
}
