package DoAncuoiki1.xayDungDoAn.Main.Admin.Menu;

import DoAncuoiki1.xayDungDoAn.Main.Login_Admin;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ThongKe extends JFrame {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=false;trustServerCertificate=true;useUnicode=true;characterEncoding=UTF-8";
    private static final String USER = "quocdat233";
    private static final String PASS = "dat23326";
    private JMenuBar menuBar;
    private JMenuItem menuItem1,itemQuanLySach,itemThongKe1,itemThongKe2,itemQuanLythe,itemKichHoatThe;
    private JMenu menu ;

    private JComboBox<String> yearComboBox; // ComboBox để chọn năm
    private JPanel chartPanelContainer; // Panel chứa biểu đồ

    public ThongKe() {


        setTitle("Thống kê doanh thu");
        setSize(2000, 1020);
        setLocationRelativeTo(null); // Căn giữa màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo giao diện
        createUI();

        // Hiển thị dữ liệu cho năm mặc định (năm đầu tiên trong ComboBox)
        loadChartData((String) yearComboBox.getSelectedItem());
        setVisible(true);
    }


    private void createUI() {
        // Tạo ComboBox để chọn năm
        yearComboBox = new JComboBox<>(getAvailableYears());
        yearComboBox.setPreferredSize(new Dimension(60, 30));
        yearComboBox.setBackground(Color.WHITE);
        yearComboBox.setFont(new Font("Arial", Font.BOLD, 14));



        // Nút để hiển thị dữ liệu
        JButton showButton = new JButton("Hiển thị");
        showButton.setFont(new Font("arial", Font.BOLD, 17));
        showButton.setFocusPainted(false);
        showButton.setBackground(Color.lightGray);
        showButton.addActionListener(e -> {
            String selectedYear = (String) yearComboBox.getSelectedItem();
            loadChartData(selectedYear);
        });

        // Tạo Panel chứa ComboBox và nút
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 7, 8)); // Sử dụng FlowLayout
        JLabel lblcontro = new JLabel("Chọn năm:");
        lblcontro.setFont(new Font("Arial", Font.BOLD ,17));
        controlPanel.add(lblcontro);
        controlPanel.add(yearComboBox);
        controlPanel.add(showButton);

        // Panel chứa biểu đồ
        chartPanelContainer = new JPanel();

        chartPanelContainer.setLayout(new BorderLayout());

        // Thêm các thành phần vào JFrame
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(chartPanelContainer, BorderLayout.CENTER);
    }

    private String[] getAvailableYears() {
        // Lấy danh sách các năm có trong dữ liệu từ cơ sở dữ liệu
        String query = "SELECT DISTINCT YEAR(ngay_muon) AS Nam FROM ThongKe ORDER BY Nam DESC";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Duyệt kết quả và lưu danh sách các năm
            java.util.List<String> years = new java.util.ArrayList<>();
            while (rs.next()) {
                years.add(String.valueOf(rs.getInt("Nam")));
            }
            return years.toArray(new String[0]);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy danh sách năm!");
            e.printStackTrace();
            return new String[0]; // Trả về mảng rỗng nếu lỗi
        }
    }

    private void loadChartData(String year) {
        // Tạo dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Khởi tạo doanh thu cho tất cả 12 tháng với giá trị 0
        for (int i = 1; i <= 12; i++) {
            dataset.addValue(0, "Doanh thu", "Tháng " + i);
        }

        // Truy vấn doanh thu từ cơ sở dữ liệu
        String query = "SELECT MONTH(ngay_muon) AS Thang, SUM(gia_tien) AS DoanhThu " +
                "FROM ThongKe " +
                "WHERE YEAR(ngay_muon) = ? " +
                "GROUP BY MONTH(ngay_muon) " +
                "ORDER BY Thang";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, year); // Gán tham số năm vào câu truy vấn
            ResultSet rs = pstmt.executeQuery();

            // Ghi đè doanh thu cho các tháng có dữ liệu
            while (rs.next()) {
                int thang = rs.getInt("Thang");
                double doanhThu = rs.getDouble("DoanhThu");

                // Ghi đè giá trị doanh thu của tháng
                dataset.setValue(doanhThu, "Doanh thu", "Tháng " + thang);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu cho biểu đồ!");
            e.printStackTrace();
        }

        // Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "DOANH THU THEO THÁNG (Năm " + year + ")", // Tiêu đề biểu đồ
                "Tháng",                                  // Nhãn trục X
                "Doanh thu (VNĐ)",                        // Nhãn trục Y
                dataset                                   // Dữ liệu
        );

        barChart.getTitle().setFont(new Font("Arial", Font.BOLD, 28));

        // Hiển thị biểu đồ trên giao diện
        chartPanelContainer.removeAll(); // Xóa biểu đồ cũ
        chartPanelContainer.add(new ChartPanel(barChart), BorderLayout.CENTER); // Thêm biểu đồ mới
        chartPanelContainer.revalidate();
        chartPanelContainer.repaint();







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
                dispose();
                new kich_Hoat_The();

            }
        });

        itemThongKe1 = new JMenuItem("Doanh thu");
        itemThongKe1.setFont(new Font("Arial", Font.PLAIN, 18));
        itemThongKe1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

    }




    public static void main(String[] args) {



        new ThongKe();

    }
}
