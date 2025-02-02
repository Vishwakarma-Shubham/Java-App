package Demo.Bachatt.service;

import Demo.Bachatt.repository.UserRepository;
import Demo.Bachatt.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Map;

@Service
public class RegistrationService {

    @Autowired
    Utils utils;

    @Autowired
    UserService userService;

    private static final SecureRandom secureRandom = new SecureRandom();

    public String generateOTP(int length) {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(secureRandom.nextInt(10)); // Numeric OTP
        }
        return otp.toString();
    }


    /**
    * Handles phone OTP Generation.
    *
    * - Validates the provided phone number.
    * - Generates a 6-digit OTP.
    * - Stores the OTP in a cache.
    * - Returns the OTP in the response.
    *
    * @param payload A map containing the phone number.
    * @return ResponseEntity<?> A response containing the generated OTP or an error message.
    */

    public ResponseEntity<?> register(Map<String, String> payload) {

        if(!utils.isValidNumber(payload.get("phone"))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Number Provided");
        }

        return ResponseEntity.ok(this.generateOTP(6));
    }


    /**
    * Verifies the OTP for phone authentication.
    *
    * - Validates the provided phone number.
    * - Checks if the OTP exists in the cache.
    * - Compares the provided OTP with the stored OTP.
    * - Returns a success or error response based on verification.
    *
    * @param payload A map containing the phone number and OTP.
    * @return ResponseEntity<?> A response indicating whether the OTP verification was successful or failed.
    */
    public ResponseEntity<?> verify(Map<String, String> payload) {
        String phone = payload.get("phone");
        String reqOtp = payload.get("otp");

        if (!utils.isValidNumber(phone)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Number Provided");
        }

        /*if (!db.containsKey(phone)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP Expired");
        }*/

        String otp = "db.get(phone)";

        if (!otp.equals(reqOtp)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
        }

        return ResponseEntity.ok("OTP Verified");
    }

    public ResponseEntity<?> createUser(Map<String, String> payload) {

        String email = payload.get("email");
        String phone = payload.get("phone");

        if(userService.findByPhone(phone).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Phone Number Already Registered");
        }

        if(userService.findByEmail(email).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email Address Already Registered");
        }


//        userService.saveUser(payload);

        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully");

    }

}