package WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_2503_숫자야구_simple
 *
 * 문제를 보고, 도저히 감이 안잡혀서, 다른 블로그를 찾아보고 위에 BOJ_2503_숫자야구 방식대로 풀었다.
 * 풀이 방법을 이해하고, 좋은 풀이라고 생각했으나 , 도저히 실버 3이 납득이 안가서
 * 다른 풀이를 찾아보니, 간단하게 푸는 (노가다) 방법이 있더라,,..
 * 세자리 수여서 값이 크지 않고, 충분히 알고리즘 없이 쌩구현으로 풀 수 있는 문제
 * -> 괜히 복잡한게 생각한 것 같음 그냥 4중 포문 돌려도 숫자 범위가 안커서 ㄱㅊ한데 괜히 겁먹었다보다
 * */


public class BOJ_2503_숫자야구_simple {

    static BufferedReader br;
    static StringTokenizer st;

    static int gameCnt;
    static Game[] gameList;
    static int result;

    // class
    static class Game{
        int number;
        int strike;
        int ball;
        public Game(int number, int strike, int ball){
            this.number = number;
            this.strike = strike;
            this.ball = ball;
        }
    }

    public static void searchResult(){

        for (int num1 = 1; num1 <= 9; num1++){
            for (int num2 = 1; num2 <= 9; num2 ++){
                for (int num3 = 1; num3 <= 9; num3 ++){

                    // 조건 1. 세 정수는 서로 달라야한다.
                    if (num1 == num2 || num2 == num3 || num1 == num3) continue;

                    int checkQuestion = 0; // 질문한 거에 얼마나 맞췄는지 확인
                    // 4가지에서 strike, ball 확인
                    for (Game gameSet : gameList) {
                        // 비교 조건 - Strike & ball
                        int strikeCnt = 0;
                        int ballCnt = 0;

                        // 비교 대상
                        int compareNum1 = gameSet.number / 100;
                        int compareNum2 = (gameSet.number % 100) / 10;
                        int compareNum3 = gameSet.number % 10;

                        // Strike 구하기
                        if (num1 == compareNum1) strikeCnt += 1;
                        if (num2 == compareNum2) strikeCnt += 1;
                        if (num3 == compareNum3) strikeCnt += 1;

                        // ball 구하기
                        if (num1 == compareNum3 || num1 == compareNum2) ballCnt += 1;
                        if (num2 == compareNum1 || num2 == compareNum3) ballCnt += 1;
                        if (num3 == compareNum1 || num3 == compareNum2) ballCnt += 1;

                        // 조건 확인
                        if (strikeCnt == gameSet.strike && ballCnt == gameSet.ball) {
                            checkQuestion += 1;
                        }

                    } // end for question

                    if (checkQuestion == gameCnt){
                        result += 1;
                    }

                } // num3
            } // num2
        } // num1

    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        // 게임 정보 입력 받기
        gameCnt = Integer.parseInt(br.readLine().trim());
        gameList = new Game[gameCnt];
        for (int idx = 0; idx < gameCnt; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int number = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());
            gameList[idx] = new Game(number, strike, ball);
        }

        searchResult();
        System.out.println(result);
    }



}
