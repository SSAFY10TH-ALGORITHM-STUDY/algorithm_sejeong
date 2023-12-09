package WEEK2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_4659_비밀번호발음하기
 *
 * 문제 해석
 * - 발음이 가능한 패스워드 만들기
 * 1. aeiou 하나를 반드시 포함
 * 2. 모음이 3개 혹은 자음이 3개 연속으로 오면 안된다.
 * 3. 같은 글자가 연속적으로 두번 오면 안되나, ee와 oo는 허용
 *
 * 풀이
 * - 한 메서드에서 여러개의 변수를 받아 처리할까 고민했지만,,
 * - 따로 분리하는게 스스로에게 이해가 더 잘가고, 문제 조건에서 한글자가 20글자 이하 문자열이라는 조건을 보았을 때,
 * - 조건을 나눠서 처리해도 시간이나 조건상 문제가 없다 판단해 노가다 ,, 구현
 *
 * */
public class BOJ_4659_비밀번호발음하기 {

    static BufferedReader br;
    static StringBuilder sb;

    // 1. aeiou 하나를 반드시 포함
    public static boolean case1(String input){
        int 모음 = 0;
        for (int strIdx = 0; strIdx < input.length(); strIdx++){
            char 글자 = input.charAt(strIdx);
            if (글자 == 'a' || 글자 == 'e' || 글자 == 'i' || 글자 == 'o' || 글자 == 'u'){
                모음 ++;
            }
        }
        if (모음 > 0){
            return true;
        }else{
            return false;
        }
    }

    // 2. 모음이 3개 혹은 자음이 3개 연속으로 오면 안된다.
    public static boolean case2(String input){

        // 3글자 미만이면 조건 2 탐색하지 않아도 된다.
        if (input.length() < 3){
            return true;
        }

        // 글자마다 세기
        for (int strIdx = 0; strIdx < input.length() - 2 ; strIdx++){
            int 모음연속 = 0;
            int 자음연속 = 0;
            for (int conIdx = strIdx; conIdx < strIdx+3; conIdx++){
                char 글자 = input.charAt(conIdx); // 한글자
                if (글자 == 'a' || 글자 == 'e' || 글자 == 'i' || 글자 == 'o' || 글자 == 'u')
                    모음연속 ++;
                else 자음연속++;
            }
            if (모음연속 == 3 || 자음연속 == 3){
                return false;
            }
        }
        return true;
    }

    // 3. 같은 글자가 연속적으로 두번 오면 안되나, ee와 oo는 허용
    public static boolean case3(String input){

        if (input.length() < 2){
            return true;
        }

        for (int strIdx = 0; strIdx < input.length()-1; strIdx++){
            char standard = input.charAt(strIdx);
            if (standard == input.charAt(strIdx+1)){
                if (standard == 'e' || standard == 'o') continue;
                return false;
            }
        }

        return true;
    }



    // 발음을 체크하는 변수
    public static boolean check(String input){
        // 결과는 세 조건이 모두 true여야 한다.
        return (case1(input) && case2(input) && case3(input));
    }

    public static void print(String input){
        // 결과 출력
        sb.append("<" + input + "> is ");
        if (!check(input)) sb.append("not ");
        sb.append("acceptable.\n");
    }

    public static void main(String[] args) throws IOException {

        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (true){
            String input = br.readLine().trim(); // 입력
            if (input.equals("end")) break;
            print(input);
        }

        System.out.println(sb);

    }

}
