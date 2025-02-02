package Demo.Bachatt.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    public boolean isValidNumber(Object number) {

        if (number == null) {
            return false; // Null is not a valid number
        }

        String num = String.valueOf(number).trim();

        // Check if it's a numeric value and has a valid length (Assuming 10-digit phone number)
        if (!num.matches("\\d{10}")) {
            return false;
        }

        // Check if the first digit is 9 or greater
        char firstDigit = num.charAt(0);

        return firstDigit >= '6';
    }

}
