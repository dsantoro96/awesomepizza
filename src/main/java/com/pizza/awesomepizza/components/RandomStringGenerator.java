package com.pizza.awesomepizza.components;

import com.pizza.awesomepizza.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Slf4j
@Component
@RequiredArgsConstructor
public class RandomStringGenerator {

    private final SecureRandom secureRandom;

    private char getRandomChar() {
        int idx = secureRandom.nextInt(Constants.CHARACTERS.length());

        return Constants.CHARACTERS.charAt(idx);
    }

    public String generateString(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(getRandomChar());
        }

        return sb.toString();
    }

}
