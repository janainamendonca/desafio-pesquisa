package fastprimenumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Resolução do desafio Fast Prime Number <br>
 * https://www.urionlinejudge.com.br/judge/en/problems/view/1221
 * 
 * @author Janaína Carraro Mendonça Lima
 */
public class FastPrimeNumber {

    public static boolean isPrime(int number) {

        if (number < 2) {
            return false;
        }

        if (number == 2) {
            return true;
        }

        if (number % 2 == 0) {
            return false;
        }

        /*
         * Para testar se um número n é primo, é suficiente testar a
         * divisibilidade apenas pelos primos <= à raiz quadrada de n
         * 
         * Fonte: http://www.cead.ufop.br/professores/claudiarmc/teoriadosnumeros/primos.pdf
         */
        double sqrt = Math.sqrt(number);

        for (int i = 3; i <= sqrt; i = i + 2) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void printPrime(List<Integer> numbers) {
        for (Integer n : numbers) {
            if (isPrime(n)) {
                System.out.println("Prime");
            } else {
                System.out.println("Not Prime");
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            numbers.add(scanner.nextInt());
        }

        scanner.close();

        printPrime(numbers);

    }

}
