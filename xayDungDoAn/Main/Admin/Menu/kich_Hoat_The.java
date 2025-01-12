package DoAncuoiki1.xayDungDoAn.Main.Admin.Menu;

import DoAncuoiki1.xayDungDoAn.Main.Login_Admin;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class kich_Hoat_The extends JFrame{

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=false;trustServerCertificate=true;useUnicode=true;characterEncoding=UTF-8";
    private static final String USER = "quocdat233";
    private static final String PASS = "dat23326";

    private JMenuBar menuBar;
    private JMenuItem menuItem1,itemQuanLySach,itemQuanLythe,itemKichHoatThe,itemThongKe1,itemThongKe2;
    private JMenu menu ;
    private JPanel pn1,pn2,pn3;
    private JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lblnote;
    private JTextField txt1,txt2,txt3,txt5;
    private JButton btn1,btn2,btn4;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scp;



    public kich_Hoat_The(){
        super("Quản lý thẻ");


        setSize(900,670);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Object [] col = {"Họ và tên", "Mã sinh viên", "Email", "SDT", "Giới tính", "Đối tượng","Ngày đăng kí"};






        lbl1 = new JLabel("Mã sinh viên");
        lbl1.setBounds(30, 40, 100, 20);
        lbl2 = new JLabel("Tên sinh viên");
        lbl2.setBounds(30, 85, 100, 20);
        lbl3 = new JLabel("Ngày đăng kí");
        lbl3.setBounds(30, 130, 100, 20);
        lbl4 = new JLabel("Ngày hết hạn");
        lbl4.setBounds(30, 175, 100, 20);

        txt1 = new JTextField();
        txt1.setBounds(145,40,160,20);
        txt2 = new JTextField();
        txt2.setBounds(145,85,160,20);
        txt3 = new JTextField();
        txt3.setBounds(145,130,160,20);

        List<String> days = new ArrayList<>();
        for (int i = 1; i <= 31; i++)
            days.add(String.valueOf(i));

        ArrayList<String> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++)
            months.add(String.valueOf(i));

        ArrayList<String> years = new ArrayList<>();
        for (int i = 1900; i < 2040; i++)
            years.add(String.valueOf(i));

        JComboBox<String> dayCombo = new JComboBox<>(days.toArray(new String[0]));
        dayCombo.setBounds(145, 175, 40, 20);

        JComboBox<String> monthCombo = new JComboBox<>(months.toArray(new String[0]));
        monthCombo.setBounds(197, 175, 50, 20);

        JComboBox<String> yearCombo = new JComboBox<>(years.toArray(new String[0]));
        yearCombo.setBounds(260, 175, 60, 20);


        btn1 = new JButton("Kích hoạt thẻ");
        btn1.setFocusPainted(false);
        btn1.setFont(new Font("Arial", Font.BOLD, 12));
        btn1.setBounds(150,230,113,28);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(txt1.getText().isEmpty() || txt2.getText().isEmpty() || txt3.getText().isEmpty()){
                   JOptionPane.showMessageDialog(kich_Hoat_The.this, "Lỗi,chưa có dữ liệu !");
               }
               else
               {
                   String maSinhVien = txt1.getText();
                   String ngaydangki = txt3.getText();
                   String ngayHetHan = yearCombo.getSelectedItem() + "-" +
                           ( monthCombo.getSelectedIndex() + 1)+  "-" +
                           dayCombo.getSelectedItem();

                   if (ngayHetHan.trim().isEmpty()) {
                       // Nếu ngày hết hạn trống, gán ngày hiện tại
                       ngayHetHan = "1900-01-01";
                   }


                   java.sql.Date ngayHetHanDate = java.sql.Date.valueOf(ngayHetHan);
                   java.sql.Date ngayDangKiDate = java.sql.Date.valueOf(ngaydangki);

                   if(ngayHetHanDate.before(ngayDangKiDate)){
                       JOptionPane.showMessageDialog(kich_Hoat_The.this, "Không được nhỏ hơn ngày đăng kí ");
                       return;
                   }

                   // Kết nối cơ sở dữ liệu
                   try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                       // 1. Chuyển dữ liệu sang bảng `user`
                       String insertQuery = "INSERT INTO SinhVienDadangKi (Tai_Khoan, Mat_Khau, Ho_Va_Ten, Ma_SinhVien, Email, SDT, Gioi_Tinh, doituong, NgayDangKy, NgayHetHan) " +
                               "SELECT  Tai_Khoan, Mat_Khau, Ho_Va_Ten, Ma_SinhVien, Email, SDT, Gioi_Tinh, doituong, NgayDangKy,?" +
                               "FROM SinhVienChoXacNhan " +
                               "WHERE Ma_SinhVien = ?";
                       try (java.sql.PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                           pstmt.setDate(1, ngayHetHanDate);
                           pstmt.setString(2, maSinhVien);

                           int rowsInserted = pstmt.executeUpdate();

                           if (rowsInserted > 0) {
                               // 2. Xóa dữ liệu khỏi bảng `SinhVienChoXacNhan`
                               String deleteQuery = "DELETE FROM SinhVienChoXacNhan WHERE Ma_SinhVien = ?";
                               try (java.sql.PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                                   deleteStmt.setString(1, maSinhVien);
                                   int rowsDeleted = deleteStmt.executeUpdate();

                                   if (rowsDeleted > 0) {
                                       JOptionPane.showMessageDialog(kich_Hoat_The.this, "Kích hoạt thẻ thành công!");
                                       loadTableData(); //noteee Cập nhật lại bảng
                                   }
                                   else
                                       JOptionPane.showMessageDialog(kich_Hoat_The.this, "Không thể xóa dữ liệu khỏi bảng SinhVienChoXacNhan.", "Error", JOptionPane.ERROR_MESSAGE);

                               }
                           } else {
                               JOptionPane.showMessageDialog(kich_Hoat_The.this, "Không thể thêm dữ liệu vào bảng user.", "Error", JOptionPane.ERROR_MESSAGE);
                           }
                       }
                   } catch (SQLException ex) {
                       ex.printStackTrace();
                       JOptionPane.showMessageDialog(kich_Hoat_The.this, "Lỗi khi thao tác với cơ sở dữ liệu!", "Error", JOptionPane.ERROR_MESSAGE);
                   }
               }
            }
        });




        pn1 = new JPanel();
        pn1.setLayout(null);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2)," Thông tin thẻ  ");
        pn1.setBorder(titledBorder);
        pn1.setBounds(25, 20, 400, 300);
        pn1.add(lbl1);
        pn1.add(txt1);
        pn1.add(lbl2);
        pn1.add(txt2);
        pn1.add(lbl3);
        pn1.add(txt3);
        pn1.add(lbl4);

        pn1.add(btn1);
        pn1.add(dayCombo);
        pn1.add(monthCombo);
        pn1.add(yearCombo);



        lbl5 = new JLabel("Thông tin tìm kiếm");
        lbl5.setBounds(30, 60, 120, 20);

        lblnote = new JLabel("*Nhập kí tự cần tìm");
        lblnote.setForeground(Color.red);
        lblnote.setFont(new Font("Arial", Font.PLAIN, 11));
        lblnote.setBounds(160, 87, 100, 20);

        txt5 = new JTextField();
        txt5.setBounds(160,58,180,23);

        btn2 = new JButton("Tìm kiếm");
        btn2.setBounds(145,135,90,25);
        btn2.setBackground(Color.LIGHT_GRAY);
        btn2.setFont(new Font("Arial", Font.BOLD, 12));
        btn2.setFocusPainted(false);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyWord = "%" + txt5.getText().trim() + "%";
                if(keyWord.equals("%%")){
                    JOptionPane.showMessageDialog(kich_Hoat_The.this,"Vui lòng nhập thông tin ");
                }
                else
                {
                    String query = "SELECT * FROM SinhVienChoXacNhan WHERE Ho_Va_Ten LIKE ? OR  Ma_SinhVien LIKE ? OR Email LIKE ? OR SDT LIKE ? OR Gioi_Tinh LIKE ? OR  doituong LIKE ? OR  NgayDangKy LIKE ?";
                    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                         PreparedStatement pstm = conn.prepareStatement(query)) {
                        pstm.setString(1, keyWord);
                        pstm.setString(2, keyWord);
                        pstm.setString(3, keyWord);
                        pstm.setString(4, keyWord);
                        pstm.setString(5, keyWord);
                        pstm.setString(6, keyWord);
                        pstm.setString(7, keyWord);

                        ResultSet rs = pstm.executeQuery();
                        model.setRowCount(0);

                        while (rs.next()) {
                            Object[] row = {
                                    rs.getString("Ho_Va_Ten"),
                                    rs.getString("Ma_SinhVien"),
                                    rs.getString("Email"),
                                    rs.getString("SDT"),
                                    rs.getString("Gioi_Tinh"),
                                    rs.getString("doiTuong"),
                                    rs.getDate("NgayDangKy"),

                            };
                            model.addRow(row); // Thêm dòng vào bảng
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(kich_Hoat_The.this, "Lỗi khi tìm kiếm dữ liệu!");
                    }
                }

            }
        });

        btn4 = new JButton("Load Bảng");
        btn4.setBounds(250,135, 100,25);
        btn4.setFont(new Font("Arial", Font.BOLD, 12));
        btn4.setBackground(Color.LIGHT_GRAY);
        btn4.setBackground(Color.LIGHT_GRAY);
        btn4.setFocusPainted(false);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                loadTableData();
            }
        });

        pn2 = new JPanel();
        pn2.setLayout(null);
        TitledBorder titledBorder1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2)," Thông tin tìm kiếm  ");
        pn2.setBorder(titledBorder1);
        pn2.setBounds(500, 90, 370, 200);
        pn2.add(lbl5);
        pn2.add(lblnote);
        pn2.add(txt5);
        pn2.add(btn2);
        pn2.add(btn4);

        model = new DefaultTableModel(col,0);
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setDefaultEditor(Object.class, null);
        scp = new JScrollPane(table);

        pn3 = new JPanel(new BorderLayout());
        TitledBorder titledBorder2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,2)," Danh sách thẻ sinh viên cần kích hoạt  ");
        pn3.setBorder(titledBorder2);
        pn3.setBounds(25, 330, 850, 270);
        pn3.add(scp,BorderLayout.CENTER);

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menu.setFont(new Font("Arial", Font.BOLD, 18));

        menuItem1 = new JMenuItem("Quay lại");
        menuItem1.setFont(new Font("Arial", Font.PLAIN, 18));
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login_Admin();
            }
        });

        itemQuanLythe = new JMenuItem("Quản lý thẻ");
        itemQuanLythe.setFont(new Font("Arial", Font.PLAIN, 18));
        itemQuanLythe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new quan_Ly_The();

            }
        });

        itemQuanLySach = new JMenuItem("Quản lý sách");
        itemQuanLySach.setFont(new Font("Arial", Font.PLAIN, 18));
        itemQuanLySach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new quan_Ly_Sach();

            }
        });

        itemKichHoatThe = new JMenuItem("Kích hoạt thẻ");
        itemKichHoatThe.setFont(new Font("Arial", Font.PLAIN, 18));
        itemKichHoatThe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        itemThongKe1 = new JMenuItem("Doanh thu");
        itemThongKe1.setFont(new Font("Arial", Font.PLAIN, 18));
        itemThongKe1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new ThongKe();
            }
        });

        itemThongKe2 = new JMenuItem("Thống kê sách");
        itemThongKe2.setFont(new Font("Arial", Font.PLAIN, 18));
        itemThongKe2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ThongKe2();
            }
        });





        menu.add(itemQuanLySach);
        menu.add(itemQuanLythe);
        menu.add(itemKichHoatThe);
        menu.add(itemThongKe1);
        menu.add(itemThongKe2);
        menu.addSeparator();
        menu.add(menuItem1);

        menuBar.add(menu);



        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int select = table.getSelectedRow();
                if(select!=-1){
                    txt1.setText(model.getValueAt(select, 1).toString());
                    txt2.setText(model.getValueAt(select, 0).toString());
                    txt3.setText( model.getValueAt(select, 6).toString());


                }}});

        add(pn1);
        add(pn2);
        add(pn3);
        loadTableData();
        setJMenuBar(menuBar);
        setVisible(true);

        //MÀU

        getContentPane().setBackground(new Color(74,136,136));
        table.setBackground(new Color(243,250,243));
        pn1.setBackground(new Color(245, 255, 240));
        pn2.setBackground(new Color(245, 255, 240));
        pn3.setBackground(new Color(74, 136, 136));
        btn1.setBackground(new Color(203, 194, 189));
        btn2.setBackground(new Color(203, 194, 189));
        btn4.setBackground(new Color(203, 194, 189));
        dayCombo.setBackground(new Color(203, 194, 189));
        monthCombo.setBackground(new Color(203, 194, 189));
        yearCombo.setBackground(new Color(203, 194, 189));




    }

    private void loadTableData() {
        model.setRowCount(0);

        String query = "SELECT Ho_Va_Ten, Ma_SinhVien, Email, SDT, Gioi_Tinh, doiTuong, NgayDangKy FROM SinhVienChoXacNhan";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             java.sql.Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {


            while (rs.next()) {
                Object[] row = {
                        rs.getString("Ho_Va_Ten"),
                        rs.getString("Ma_SinhVien"),
                        rs.getString("Email"),
                        rs.getString("SDT"),
                        rs.getString("Gioi_Tinh"),
                        rs.getString("doiTuong"),
                        rs.getDate("NgayDangKy")
                };
                // Thêm dòng dữ liệu vào bảng
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ cơ sở dữ liệu!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // Lấy tên cột
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }

        // Lấy dữ liệu hàng
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }
        return model;
    }



    public static void main(String[] args) {

        new kich_Hoat_The();
    }
    }

