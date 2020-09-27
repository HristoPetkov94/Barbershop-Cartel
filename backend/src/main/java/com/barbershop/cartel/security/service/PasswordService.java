package com.barbershop.cartel.security.service;

import com.barbershop.cartel.notifications.email.interfaces.EmailInterface;
import com.barbershop.cartel.notifications.email.models.EmailDetailsModel;
import com.barbershop.cartel.security.entity.UserEntity;
import com.barbershop.cartel.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailInterface emailInterface;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    private boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private String generateTemporaryPassword() {

        int passwordLength = 15;

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        StringBuilder password = new StringBuilder(passwordLength);

        for (int i = 0; i < passwordLength; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length() * Math.random());

            // add Character one by one in end of password
            password.append(AlphaNumericString.charAt(index));
        }

        return password.toString();
    }

    private EmailDetailsModel emailDetails(String password) {

        EmailDetailsModel emailDetailsModel = new EmailDetailsModel();

        emailDetailsModel.setFrom("noreply@barbershop-cartel.com");
        emailDetailsModel.setTo("petkovhristo94@gmail.com");
        emailDetailsModel.setSubject("Забравена парола");
        emailDetailsModel.setText("Здравей!\n\n Временната Ви парола е <b>" + password + "</b>. \n\nМоля сменете си я от тук: <a href=https://www.google.bg>www.google.bg</a> \n\nПоздрави!");

        return emailDetailsModel;
    }

    public void changePassword(String email, String password) {

        UserEntity user = userRepository.findByEmail(email);
        user.setPassword(bcryptEncoder.encode(password));

        userRepository.save(user);
        log.info("New password for user:" + user.getEmail() + " has been applied.");
    }

    /* ако трябва да добавя таблицата password_change_requests https://stackoverflow.com/questions/1102781/best-way-for-a-forgot-password-implementation*/
    public void forgotPassword(String email) {

        if (userExists(email)) {

            String password = generateTemporaryPassword();

            changePassword(email, password);

            emailInterface.sendMailMessage(emailDetails(password));
        } else {
            throw new IllegalArgumentException("Email:" + email + " does not exist");
        }
    }
}