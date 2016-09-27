package indanger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Resolução do desafio In Danger.<br>
 * https://www.urionlinejudge.com.br/judge/en/problems/view/1672
 * 
 * @author Janaína Mendonça
 */
public class InDanger {

    public static int safePosition(int n) {
        int pos = 1;
        for (int i = 1; i < n; i++) {
            if (pos >= i) {
                pos = 1;
            } else {
                pos = pos + 2;
            }
        }
        return pos;
    }

    public static int toInt(String value) {
        String[] tokens = value.split("e");

        if (tokens.length != 2) {
            throw new IllegalArgumentException("Invalid input: " + value);
        }

        String number;
        if (tokens[0].charAt(0) == '0') {
            number = String.valueOf(tokens[0].charAt(1));
        } else {
            number = String.valueOf(tokens[0]);
        }

        if (!tokens[1].equals("0")) {
            for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                number += "0";
            }
        }

        return Integer.parseInt(number);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            if (line.equals("00e0")) {
                break;
            }
            numbers.add(toInt(line));
        }
        scanner.close();

        for (Integer n : numbers) {
            System.out.println(safePosition(n));
        }
    }
}
