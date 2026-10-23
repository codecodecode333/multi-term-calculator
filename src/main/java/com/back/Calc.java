package com.back;

public class Calc {
    public static int run(String s) {
        String[] parts = s.split(" ");
        int sum = 0;
        String op = "";
        boolean sw = false;
        for(String part : parts){
            if(part.matches("-?\\d+")){
                if(sw == false){
                    sum = Integer.parseInt(part);
                    sw = true;
                }
                if(op.equals("+")){
                    sum += Integer.parseInt(part);
                } else if (op.equals("-")) {
                    sum -= Integer.parseInt(part);
                } else if (op.equals("*")) {
                    sum *= Integer.parseInt(part);
                }
            } else {
                op = part;
            }
        }
        return sum;

    }
}
