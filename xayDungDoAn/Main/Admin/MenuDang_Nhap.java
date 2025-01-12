package DoAncuoiki1.xayDungDoAn.Main.Admin;

import DoAncuoiki1.xayDungDoAn.Main.Login_Admin;
import DoAncuoiki1.xayDungDoAn.Main.Admin.Menu.quan_Ly_Sach;
import DoAncuoiki1.xayDungDoAn.Main.Admin.Menu.quan_Ly_The;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuDang_Nhap extends JFrame {
    private JLabel lbl1;
    private JButton btn1,btn2,btn3,btn4,btn5;
    private JPanel pn1;

    public MenuDang_Nhap(){
        super("");
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        pn1 = new JPanel();
        pn1.setLayout(null);


        lbl1 = new JLabel("Quản lý thư viện");
        lbl1.setBounds(125,40, 300, 40);
        lbl1.setFont(new Font("Arial", Font.BOLD, 30));
        lbl1.setForeground(Color.RED);
        pn1.add(lbl1);

        btn1 = new JButton("Quản lý sách");
        btn1.setBounds(142,130,200,40);
        btn1.setFont(new Font("Arial", Font.PLAIN, 20));
        btn1.setBackground(Color.LIGHT_GRAY);
        btn1.setFocusPainted(false);
        pn1.add(btn1);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new quan_Ly_Sach();
            }
        });


        btn3 = new JButton("Quản lý thẻ");
        btn3.setBounds(142,230,200,40);
        btn3.setFont(new Font("Arial", Font.PLAIN, 20));
        btn3.setBackground(Color.LIGHT_GRAY);
        btn3.setFocusPainted(false);
        pn1.add(btn3);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new quan_Ly_The();
                MenuDang_Nhap.this.dispose();
            }
        });

        btn4 = new JButton("Quản lý mượn trả");
        btn4.setBounds(142,330,200,40);
        btn4.setFont(new Font("Arial", Font.PLAIN, 20));
        btn4.setBackground(Color.LIGHT_GRAY);
        btn4.setFocusPainted(false);
        pn1.add(btn4);


        btn5 = new JButton("Đăng xuất");
        btn5.setBounds(142,430,200,40);
        btn5.setFont(new Font("Arial", Font.PLAIN, 20));
        btn5.setBackground(Color.LIGHT_GRAY);
        btn5.setFocusPainted(false);
        pn1.add(btn5);
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuDang_Nhap.this.dispose();
                new Login_Admin();
            }
        });

        add(pn1);
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {

        new MenuDang_Nhap();
    }
}
