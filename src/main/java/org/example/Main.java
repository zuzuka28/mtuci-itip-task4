package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(firstTask(10, 7, "hello my name is Bessie and this is my essay"));

        System.out.println(split("()()()"));
        System.out.println(split("((()))"));
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(split("((())())(()(()()))"));

        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("getColor"));

        System.out.println(overTime(new double[]{9, 17, 30, 1.5}));
        System.out.println(overTime(new double[]{16, 18, 30, 1.8}));
        System.out.println(overTime(new double[]{13.25, 15, 30, 1.5}));

        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));

        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));

        System.out.println(toStarShorthand("abbccc"));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(toStarShorthand("abc"));
        System.out.println(toStarShorthand(""));

        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));

        System.out.println(trouble(451999277L, 41177722899L));
        System.out.println(trouble(1222345L, 12345L));
        System.out.println(trouble(666789L, 12345667L));
        System.out.println(trouble(33789L, 12345337L));

        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));

    }

    public static int lengthOfChunk(ArrayList<String> source){
        int result = 0;
        for (String word: source){
            result += word.length();
        }
        return result;
    }
    public static ArrayList<ArrayList<String>> firstTask(int n, int k , String source){
        ArrayList<ArrayList<String>> formatted = new ArrayList<>();

        ArrayList<String> separated = new ArrayList<>(List.of(source.split(" ")));

        ArrayList<String> temp = new ArrayList<>();


        for (int i = 0; i < n; i++){
            String word = separated.get(0);
            separated.remove(0);
            if (lengthOfChunk(temp) + word.length() > k) {
                formatted.add(new ArrayList<String>(temp));
                temp.clear();
            }
            temp.add(word);
            if (i == n-1) {
                formatted.add(new ArrayList<String>(temp));
                temp.clear();
            }
        }

        return formatted;
    }

    public static ArrayList<String> split(String source){
        String[] brackets = source.split("");

        ArrayList<String> result = new ArrayList<>();

        Stack<String> stack = new Stack<>();

        StringBuilder temp = new StringBuilder();
        for (String item: brackets) {
            if (item.equals("(")){
                stack.push(item);
                temp.append(item);
            }
            if (item.equals(")")){
                stack.pop();
                temp.append(item);
            }

            if (stack.empty()){
                result.add(temp.toString());
                temp.setLength(0);
            }
        }

        return result;
    }

    public static String toCamelCase(String source){
        String[] separated = source.split("_");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < separated.length; i ++) {
            String word = separated[i];
            if (i == 0) {
                result.append(word);
                continue;
            }
            result.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
        }
        return result.toString();
    }

    public static String toSnakeCase(String source){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < source.length(); i ++){
            String letter = String.valueOf(source.charAt(i));
            if (letter.equals(letter.toUpperCase())){
                result.append("_").append(letter.toLowerCase());
                continue;
            }
            result.append(letter.toLowerCase());
        }
        return result.toString();
    }

    public  static String overTime(double[] args) {
        double startTime = args[0];
        double endTime = args[1];
        double pay = args[2];
        double multPay = args[3];

        double normTimeStart = 9.0;
        double normTimeEnd = 17.0;

        return "$" + (
                Math.max(Math.min(endTime, normTimeEnd) - Math.max(startTime, normTimeStart), 0)*pay +
                        (Math.max(endTime, normTimeEnd) - Math.max(startTime, normTimeEnd))*pay*multPay+
                        (Math.min(endTime, normTimeStart) - Math.min(startTime, normTimeStart))*pay*multPay

                );
    }

    public static  String BMI(String weight, String height) {
        String[] weightSeparated = weight.split(" ");
        String[] heightSeparated = height.split(" ");
        double kgWeight = 0;
        double mHeight = 0;

        switch (weightSeparated[1]) {
            case "pounds" -> kgWeight = Float.parseFloat(weightSeparated[0]) / 2.205;
            case "kilos" -> kgWeight = Float.parseFloat(weightSeparated[0]);
        }
        switch (heightSeparated[1]) {
            case "inches" -> mHeight = Float.parseFloat(heightSeparated[0]) / 39.37;
            case "meters" -> mHeight = Float.parseFloat(heightSeparated[0]);
        }

        double bmi = kgWeight/(mHeight*mHeight);
        bmi = Math.floor(bmi*10)/10;
        if (bmi < 18.5) {
            return bmi + " Overweight";
        } else if ( bmi >= 18.5 && bmi <= 24.9) {
            return bmi + " Normal weight";
        } else if (bmi > 25) {
            return bmi + " Underweight";
        }
        return "";
    }

    public static int bugger(int number){
        String[] digits = Integer.toString(number).split("");
        if (digits.length == 1) {
            return 0;
        }
        int newNumber = 1;
        for (String i: digits){
            newNumber *= Integer.parseInt(i);
        }
        return 1 + bugger(newNumber);
    }

    public  static String toStarShorthand(String source){
        StringBuilder result = new StringBuilder();
        String[] digits = source.split("");
        int similarDigits = 0;
        for (int i = 0; i < digits.length; i++){
            if (i == digits.length - 1 || !digits[i].equals(digits[i+1])  ){
                if (similarDigits == 0) {
                    result.append(digits[i]);
                } else {
                    result.append(digits[i]).append("*").append(similarDigits+1);
                }
                similarDigits = 0;
            } else {
                similarDigits += 1;
            }
        }
        return result.toString();
    }

    public static boolean doesRhyme(String firstString, String secondString) {
        String[] separated = firstString.split(" ");
        String[] firstLastWord = separated[separated.length - 1].toLowerCase().split("");
        separated = secondString.split(" ");
        String[] secondLastWord = separated[separated.length - 1].toLowerCase().split("");

        HashSet<String> vowel = new HashSet<>(Arrays.asList("a", "e", "o", "i", "u", "y"));
        HashSet<String> firstVowelDigits = new HashSet<>();
        HashSet<String> secondVowelDigits = new HashSet<>();

        for (String digit : firstLastWord) {
            if (vowel.contains(digit)) {
                firstVowelDigits.add(digit);
            }
        }
        for (String digit : secondLastWord) {
            if (vowel.contains(digit)) {
                secondVowelDigits.add(digit);
            }
        }

        return firstVowelDigits.size() == secondVowelDigits.size() &&
                firstVowelDigits.containsAll(secondVowelDigits);
    }

    public static  HashSet<String> digitsCount(long num, int k){
        HashSet<String> result = new HashSet<>();

        String[] digits = Long.toString(num).split("");
        int similarDigits = 1;

        for (int i = 0; i < digits.length; i++){
            if (i == digits.length - 1 || !digits[i].equals(digits[i+1])  ){
                if (similarDigits == k) {
                    result.add(digits[i]);
                }
                similarDigits = 1;
            } else {
                similarDigits += 1;
            }
        }
        return result;
    }
    public static boolean trouble (long num1, long num2){
        Set<String> intersection = digitsCount(num1, 3);
        intersection.retainAll(digitsCount(num2, 2));
        return intersection.size() > 0;
    }

    public static int countUniqueBooks(String source, char border){;
        HashSet<Character> uniqueBooks = new HashSet<>();
        boolean startAdding = false;
        for (char digit : source.toCharArray()) {
            if (digit == border) {
                startAdding = !startAdding;
                continue;
            }
            if (startAdding) {
                uniqueBooks.add(digit);
            }
        }
        return uniqueBooks.size();
    }
}
