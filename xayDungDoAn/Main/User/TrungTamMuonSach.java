package DoAncuoiki1.xayDungDoAn.Main.User;
import DoAncuoiki1.xayDungDoAn.Main.Admin.Menu.quan_Ly_The;
import DoAncuoiki1.xayDungDoAn.Main.Login_User;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrungTamMuonSach extends JFrame {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=false;trustServerCertificate=true";
    private static final String USER = "quocdat233";
    private static final String PASS = "dat23326";

    private  JMenu mn;
    private JMenuBar mnb;
    private JTextField txt1;
    private JScrollPane sc1,sc2;
    private JTable table1, table2;
    private JButton btn1, btn2,btn3;
    private JMenuItem menuItem1,menuItem3,menuitem4;
    private DefaultTableModel modelDanhSach, modelDangKy;
    private JComboBox<String> dayCombo, monthCombo, yearCombo;

    public TrungTamMuonSach(String Ma_SinhVien) {
        super("Trung Tâm Mượn Sách");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(910, 680);
        setLocationRelativeTo(null);
        setLayout(null);
        Object[] col = {"Mã sách","Tên sách","Tác giả","Nhà xuất bản","Thể loại","Giá Tiền","Số lượng"};





        // Thay đổi trong phần tạo menu
        mnb = new JMenuBar();
        mn = new JMenu("Tài khoản");
        mnb.add(mn);

        // Tạo menu item cho "Đăng xuất"
        menuItem1 = new JMenuItem("Mượn sách");
        menuItem3 = new JMenuItem("Đăng xuất");
        menuitem4 = new JMenuItem("Thông tin");


        //CHỨC NĂNG


        menuitem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new thongTin();
            }});

        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                    new Login_User();
            }});


        JPanel pnTimKiem = new JPanel(null);
        TitledBorder tt = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2), "Tìm kiếm  ");
        pnTimKiem.setBorder(tt);
        pnTimKiem.setBounds(20, 33, 400, 160);


        JLabel lblnote = new JLabel("*Nhập kí tự cần tìm");
        JButton btnLoad = new JButton("Load bảng");
        JLabel lbl1 = new JLabel("Thông tin:");
        btn1 = new JButton("Tìm kiếm");
        txt1 = new JTextField();

        lbl1.setBounds(20, 30, 120, 25);

        lblnote.setForeground(Color.red);
        lblnote.setFont(new Font("Arial", Font.PLAIN, 11));
        lblnote.setBounds(120, 57, 150, 20);

        btn1.setBounds(120, 100, 100, 25);
        btn1.setFocusPainted(false);
        btn1.setBackground(Color.LIGHT_GRAY);

        txt1.setBounds(120, 30, 250, 25);
        txt1.setBackground(Color.WHITE);

        btnLoad.setBounds(245,100, 100,25);
        btnLoad.setFont(new Font("Arial", Font.BOLD, 12));
        btnLoad.setFocusPainted(false);

        //CHỨC NĂNG

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelDanhSach.setRowCount(0);
              loadtable();
            }
        });


        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyWord = "%" + txt1.getText().trim() + "%";
                if(keyWord.equals("%%")){
                    JOptionPane.showMessageDialog(TrungTamMuonSach.this,"Vui lòng nhập thông tin ");
                }
                else {
                    String query = "SELECT * FROM SACH WHERE So_Luong LIKE ? OR  Ma_Sach LIKE ? OR  Ten_Sach LIKE ? OR Tac_Gia LIKE ? OR Nha_Xuat_Ban LIKE ? OR The_Loai LIKE ? OR  Gia_Tien LIKE ?";
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
                        modelDanhSach.setRowCount(0);

                        while (rs.next()) {
                            Object[] row = {
                                    rs.getString("Ma_Sach"),
                                    rs.getString("Ten_Sach"),
                                    rs.getString("Tac_Gia"),
                                    rs.getString("Nha_Xuat_Ban"),
                                    rs.getString("The_Loai"),
                                    rs.getString("Gia_Tien"),
                                    rs.getString("So_luong")
                            };
                            modelDanhSach.addRow(row); // Thêm dòng vào bảng
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(TrungTamMuonSach.this, "Lỗi khi tìm kiếm dữ liệu!");
                    }}}});

        mn.add(menuItem1);
        mn.add(menuitem4);
        mn.addSeparator();
        mn.add(menuItem3);
        setJMenuBar(mnb);
        pnTimKiem.add(lblnote);
        pnTimKiem.add(txt1);
        pnTimKiem.add(btn1);
        pnTimKiem.add(btnLoad);
        pnTimKiem.add(lbl1);


        // Panel Danh Sách Sách
        JPanel pnDanhSach = new JPanel(new BorderLayout());
        TitledBorder borderDanhSachSach = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,2), "Danh sách sách ");
        pnDanhSach.setBorder(borderDanhSachSach);
        pnDanhSach.setBounds(18, 215, 864, 390);

        modelDanhSach = new DefaultTableModel(col,0);
        table1 = new JTable(modelDanhSach);
        sc1 = new JScrollPane(table1);
        pnDanhSach.add(sc1,BorderLayout.CENTER);
        table1.setDefaultEditor(Object.class, null);




        // Panel Giỏ Đăng Ký
        JPanel pnDangKy = new JPanel(new BorderLayout());
        TitledBorder tt3 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,2), " Giỏ đăng ký sách  ");
        pnDangKy.setBorder(tt3);
        pnDangKy.setBounds(450, 10, 420, 190);

        modelDangKy = new DefaultTableModel(new Object[]{"Mã sách", "Tên sách","Giá tiền"},0);
        table2 = new JTable(modelDangKy);
        sc2 = new JScrollPane(table2);
        pnDangKy.add(sc2, BorderLayout.CENTER);

        //PANEL NUT DANG KI

        JPanel pn4 = new JPanel();

       JLabel lbl4 = new JLabel("Ngày hết hạn:");
        pn4.add(lbl4);
        lbl4.setBounds(10,10,50,25);

        List<String> days = new ArrayList<>();
        for (int i = 1; i <= 31; i++)
            days.add(String.valueOf(i));

        ArrayList<String> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++)
            months.add(String.valueOf(i));


        ArrayList<String> years = new ArrayList<>();
        for (int i = 2025; i < 2050; i++)
            years.add(String.valueOf(i));


        // Sửa từ khai báo cục bộ sang gán giá trị cho biến toàn cục
        dayCombo = new JComboBox<>(days.toArray(new String[0]));
        dayCombo.setBounds(15, 175, 40, 20);

        pn4.add(dayCombo);

        monthCombo = new JComboBox<>(months.toArray(new String[0]));
        monthCombo.setBounds(197, 175, 50, 20);

        pn4.add(monthCombo);

        yearCombo = new JComboBox<>(years.toArray(new String[0]));
        yearCombo.setBounds(260, 175, 60, 20);

        pn4.add(yearCombo);



        JLabel lbl5 = new JLabel(" ");
        pn4.add(lbl5);


        btn2 = new JButton("Đăng ký");
        btn2.setFocusPainted(false);

        pn4.add(btn2);
        btn2.setBounds(110, 10, 80, 25);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              try{
                  int day = Integer.parseInt((String) dayCombo.getSelectedItem());
                  int month = Integer.parseInt((String) monthCombo.getSelectedItem());
                  int year = Integer.parseInt((String) yearCombo.getSelectedItem());

                  // Kiểm tra ngày hợp lệ bằng LocalDate
                  LocalDate selectedDate;
                  try{
                  selectedDate = LocalDate.of(year, month, day);}
                  catch (Exception ex){
                      JOptionPane.showMessageDialog(TrungTamMuonSach.this,"LỖI !" );
                      return;
                  }
                  int selectedRow = table1.getSelectedRow();
                  if(selectedRow==-1){
                      JOptionPane.showMessageDialog(TrungTamMuonSach.this, "Chọn sách cần mượn");
                      return;
                  }

                  // Nếu ngày hết hạn nhỏ hơn ngày hiện tại, hiển thị thông báo lỗi
                  LocalDate today = LocalDate.now();
                  if (selectedDate.isAfter(today.plusMonths(3)) || selectedDate.isBefore(today)) {
                      JOptionPane.showMessageDialog(TrungTamMuonSach.this, "Ngày hết hạn không hợp lệ! (phải trong vòng 3 tháng kể từ hôm nay)");
                      return;
                  }


                  if (selectedRow != -1) {
                      String maSach = (String) table1.getValueAt(selectedRow, 0);
                      String tenSach = (String) table1.getValueAt(selectedRow, 1);
                      String tacGia = (String) table1.getValueAt(selectedRow, 2);
                      String nhaXuatBan = (String) table1.getValueAt(selectedRow, 3);
                      String theLoai = (String) table1.getValueAt(selectedRow, 4);
                      String giaTien = (String) table1.getValueAt(selectedRow, 5);


                      // Kiểm tra số lượng sách
                      String queryCheckSoLuong = "SELECT So_Luong FROM Sach WHERE Ma_Sach = ?";
                      try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                           PreparedStatement stmt = conn.prepareStatement(queryCheckSoLuong)) {

                          stmt.setString(1, maSach);
                          ResultSet rs = stmt.executeQuery();

                          if (rs.next()) {
                              int soLuongHienTai = rs.getInt("So_Luong");
                              if (soLuongHienTai <= 0) {
                                  JOptionPane.showMessageDialog(TrungTamMuonSach.this, "Sách đã hết!");
                                  return;
                              }
                          }
                      }


                      // Kiểm tra xem độc giả có đã mượn quá 3 cuốn sách trong tuần chưa
                      String queryCheck = "SELECT COUNT(*) FROM sachChoDangKi WHERE Ma_SinhVien = ?";
                      try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                           PreparedStatement stmt = conn.prepareStatement(queryCheck)) {

                          stmt.setString(1, Ma_SinhVien);

                          ResultSet rs = stmt.executeQuery();
                          if (rs.next()) {
                              int count = rs.getInt(1);
                              if (count >= 3) {
                                  JOptionPane.showMessageDialog(TrungTamMuonSach.this, "Bạn chỉ được mượn tối đa 3 cuốn sách trong 1 tuần.");
                                  return;
                              }
                          }
                      }
//stmt.setDate(6, java.sql.Date.valueOf(selectedDate));
                      // Nếu không có vấn đề gì, tiến hành đăng ký
                      String queryInsert = "INSERT INTO sachChoDangKi(Ma_Sach, Ten_Sach, Tac_Gia, Nha_Xuat_Ban, The_Loai,  Ngay_Het_Han,Gia_Tien, Ma_SinhVien,ngay_muon) VALUES (?, ?, ?,?, ?, ?, ?,?, ?)";
                      try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                           PreparedStatement stmt = conn.prepareStatement(queryInsert)) {

                          stmt.setString(1, maSach);
                          stmt.setString(2, tenSach);
                          stmt.setString(3, tacGia);
                          stmt.setString(4, nhaXuatBan);
                          stmt.setString(5, theLoai);
                          stmt.setDate(6, java.sql.Date.valueOf(selectedDate));
                          stmt.setString(7, giaTien);

                          stmt.setString(8,Ma_SinhVien);
                          stmt.setDate(9, java.sql.Date.valueOf(today));  // Thêm ngày mượn vào đây

                          stmt.executeUpdate();

                          // Thêm sách vào giỏ đăng ký
                          modelDangKy.addRow(new Object[]{maSach, tenSach,giaTien});

                          // Cập nhật số lượng sách
                          String queryUpdateSoLuong = "UPDATE Sach SET So_Luong = So_Luong - 1 WHERE Ma_Sach = ?";
                          try (PreparedStatement updateStmt = conn.prepareStatement(queryUpdateSoLuong)) {
                              updateStmt.setString(1, maSach);
                              updateStmt.executeUpdate();
                          }
                          loadtable();

                      }
                      // Nếu không có vấn đề gì, tiến hành đăng ký
                      String queryInsert2 = "INSERT INTO Thongke(Ma_Sach, Ten_Sach, Tac_Gia, Nha_Xuat_Ban, The_Loai,Gia_Tien, Ma_SinhVien,ngay_muon) VALUES ( ?, ?,?, ?, ?, ?,?, ?)";
                      try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                           PreparedStatement stmt = conn.prepareStatement(queryInsert2)) {

                          stmt.setString(1, maSach);
                          stmt.setString(2, tenSach);
                          stmt.setString(3, tacGia);
                          stmt.setString(4, nhaXuatBan);
                          stmt.setString(5, theLoai);
                          stmt.setString(6, giaTien);

                          stmt.setString(7,Ma_SinhVien);
                          stmt.setDate(8, java.sql.Date.valueOf(today));  // Thêm ngày mượn vào đây

                          stmt.executeUpdate();


                          // Cập nhật số lượng sách



                      }


                  }

                }
              catch (Exception ex){
                  ex.printStackTrace();
              }



            }

        });


        btn3  = new JButton(" Xoá ");
        btn3.setFocusPainted(false);

        pn4.add(btn3);
        btn3.setBounds(10,10,10,10);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table2.getSelectedRow();
                if (selectedRow != -1) {
                    String maSach = (String) table2.getValueAt(selectedRow, 0);

                    // Xóa sách khỏi bảng sachChoXacNhan
                    String queryDelete = "DELETE FROM sachChoDangKi WHERE Ma_Sach = ?";
                    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                         PreparedStatement stmt = conn.prepareStatement(queryDelete)) {

                        stmt.setString(1, maSach);
                        stmt.executeUpdate();

                        // Xóa sách khỏi giỏ đăng ký
                        modelDangKy.removeRow(selectedRow);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(TrungTamMuonSach.this, "Lỗi khi xóa sách.");
                    }
                } else {
                    JOptionPane.showMessageDialog(TrungTamMuonSach.this, "Hãy chọn sách cần xóa.");
                }
            }
        });





        // Thêm các panel vào giao diện
        pnDangKy.add(pn4,BorderLayout.SOUTH);
        add(pnTimKiem);
        add(pnDanhSach);
        add(pnDangKy);
        loadtableSachDamuon();
        loadtable();
        setVisible(true);


        //CHỈNH MÀUUUU
        pnDangKy.setBackground(new Color(74, 136, 136));
        pnDanhSach.setBackground(new Color(74, 136, 136));
        pnTimKiem.setBackground(new Color(245, 255, 240));
        getContentPane().setBackground(new Color(74, 136, 136));
        table1.setBackground(new Color(243, 250, 243));
        table2.setBackground(new Color(243, 250, 243));
        btnLoad.setBackground(new Color(203, 194, 189));
        btn1.setBackground(new Color(203, 194, 189));
        btn2.setBackground(new Color(203, 194, 189));
        btn3.setBackground(new Color(203, 194, 189));
        dayCombo.setBackground(new Color(203, 194, 189));
        monthCombo.setBackground(new Color(203, 194, 189));
        yearCombo.setBackground(new Color(203, 194, 189));


    }
     public  void loadtable(){
        modelDanhSach.setRowCount(0);
        String query = "SELECT Ma_Sach, Ten_Sach, Tac_Gia, Nha_Xuat_Ban, The_Loai,Gia_Tien,So_Luong FROM Sach";
        try(Connection conn= DriverManager.getConnection(URL,USER,PASS);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query)){
            if(conn == null)
                JOptionPane.showMessageDialog(TrungTamMuonSach.this, "Ket noi sql ko  thanh cong");

            while (resultSet.next()){
                Object [] row ={
                        resultSet.getString("Ma_Sach"),
                        resultSet.getString("Ten_Sach"),
                        resultSet.getString("Tac_Gia"),
                        resultSet.getString("Nha_Xuat_Ban"),
                        resultSet.getString("The_Loai"),
                        resultSet.getString("Gia_Tien"),
                        resultSet.getString("So_Luong")
                };
                modelDanhSach.addRow(row);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(TrungTamMuonSach.this, "Lỗi khi truyền dữ liệu !");
        }
    }

    public void loadtableSachDamuon() {
        modelDangKy.setRowCount(0);  // Xóa tất cả các hàng cũ trong bảng

        // Câu lệnh SQL với tham số Ma_SinhVien
        String query = "SELECT Ma_Sach, Ten_Sach,Gia_Tien FROM SachChoDangKi WHERE Ma_SinhVien = ?";
        String maSinhVien = UserSession.getMaSinhVien();  // Lấy mã sinh viên từ session

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Gán giá trị cho tham số trong câu lệnh SQL
            stmt.setString(1, maSinhVien);

            // Thực thi truy vấn
            ResultSet resultSet = stmt.executeQuery();

            // Kiểm tra nếu kết nối không thành công
            if (conn == null) {
                JOptionPane.showMessageDialog(TrungTamMuonSach.this, "Kết nối SQL không thành công");
                return;
            }

            // Duyệt qua kết quả và thêm vào bảng
            while (resultSet.next()) {

                Object[] row = {
                        resultSet.getString("Ma_Sach"),  // Mã sách
                        resultSet.getString("Ten_Sach"),  // Tên sách
                        resultSet.getString("Gia_Tien")  // Tên sách
                };
                modelDangKy.addRow(row);  // Thêm một dòng vào bảng
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(TrungTamMuonSach.this, "Lỗi khi truy vấn dữ liệu!");
        }
    }


    public static void main(String[] args) {
        new TrungTamMuonSach("MSV144");


    }














}
