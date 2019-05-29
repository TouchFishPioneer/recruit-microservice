package cn.herculas.recruit.student.util.formatter;

public class QueryFormatter {
    public static String regionQueryFormatter(String region) {
        if (region.equals("000000")) {
            return "%";
        } else if (region.substring(2, 6).equals("0000")) {
            return region.substring(0, 2) + "%";
        } else if (region.substring(4, 6).equals("00")) {
            return region.substring(0, 4) + "%";
        } else {
            return region;
        }
    }
}
