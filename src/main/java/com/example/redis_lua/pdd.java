package com.example.redis_lua;

import java.util.Scanner;

/**
 * @Description TODO
 * @Auther chengjiahui
 * @Date 2024/4/15 19:04
 * @Version 1.0
 */
public class pdd {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int N = scanner.nextInt();
//        String input = scanner.next();
//        int ans = 0;
//        int sum = 0;
//        for (int i = 0; i < input.length(); i += 3) {
//            if (input.length() - i < 2) {
//                break;
//            }
//            ans++;
//            sum += getCount(input.substring(i, i + 3));
//        }
//        System.out.print(ans+" "+sum);
//    }
//
//    public static int getCount(String str) {
//        int sum = 0;
//        sum += Math.abs(str.charAt(0) - 'P');
//        sum += Math.abs(str.charAt(1) - 'D');
//        sum += Math.abs(str.charAt(2) - 'D');
//        return sum;
//    }

    int sum = 0;

    public static void main(String[] args) {
//        int[] nums = new int[101];
//        for (int i = 1; i <= 100; i++) {
//            nums[i] = 1;
//        }
//        for (int i = 1; i <= 100; i++) {
//            for (int j = i; j <= 100; j = j + i) {
//                if (nums[i] == 0) {
//                    nums[i] = 1;
//                } else if (nums[i] == 1) {
//                    nums[i] = 0;
//                }
//            }
//        }
//        int ans = 0;
//        for (int i = 1; i <= 100; i++) {
//            if (nums[i] == 0) {
//                ans++;
//            }
//        }
//        System.out.println(ans);


        boolean[] arr = new boolean[101];
        for(int i = 1;i < 101;i++){
            System.out.print("第"+i+"个学生按了灯的编号为：");
            for(int j = i  ;j < 101; j = j + i){
                arr[j] = !arr[j];
            }
            System.out.print("\n\n");
        }
    }

    public void dfs(int[] nums, int beginIndex) {
        for (int i = beginIndex; i < nums.length; i++) {

        }
    }

}
