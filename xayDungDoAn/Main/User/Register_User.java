package DoAncuoiki1.xayDungDoAn.Main.User;

import DoAncuoiki1.xayDungDoAn.Main.Login_User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Register_User extends JFrame {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=false;trustServerCertificate=true;useUnicode=true;characterEncoding=UTF-8";

    private static final String USER = "quocdat233";
    private static final String PASS = "dat23326";

    private JLabel lblNote,lblTaiKhoan,lblTen,lblPass,lblMaSinhVien,lblSdt,lblDoiTuong,lblnote1,lblGioiTinh,lblnote2,lblEmail;
    private JRadioButton rdbt1,rdbt2,rdbt3,rdbt4,rdbt5;
    private JTextField txtTaiKhoan,txtTen,txtMaSinhVien,txtSdt,txtEmail;
    private JPasswordField txtPass;
    private JButton btnTroLai,btnDangki;
    private JCheckBox cb1;
    private JPanel pn1,pn2;
    public Register_User() {

        setTitle("Đăng ký Admin");
        setSize(520, 675);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 1);
        setResizable(false);
        hienThi();
        setVisible(true);
    }

    private void hienThi() {
        pn1 = new JPanel();
        pn1.setLayout(null);
        lblNote = new JLabel("Thư viện VKU hân hạnh phục vụ quý khách");
        lblNote.setForeground(Color.RED);
        lblNote.setFont(new Font("Arial", Font.BOLD, 17));
        lblNote.setBounds(70, 13,380, 20);
        pn1.add(lblNote);

        pn2 = new JPanel();
        pn2.setLayout(null);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,2), "Thông tin đăng ký  ");
        pn2.setBorder(titledBorder);
//        pn2.setBorder(BorderFactory.createTitledBorder("Thông tin đăng kí "));
        pn2.setBounds(70,50,370,480);
        pn1.add(pn2);




        lblTaiKhoan = new JLabel("Tài khoản");
        lblTaiKhoan.setBounds(15, 55, 60, 20);
        pn2.add(lblTaiKhoan);

        txtTaiKhoan = new JTextField("");
        txtTaiKhoan.setBounds(115,55,160,21);
        pn2.add(txtTaiKhoan);


        lblTen = new JLabel("Họ và tên");
        lblTen.setBounds(15,100, 60, 20);
        pn2.add(lblTen);

        txtTen = new JTextField();
        txtTen.setBounds(115,100,160,21);
        pn2.add(txtTen);


        lblPass = new JLabel("Mật khẩu");
        lblPass.setBounds(15, 145,60 ,20 );
        pn2.add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(115,145,160,21);
        pn2.add(txtPass);

        lblnote1 = new JLabel("*Tối thiểu 6 ký tự ");
        lblnote1.setFont(new Font("Arial", Font.PLAIN,  11));
        lblnote1.setForeground(Color.red);
        lblnote1.setBounds(115, 170, 130, 20);
        pn2.add(lblnote1);

        lblDoiTuong = new JLabel("Đối tượng");
        lblDoiTuong.setBounds(15,205,60,20);
        pn2.add(lblDoiTuong);

        rdbt1 = new JRadioButton("Sinh viên VKU");
        rdbt1.setBounds(110, 205, 115, 20);
        pn2.add(rdbt1);
        rdbt2 = new JRadioButton("Khác");
        rdbt2.setBounds(225,205,60,20);
        pn2.add(rdbt2);

        ButtonGroup btnGroup2 = new ButtonGroup();
        btnGroup2.add(rdbt1);
        btnGroup2.add(rdbt2);



        lblMaSinhVien = new JLabel("Mã sinh viên");
        lblMaSinhVien.setBounds(15, 250, 115, 20);
        pn2.add(lblMaSinhVien);



        txtMaSinhVien = new JTextField();
        txtMaSinhVien.setBounds(115,250,160,21);
        pn2.add(txtMaSinhVien);

        lblnote2 = new JLabel("*Số CCCD đối với đối tượng khác");
        lblnote2.setForeground(Color.red);
        lblnote2.setFont(new Font("Arial", Font.PLAIN, 11));
        lblnote2.setBounds(115, 280, 170, 20);
        pn2.add(lblnote2);

        lblEmail = new JLabel("Email");
        lblEmail.setBounds(15, 315, 70, 20);
        pn2.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(115,315,160,21);
        pn2.add(txtEmail);

        lblSdt = new JLabel("Điện thoại");
        lblSdt.setBounds(15,360, 100, 20);
        pn2.add(lblSdt);

        txtSdt = new JTextField();
        txtSdt.setBounds(115,360,160,21);
        pn2.add(txtSdt);


        lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setBounds(15, 405, 60, 20);
        pn2.add(lblGioiTinh);

        rdbt3 = new JRadioButton("Nam");
        rdbt3.setBounds(110,405,60,20);
        pn2.add(rdbt3);

        rdbt4 = new JRadioButton("Nữ");
        rdbt4.setBounds(167,405,50,20);
        pn2.add(rdbt4);

        rdbt5 = new JRadioButton("Khác");
        rdbt5.setBounds(225,405,60,20);
        pn2.add(rdbt5);
        ButtonGroup btnGroup1 = new ButtonGroup();
        btnGroup1.add(rdbt5);
        btnGroup1.add(rdbt4);
        btnGroup1.add(rdbt3);



        cb1 = new JCheckBox("Tôi đã đọc nội quy của thư viện");
        cb1.setBounds(58, 575, 210, 20);
        pn1.add(cb1);

        btnTroLai = new JButton("Trở lại");
        btnTroLai.setBounds(273,575,78,20);
        btnTroLai.setFocusPainted(false);



        btnTroLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register_User.this.dispose();
                new Login_User();

            }
        });

        pn1.add(btnTroLai);

        btnDangki = new JButton("Đăng ký");
        btnDangki.setBounds(366,575,81,20);
        btnDangki.setFocusPainted(false);

        btnDangki.addActionListener(new ActionListener() {

            public boolean checkTaiKhoan(String str){
                boolean check = true;
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if (!(c >= '0' && c <= '9') && !(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
                        check = false;
                    }
                }
                return  check;
            }

            public boolean checkten(String str){
                boolean check = true;
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if(!(Character.isLetter(c) || c== ' '))

                        check = false;

                }
                return  check;
            }
            public boolean checkPass(String str){
                boolean check = true;
                if(str.length()<6)
                    check = false;
                return check;
            }

            public boolean checkKeyMail(String str){
                boolean check = true;
                String query1 = "SELECT EMAIL FROM SINHVIENCHOXACNHAN UNION SELECT EMAIL FROM SINHVIENDADANGKI";

                try(Connection con = DriverManager.getConnection(URL,USER,PASS);
                Statement stmt = con.createStatement();
                ResultSet resultSet = stmt.executeQuery(query1)
                ){
                    while (resultSet.next()){
                        System.out.println(resultSet);
                        String email = resultSet.getString("email");
                        if(email.equals(str)){
                            check = false;
                            break;
                        }
                    }
                }

                catch (SQLException ex){
                    System.out.println("Lỗi kết nối SQL: " + ex.getMessage());
                    ex.printStackTrace();
                }
                return  check;

            }


            public boolean checkMaSV(String str){
                    boolean check = true;
                    for (int i = 0; i < str.length(); i++) {
                        char c = str.charAt(i);
                        if (!(c >= '0' && c <= '9') && !(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
                            check = false;
                        }
                    }
                    return  check;
                }


            public boolean checkEmail(String email){
                if (!email.contains("@") || email.indexOf('@') <= 0 || email.indexOf('@') >= email.length() - 1) {
                    return false;
                }
                String chuoi = email.substring(email.indexOf('@') + 1);
                for (int i = 0; i < chuoi.length(); i++) {
                    char c = chuoi.charAt(i);
                    if (Character.isDigit(c))
                        return false;

                }
                return true;
            }


            public boolean checkSDT(String str){
                boolean check = true;
                for (int i =0;i<str.length();i++){
                    char c = str.charAt(i);
                    if(!(c<='9' && c>='0')){
                        check = false;
                    }

                }
                return check;

            }

            public void actionPerformed(ActionEvent e) {
                String taiKhoan = txtTaiKhoan.getText().trim();
                String hoVaTen = txtTen.getText().trim();
                String gioiTinh = "";
                String doiTuong = "";
                if (rdbt3.isSelected())
                    gioiTinh = "Nam";
                else if (rdbt4.isSelected())
                    gioiTinh = "Nữ";
                else if (rdbt5.isSelected())
                    gioiTinh = "Khác";

                if (rdbt1.isSelected())
                    doiTuong = "Sinh viên VKU";
                else if (rdbt2.isSelected())
                    doiTuong = "Khác";


                String pass = String.valueOf(txtPass.getPassword());
                String maSV = txtMaSinhVien.getText().trim();
                String  email = txtEmail.getText().trim();
                String sdt = txtSdt.getText().trim();
                java.time.LocalDate ngayDangKy = java.time.LocalDate.now();
                java.sql.Date Date = java.sql.Date.valueOf(ngayDangKy);

                if(taiKhoan.isEmpty()||hoVaTen.isEmpty()
                        ||pass.isEmpty()||maSV.isEmpty()
                        ||email.isEmpty()||sdt.isEmpty()
                        || (!rdbt1.isSelected() && !rdbt2.isSelected())
                        || (!rdbt3.isSelected() && !rdbt4.isSelected()&&!rdbt5.isSelected())
                        ){
                    JOptionPane.showMessageDialog( Register_User.this, "Thông tin bị thiếu !!" );
                }
                 else if (!checkTaiKhoan(taiKhoan)) {
                    JOptionPane.showMessageDialog(Register_User.this,
                            "Tài khoản chỉ được chứa chữ và số !!"," ",
                            JOptionPane.ERROR_MESSAGE);

                }
                else if (!checkten(hoVaTen)){
                    JOptionPane.showMessageDialog(Register_User.this, "Xem lại định dạng tên !!");


                }
                else if (!checkPass(pass)) {
                    JOptionPane.showMessageDialog(Register_User.this,
                            "Mật khẩu phải có ít nhất 6 ký tự !!","",
                            JOptionPane.ERROR_MESSAGE);

                }
                else if (!checkMaSV(maSV)) {
                    JOptionPane.showMessageDialog(Register_User.this, "Xem lại mã SV !!");

                }
                else if(!checkEmail(email)){
                    JOptionPane.showMessageDialog(Register_User.this,
                            "Email không hợp lệ. Vui lòng nhập đúng định dạng email !!",
                            "",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(!checkKeyMail(email)){
                    JOptionPane.showMessageDialog(Register_User.this, "Email đã được sử dụng");

                }





                else if(!checkSDT(sdt)){
                    JOptionPane.showMessageDialog(Register_User.this,
                            "Số điện thoại chỉ chứa số !!",
                            "",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(!cb1.isSelected()){
                    JOptionPane.showMessageDialog(Register_User.this, "Vui lòng đồng ý nội quy của thư viện");
                }
                else{
                    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                         PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM SinhVienChoXacNhan WHERE Tai_Khoan = ?")) {
                        ps.setString(1, taiKhoan);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next() && rs.getInt(1) > 0) {
                            // Tài khoản đã tồn tại
                            JOptionPane.showMessageDialog(Register_User.this, "Tên Tài khoản đã tồn tại.Vui lòng chọn tài khoản khác !","",JOptionPane.ERROR_MESSAGE);
                            return;  // Dừng lại nếu tài khoản trùng
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Register_User.this, "Lỗi kiểm tra tài khoản, vui lòng thử lại!","",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                hoVaTen = hoVaTen.toLowerCase();
                String [] arr = hoVaTen.split("\\s+");
                StringBuilder sb = new StringBuilder();
                for(String fullname : arr){
                    sb.append(Character.toUpperCase(fullname.charAt(0))).append(fullname.substring(1)).append(" ");
                }
                hoVaTen = sb.toString().trim();

                try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                     PreparedStatement ps = conn.prepareStatement(
                             "INSERT INTO SinhVienChoXacNhan ( Mat_Khau, Tai_Khoan,Ho_Va_Ten, Ma_SinhVien, Email, SDT, Gioi_Tinh, doituong,NgayDangKy) VALUES (?, ?,?, ?, ?, ?, ?,?,?)")) {
                    ps.setString(1, pass);
                    ps.setString(2, taiKhoan);
                    ps.setString(3, hoVaTen);
                    ps.setString(4, maSV);
                    ps.setString(5, email);
                    ps.setString(6, sdt);
                    ps.setString(7, gioiTinh);
                    ps.setString(8, doiTuong);
                    ps.setDate(9, Date);

                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Gửi đăng kí thành công,bạn chờ kích hoạt thẻ để có thể mượn sách");
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "ID đã tồn tại !","",JOptionPane.ERROR_MESSAGE);
                }
            }}

        });


//MÀU
        btnDangki.setBackground(Color.LIGHT_GRAY);
        btnTroLai.setBackground(Color.LIGHT_GRAY);



        pn1.add(btnDangki);


        add(pn1);


    }

    public static void main(String[] args) {
        new Register_User();

    }
}










