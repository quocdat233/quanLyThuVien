package DoAncuoiki1.xayDungDoAn.Main;

import DoAncuoiki1.xayDungDoAn.Main.Admin.Menu.quan_Ly_Sach;
import DoAncuoiki1.xayDungDoAn.Main.Admin.MenuDang_Nhap;
import DoAncuoiki1.xayDungDoAn.Main.Admin.Register_Admin;
import DoAncuoiki1.xayDungDoAn.Main.User.Register_User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Login_Admin extends JFrame {


    private JLabel lblIcon1,lblIcon2,lblIcon3, lbl1, lbl2, lbl3, lbl4,lbl5,lbl6,lbl7;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnRegister, btnLogin,btnEye;
    private JPanel pn1, pn2;
    private ImageIcon icon1, icon2,icon3;
    
    public Login_Admin(){
        super("Login_Admin");

        pn1 = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:\\HOCTAP\\JAVA\\Quan_Ly_Thu_Vien\\src\\DoAncuoiki1\\xayDungDoAn\\image\\wmremove-transformed (1).png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };


        pn1.setBounds(0, 0, 660, 515);


        lbl1 = new JLabel("HỆ THỐNG QUẢN LÝ THƯ VIỆN");
        lbl1.setForeground(Color.BLACK);
        lbl1.setFont(new Font("Arial", Font.BOLD, 30));
        lbl1.setBounds(95, 20, 490, 50);

        lbl2 = new JLabel("V");
        lbl2.setForeground(Color.RED);
        lbl2.setFont(new Font("Arial", Font.BOLD, 45));
        lbl2.setBounds(280, 67, 40, 30);

        lbl3 = new JLabel("K");
        lbl3.setForeground(Color.YELLOW);
        lbl3.setFont(new Font("Arial", Font.BOLD, 45));
        lbl3.setBounds(312, 67, 40, 30);

        lbl4 = new JLabel("U");
        lbl4.setForeground(Color.BLUE);
        lbl4.setFont(new Font("Arial", Font.BOLD, 45));
        lbl4.setBounds(344, 67, 40, 30);


        pn1.add(lbl1);
        pn1.add(lbl2);
        pn1.add(lbl3);
        pn1.add(lbl4);

        // Panel bên phải
        pn2 = new JPanel();
        pn2.setLayout(null);
        pn2.setBounds(660, 0, 320, 515);

        icon1 = new ImageIcon("C:\\HOCTAP\\JAVA\\Quan_Ly_Thu_Vien\\src\\DoAncuoiki1\\xayDungDoAn\\image\\people.png");
        icon2 = new ImageIcon("C:\\HOCTAP\\JAVA\\Quan_Ly_Thu_Vien\\src\\DoAncuoiki1\\xayDungDoAn\\image\\padlock.png");
        icon3 = new ImageIcon("C:\\HOCTAP\\JAVA\\Quan_Ly_Thu_Vien\\src\\DoAncuoiki1\\xayDungDoAn\\image\\administrator.png");

        icon1 = new ImageIcon(icon1.getImage().getScaledInstance(17, 17, Image.SCALE_AREA_AVERAGING));
        icon2 = new ImageIcon(icon2.getImage().getScaledInstance(22, 21 , Image.SCALE_AREA_AVERAGING));
        icon3 = new ImageIcon(icon3.getImage().getScaledInstance(80, 80 , Image.SCALE_AREA_AVERAGING));
        Color color = pn2.getBackground();

        lbl5 = new JLabel("Đăng nhập");
        lbl5.setFont(new Font("Arial", Font.BOLD, 28));
        lbl5.setForeground(Color.BLACK);
        lbl5.setBounds(40, 120, 200, 35);

        lbl6 = new JLabel("Chào mừng bạn đến với thư viện VKU");
        lbl6.setFont(new Font("Arial", Font.PLAIN, 12));
        lbl6.setForeground(Color.GRAY);
        lbl6.setBounds(41, 155, 300, 30);

        lblIcon3 = new JLabel(icon3);
        lblIcon3.setBounds(122, 15, 90, 90);

        // Đặt vị trí lblUser và txtUser



        txtUser = new JTextField("Tài khoản");
        txtUser.setBounds(60, 232, 185, 19);
        txtUser.setFont(new Font("Arial", Font.PLAIN, 10));
        txtUser.setBackground(color);
        txtUser.setForeground(Color.DARK_GRAY);
        txtUser.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        txtUser.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtUser.getText().equals("Tài khoản")) {
                    txtUser.setText("");
                    txtUser.setForeground(Color.BLACK);}}
            @Override
            public void focusLost(FocusEvent e) {
                if (txtUser.getText().isEmpty()) {
                    txtUser.setText("Tài khoản");
                    txtUser.setForeground(Color.darkGray);}}
        });


        // Đặt vị trí lblPass và txtPass

        txtPass = new JPasswordField("Mật khẩu");
        txtPass.setBounds(60, 271,185, 20);
        txtPass.setEchoChar((char) 0);
        txtPass.setFont(new Font("Arial", Font.PLAIN, 10));
        txtPass.setBackground(color);
        txtPass.setForeground(Color.darkGray);
        txtPass.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        txtPass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(txtPass.getPassword()).equals("Mật khẩu")) {
                    txtPass.setText("");
                    txtPass.setEchoChar('•');
                    txtPass.setForeground(Color.BLACK);}}
            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(txtPass.getPassword()).isEmpty()) {
                    txtPass.setText("Mật khẩu");
                    txtPass.setEchoChar((char) 0);
                    txtPass.setForeground(Color.darkGray);}}});


        // Đặt vị trí nút Đăng ký và Đăng nhập
        lbl7 = new JLabel("Bạn chưa có tài khoản ?");
        lbl7.setFont(new Font("Arial", Font.PLAIN, 11));
        lbl7.setForeground(Color.BLACK);
        lbl7.setBounds(60, 420, 120, 30);

        btnRegister = new JButton("Đăng ký");
        btnRegister.setFont(new Font("Arial", Font.PLAIN, 11));
        btnRegister.setForeground(Color.decode("#C50023"));
        btnRegister.setBounds(170, 425, 75, 20);
        btnRegister.setFocusPainted(false);
        btnRegister.setContentAreaFilled(false); // Không tô màu nền
        btnRegister.setBorderPainted(false); // Không vẽ viền


        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Khi di chuột vào, thay đổi màu chữ
                btnRegister.setForeground(Color.decode("#0000FF")); // Đổi màu chữ khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Khi chuột ra ngoài, trả lại màu chữ ban đầu
                btnRegister.setForeground(Color.decode("#C50023")); // Màu chữ ban đầu
            }
        });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register_Admin();

            }
        });

        btnLogin = new JButton("Đăng nhập");
        btnLogin.setBounds(60, 340, 185, 23);
        btnLogin.setFont(new Font("Arial ", Font.BOLD, 12));
        btnLogin.setFocusPainted(false);
        btnLogin.setBackground(Color.lightGray);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtUser.getText().equals("quocdat233") && String.valueOf(txtPass.getPassword()).equals("dat23326")){
                    new quan_Ly_Sach();
                   dispose();
                }
                else
                    JOptionPane.showMessageDialog(Login_Admin.this, "Sai tài khoản hoặc mật khẩu");
            }
        });

        lblIcon1 = new JLabel(icon1);
        lblIcon1.setBounds(20, 228, 35, 30);
        lblIcon2 = new JLabel(icon2);
        lblIcon2.setBounds(22,268,30,30);


        btnEye = new JButton("\uD83D\uDC41"); // Icon con mắt
        btnEye.setBounds(224, 272, 55, 20); // Vị trí bên phải trường mật khẩu
        btnEye.setBorderPainted(false);
        btnEye.setFocusPainted(false);
        btnEye.setContentAreaFilled(false);     // Không tô nền
        btnEye.setOpaque(false); // Trong suốt
        btnEye.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành biểu tượng tay

        btnEye.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Hiển thị mật khẩu khi nhấn chuột vào nút mắt
                if(txtPass.getText().equals("Mật khẩu")){}
                else {
                    txtPass.setEchoChar((char) 0); // Loại bỏ ký tự ẩn mật khẩu
                }}
            @Override
            public void mouseExited(MouseEvent e) {
                // Ẩn mật khẩu khi chuột rời khỏi nút mắt
                if(txtPass.getText().equals("Mật khẩu")){}
                else{
                    txtPass.setEchoChar('•'); // Đặt lại ký tự ẩn mật khẩu
                }}
        });




        pn2.add(btnLogin);
        pn2.add(btnRegister);
        pn2.add(txtPass);
        pn2.add(txtUser);
        pn2.add(lbl5);
        pn2.add(lbl6);
        pn2.add(lbl7);
        pn2.add(lblIcon1);
        pn2.add(lblIcon2);
        pn2.add(lblIcon3);
        pn2.add(btnEye);


        this.add(pn1);
        this.add(pn2);
        this.setFocusable(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               new Hello();
            }
        });

        this.setSize(980, 550);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        setResizable(false);




    }

    public static void main(String[] args) {
        new Login_Admin();
    }

}
