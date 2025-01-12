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
import java.time.LocalDate;


public class quan_Ly_The extends JFrame {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=false;trustServerCertificate=true";
    private static final String USER = "quocdat233";
    private static final String PASS = "dat23326";

    private JMenuBar menuBar;
    private JMenuItem menuItem1,itemQuanLySach,itemThongKe1,itemThongKe2,itemQuanLythe,itemKichHoatThe;
    private JMenu menu ;
    private JPanel pn1,pn2,pn3;
    private JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lblnote;
    private JTextField txt1,txt2,txt3,txt4,txt5;
    private JButton btn1,btn2,btn3,btn4;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scp;

    public quan_Ly_The(){
        super("Quản lý thẻ");
        setSize(910,680);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Object [] col = {"Họ và tên", "Mã sinh viên", "Email", "SDT", "Giới tính", "Đối tượng","Ngày đăng kí","Ngày hết hạn"};





        lbl1 = new JLabel("Mã sinh viên");
        lbl1.setBounds(30, 40, 100, 20);
        lbl2 = new JLabel("Tên sinh viên");
        lbl2.setBounds(30, 85, 100, 20);
        lbl3 = new JLabel("Ngày đăng kí");
        lbl3.setBounds(30, 130, 100, 20);
        lbl4 = new JLabel("Ngày hết hạn");
        lbl4.setBounds(30, 175, 100, 20);

        txt1 = new JTextField();
        txt1.setBounds(150,40,160,20);
        txt1.setEditable(false);
        txt1.setBackground(Color.WHITE);


        txt2 = new JTextField();
        txt2.setBounds(150,85,160,20);
        txt2.setEditable(false);
        txt2.setBackground(Color.white);

        txt3 = new JTextField();
        txt3.setBounds(150,130,160,20);
        txt3.setEditable(false);
        txt3.setBackground(Color.WHITE);

        txt4 = new JTextField();
        txt4.setBounds(150,175,160,20);

        btn1 = new JButton("Hủy kích hoạt");
        btn1.setFocusPainted(false);
        btn1.setBackground(Color.LIGHT_GRAY);
        btn1.setFont(new Font("Arial", Font.BOLD, 12));
        btn1.setBounds(105,230,113,28);


        btn2 = new JButton("Cập nhật");
        btn2.setBounds(240,230,113,28);
        btn2.setFont(new Font("Arial", Font.BOLD, 12));
        btn2.setBackground(Color.LIGHT_GRAY);
        btn2.setFocusPainted(false);



        pn1 = new JPanel();
        pn1.setLayout(null);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2)," Thông tin thẻ  ");
        pn1.setBorder(titledBorder);
        pn1.setBounds(25, 20, 400, 300);
        pn1.add(btn2);
        pn1.add(lbl1);
        pn1.add(txt1);
        pn1.add(lbl2);
        pn1.add(txt2);
        pn1.add(lbl3);
        pn1.add(txt3);
        pn1.add(lbl4);
        pn1.add(txt4);
        pn1.add(btn1);



        lbl5 = new JLabel("Thông tin tìm kiếm");
        lbl5.setBounds(20, 60, 120, 20);

        lblnote = new JLabel("*Nhập kí tự cần tìm");
        lblnote.setForeground(Color.red);
        lblnote.setFont(new Font("Arial", Font.PLAIN, 11));
        lblnote.setBounds(150, 87, 150, 20);

        txt5 = new JTextField();
        txt5.setBounds(150,58,180,23);



        btn3 = new JButton("Tìm kiếm");
        btn3.setBounds(140,135,90,27);
        btn3.setFont(new Font("Arial", Font.BOLD, 12));
        btn3.setBackground(Color.LIGHT_GRAY);
        btn3.setFocusPainted(false);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        String keyWord = "%" + txt5.getText().trim() + "%";
        if(keyWord.equals("%%")){
            JOptionPane.showMessageDialog(quan_Ly_The.this,"Vui lòng nhập thông tin ");
        }
        else
        {
            String query = "SELECT * FROM SinhVienDaDangKi WHERE Ho_Va_Ten LIKE ? OR  Ma_SinhVien LIKE ? OR Email LIKE ? OR SDT LIKE ? OR Gioi_Tinh LIKE ? OR  doituong LIKE ? OR  NgayDangKy LIKE ? OR NgayHetHan LIKE ?";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                 PreparedStatement pstm = conn.prepareStatement(query)) {
                pstm.setString(1, keyWord);
                pstm.setString(2, keyWord);
                pstm.setString(3, keyWord);
                pstm.setString(4, keyWord);
                pstm.setString(5, keyWord);
                pstm.setString(6, keyWord);
                pstm.setString(7, keyWord);
                pstm.setString(8, keyWord);



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
                            rs.getString("NgayHetHan")
                    };
                    model.addRow(row); // Thêm dòng vào bảng
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(quan_Ly_The.this, "Lỗi khi tìm kiếm dữ liệu!");
            }





        }

            }
        });

        btn4 = new JButton("Load Bảng");
        btn4.setBounds(245,135, 100,27);
        btn4.setFont(new Font("Arial", Font.BOLD, 12));
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
        pn2.add(btn3);
        pn2.add(btn4);


        model = new DefaultTableModel(col,0);
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setDefaultEditor(Object.class, null);
        scp = new JScrollPane(table);

        pn3 = new JPanel(new BorderLayout());

        //Chú ý...
        loadTableData();


        TitledBorder titledBorder2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,2)," Danh sách thẻ sinh viên  ");
        pn3.setBorder(titledBorder2);
        pn3.setBounds(25, 340, 850, 270);
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
          txt4.setText(model.getValueAt(select, 7).toString());

        }}});


        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//
                if (txt1.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(quan_Ly_The.this, "Vui lòng chọn mã sinh viên để hủy kích hoạt!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                else

                    deleteData(); // Xóa dữ liệu nếu mã hợp lệ
                resetForm();

            }


        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txt1.getText().isEmpty()){
                    JOptionPane.showMessageDialog(quan_Ly_The.this, "Vui lòng chọn thẻ sinh viên cần câp nhật ");
                    return;
                }
                int select = table.getSelectedRow();
               String maSv=txt1.getText();
                String ngayhetHan = model.getValueAt(select,7).toString();
                String ngayhetHanNew = txt4.getText().trim();

                if(ngayhetHanNew.equals(ngayhetHan)){
                    JOptionPane.showMessageDialog(quan_Ly_The.this, "Bạn chưa thay dổi thông tin");
                return;}


                try {
                    // Chuyển đổi chuỗi sang LocalDate
                    LocalDate ngayMoi = LocalDate.parse(ngayhetHanNew);
                    LocalDate ngayToiDa = LocalDate.of(2040, 12, 31); // Giới hạn ngày

                    // Kiểm tra nếu ngày mới vượt quá giới hạn
                    if (ngayMoi.isAfter(ngayToiDa)) {
                        JOptionPane.showMessageDialog(quan_Ly_The.this, "Ngày hết hạn không được vượt quá " + ngayToiDa, "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    else {
                    String query = "UPDATE SinhVienDadangKi SET NgayHetHan = ? WHERE  Ma_SinhVien = ?";
                    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                         PreparedStatement pstm = conn.prepareStatement(query)) {
                        pstm.setString(1,ngayhetHanNew);
                        pstm.setString(2,maSv);

                        int a = pstm.executeUpdate();

                        if(a>0){
                            JOptionPane.showMessageDialog(quan_Ly_The.this, "Cập nhật thông tin thành công ");
                            model.setValueAt(ngayhetHanNew, select, 7);
                        }
                        else {
                            JOptionPane.showMessageDialog(quan_Ly_The.this, "Ban chưa thay đổi thông tin");
                        }

                    }
                catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(quan_Ly_The.this, "Lỗi kết nối ");
                    }}}
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(quan_Ly_The.this, "Ngày ko đúng định dạng!");
                    }


                }
        });
        add(pn1);
        add(pn2);
        add(pn3);
        setResizable(false);
        setJMenuBar(menuBar);
        setVisible(true);

        //MÀU
        getContentPane().setBackground(new Color(74, 136, 136));
        pn1.setBackground(new Color(245, 255, 240));
        pn2.setBackground(new Color(245, 255, 240));
        pn3.setBackground(new Color(74, 136, 136));
        table.setBackground(new Color(243, 250, 243));

        btn1.setBackground(new Color(203, 194, 189));
        btn2.setBackground(new Color(203, 194, 189));
        btn3.setBackground(new Color(203, 194, 189));
        btn4.setBackground(new Color(203, 194, 189));

    }
    private void deleteData() {
        String maSinhVien = txt1.getText();
        String query = "DELETE FROM SinhVienDadangKi WHERE Ma_SinhVien = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, maSinhVien);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Hủy kích hoạt thẻ thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                model.setRowCount(0); // Xóa toàn bộ dữ liệu hiện tại
                loadTableData();     // Tải lại dữ liệu
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã sinh viên để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi kết nối cơ sở dữ liệu!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void loadTableData() {
        String query = "SELECT Ho_Va_Ten, Ma_SinhVien, Email, SDT, Gioi_Tinh, doiTuong, NgayDangKy,NgayHetHan FROM SinhVienDadangKi";
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
                        rs.getDate("NgayDangKy"),
                        rs.getString("NgayHetHan")
                };
                // Thêm dòng dữ liệu vào bảng
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ cơ sở dữ liệu!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
    }



    public static void main(String[] args) {
        new quan_Ly_The();
    }

}
