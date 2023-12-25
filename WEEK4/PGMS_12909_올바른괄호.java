package WEEK4;

import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        // 올바른 괄호
        Stack<Character> stack = new Stack<>();
        for (int idx = 0; idx < s.length(); idx++){
            if (s.charAt(idx) == '('){
                stack.push('(');
            } else if (s.charAt(idx) == ')'){
                if (stack.isEmpty())
                    return false;
                stack.pop(); // 뽑기
            }
        }

        return stack.isEmpty();
    }
}