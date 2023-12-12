package WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_7453_합이0인네정수
 *
 *
 *
 *
 * */

public class BOJ_7453_합이0인네정수 {

    static BufferedReader br;
    static StringTokenizer st;

    static int[][] arrayList;

    public static void main(String [] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int arrayCnt = Integer.parseInt(br.readLine().trim());
        arrayList = new int[arrayCnt][4];
        for (int idx = 0; idx < arrayCnt; idx++){
            st = new StringTokenizer(br.readLine().trim());
            for (int pairIdx = 0; pairIdx < 4; pairIdx++){
                arrayList[idx][pairIdx] = Integer.parseInt(st.nextToken());
            }
        } // end for input




    }


}
