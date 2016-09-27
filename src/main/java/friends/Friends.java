package friends;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * Resolução do desafio Friends <br>
 * https://www.urionlinejudge.com.br/judge/en/problems/view/1726
 * 
 * @author Janaína Carraro Mendonça Lima
 */
public class Friends {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> expressions = new ArrayList<>();

        while (scanner.hasNextLine()) {

            String expr = scanner.nextLine();

            if (expr.isEmpty()) {
                break;
            }

            expressions.add(expr);

        }

        for (String e : expressions) {
            System.out.println(resolve(e));
        }

        scanner.close();
    }

    public static String resolve(String expr) {

        // separa a expressaão em tokens, ex: ABC + ABDF
        List<Token> tokens = getTokens(expr);

        // transforma em uma expressão postfix, ex: ABC ABDF +
        List<Token> postfixTokens = postfix(tokens);

        // resolve a expressão postfix
        Iterator<Token> iterator = postfixTokens.iterator();

        Stack<Token> stack = new Stack<Token>();

        while (iterator.hasNext()) {
            Token token = iterator.next();

            if (token.getType() == TokenType.OPERAND) {
                stack.push(token);
            } else if (token.getType() == TokenType.OPERATOR) {
                Token a = stack.pop();
                Token b = stack.pop();

                Operator operator = Operator.getBySymbol(token.getValue().charAt(0));

                String result = eval(b.getValue(), a.getValue(), operator);

                stack.push(new Token(TokenType.OPERAND, result));

            }
        }

        String result = stack.pop().getValue();

        char[] chars = result.toCharArray();
        Arrays.sort(chars);
        result = new String(chars);

        return "{" + result + "}";
    }

    private static List<Token> postfix(List<Token> tokens) {
        Iterator<Token> iterator = tokens.iterator();

        Stack<Token> stack = new Stack<Token>();
        List<Token> p = new ArrayList<Token>();

        while (iterator.hasNext()) {
            Token token = iterator.next();

            switch (token.getType()) {
                case OPERAND:
                    p.add(token);
                    break;
                case LEFT_PARENTHESIS:
                    stack.push(token);
                    break;
                case RIGHT_PARENTHESIS:
                    while (!stack.isEmpty() && stack.peek().getType() != TokenType.LEFT_PARENTHESIS) {
                        p.add(stack.pop());
                    }
                    stack.pop();
                    break;
                case OPERATOR:
                    if (stack.isEmpty() || stack.peek().getType() == TokenType.LEFT_PARENTHESIS) {
                        stack.push(token);
                    } else {
                        Operator operator = Operator.getBySymbol(token.getValue().charAt(0));

                        while (!stack.isEmpty() && stack.peek().getType() != TokenType.LEFT_PARENTHESIS && operator != Operator.INTERSECTION) {
                            p.add(stack.pop());
                        }
                        stack.push(token);
                    }
                    break;
            }
        }

        while (!stack.isEmpty()) {
            p.add(stack.pop());
        }
        return p;
    }

    private static List<Token> getTokens(String expr) {
        List<Token> tokens = new ArrayList<Token>();

        char[] chars = expr.toCharArray();

        String set = null;

        for (char c : chars) {
            Operator operator = Operator.getBySymbol(c);
            if (operator != null) {
                tokens.add(new Token(TokenType.OPERATOR, String.valueOf(c)));
            } else {
                switch (c) {
                    case '{':
                        set = "";
                        break;
                    case '}':
                        tokens.add(new Token(TokenType.OPERAND, set));
                        set = null;
                        break;
                    case '(':
                        tokens.add(new Token(TokenType.LEFT_PARENTHESIS, set));
                        break;
                    case ')':
                        tokens.add(new Token(TokenType.RIGHT_PARENTHESIS, set));
                        break;
                    case ' ':
                        // ignora espaços
                        break;
                    default:
                        set += c;
                        break;
                }
            }
        }
        return tokens;
    }

    private static String eval(String v1, String v2, Operator operation) {
        Set<Character> set1 = new HashSet<Character>();
        Set<Character> set2 = new HashSet<Character>();

        for (Character c : v1.toCharArray()) {
            set1.add(c);
        }
        for (Character c : v2.toCharArray()) {
            set2.add(c);
        }

        switch (operation) {
            case INTERSECTION:
                return intersection(set1, set2);
            case DIFFERENCE:
                return difference(set1, set2);
            case UNION:
                return union(set1, set2);
        }

        return null;
    }

    private static String union(Set<Character> set1, Set<Character> set2) {
        HashSet<Character> set = new HashSet<Character>();
        set.addAll(set1);
        set.addAll(set2);

        return toString(set);
    }

    private static String difference(Set<Character> set1, Set<Character> set2) {
        HashSet<Character> set = new HashSet<Character>();

        for (Character c : set1) {
            if (!set2.contains(c)) {
                set.add(c);
            }
        }
        return toString(set);
    }

    private static String intersection(Set<Character> set1, Set<Character> set2) {
        HashSet<Character> set = new HashSet<Character>();

        for (Character c : set1) {
            if (set2.contains(c)) {
                set.add(c);
            }
        }

        for (Character c : set2) {
            if (set1.contains(c)) {
                set.add(c);
            }
        }

        return toString(set);
    }

    private static String toString(HashSet<Character> set) {
        String r = "";
        for (Character c : set) {
            r += c;
        }
        return r;
    }

    public static enum Operator {

        UNION('+'),

        INTERSECTION('*'),

        DIFFERENCE('-');

        private char symbol;

        private Operator(char symbol) {
            this.symbol = symbol;
        }

        public static Operator getBySymbol(char symbol) {
            for (Operator op : Operator.values()) {
                if (symbol == op.symbol) {
                    return op;
                }
            }

            return null;
        }
    }

    public static class Token {

        private TokenType type;

        private String value;

        public Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }

        public TokenType getType() {
            return type;
        }

        public String getValue() {
            return value;
        }

    }

    public static enum TokenType {

        OPERATOR,

        OPERAND,

        RIGHT_PARENTHESIS,

        LEFT_PARENTHESIS;
    }
}
