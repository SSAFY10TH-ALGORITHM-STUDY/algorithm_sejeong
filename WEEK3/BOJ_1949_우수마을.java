package WEEK3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ_1949_우수마을
 *
 * 1. N개의 마을  -> 트리 구조
 * 2. 마을과 마을 사이를 직접 잇는 N-1개의 길 존재
 * 3. 우수마을 선정 기준
 * - 마을 주민 수의 총 합 최대
 * - 마을 사이의 충돌 방지를 위해 두 마을이 인접해 있으면, 두 마을을 모두 우수 마을로 선정 불가
 * - 우수 마을로 선정되지 못한 마을은 적어도 하나의 우수 마을과 인접해야한다.
 *
 * 참고 : https://lotuslee.tistory.com/96
 * 이거 봤는데도 모르겠어서, 세민이 코드가 제일 깔끔해서 참고함 ,,
 * 근데도 뭔소리인지 모르겠음
 * 루트 노드에서 리프노드까지 top-down으로 내려간 후 다시 리프노드에서부터 위로 올라가면서 dp 배열 업데이트
 *
 * */

public class BOJ_1949_우수마을 {

    static BufferedReader br;
    static StringTokenizer st;

    static int townCnt;
    static int[] townList;
    static boolean[] visited;
    static int[][] dp;

    static ArrayList<ArrayList<Integer>> list =
            new ArrayList<>();

    static final int 우수마을 = 1;
    static final int 일반마을 = 0;

    public static void dfs(int currentIdx) {
        visited[currentIdx] = true; // 방문

        dp[currentIdx][일반마을] = 0; // currentIdx번 마을이 우수 마을이 아닐 때, currentIdx번 마을을 루트노드로 하는 하위트리의 마을 주민 수의 총합
        dp[currentIdx][우수마을] = townList[currentIdx]; // currentIdx번 마을이 우수마을 일 때, currentIdx 번 마을을 루트노드로 하는 하위트리의 마을 주민 수의 총합

        // 인접해 있는 마을 순회
        for (int nearByIdx : list.get(currentIdx)){
            if (visited[nearByIdx]) continue; // 이미 방문한 마을 일 경우
            dfs(nearByIdx);

            // 일반 마을은 일반 마을, 우수 마을 몯 읹ㅂ 가능
            dp[currentIdx][일반마을] += Math.max(dp[nearByIdx][일반마을], dp[nearByIdx][우수마을]);
            // 우수마을끼리는 인접해있을 수 없음
            dp[currentIdx][우수마을] += dp[nearByIdx][일반마을];
        }

    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        townCnt = Integer.parseInt(br.readLine().trim());

        townList = new int[townCnt + 1]; // 마을 번호 1부터 시작
        visited = new boolean[townCnt + 1];
        dp = new int[townCnt + 1][2];

        for (int idx = 0; idx <= townCnt; idx++){
            list.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 1; idx <= townCnt; idx++){
            townList[idx] = Integer.parseInt(st.nextToken());
        }

        // 길에 대한 입력
        for (int idx = 1; idx < townCnt; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int townNum1 = Integer.parseInt(st.nextToken());
            int townNum2 = Integer.parseInt(st.nextToken());

            // 길에 대한 방향성 없음
            list.get(townNum1).add(townNum2);
            list.get(townNum2).add(townNum1);
        }

        dfs(1);

        // 1번 마을이 일반마을인 경우 우수마을인 경우 비교 -> 최대값
        System.out.println(Math.max(dp[1][일반마을], dp[1][우수마을]));

    }

}
