package pl.validator.services;

import pl.validator.enums.PeselGeneratorType;
import pl.validator.model.Sex;

import java.time.LocalDate;
import java.util.Random;

public class DateToPeselParser {

    private static final Random random = new Random();

    public static String parseDateToPesel(LocalDate date, PeselGeneratorType generatorType, Sex gender) {
        String year = String.valueOf(date.getYear()).substring(2);

        int monthValue = date.getMonthValue();
        if (PeselGeneratorType.TWENTY_FIRST_CENTURY.equals(generatorType)) {
            monthValue += 20;
        } else if (PeselGeneratorType.NINETEENTH_CENTURY.equals(generatorType)) {
            monthValue += 80;
        }

        String month = String.valueOf(monthValue).length() == 1
                ? "0" + String.valueOf(date.getMonthValue())
                : String.valueOf(monthValue);
        String day = String.valueOf(date.getDayOfMonth()).length() == 1
                ? "0" + String.valueOf(date.getDayOfMonth())
                : String.valueOf(date.getDayOfMonth());
        String serialNum = generateSerialNum();
        String sex = Sex.MALE.equals(gender)
                ? generateSex(1)
                : Sex.FEMALE.equals(gender)
                    ? generateSex(2)
                    : generateSex(0);
        String controlNum = generateControlNum(year, month, day, serialNum, sex);

        return year + month + day + serialNum + sex + controlNum;
    }

    public static String parseDateToPesel(LocalDate date, PeselGeneratorType generatorType) {

        String year = String.valueOf(date.getYear()).substring(2);

        int monthValue = date.getMonthValue();
        if (PeselGeneratorType.TWENTY_FIRST_CENTURY.equals(generatorType)) {
            monthValue += 20;
        } else if (PeselGeneratorType.NINETEENTH_CENTURY.equals(generatorType)) {
            monthValue += 80;
        }

        String month = String.valueOf(monthValue).length() == 1
                ? "0" + String.valueOf(date.getMonthValue())
                : String.valueOf(monthValue);
        String day = String.valueOf(date.getDayOfMonth()).length() == 1
                ? "0" + String.valueOf(date.getDayOfMonth())
                : String.valueOf(date.getDayOfMonth());
        String serialNum = generateSerialNum();
        String sex = generateSex(0);
        String controlNum = generateControlNum(year, month, day, serialNum, sex);

        return year + month + day + serialNum + sex + controlNum;
    }

    private static String generateSerialNum() {
        return String.valueOf(random.nextInt(100, 1000));
    }

    private static String generateSex(int gender) {
        int genderNum = random.nextInt(0, 10);
        if (gender == 1) {
            if (genderNum % 2 == 0) {
                return String.valueOf(genderNum);
            }
            generateSex(1);
        } else if (gender == 2) {
            if (genderNum % 2 != 0) {
                return String.valueOf(genderNum);
            }
            generateSex(2);
        }
        return String.valueOf(genderNum);
    }

    private static String generateControlNum(String year, String month, String day, String serialNum, String sex) {
        int y1 = Integer.parseInt(year.substring(0, 1));
        int y2 = (Integer.parseInt(year.substring(1)) * 3);
        int m1 = Integer.parseInt(month.substring(0, 1)) * 7;
        int m2 = Integer.parseInt(month.substring(1)) * 9;
        int d1 = Integer.parseInt(day.substring(0, 1));
        int d2 = Integer.parseInt(day.substring(1)) * 3;
        int s1 = Integer.parseInt(serialNum.substring(0, 1)) * 7;
        int s2 = Integer.parseInt(serialNum.substring(1, 2)) * 9;
        int s3 = Integer.parseInt(serialNum.substring(2, 3));
        int p = Integer.parseInt(sex) * 3;

        int controlNumberValue = (
                y1 +
                        (y2 >= 10 ? Integer.parseInt(String.valueOf(y2).substring(1)) : y2) +
                        (m1 >= 10 ? Integer.parseInt(String.valueOf(m1).substring(1)) : m1) +
                        (m2 >= 10 ? Integer.parseInt(String.valueOf(m2).substring(1)) : m2) +
                        d1 +
                        (d2 >= 10 ? Integer.parseInt(String.valueOf(d2).substring(1)) : d2) +
                        (s1 >= 10 ? Integer.parseInt(String.valueOf(s1).substring(1)) : s1) +
                        (s2 >= 10 ? Integer.parseInt(String.valueOf(s2).substring(1)) : s2) +
                        s3 +
                        (p >= 10 ? Integer.parseInt(String.valueOf(p).substring(1)) : p)
        );

        if (controlNumberValue > 10) {

            int controlNumberSecondDigit = Integer.parseInt(String.valueOf(controlNumberValue).substring(1));
            if (controlNumberSecondDigit == 0) {
                return "0";
            }
            return String.valueOf(
                    Math.abs(controlNumberSecondDigit - 10)
            );
        }
        return String.valueOf(Math.abs(Integer.parseInt(String.valueOf(controlNumberValue)) - 10));
    }
}
