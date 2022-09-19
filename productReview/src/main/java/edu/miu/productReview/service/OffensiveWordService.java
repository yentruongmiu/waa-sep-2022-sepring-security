package edu.miu.productReview.service;

import edu.miu.productReview.domain.OffensiveWordRecord;
import edu.miu.productReview.domain.User;
import edu.miu.productReview.repo.OffensiveWordRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class OffensiveWordService {
    private static final Set<String> dictionary = new HashSet<>();
    private final OffensiveWordRedisRepository offensiveWordRepo;

    static {
        dictionary.add("springing");
    }

    public Optional<String> maskOffensiveWords(String text) {
        AtomicReference<Optional<String>> maskedText = new AtomicReference<>(Optional.empty());
        dictionary.parallelStream()
                .filter(text::contains)
                .forEach(key -> {
                    maskedText.set(Optional.of(text
                            .replaceAll(key, maskText(key))));
                });
        return maskedText.get();
    }

    public boolean validateOffensiveWords(String value) {
        int userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        boolean isViolated = dictionary.parallelStream()
                .anyMatch(value::contains);
        return offensiveWordRepo.findById(userId)
                .map(owRecord -> {
                    if(owRecord.isBlocked()){
                        return false;
                    }
                    if(isViolated){
                        if (owRecord.getNumOfViolation() > 4) {
                            owRecord.setTtl(60L);
                            owRecord.setBlocked(true);
                            offensiveWordRepo.save(owRecord);
                            return false;
                        } else {
                            owRecord.setNumOfViolation(owRecord.getNumOfViolation() + 1);
                            offensiveWordRepo.save(owRecord);
                        }
                    }
                    return true;
                })
                .orElseGet(() -> {
                    if(isViolated){
                        offensiveWordRepo.save(new OffensiveWordRecord(userId, 1, 120L));
                    }
                    return true;
                });
    }

    private String maskText(String text) {
        int maskLength = (int) (text.length() * 0.7);
        String maskedString = "*".repeat(maskLength);
        StringBuilder sb = new StringBuilder(maskedString);
        sb.append(text.substring(maskLength));
        return sb.toString();
    }
}
