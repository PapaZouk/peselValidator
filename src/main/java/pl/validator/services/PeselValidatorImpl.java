package pl.validator.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PeselValidatorImpl implements PeselValidator{
    @Override
    public boolean validatePESEL(String peselNum) {
        return checkControlSumNumber(getPeselNumbers(peselNum));
    }

    @Override
    public int[] getPeselNumbers(String peselNum) {
        return Arrays.stream(peselNum.split(""))
                .mapToInt(Integer::valueOf)
                .toArray();
    }

    @Override
    public boolean checkControlSumNumber(int[] numbersArray) {
        return calculate(numbersArray) == 0;
    }

    @Override
    public int calculate(int[] numbersArray) {
        // (Y *1 + Y*3 + M*7 + M*9 + D*1 + D*3 + S*7 + S*9 + S*1 + P*3 + C*1) % 10 == 0
        int y1 = numbersArray[0];
        int y2 = numbersArray[1];
        int m1 = numbersArray[2];
        int m2 = numbersArray[3];
        int d1 = numbersArray[4];
        int d2 = numbersArray[5];
        int s1 = numbersArray[6];
        int s2 = numbersArray[7];
        int s3 = numbersArray[8];
        int p = numbersArray[9];
        int c = numbersArray[10];
        int calculation = y1 + (y2 * 3 ) + (m1 * 7) + (m2 * 9) + d1 + (d2 * 3) + (s1 * 7) + (s2 * 9) + s3 + (p * 3) + c;
        return calculation % 10;
    }

}
