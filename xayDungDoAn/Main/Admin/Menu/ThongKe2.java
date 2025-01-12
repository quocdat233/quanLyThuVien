package DoAncuoiki1.xayDungDoAn.Main.Admin.Menu;

import DoAncuoiki1.xayDungDoAn.Main.Login_Admin;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ThongKe2 extends JFrame {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=false;trustServerCertificate=true;useUnicode=true;characterEncoding=UTF-8";
    private static final String USER = "quocdat233";
    private static final String PASS = "dat23326";
    private JMenuBar menuBar;
    private JMenuItem menuItem1,itemQuanLySach,itemThongKe1,itemThongKe2,itemQuanLythe,itemKichHoatThe;
    private JMenu menu ;

    private JPanel chartPanelContainer;

    public ThongKe2() {
        setTitle("Thống kê sách theo thể loại");
        setSize(1450, 1020);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo giao diện
        createUI();

        // Hiển thị dữ liệu
        loadChartData();

        setVisible(true);
    }

    private void createUI() {
        // Tạo Panel chứa biểu đồ
        chartPanelContainer = new JPanel();
        chartPanelContainer.setLayout(new BorderLayout());

        // Thêm panel vào JFrame
        setLayout(new BorderLayout());
        add(chartPanelContainer, BorderLayout.CENTER);
    }

    private void loadChartData() {
        // Tạo dataset cho biểu đồ hình tròn
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Câu truy vấn SQL để đếm số lượng sách theo thể loại
        String query = "SELECT The_Loai, COUNT(*) AS SoLuongMuon FROM sachchodangki GROUP BY The_Loai";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Thêm dữ liệu vào dataset
            while (rs.next()) {
                String theLoai = rs.getString("The_Loai");
                int soLuongMuon = rs.getInt("SoLuongMuon");

                dataset.setValue(theLoai, soLuongMuon);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu cho biểu đồ!");
            e.printStackTrace();
        }


        // Tạo biểu đồ hình tròn
        JFreeChart pieChart = ChartFactory.createPieChart(
                "THỂ LOẠI SÁCH ĐƯỢC MƯỢN NHIỀU NHẤT",  // Tiêu đề biểu đồ
                dataset,                        // Dữ liệu
                true,                            // Hiển thị phần trăm
                true,                            // Hiển thị tiêu đề
                false                           // Không hiển thị tooltips
        );

        pieChart.getTitle().setFont(new Font("Arial", Font.BOLD, 33));


        // Hiển thị biểu đồ trên giao diện
        chartPanelContainer.removeAll();
        chartPanelContainer.add(new ChartPanel(pieChart), BorderLayout.CENTER);
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
                dispose();
                new ThongKe();
            }
        });

        itemThongKe2 = new JMenuItem("Thống kê sách");
        itemThongKe2.setFont(new Font("Arial", Font.PLAIN, 18));
        itemThongKe2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        new ThongKe2();
    }
}
