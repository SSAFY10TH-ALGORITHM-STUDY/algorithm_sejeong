package WEEK2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_19637_IF문좀대신써줘
 * - 칭호와 전투력 상한값
 * - 캐릭터의 전투력이 나타나는 음이 아닌 전투력
 * - 전투력에 맞는 칭호를 입력 순서대로 출력,
 * - if문을 이분 탐색으로 치환해서 시간복잡도를 줄이는 문제
 * - 이진 탐색의 시간 복잡도 : O(logn)
 * * */
public class BOJ_19637_IF문좀대신써줘 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int namingCnt; // 칭호 개수
    static int characterCnt; // 캐릭터들의 개수
    static String[][] powerList;

    public static String binarySearch(int target){

        int start = 0;
        int end = powerList.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int midPower = Integer.parseInt(powerList[mid][1]); // String to Int

            if (midPower < target) {
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return powerList[end + 1][0];
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        sb = new StringBuilder();

        namingCnt = Integer.parseInt(st.nextToken());
        characterCnt = Integer.parseInt(st.nextToken());

        // 칭호 입력 받기
        powerList = new String[namingCnt][2];
        for (int idx = 0; idx < namingCnt; idx++){
            st = new StringTokenizer(br.readLine().trim());
            powerList[idx][0] = st.nextToken(); // 칭호 입력
            powerList[idx][1] = st.nextToken();
        }

        // 전투력 입력 받기
        for (int idx = 0; idx < characterCnt; idx++){
            int power = Integer.parseInt(br.readLine().trim());
            sb.append(binarySearch(power)).append("\n");
        }
        // 결과 출력
        System.out.println(sb);

    }

}
