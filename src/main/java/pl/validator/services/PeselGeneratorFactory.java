package pl.validator.services;

import org.springframework.stereotype.Service;
import pl.validator.enums.PeselGeneratorType;

@Service
public abstract class PeselGeneratorFactory implements PeselGenerator{

    public static PeselGenerator build(PeselGeneratorType peselType) {
        return selectPeselType(peselType);
    }

    private static PeselGenerator selectPeselType(PeselGeneratorType peselType) {
        if (peselType.equals(PeselGeneratorType.TWENTIETH_CENTURY)) {
            return new PeselGeneratorTwentieth();
        } else if (peselType.equals(PeselGeneratorType.TWENTY_FIRST_CENTURY)) {
            return new PeselGeneratorTwentyFirstCentury();
        } else if (peselType.equals(PeselGeneratorType.NINETEENTH_CENTURY)) {
            return new PeselGeneratorNineteenthCentury();
        } else {
            throw new IllegalArgumentException("Wrong PESEL generator type");
        }
    }

}
