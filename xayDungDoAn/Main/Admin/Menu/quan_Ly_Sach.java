package DoAncuoiki1.xayDungDoAn.Main.Admin.Menu;

import DoAncuoiki1.xayDungDoAn.Main.Login_Admin;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class quan_Ly_Sach extends JFrame {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=false;trustServerCertificate=true;useUnicode=true;characterEncoding=UTF-8";
    private static final String USER = "quocdat233";
    private static final String PASS = "dat23326";
    // Khai báo các thành phần giao diện dưới dạng private
    private JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lblTongSoSach,lblTong,lbl9,lbl10;
    private JTextField txt1, txt2, txt3, txt4, txt5, txt6,txt7,txt8;
    private JButton btn1,btn2,btn3,btn4;
    private JTable table,table1;
    private JPanel pn1,pn2,pn3,pnTimKiem;
    private JScrollPane scrollPane,scrollPane1;
    private DefaultTableModel model,model1 ;
    private JMenuBar menuBar;
    private JMenuItem menuItem1,itemQuanLySach,itemQuanLythe,itemKichHoatThe,itemThongKe1,itemThongKe2,itemSachQuaHan;
    private JMenu menu ;
    private Connection connection;

    public quan_Ly_Sach() {


        // Cài đặt giao diện cửa sổ chính
        setTitle("Quản lý sách");
        setSize(1280,680);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Căn giữa màn hình

        // Phần thông tin sách
        pn1 = new JPanel(null);


        lbl1 = new JLabel("Mã sách");
        lbl1.setBounds(30, 30, 80, 25);
        lbl2 = new JLabel("Tên sách");
        lbl2.setBounds(320, 30, 80, 25);
        lbl3= new JLabel("Giá Tiền");
        lbl3.setBounds(30, 70, 100, 25);
        lbl4 = new JLabel("Tác giả");
        lbl4.setBounds(320, 70, 80, 25);
        lbl5 = new JLabel("Nhà xuất bản");
        lbl5.setBounds(30, 110, 100, 25);
        lbl6 = new JLabel("Thể loại");
        lbl6.setBounds(320, 110, 80, 25);
        lbl8 = new JLabel("Số lượng");
        lbl8.setBounds(30, 150, 80, 25);

        txt1= new JTextField();
        txt1.setBounds(120, 30, 150, 22);
        txt2 = new JTextField();
        txt2.setBounds(400, 30, 150, 22);
        txt3 = new JTextField();
        txt3.setBounds(120, 70, 150, 22);
        txt4 = new JTextField();
        txt4.setBounds(400, 70, 150, 22);
        txt5 = new JTextField();
        txt5.setBounds(120, 110, 150, 22);
        txt6 = new JTextField();
        txt6.setBounds(400, 110, 150, 22);
        txt7 = new JTextField();
        txt7.setBounds(120, 150, 150, 22);

        btn1 = new JButton("Thêm sách");
        btn1.setBounds(365, 147, 100, 25);
        btn1.setFocusPainted(false);
        btn1.setBackground(Color.LIGHT_GRAY);

        pn1.add(lbl1);
        pn1.add(lbl2);
        pn1.add(lbl3);
        pn1.add(lbl5);
        pn1.add(lbl4);
        pn1.add(lbl6);
        pn1.add(lbl8);

        pn1.add(txt1);
        pn1.add(txt2);
        pn1.add(txt3);
        pn1.add(txt4);
        pn1.add(txt5);
        pn1.add(txt6);
        pn1.add(txt7);
        pn1.add(btn1);


        pn1.setBounds(15, 10, 600, 185);
        TitledBorder tt1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2)," Điền Thông tin  ");
        pn1.setBorder(tt1);
        add(pn1);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ các trường nhập liệu
                String maSach = txt1.getText().trim();
                String tenSach = txt2.getText().trim();
                String giaTienStr = txt3.getText().trim();
                String tacGia = txt4.getText().trim();
                String nhaXuatBan = txt5.getText().trim();
                String theLoai = txt6.getText().trim();
                String soluong  = txt7.getText().trim();

                int soluongg =0;


                double giaTien = 0;

                // Kiểm tra dữ liệu đầu vào
                if(checkSachExists(maSach)){
                    if(soluong.isEmpty()){
                        JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Vui lòng nhập số lượng");
                        return;

                    }
                    else {
                        try {
                            soluongg =Integer.parseInt(soluong);


                            if (soluongg<=0){
                                JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Số lượng phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);return;}
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Số lượng không hợp lệ");return;}
                    }

                    // Nếu sách tồn tại, cộng thêm số lượng vào số lượng hiện tại
                    updateSoluongSach(maSach, soluongg);
                    return; // Không cần thêm sách mới
                }
                if (maSach.isEmpty() || tenSach.isEmpty() || giaTienStr.isEmpty() || tacGia.isEmpty() ||soluong.isEmpty()||
                        nhaXuatBan.isEmpty() || theLoai.isEmpty()) {
                    JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Vui lòng điền đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                 if(!checkMaSach(maSach)){
                    JOptionPane.showMessageDialog(quan_Ly_Sach.this, "xem lại mã sách");
                    return;
                }

                 if(!all(tenSach)||!all(tacGia)||!all(nhaXuatBan)||!all(theLoai)){
                    JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Kiểm tra lại thông tin");
                    return;
                }

                    try {

                        giaTien = Double.parseDouble(giaTienStr);
                        if (giaTien <= 0) {
                            JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Giá tiền phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Giá tiền không hợp lệ");return;}

                try {
                    soluongg =Integer.parseInt(soluong);


                    if (soluongg<=0){
                        JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Số lượng phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);return;}
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Số lượng không hợp lệ");return;}







                    // Thêm sách vào cơ sở dữ liệu
                    String insertQuery = "INSERT INTO Sach (Ma_Sach, Ten_Sach, Gia_Tien, Tac_Gia, Nha_Xuat_Ban, The_Loai,So_Luong) VALUES (?, ?, ?, ?, ?,?, ?)";
                    try (Connection con = DriverManager.getConnection(URL, USER, PASS);
                         PreparedStatement pstmt = con.prepareStatement(insertQuery)) {

                        pstmt.setString(1, maSach);
                        pstmt.setString(2, tenSach);
                        pstmt.setDouble(3, giaTien);
                        pstmt.setString(4, tacGia);
                        pstmt.setString(5, nhaXuatBan);
                        pstmt.setString(6, theLoai);
                        pstmt.setInt(7,soluongg );

                        int rowsInserted = pstmt.executeUpdate();

                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Thêm sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                            // Thêm sách vào bảng hiển thị
                            model.addRow(new Object[]{maSach, tenSach, tacGia, nhaXuatBan, theLoai, giaTien});

                            // Xóa dữ liệu nhập
                            txt1.setText("");
                            txt2.setText("");
                            txt3.setText("");
                            txt4.setText("");
                            txt5.setText("");
                            txt6.setText("");
                            txt7.setText("");
                            model.setRowCount(0);
                            loadSachTable();
                            countTotalBooks();
                        } else {
                            JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Thêm sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Lỗi");
                    }






            }});



        // Tạo JPanel Hiện có
        pn2 = new JPanel();
        pn2.setLayout(new BorderLayout());
        pn2.setBounds(650, 230, 610, 380);
        add(pn2);
        TitledBorder tt2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,2)," Hiện có  ");
        pn2.setBorder(tt2);
        String[] col = {"Mã sách", "Tên sách", "Tác giả", "Nhà xuất bản", "Thể loại", "Giá tiền","Số Lượng"};
        model = new DefaultTableModel(col,0);
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        scrollPane = new JScrollPane(table);
        pn2.add(scrollPane, BorderLayout.CENTER);
        loadSachTable();
        countTotalBooks();
        add(pn2);


        lblTongSoSach = new JLabel("Tổng số sách:");
        lblTongSoSach.setForeground(Color.WHITE);
        lblTongSoSach.setFont(new Font("Arial", Font.BOLD, 12));
        lblTongSoSach.setBounds(17, 205, 100, 20);
        add(lblTongSoSach);


        //JPanel Mượn
        pn3 = new JPanel();
        pn3.setLayout(new BorderLayout());
        pn3.setBounds(15,230, 600, 380);
        TitledBorder tt3 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,2)," Đã mượn ");
        pn3.setBorder(tt3);
        String[] col1 = {"Mã sách", "Tên sách", "Tác giả", "Nhà xuất bản", "Thể loại","Ngày hết hạn", "Giá tiền","Mã sinh viên"};

        model1 = new DefaultTableModel(col1,0);
        table1 = new JTable(model1);
        scrollPane1 = new JScrollPane(table1);
        pn3.add(scrollPane1, BorderLayout.CENTER);
        loadSachChoDangKiTable();

        add(pn3);

        //dsnjsbfbhjdfsd

        lbl9 = new JLabel("Thông tin tìm kiếm");
        lbl9.setBounds(30, 60, 120, 20);

        lbl10 = new JLabel("*Nhập kí tự cần tìm");
        lbl10.setForeground(Color.red);
        lbl10.setFont(new Font("Arial", Font.PLAIN, 11));
        lbl10.setBounds(160, 87, 100, 20);

        txt8 = new JTextField();
        txt8.setBounds(160,58,180,23);

        btn2 = new JButton("Tìm kiếm");
        btn2.setBounds(145,135,90,25);
        btn2.setBackground(Color.LIGHT_GRAY);
        btn2.setFont(new Font("Arial", Font.BOLD, 12));
        btn2.setFocusPainted(false);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyWord = "%" + txt8.getText().trim() + "%";
                if(keyWord.equals("%%")){
                    JOptionPane.showMessageDialog(quan_Ly_Sach.this,"Vui lòng nhập thông tin ");
                }
                else
                {
                    String query = "SELECT * FROM sach WHERE Ma_Sach Like ? OR Ten_Sach LIKE ? OR  Tac_Gia LIKE ? OR Nha_Xuat_Ban LIKE ? OR The_Loai LIKE ? OR Gia_Tien LIKE ? OR  So_Luong LIKE ? ";
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
                                    rs.getString("Ma_Sach"),
                                    rs.getString("Ten_Sach"),
                                    rs.getString("Tac_Gia"),
                                    rs.getString("Nha_Xuat_Ban"),
                                    rs.getString("The_Loai"),
                                    rs.getDouble("Gia_Tien"),
                                    rs.getInt("So_Luong"),


                            };
                            model.addRow(row); // Thêm dòng vào bảng
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Lỗi khi tìm kiếm dữ liệu!");
                    }





                }

            }
        });


        btn4 = new JButton("Xóa sách");
        btn4.setBounds(480, 147, 100, 25);
        btn4.setFont(new Font("Arial", Font.BOLD, 12));
        btn4.setBackground(Color.LIGHT_GRAY);
        btn4.setFocusPainted(false);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txt1.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Vui lòng nhập id cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                else
                deleteData();

            }
        });
        pn1.add(btn4);

        btn3 = new JButton("Load Bảng");
        btn3.setBounds(250,135, 100,25);
        btn3.setFont(new Font("Arial", Font.BOLD, 12));
        btn3.setBackground(Color.LIGHT_GRAY);
        btn3.setBackground(Color.LIGHT_GRAY);
        btn3.setFocusPainted(false);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                loadSachTable();
            }
        });


        pnTimKiem = new JPanel(null){
            @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon backgroundImage = new ImageIcon("C:\\HOCTAP\\JAVA\\Quan_Ly_Thu_Vien\\src\\DoAncuoiki1\\xayDungDoAn\\image\\Remove-bg.ai_1736649911034.png");
            g.drawImage(backgroundImage.getImage(), 390, 0, 210, 210, this);
        }};


        TitledBorder titledBorder1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2)," Thông tin tìm kiếm  ");
        pnTimKiem.setBorder(titledBorder1);
        pnTimKiem.setBounds(650, 10, 610, 200);
        pnTimKiem.add(lbl9);
        pnTimKiem.add(lbl10);
        pnTimKiem.add(txt8);
        pnTimKiem.add(btn2);
        pnTimKiem.add(btn3);

        add(pnTimKiem);





        lbl7 = new JLabel("");
        lbl7.setBounds(632,11,2,602);
        lbl7.setBorder(new LineBorder(Color.WHITE,2));
        add(lbl7);

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

        itemQuanLySach = new JMenu("Quản lý sách");
        itemQuanLySach.setFont(new Font("Arial", Font.PLAIN, 18));
        itemQuanLySach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        itemSachQuaHan = new JMenuItem("Sách quá hạn");
        itemSachQuaHan.setFont(new Font("Arial", Font.PLAIN, 12));
        itemQuanLySach.add(itemSachQuaHan);
        itemSachQuaHan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new quan_Ly_MuonTra();
            }
        });

        itemKichHoatThe = new JMenuItem("Kích hoạt thẻ");
        itemKichHoatThe.setFont(new Font("Arial", Font.PLAIN, 18));
        itemKichHoatThe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();new kich_Hoat_The();
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
        setJMenuBar(menuBar);
        setVisible(true);

        //MÀU
        getContentPane().setBackground(new Color(74, 136, 136));
        pnTimKiem.setBackground(new Color(245, 255, 240));
        pn1.setBackground(new Color(245, 255, 240));
        pn3.setBackground(new Color(74, 136, 136));
        pn2.setBackground(new Color(74, 136, 136));
        table1.setBackground(new Color(243, 250, 243));
        table.setBackground(new Color(243, 250, 243));
        btn1.setBackground(new Color(203, 194, 189));
        btn4.setBackground(new Color(203, 194, 189));
        btn2.setBackground(new Color(203, 194, 189));
        btn3.setBackground(new Color(203, 194, 189));

    }

    public boolean checkMaSach(String str){
        boolean check = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!(c >= '0' && c <= '9') && !(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
                check = false;
            }
        }
        return  check;
    }

    public boolean all(String str){
        boolean check = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(!(Character.isLetter(c) || c== ' '))

                check = false;

        }
        return  check;
    }

    private void loadSachTable() {
        String query = "SELECT * FROM Sach";
        try (Connection con = DriverManager.getConnection(URL,USER,PASS);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query)){

            while (rs.next()) {
                  Object[] row ={
                        rs.getString("Ma_Sach"),
                        rs.getString("Ten_Sach"),
                        rs.getString("Tac_Gia"),
                        rs.getString("Nha_Xuat_Ban"),
                        rs.getString("The_Loai"),
                        rs.getDouble("Gia_Tien"),
                        rs.getInt("So_Luong")
                };
                model.addRow(row);
            }
            countTotalBooks();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Lỗi !");
        }
    }

    private void loadSachChoDangKiTable() {
        String query = "SELECT * FROM sachChoDangKi";
        try ( Connection conn = DriverManager.getConnection(URL,USER,PASS);
                Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(query);){

            while (rs.next()) {
                model1.addRow(new Object[]{
                        rs.getString("Ma_Sach"),
                        rs.getString("Ten_Sach"),
                        rs.getString("Tac_Gia"),
                        rs.getString("Nha_Xuat_Ban"),
                        rs.getString("The_Loai"),
                        rs.getDate("Ngay_Het_Han"),
                        rs.getDouble("Gia_Tien"),
                        rs.getString("Ma_SinhVien")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Lỗi !");
        }
    }

    public boolean checkSachExists(String maSach) {
        // Chuỗi truy vấn SQL để kiểm tra sự tồn tại của sách theo mã sách
        String query = "SELECT COUNT(*) FROM Sach WHERE Ma_Sach = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            // Gán giá trị cho tham số truy vấn
            pstmt.setString(1, maSach);

            // Thực thi truy vấn và lấy kết quả
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);  // Lấy số lượng sách có mã tương ứng
                    return count > 0;  // Nếu số lượng lớn hơn 0 thì sách đã tồn tại
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra sách: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return false;  // Trả về false nếu có lỗi hoặc sách không tồn tại
    }

    public void updateSoluongSach(String maSach, int soluong) {
        // Truy vấn SQL để lấy số lượng hiện tại của sách
        String selectQuery = "SELECT So_Luong FROM Sach WHERE Ma_Sach = ?";
        String updateQuery = "UPDATE Sach SET So_Luong = ? WHERE Ma_Sach = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmtSelect = con.prepareStatement(selectQuery);
             PreparedStatement pstmtUpdate = con.prepareStatement(updateQuery)) {

            // Lấy số lượng sách hiện tại
            pstmtSelect.setString(1, maSach);
            try (ResultSet rs = pstmtSelect.executeQuery()) {
                if (rs.next()) {
                    int currentSoluong = rs.getInt("So_Luong");
                    int newSoluong = currentSoluong + soluong;

                    // Cập nhật số lượng mới vào cơ sở dữ liệu
                    pstmtUpdate.setInt(1, newSoluong);
                    pstmtUpdate.setString(2, maSach);
                    int rowsUpdated = pstmtUpdate.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Cập nhật số lượng sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        // Cập nhật lại bảng sách
                        model.setRowCount(0);  // Xóa tất cả các hàng trong bảng
                        loadSachTable();  // Tải lại bảng
                        countTotalBooks();
                    } else {
                        JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Cập nhật số lượng sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Lỗi khi cập nhật số lượng sách: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void countTotalBooks() {
        lblTong = new JLabel("fff");
        lblTong.setForeground(Color.WHITE);
        lblTong.setFont(new Font("Arial", Font.BOLD, 12));
        lblTong.setBounds(105, 205, 100, 20);
        add(lblTong);
        String query = "SELECT SUM(So_Luong) AS TotalBooks FROM Sach";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                int totalBooks = rs.getInt("TotalBooks");
                lblTong.setText(String.valueOf(totalBooks)); // Cập nhật nhãn lblTong
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(quan_Ly_Sach.this, "Lỗi khi đếm tổng số sách: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteData() {
        String maSach = txt1.getText();
        String query = "DELETE FROM Sach WHERE Ma_Sach = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, maSach);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Xoá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                model.setRowCount(0); // Xóa toàn bộ dữ liệu hiện tại
                loadSachTable();  // Tải lại dữ liệu
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sách để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi kết nối cơ sở dữ liệu!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }






    public static void main(String[] args) {
        new quan_Ly_Sach(); // Khởi chạy chương trình
    }
}
