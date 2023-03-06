package pl.validator.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PeselGeneratorTwentiethTest {

    private static PeselGenerator generator;
    @BeforeEach
    void setUp() {
        generator = new PeselGeneratorTwentieth();
    }

    @Test
    void generatePesel() {
        // given
        int expectedLength = 11;
        // when
        String pesel = generator.generatePesel(0, 10);
        System.out.println(pesel);

        // then
        Assertions.assertEquals(expectedLength, pesel.length());
    }
}