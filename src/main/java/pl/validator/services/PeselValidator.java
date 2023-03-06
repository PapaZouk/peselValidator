package pl.validator.services;

public interface PeselValidator {

    boolean validatePESEL(String peselNum);

    boolean checkPeselNumbers(int[] numbersArray);

    int calculate(int[] numbersArray);

    int[] getPeselNumbers(String peselNum);


}
