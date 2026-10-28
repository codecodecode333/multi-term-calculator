
package com.back;

public class Calc {

    public static int run(String s) {
        return new Parser(s).parse();
    }

    private static class Parser {
        private final String expression;
        private int pos = 0;

        Parser(String s) {
            if (s == null) {
                throw new IllegalArgumentException("수식이 null입니다.");
            }

            expression = s.replaceAll("\\s+", "");
        }

        int parse() {
            int result = parseExpression();

            if (pos != expression.length()) {
                throw new IllegalArgumentException("지원하지 않는 문자");
            }

            return result;
        }

        // 덧셈과 뺄셈
        private int parseExpression() {
            int result = parseTerm();

            while (pos < expression.length()) {
                char op = expression.charAt(pos);

                if (op != '+' && op != '-') {
                    break;
                }

                pos++;

                int next = parseTerm();

                if (op == '+') {
                    result += next;
                } else {
                    result -= next;
                }
            }

            return result;
        }

        // 곱셈을 먼저 계산
        private int parseTerm() {
            int result = parseFactor();

            while (pos < expression.length()
                    && expression.charAt(pos) == '*') {

                pos++;
                result *= parseFactor();
            }

            return result;
        }

        // 숫자 및 양수·음수 부호 처리
        private int parseFactor() {
            if (pos >= expression.length()) {
                throw new IllegalArgumentException("숫자가 필요합니다.");
            }

            char c = expression.charAt(pos);

            if (c == '+' || c == '-') {
                pos++;

                int number = parseFactor();

                return c == '-' ? -number : number;
            }

            int start = pos;

            while (pos < expression.length()
                    && expression.charAt(pos) >= '0'
                    && expression.charAt(pos) <= '9') {
                pos++;
            }

            if (start == pos) {
                throw new IllegalArgumentException("올바르지 않은 숫자");
            }

            return Integer.parseInt(
                    expression.substring(start, pos)
            );
        }
    }
}
