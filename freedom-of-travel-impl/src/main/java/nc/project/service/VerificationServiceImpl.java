package nc.project.service;

import lombok.RequiredArgsConstructor;
import nc.project.jpa.entity.User;
import nc.project.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final UserRepository userRepository;

    @Override
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isVerified()) {
            return false;
        } else {
            user.setVerified(true);
            userRepository.save(user);

            return true;
        }
    }

}