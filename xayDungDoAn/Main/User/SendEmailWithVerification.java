package DoAncuoiki1.xayDungDoAn.Main.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class SendEmailWithVerification {
    public static void main(String[] args) {
        // Thông tin người gửi
        String maiGui = "thuvienvku@gmail.com"; // Thay bằng email của bạn
        String passMail = "ixwf dqle pphr lrgw"; // Thay bằng mật khẩu ứng dụng

        // Thông tin người nhận
        String emailNhan = "dat23326@gmail.com"; // Email người nhận

        // Tạo mã xác nhận ngẫu nhiên
        String verificationCode = generateVerificationCode();
        System.out.println("Mã xác nhận là: " + verificationCode); // In ra để bạn kiểm tra trong quá trình phát triển (nên loại bỏ khi triển khai thực tế)

        // Thiết lập các thuộc tính của mail server (SMTP)
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Tạo phiên làm việc với server
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(maiGui, passMail);
            }
        });

        try {
            // Tạo email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(maiGui)); // Email người gửi
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailNhan) // Email người nhận
            );
            message.setSubject("Mã xác nhận của bạn"); // Tiêu đề email
            message.setText("Xin chào, đây là mã xác nhận của bạn: " + verificationCode); // Nội dung email

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công!");

            // Kiểm tra mã xác nhận người dùng nhập vào
            Scanner scanner = new Scanner(System.in);
            System.out.print("Vui lòng nhập mã xác nhận đã gửi vào email: ");
            String userCode = scanner.nextLine();

            if (userCode.equals(verificationCode)) {
                System.out.println("Mã xác nhận đúng!");
            } else {
                System.out.println("Mã xác nhận không đúng. Vui lòng thử lại.");
            }

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Không thể gửi email!");
        }
    }

    // Hàm tạo mã xác nhận ngẫu nhiên 6 chữ số
    public static String generateVerificationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10)); // Thêm số ngẫu nhiên vào mã
        }
        return code.toString();
    }
}
