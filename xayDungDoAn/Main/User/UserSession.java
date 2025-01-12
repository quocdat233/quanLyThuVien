package DoAncuoiki1.xayDungDoAn.Main.User;



public class UserSession {
    private static String maSinhVien; // Biến static để lưu mã sinh viên

    // Getter cho mã sinh viên
    public static String getMaSinhVien() {
        return maSinhVien;
    }

    // Setter để lưu mã sinh viên
    public static void setMaSinhVien(String maSinhVien) {
        UserSession.maSinhVien = maSinhVien;
    }

    // Phương thức xóa thông tin session (nếu cần)
    public static void clearSession() {
        maSinhVien = null;
    }
}
