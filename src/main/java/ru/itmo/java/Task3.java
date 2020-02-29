package ru.itmo.java;

import java.util.HashMap;
import java.util.Map;

public class Task3 {

    /**
     * Напишите функцию, которая принимает массив целых чисел и циклически сдвигает элементы этого массива вправо:
     * A[0] -> A[1], A[1] -> A[2] .. A[length - 1] -> A[0].
     * Если инпут равен null - вернуть пустой массив
     */
    int[] getShiftedArray(int[] inputArray) {
        if (inputArray == null) {
            return new int[0];
        }
        if (inputArray.length == 1 || inputArray.length == 0) {
            return inputArray;
        }
        int prev = inputArray[0];
        int temp;
        for (int i = 0; i < inputArray.length - 1; ++i) {
            temp = inputArray[i + 1];
            inputArray[i + 1] = prev;
            prev = temp;
        }
        inputArray[0] = prev;
        return inputArray;
    }

    /**
     * Напишите функцию, которая принимает массив целых чисел и возвращает максимальное значение произведения двух его элементов.
     * Если массив состоит из одного элемента, то функция возвращает этот элемент.
     * <p>
     * Если входной массив пуст или равен null - вернуть 0
     * <p>
     * Пример: 2 4 6 -> 24
     */
    int getMaxProduct(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return 0;
        }
        if (inputArray.length == 1) {
            return inputArray[0];
        }
        int max = Math.max(inputArray[1], inputArray[0]);
        int pmax = Math.min(inputArray[1], inputArray[0]);
        int min = Math.min(inputArray[1], inputArray[0]);
        int pmin = Math.max(inputArray[1], inputArray[0]);
        for (int i = 2; i < inputArray.length; ++i) {
            int el = inputArray[i];
            if (el > max) {
                pmax = max;
                max = el;
            } else if (el > pmax) {
                pmax = el;
            } else if (el < min) {
                pmin = min;
                min = el;
            } else if (el < pmin) {
                pmin = el;
            }
        }
        if (pmin > 0 || min > 0) {
            return max * pmax;
        }
        return Math.max(max * pmax, min * pmin);
    }

    /**
     * Напишите функцию, которая вычисляет процент символов 'A' и 'B' (латиница) во входной строке.
     * Функция не должна быть чувствительна к регистру символов.
     * Результат округляйте путем отбрасывания дробной части.
     * <p>
     * Пример: acbr -> 50
     */
    int getABpercentage(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        float abcount = 0;
        String pattern = "ABab";
        for (int i = 0; i < input.length(); ++i) {
            String ch = Character.toString(input.charAt(i));
            if (pattern.contains(ch)) {
                abcount++;
            }
        }
        return (int) (100 * abcount / input.length());
    }

    /**
     * Напишите функцию, которая определяет, является ли входная строка палиндромом
     */
    boolean isPalindrome(String input) {
        if (input == null) {
            return false;
        }
        if (input.length() == 0 || input.length() == 1) {
            return true;
        }
        for (int i = 0; i < input.length() / 2; i++) {
            if (input.charAt(i) != input.charAt(input.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Напишите функцию, которая принимает строку вида "bbcaaaak" и кодирует ее в формат вида "b2c1a4k1",
     * где группы одинаковых символов заменены на один символ и кол-во этих символов идущих подряд в строке
     */
    String getEncodedString(String input) {
        if (input == null) {
            return "";
        }
        if (input.length() == 0) {
            return "";
        }
        char cc = input.charAt(0);
        int count = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(i) == cc) {
                count++;
            }
            else {
                ans.append(cc);
                ans.append(count);
                count = 1;
                cc = input.charAt(i);
            }
        }
        ans.append(cc);
        ans.append(count);
        return ans.toString();
    }

    /**
     * Напишите функцию, которая принимает две строки, и возвращает true, если одна может быть перестановкой (пермутатсией) другой.
     * Строкой является последовательность символов длинной N, где N > 0
     * Примеры:
     * isPermutation("abc", "cba") == true;
     * isPermutation("abc", "Abc") == false;
     */
    boolean isPermutation(String one, String two) {
        if (one == null || two == null) {
            return false;
        }
        if (one.length() == 0 || two.length() == 0) {
            return false;
        }
        if (one.length() != two.length()) {
            return false;
        }
        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < one.length(); i++) {
            if (dict.containsKey(one.charAt(i))) {
                dict.put(one.charAt(i), dict.get(one.charAt(i)) + 1);
            } else {
                dict.put(one.charAt(i), 1);
            }
        }
        for (int i = 0; i < two.length(); i++) {
            if (dict.containsKey(two.charAt(i)) && dict.get(two.charAt(i)) != 0) {
                dict.put(two.charAt(i), dict.get(two.charAt(i)) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Напишите функцию, которая принимает строку и возвращает true, если она состоит из уникальных символов.
     * Из дополнительной памяти (кроме примитивных переменных) можно использовать один массив.
     * Строкой является последовательность символов длинной N, где N > 0
     */
    boolean isUniqueString(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0) {
            return false;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            for (int j = i + 1; j < s.length(); j++) {
                if (c == s.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Напишите функцию, которая транспонирует матрицу. Только квадратные могут быть на входе.
     * <p>
     * Если входной массив == null - вернуть пустой массив
     */
    int[][] matrixTranspose(int[][] m) {
        if (m == null) {
            return new int[2][0];
        }
        if (m.length == 0) {
            return new int[2][0];
        }
        if (m[0].length == 0) {
            return new int[2][0];
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m.length; j++) {
                int c = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = c;
            }
        }
        return m;
    }

    /**
     * Напиишите функцию, принимающую массив строк и символ-разделитель,
     * а возвращает одну строку состоящую из строк, разделенных сепаратором.
     * <p>
     * Запрещается пользоваться функций join
     * <p>
     * Если сепаратор == null - считайте, что он равен ' '
     * Если исходный массив == null -  вернуть пустую строку
     */
    String concatWithSeparator(String[] inputStrings, Character separator) {
        if (separator == null) {
            separator = ' ';
        }
        if (inputStrings == null) {
            return "";
        }
        if (inputStrings.length == 0) {
            return "";
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < inputStrings.length - 1; i++) {
            ans.append(inputStrings[i]).append(separator);
        }
        return ans.append(inputStrings[inputStrings.length - 1]).toString();
    }

    /**
     * Напишите функцию, принимающую массив строк и строку-перфикс и возвращающую кол-во строк массива с данным префиксом
     */
    int getStringsStartWithPrefix(String[] inputStrings, String prefix) {
        if (prefix == null || inputStrings == null || inputStrings.length == 0) {
            return 0;
        }
        int count = 0;
        for (String el : inputStrings) {
            if (el.length() >= prefix.length() && el.substring(0, prefix.length()).equals(prefix)) {
                count++;
            }
        }
        return count;
    }
}
