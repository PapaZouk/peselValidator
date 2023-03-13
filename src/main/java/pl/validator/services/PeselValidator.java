package pl.validator.services;

public interface PeselValidator {

    boolean validatePESEL(String peselNum);

    boolean checkControlSumNumber(int[] numbersArray);

    int calculate(int[] numbersArray);

    int[] getPeselNumbers(String peselNum);


}
