package pl.validator.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.validator.enums.PeselGeneratorType;

class ValidationResultGeneratorFactoryTest {

    @Test
    @DisplayName("Should return proper PeselGenerator class")
    void shouldBuildProperGeneratorType() {
        // given
        PeselGeneratorType nineteenthCentury = PeselGeneratorType.NINETEENTH_CENTURY;
        PeselGeneratorType twentiethCentury = PeselGeneratorType.TWENTIETH_CENTURY;
        PeselGeneratorType twentyFirstCentury = PeselGeneratorType.TWENTY_FIRST_CENTURY;

        PeselGenerator expected1 = new PeselGeneratorNineteenthCentury();
        PeselGenerator expected2 = new PeselGeneratorTwentieth();
        PeselGenerator expected3 = new PeselGeneratorTwentyFirstCentury();

        // when
        PeselGenerator result1 = PeselGeneratorFactory.build(nineteenthCentury);
        PeselGenerator result2 = PeselGeneratorFactory.build(twentiethCentury);
        PeselGenerator result3 = PeselGeneratorFactory.build(twentyFirstCentury);

        // then
        Assertions.assertEquals(expected1.getClass(), result1.getClass());
        Assertions.assertEquals(expected2.getClass(), result2.getClass());
        Assertions.assertEquals(expected3.getClass(), result3.getClass());
    }
}