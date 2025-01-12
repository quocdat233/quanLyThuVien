package DoAncuoiki1.xayDungDoAn.Main.Admin.Menu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Vector;

public class quan_Ly_MuonTra extends JFrame {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=false;trustServerCertificate=true;useUnicode=true;characterEncoding=UTF-8";
    private static final String USER = "quocdat233";
    private static final String PASS = "dat23326";

    private JTable table;
    private DefaultTableModel tableModel;

    public quan_Ly_MuonTra() {
        setTitle("Quản Lý Mượn Trả");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Khởi tạo giao diện
        initUI();

        // Kiểm tra và xử lý sách quá hạn
        checkAndProcessExpiredBooks();

        // Hiển thị sách đã hết hạn
        loadExpiredBooks();
        setVisible(true);
    }

    private void initUI() {
        // Tạo JPanel chính
        JPanel panel = new JPanel(new BorderLayout());

        // Tạo bảng JTable để hiển thị thông tin sách
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        table.setBackground(new Color(243, 250, 243));

        // Thêm các cột vào bảng
        tableModel.addColumn("Mã Sách");
        tableModel.addColumn("Tên Sách");
        tableModel.addColumn("Tác Giả");
        tableModel.addColumn("Nhà Xuất Bản");
        tableModel.addColumn("Thể Loại");
        tableModel.addColumn("Ngày Hết Hạn");
        tableModel.addColumn("Giá Tiền");
        tableModel.addColumn("Mã Sinh Viên");
        tableModel.addColumn("Ngày Mượn");
        tableModel.addColumn("Trạng Thái");

        // Thêm JScrollPane để bảng có thanh cuộn


        // Thêm panel vào JFrame
        panel.setBackground(new Color(74, 136, 136));
        add(panel);
    }

    private void checkAndProcessExpiredBooks() {
        Connection conn = null;
        PreparedStatement selectStmt = null;
        PreparedStatement checkExistStmt = null;
        PreparedStatement insertStmt = null;
        ResultSet rs = null;
        ResultSet checkRs = null;

        try {
            // Kết nối database
            conn = DriverManager.getConnection(URL, USER, PASS);

            // Query để lấy thông tin từ bảng sachchodangki
            String selectQuery = "SELECT * FROM sachchodangki";
            selectStmt = conn.prepareStatement(selectQuery);
            rs = selectStmt.executeQuery();

            // Ngày hiện tại
            LocalDate currentDate = LocalDate.now();

            while (rs.next()) {
                String maSach = rs.getString("Ma_Sach");
                String tenSach = rs.getString("Ten_Sach");
                String tacGia = rs.getString("Tac_Gia");
                String nhaXuatBan = rs.getString("Nha_Xuat_Ban");
                String theLoai = rs.getString("The_Loai");
                Date ngayHetHan = rs.getDate("Ngay_Het_Han");
                double giaTien = rs.getDouble("Gia_Tien");
                String maSinhVien = rs.getString("Ma_SinhVien");
                Date ngayMuon = rs.getDate("Ngay_Muon");

                // Kiểm tra nếu ngày hết hạn đã qua
                if (ngayHetHan.toLocalDate().isBefore(currentDate)) {
                    // Kiểm tra xem mã sách đã tồn tại trong bảng sachdaquahan
                    String checkExistQuery = "SELECT 1 FROM sachdaquahan WHERE Ma_Sach = ?";
                    checkExistStmt = conn.prepareStatement(checkExistQuery);
                    checkExistStmt.setString(1, maSach);
                    checkRs = checkExistStmt.executeQuery();

                    if (checkRs.next()) {
                        // Mã sách đã tồn tại, bỏ qua
                        System.out.println("Mã sách đã tồn tại trong bảng sachdaquahan: " + maSach);
                        continue;
                    }

                    // Nếu mã sách chưa tồn tại, thêm mới vào bảng sachdaquahan
                    String insertQuery = "INSERT INTO sachdaquahan (Ma_Sach, Ten_Sach, Tac_Gia, Nha_Xuat_Ban, The_Loai, Ngay_Het_Han, Gia_Tien, Ma_SinhVien, Ngay_Muon, Trang_Thai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    insertStmt = conn.prepareStatement(insertQuery);
                    insertStmt.setString(1, maSach);
                    insertStmt.setString(2, tenSach);
                    insertStmt.setString(3, tacGia);
                    insertStmt.setString(4, nhaXuatBan);
                    insertStmt.setString(5, theLoai);
                    insertStmt.setDate(6, ngayHetHan);
                    insertStmt.setDouble(7, giaTien);
                    insertStmt.setString(8, maSinhVien);
                    insertStmt.setDate(9, ngayMuon);
                    insertStmt.setString(10, "Đã hết hạn");
                    insertStmt.executeUpdate();
                    System.out.println("Đã thêm mã sách vào bảng sachdaquahan: " + maSach);
                }
            }

            System.out.println("Kiểm tra và xử lý sách quá hạn hoàn tất!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
        } finally {
            // Đóng kết nối
            try {
                if (checkRs != null) checkRs.close();
                if (rs != null) rs.close();
                if (selectStmt != null) selectStmt.close();
                if (checkExistStmt != null) checkExistStmt.close();
                if (insertStmt != null) insertStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    private void loadExpiredBooks() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Kết nối database
            conn = DriverManager.getConnection(URL, USER, PASS);

            // Query để lấy sách đã hết hạn từ bảng sachdaquahan
            String query = "SELECT * FROM sachdaquahan";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            // Xóa dữ liệu cũ trong bảng JTable
            tableModel.setRowCount(0);

            // Thêm dữ liệu vào JTable
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("Ma_Sach"));
                row.add(rs.getString("Ten_Sach"));
                row.add(rs.getString("Tac_Gia"));
                row.add(rs.getString("Nha_Xuat_Ban"));
                row.add(rs.getString("The_Loai"));
                row.add(rs.getDate("Ngay_Het_Han"));
                row.add(rs.getDouble("Gia_Tien"));
                row.add(rs.getString("Ma_SinhVien"));
                row.add(rs.getDate("Ngay_Muon"));
                row.add(rs.getString("Trang_Thai"));

                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
        } finally {
            // Đóng kết nối
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
      new quan_Ly_MuonTra();

    }
}
