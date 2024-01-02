package WEEK5;

import java.util.*;

/* 단어변환 - 자바
begin -> target 가장 짧은 변환 과정
조건 : 한번에 한개의 알파벳만 바꿀 수 있다.

기억할 문법
- str.charAt(idx)
*/

public class PGMS_43163_단어변환 {

    static boolean[] visited;
    static String[] words;
    static String target;
    static int resultCnt = Integer.MAX_VALUE;

    public boolean hitWord(String current, String next){
        // 조건 1. 한번에 한개의 알파벳만 바꿀 수 있다.
        int count = 0;
        for (int idx = 0; idx < current.length(); idx++){
            if (current.charAt(idx) != next.charAt(idx)){
                count++;
            }
        }
        // count가 한개 씩 만 변경가능
        if (count == 1){
            return true;
        }else{
            return false;
        }
    }

    public void dfs(String currentWord, int changeCnt){

        // 1. 기저 조건
        if (currentWord.equals(target)) {
            // 1-1. 현재 단어와 목표 단어가 완전히 일치할 경우
            // 최종 changeCnt 가 가장 적을 경우 result 변수에넣기
            resultCnt = Math.min(resultCnt, changeCnt);
            return;
        }

        // 2. 진행 조건
        for (int idx = 0; idx < words.length; idx++){
            if (hitWord(currentWord, words[idx]) && !visited[idx]){
                visited[idx] = true;
                dfs(words[idx], changeCnt+1);
                visited[idx] = false; // 다음 것으로 넘어가기 위해 풀기
            }
        }
    }

    public int solution(String begin, String target, String[] words) {
        this.words = words; // static으로 설정
        this.target = target;

        visited = new boolean[words.length];

        // 변경
        dfs(begin, 0); // 탐색 인덱스 와 changeCnt

        if (resultCnt == Integer.MAX_VALUE){
            return 0;
        }else{
            return resultCnt;
        }
    }

    public void main(String[] args){
        solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
    }

}
