package WEEK4;


import java.util.*;
class PGMS_42746_가장큰수 {
    public String solution(int[] numbers) {
        String answer = "";

        String [] strList = new String[numbers.length];
        for (int idx = 0; idx < numbers.length; idx++){
            strList[idx] = Integer.toString(numbers[idx]);
        }

        Arrays.sort(strList, new Comparator<String>(){
            @Override
            public int compare(String str1, String str2){
                return (str2 + str1).compareTo(str1 + str2);
            }
        });

        if (strList[0].equals("0")) return "0";
        for (int idx = 0; idx < strList.length; idx++){
            answer += strList[idx];
        }

        return answer;
    }
}