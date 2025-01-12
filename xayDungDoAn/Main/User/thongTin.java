package DoAncuoiki1.xayDungDoAn.Main.User;

import DoAncuoiki1.xayDungDoAn.Main.Admin.Menu.quan_Ly_Sach;
import DoAncuoiki1.xayDungDoAn.Main.Login_User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class thongTin extends JFrame {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=false;trustServerCertificate=true";
    private static final String USER = "quocdat233";
    private static final String PASS = "dat23326";

    private JTable table1 ;
    private JScrollPane sc1;
    private JMenuBar menuBar;
    private JMenu menuTaiKhoan;
    private JPanel panelThongTin;
    private DefaultTableModel modelDanhSach;
    private JMenuItem menuMuonSach, menuDangXuat,menuThongTin;
    private JLabel lblMaSinhVien, lblTenSinhVien, lblNgayDangKy, lblNgayHetHan, lblEmail, lblGioiTinh, txtMaSinhVien, txtTenSinhVien, txtNgayDangKy, txtNgayHetHan, txtEmail, txtGioiTinh;


    public thongTin() {
        super("Thông tin");
        setSize(940, 430);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Object[] col = {"Mã sách","Tên sách","Tác giả","Ngày hết hạn","Thể loại","Giá Tiền"};

        // Tạo các label
        lblMaSinhVien = new JLabel("Mã sinh viên ");
        lblMaSinhVien.setBounds(30, 40, 100, 20);
        lblTenSinhVien = new JLabel("Tên sinh viên ");
        lblTenSinhVien.setBounds(30, 85, 100, 20);
        lblNgayDangKy = new JLabel("Ngày đăng ký ");
        lblNgayDangKy.setBounds(30, 130, 100, 20);
        lblNgayHetHan = new JLabel("Ngày hết hạn ");
        lblNgayHetHan.setBounds(30, 175, 100, 20);
        lblEmail = new JLabel("Email    ");
        lblEmail.setBounds(30, 215, 100, 20);
        lblGioiTinh = new JLabel("Giới tính  ");
        lblGioiTinh.setBounds(30, 255, 100, 20);

        // Tạo các text field
        txtMaSinhVien = new JLabel();
        txtMaSinhVien.setBounds(150, 40, 160, 20);
        txtMaSinhVien.setForeground(new Color(255, 102, 102));



        txtTenSinhVien = new JLabel();
        txtTenSinhVien.setBounds(150, 85, 160, 20);
        txtTenSinhVien.setForeground(new Color(255, 102, 102));
        txtTenSinhVien.setBackground(Color.WHITE);

        txtNgayDangKy = new JLabel();
        txtNgayDangKy.setBounds(150, 130, 160, 20);
        txtNgayDangKy.setForeground(new Color(255, 102, 102));
        txtNgayDangKy.setBackground(Color.WHITE);

        txtNgayHetHan = new JLabel();
        txtNgayHetHan.setBounds(150, 175, 160, 20);
        txtNgayHetHan.setForeground(new Color(255, 102, 102));
        txtNgayHetHan.setBackground(Color.WHITE);

        txtEmail = new JLabel();
        txtEmail.setBounds(150, 215, 160, 20);
        txtEmail.setForeground(new Color(255, 102, 102));
        txtEmail.setBackground(Color.WHITE);

        txtGioiTinh = new JLabel();
        txtGioiTinh.setBounds(150, 255, 160, 20);
        txtGioiTinh.setForeground(new Color(255, 102, 102));
        txtGioiTinh.setBackground(Color.WHITE);
        loadThongTinSinhVien();

        // Tạo panel hiển thị thông tin
        panelThongTin = new JPanel();
        panelThongTin.setLayout(null);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Thông tin thẻ  ");
        panelThongTin.setBorder(titledBorder);
        panelThongTin.setBounds(25, 10, 340, 320);

        panelThongTin.add(lblMaSinhVien);
        panelThongTin.add(txtMaSinhVien);
        panelThongTin.add(lblTenSinhVien);
        panelThongTin.add(txtTenSinhVien);
        panelThongTin.add(lblNgayDangKy);
        panelThongTin.add(txtNgayDangKy);
        panelThongTin.add(lblNgayHetHan);
        panelThongTin.add(txtNgayHetHan);
        panelThongTin.add(lblEmail);
        panelThongTin.add(txtEmail);
        panelThongTin.add(lblGioiTinh);
        panelThongTin.add(txtGioiTinh);

        add(panelThongTin);

        // Tạo menu bar và các menu
        menuBar = new JMenuBar();
        menuTaiKhoan = new JMenu("Tài khoản");

        menuMuonSach = new JMenuItem("Mượn sách");
        menuDangXuat = new JMenuItem("Đăng xuất");
        menuThongTin = new JMenuItem("Thông tin");


        menuMuonSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TrungTamMuonSach(UserSession.getMaSinhVien());
            }
        });

        menuDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng giao diện hiện tại
                new Login_User(); // Mở lại màn hình đăng nhập
            }
        });


        menuBar.add(menuTaiKhoan);
        menuTaiKhoan.add(menuMuonSach);
        menuTaiKhoan.add(menuThongTin);
        menuTaiKhoan.addSeparator();
        menuTaiKhoan.add(menuDangXuat);


        // Panel Danh Sách Sách
        JPanel pnDanhSach = new JPanel(new BorderLayout());
        TitledBorder borderDanhSachSach = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,2), "Danh sách sách ");
        pnDanhSach.setBorder(borderDanhSachSach);
        pnDanhSach.setBounds(405, 10, 500, 320);

        modelDanhSach = new DefaultTableModel(col,0);
        table1 = new JTable(modelDanhSach);
        sc1 = new JScrollPane(table1);
        pnDanhSach.add(sc1,BorderLayout.CENTER);
        table1.setDefaultEditor(Object.class, null);
        loadSachTable();
        // Đặt menu bar

        add(pnDanhSach);
        setJMenuBar(menuBar);
        setVisible(true);
        setResizable(false);

        //

        getContentPane().setBackground(new Color(74, 136, 136));
        pnDanhSach.setBackground(new Color(74, 136, 136));
        panelThongTin.setBackground(new Color(245, 255, 240));
        table1.setBackground(new Color(243, 250, 243));





    }
    private void loadSachTable() {
        String query = "SELECT * FROM sachChoDangKi WHERE Ma_SinhVien = ?";
        String maSinhVien = UserSession.getMaSinhVien();
        System.out.println("Mã sinh viên hiện tại: " + maSinhVien);

        // Định dạng ngày mà bạn muốn hiển thị
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            // Gán giá trị cho tham số ?
            pstmt.setString(1, maSinhVien);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Xóa dữ liệu cũ trong bảng trước khi thêm mới
                modelDanhSach.setRowCount(0);

                while (rs.next()) {
                    // Lấy ngày hết hạn và chuyển sang định dạng mong muốn
                    String formattedNgayHetHan = "";
                    java.sql.Date ngayHetHanSQL = rs.getDate("Ngay_Het_Han");
                    if (ngayHetHanSQL != null) {
                        // Chuyển đổi ngày sang định dạng mong muốn
                        formattedNgayHetHan = sdf.format(ngayHetHanSQL);
                    }

                    // Tạo dòng dữ liệu cho bảng
                    Object[] row = {
                            rs.getString("Ma_Sach"),
                            rs.getString("Ten_Sach"),
                            rs.getString("Tac_Gia"),
                            formattedNgayHetHan, // Ngày đã được định dạng
                            rs.getString("The_Loai"),
                            rs.getDouble("Gia_Tien")
                    };
                    modelDanhSach.addRow(row);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(thongTin.this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }




    private void loadThongTinSinhVien() {
        // Lấy mã sinh viên từ UserSession
        String maSinhVien = UserSession.getMaSinhVien();

        // Câu truy vấn SQL
        String query = "SELECT * FROM SinhVienDaDangKi WHERE Ma_SinhVien = ?";

        // Định dạng ngày mà bạn muốn hiển thị
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            // Gán giá trị tham số cho câu truy vấn
            pstmt.setString(1, maSinhVien);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Lấy thông tin từ ResultSet và lưu vào các biến
                    String maSinhVienResult = rs.getString("Ma_SinhVien");
                    String tenSinhVienResult = rs.getString("Ho_Va_Ten");

                    // Lấy ngày đăng ký và ngày hết hạn, sau đó định dạng chúng
                    String ngayDangKyResult = "";
                    String ngayHetHanResult = "";
                    java.sql.Date ngayDangKySQL = rs.getDate("NgayDangKy");
                    java.sql.Date ngayHetHanSQL = rs.getDate("NgayHetHan");

                    if (ngayDangKySQL != null) {
                        ngayDangKyResult = sdf.format(ngayDangKySQL);  // Định dạng ngày đăng ký
                    }

                    if (ngayHetHanSQL != null) {
                        ngayHetHanResult = sdf.format(ngayHetHanSQL);  // Định dạng ngày hết hạn
                    }

                    String emailResult = rs.getString("Email");
                    String gioiTinhResult = rs.getString("Gioi_Tinh");

                    // Cập nhật các thông tin vào JTextField
                    txtMaSinhVien.setText(": " + maSinhVienResult);
                    txtTenSinhVien.setText(": " + tenSinhVienResult);
                    txtNgayDangKy.setText(": " + ngayDangKyResult);
                    txtNgayHetHan.setText(": " + ngayHetHanResult);
                    txtEmail.setText(": " + emailResult);
                    txtGioiTinh.setText(": " + gioiTinhResult);
                } else {
                    System.out.println("Lỗi");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(thongTin.this, "Lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }




    public static void main(String[] args) {
        new thongTin();
    }
}
