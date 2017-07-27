package be.xplore.recruitment.domain.util;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public class Validator {
    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+" +
                "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])" +
                "|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidPhone(String phone) {
        String regex = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|" +
                "3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|" +
                "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}";
        Pattern p = compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isValidDate(Date date) {
        Date after = new Calendar.Builder().setDate(1900, 1, 1).build().getTime();
        return date.after(after);
    }
}
