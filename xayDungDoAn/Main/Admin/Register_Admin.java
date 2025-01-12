package DoAncuoiki1.xayDungDoAn.Main.Admin;

import javax.swing.*;
import java.awt.*;

public  class Register_Admin extends JFrame {
    public Register_Admin() {
        super("Register_User");
        JLabel customMessage = new JLabel("Vui lòng đến thư viện VKU để đăng kí quyền quản lý ");
        customMessage.setForeground(new Color(204, 48, 0));
        customMessage.setFont(new Font("Arial", Font.BOLD, 16));
        JOptionPane.showMessageDialog(this, customMessage, "Thông báo",JOptionPane.INFORMATION_MESSAGE
        );
    }

//    public static void main(String[] args) {
//        new Register_Admin();
//    }
}
