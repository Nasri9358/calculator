import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            calc(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void calc(String input) {

        String math = input.replace(" ", "");
        if (math.contains("+")) {
            String[] numbers = math.split("\\+");
            if (numbers.length != 2) throw new ArithmeticException();
            try {
                resultArabic(numbers, TypeOperation.ADDITION);
            } catch (NumberFormatException nfe) {
                try {
                    resultRome(numbers, TypeOperation.ADDITION);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (math.contains("-")) {
            String[] numbers = math.split("-");
            if (numbers.length != 2) throw new ArithmeticException();
            try {
                resultArabic(numbers, TypeOperation.SUBTRACTION);
            } catch (NumberFormatException nfe) {
                try {
                    resultRome(numbers, TypeOperation.SUBTRACTION);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (math.contains("/")) {
            String[] numbers = math.split("/");
            if (numbers.length != 2) throw new ArithmeticException();
            try {
                resultArabic(numbers, TypeOperation.DIVISION);
            } catch (NumberFormatException nfe) {
                try {
                    resultRome(numbers, TypeOperation.DIVISION);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (math.contains("*")) {
            String[] numbers = math.split("\\*");
            if (numbers.length != 2) throw new ArithmeticException();
            try {
                resultArabic(numbers, TypeOperation.MULTIPLIED);
            } catch (NumberFormatException nfe) {
                try {
                    resultRome(numbers, TypeOperation.MULTIPLIED);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else throw new ArithmeticException();
    }

    private static void resultRome(String[] numbers, TypeOperation type) throws Exception {
        int value1 = toArabic(numbers[0]);
        int value2 = toArabic(numbers[1]);

        int result = switch (type) {
            case TypeOperation.ADDITION -> value1 + value2;
            case TypeOperation.SUBTRACTION -> value1 - value2;
            case TypeOperation.MULTIPLIED -> value1 * value2;
            case TypeOperation.DIVISION -> value1 / value2;
        };

        if (result <= 0) throw new Exception();
        int units = result % 10;
        int tens = (result % 100) / 10;
        int hundreds = (result % 1000) / 100;
        System.out.println(hundreds(hundreds) + tens(tens) + units(units));
    }

    private static void resultArabic(String[] numbers, TypeOperation type) throws Exception {
        int value1 = Integer.parseInt(numbers[0]);
        int value2 = Integer.parseInt(numbers[1]);
        if (value1 > 10 || value2 > 10) throw new Exception();
        switch (type) {
            case TypeOperation.ADDITION:
                System.out.println(value1 + value2);
                break;
            case TypeOperation.SUBTRACTION:
                System.out.println(value1 - value2);
                break;
            case TypeOperation.MULTIPLIED:
                System.out.println(value1 * value2);
                break;
            case TypeOperation.DIVISION:
                System.out.println(value1 / value2);
                break;
        }
    }

    private static int toArabic(String rome) {
        return switch (rome.toUpperCase()) {
            case ("I") -> 1;
            case ("II") -> 2;
            case ("III") -> 3;
            case ("IV") -> 4;
            case ("V") -> 5;
            case ("VI") -> 6;
            case ("VII") -> 7;
            case ("VIII") -> 8;
            case ("IX") -> 9;
            case ("X") -> 10;
            default -> throw new ArithmeticException();
        };
    }

    public static String units(int units) {
        return switch (units) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            default -> "";
        };
    }

    public static String tens(int tens) {
        return switch (tens) {
            case 1 -> "X";
            case 2 -> "XX";
            case 3 -> "XXX";
            case 4 -> "XL";
            case 5 -> "L";
            case 6 -> "LX";
            case 7 -> "LXX";
            case 8 -> "LXXX";
            case 9 -> "XC";
            default -> "";
        };
    }

    public static String hundreds(int hundreds) {
        String s_hundreds = "";
        if (hundreds == 1) s_hundreds = "C";
        return s_hundreds;
    }
}