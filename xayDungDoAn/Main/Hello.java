package DoAncuoiki1.xayDungDoAn.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public  class Hello extends JFrame {
    private JLabel lbl1, lbl2;
    private JButton btnadmin, btnUser;
    private JPanel pn1;
    private ImageIcon icon1, icon2;

    public Hello() {
        this.setTitle("Hệ Thống Quản Lý Thư Viện VKU");
        pn1 = new JPanel(null)
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:\\HOCTAP\\JAVA\\Quan_Ly_Thu_Vien\\src\\DoAncuoiki1\\xayDungDoAn\\image\\pexels-technobulka-2908984.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        lbl1 = new JLabel("HỆ THỐNG QUẢN LÝ THƯ VIỆN VKU");
        lbl1.setForeground(Color.white);
        lbl1.setFont(new Font("Arial", Font.BOLD, 38));
        lbl1.setBounds(40, 40, 700, 50);

        lbl2 = new JLabel("Đăng nhập với quyền");
        lbl2.setFont(new Font("Arial", Font.BOLD, 18));
        lbl2.setForeground(Color.WHITE);
        lbl2.setBounds(277, 143, 200, 30);

        btnadmin = new JButton("Admin");
        btnadmin.setBounds(320, 205, 100, 30);
        btnadmin.setForeground(Color.WHITE);
        btnadmin.setBackground(Color.DARK_GRAY);

        btnUser = new JButton("User");
        btnUser.setForeground(Color.WHITE);
        btnUser.setBounds(320, 265, 100, 30);
        btnUser.setBackground(Color.DARK_GRAY);


        icon1 = new ImageIcon("C:/HOCTAP/JAVA/baitap/src/DoAncuoiki1/xayDungDoAn/image/admin.png");
        icon2 = new ImageIcon("C:/HOCTAP/JAVA/baitap/src/DoAncuoiki1/xayDungDoAn/image/user.png");
        icon1 = new ImageIcon(icon1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        icon2 = new ImageIcon(icon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));


        btnadmin.setIcon(icon1);
        btnadmin.setMargin(new Insets(5,5 , 5, 10));
            btnadmin.setFocusPainted(false);
        btnadmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login_Admin();
                Hello.this.dispose();
            }
        });




        btnUser.setIcon(icon2);
        btnUser.setMargin(new Insets(5, 3, 5, 12));
        btnUser.setFocusPainted(false);
       btnUser.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new Login_User();
               Hello.this.dispose();
           }
       });


        pn1.add(lbl1);
        pn1.add(lbl2);
        pn1.add(btnadmin);
        pn1.add(btnUser);

        setSize(750, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        this.add(pn1);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {

        new Hello();
    }
}



