package WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_2503_숫자야구
 * 영수 1 ~ 9 세개로 구성된 세자리수
 * 민혁 1 ~ 9 세개로 구성된 세자리수
 * 민혁이가 말한 세 자리수에 있는 숫자들 중 하나가 영수의 세자리수의 동일한 자리에 위치하면, 스트라이크 한번
 * 숫자가 영수의 세자리수에 있긴 하나 다른 자리에 위치하면 볼 한번
 * 종료 조건 : 민혁이가 영수의 세자리수를 정확하게 맞추어 3 스트라이크가 되면 게임 종료
 * 조건 : 서로 다른 세자리 수 & 0은존재하지 않음
 * 조합 & 구현
 *
 * ps : 순수 피지컬로 못풀어서 다음 블로그 코드를 참고했음 ( 브루트포스 & 구현 이 제일 직관적이라고 느낌 )
 * https://tussle.tistory.com/912
 * 계속,, 혼자서 못풀어서 멘붕 ,,ㅜ
 * 이게 실버 3이 맞나,,
 * */

public class BOJ_2503_숫자야구 {

    static BufferedReader br;
    static StringTokenizer st;
    static Game[] gameList;
    static int result;
    static boolean[] visited;

    // class
    static class Game{
        String number;
        int strike;
        int ball;
        Game(String number, int strike, int ball){
            this.number = number;
            this.strike = strike;
            this.ball = ball;
        }
    }

    public static void findByResult(String number){

        // 이미 존재하는 Game 조건 찾기
        for (Game gameSet : gameList){
            int strikeCnt = 0; // 스트라이크 조건 부합 여부
            int ballCnt = 0; // 볼 조건 부합 여부

            // 1. 스트라이크 조건 확인하기
            // 1-1. 세글자에 number와 영수가 추측한 숫자가 맞는 지 확인
            for (int numIdx = 0; numIdx < 3; numIdx ++) {
                if (number.charAt(numIdx) == gameSet.number.charAt(numIdx)) strikeCnt += 1;
            }
            // 1-2. 틀렸다면 리턴
            if (gameSet.strike != strikeCnt) return;

            // 2. 볼 조건 확인하기
            // 2-1. 세글자 중 한글자라도 ball 인게 있나 확인하기 -> 세자리 수 순회하며, ball 개수 일치 여부 확인
            for (int numIdx = 0; numIdx < 3; numIdx ++) {
                int otherNum1 = (numIdx + 1) % 3; // numIdx를 제외한 첫번째 숫자
                int otherNum2 = (numIdx + 2) % 3; // numIdx를 제외한 두번째 숫자
                if (number.charAt(numIdx) == gameSet.number.charAt(otherNum1)) ballCnt += 1;
                if (number.charAt(numIdx) == gameSet.number.charAt(otherNum2)) ballCnt += 1;
            }
            // 2-2. 가정한 조건이 아니라면, 리턴
            if (gameSet.ball != ballCnt) return;
        } // end for 검사

        // System.out.println(number);
        result += 1; // 최종 결과 추가
    }

    // 조합으로 가능한 세자리수 찾기
    public static void searchResult(String number, int depth){

        // 기저 조건
        if (depth == 3){
            findByResult(number); // 결과 찾기
            return;
        }

        // 진행 조건
        // 1 ~ 9로 중복되는 수는 없고, 0 없다
        for (int numIdx = 1; numIdx <= 9; numIdx++){
            if (visited[numIdx]) continue;

            visited[numIdx] = true;
            searchResult(number + numIdx, depth+1); // number + numIdx -> Str으로 합친다.
            visited[numIdx] = false;
        }

    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int gameCnt = Integer.parseInt(br.readLine().trim());
        gameList = new Game[gameCnt];
        for (int idx = 0; idx < gameCnt; idx++){
            st = new StringTokenizer(br.readLine().trim());
            String number = st.nextToken();
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

           gameList[idx] = new Game(number, strike, ball); // 객체 생성
        }

        visited = new boolean[10]; // 방문 여부
        searchResult("", 0); // 숫자야구 결과 탐색
        System.out.println(result);
    }


}
