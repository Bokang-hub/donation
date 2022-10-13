package za.ac.cput.donation.util;

public class Helper {
    public static boolean isValid(int d){
        return d > 0;
    }
    public static boolean isValid(long d){
        return d > 0;
    }
    public static boolean isValid(String d){
        return d != null && !d.trim().equals("");
    }
    public static boolean isValidPassword(String p){
        return isValid(p) && p.length() > 4;
    }
}
