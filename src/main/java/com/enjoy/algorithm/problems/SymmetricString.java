package com.enjoy.algorithm.problems;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wu
 * @Date 3/29/2021 3:13 PM
 * @Version 1.0
 * 判断字符串是否有回文，最长是什么？如1234321, 4334等
 */
public class SymmetricString {


    public static void main(String[] args) {

        String regex="[0-9]/d";
        getLongestSubString();


//        int[] coins = {1, 2, 7, 8, 12};
//        int money = 15;
//        int leastCoins = getLeastCoins(coins, money);
//        System.out.println(leastCoins);

//        longestIncreasingSubsequence(new int[]{12, 3, 8, 7, 5, 6, 13, 10, 11, 4});
//        int[] array = new int[]{2, 3, 4, 5};
//        reverseArray(array);
//        String max = Max("12345", "35");
//        System.out.println(max);

//        String abdefgabef = returnLongestNonRepeatSubString("ABDEFGABEF");
//        System.out.println(abdefgabef);

//        int partition = partition(new int[]{4, 5, 1, 3, 4}, 0, 4);
//        System.out.println(partition);

//        threeSumClosest(new int[]{ -2, -1, 0, 1, 2}, 0);
//        longestCommonPrefix();
    }

    //"pwwkew"->"wke"
    public static void getLongestSubString() {
        String content = "pwwkeaebc";
        List<String> result = new ArrayList<>();
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            String item = chars[i] + "";
            for (int j = i + 1; j < chars.length; j++) {
                String val = chars[j] + "";
                if (item.contains(val) || j == chars.length-1) {
                    // 包含有重复
                    result.add(item);
                    break;
                } else {
                    // 加入
                    item += val;
                }
            }
        }

        String s = result.stream().max(Comparator.comparingInt(x -> x.length())).get();
        System.out.println(s);
    }


    //动态规划：时间复杂度o(n^2)，空间复杂度o(n)
    public static int longestIncreasingSubsequence(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //dp[i]代表从0-i的最长上升子序列
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            //遍历0-i的每一个元素j，如果nums[i]>nums[j]，说明可以在序列0-j的基础dp[j]上，加上一个元素i拓展序列
            //如果当前i全局最小，则dp[i]=1
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            result = dp[i] > result ? dp[i] : result;
        }
        return result;
    }

    public static void reverseArray(int[] arrays) {
        int[] reverseArray = new int[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            reverseArray[i] = arrays[arrays.length - i - 1];
        }
        System.out.println(Arrays.toString(reverseArray));
    }

    private static void get() {
        int[] arrays = {1, 2, -3, 4, 5, -5};
        //和最大
        int sum = 0;
        for (int i = 0; i < arrays.length; i++) {
            int temp = sum + arrays[i];
            if (sum < temp) {
                sum += arrays[i];
                System.out.println(arrays[i]);
            }
        }
    }

    //最长前缀
    public static void longestCommonPrefix() {
        String[] strs = {"flower", "flow", "flight"};
        int minLen = Arrays.stream(strs).max((a, b) -> Math.min(a.length(), b.length())).get().length();
        int min = Math.min(strs[0].length(), strs[1].length());
        min = Math.min(min, strs[2].length());

        int[] result = new int[min];
        for (int i = 0; i < min; i++) {
            boolean isValid = true;
            int element = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (element != strs[j].charAt(i)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                System.out.print((char) element);
            } else {
                break;
            }
        }
    }

    // 三个数为0
    public static void threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);// 排序+二分查找法

        int ret = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
//            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum > target)
                    high--;
                else if (sum < target)
                    low++;
                else {
                    System.out.println("[" + nums[i] + "," + nums[low] + "," + nums[high] + "]");
                    low++;
                }
                // 没有找到
//                if (Math.abs(ret - target) > Math.abs(sum - target)) ret = sum;
            }
        }
    }


    // 中位数
    public static void middleNumber(int[] arrays, int start, int end) {

        //快速排序中有pivot，小于pivot在左边，大于pivot在右边，

        int randomIndex = (start + end) / 2;
        // 与最后一位交换位置
        swap(arrays, randomIndex, arrays.length - 1);

        int smallIndex = start - 1;
        //开始比较
        for (int i = start; i < arrays.length; i++) {
            // 与最后一个数比较
            if (arrays[start] <= arrays[end]) {
                smallIndex++;
                if (smallIndex > start) {
                    swap(arrays, smallIndex, start);
                }
            }
        }


    }


    public static int partition(int[] array, int left, int right) {

        int pos = right;
        right--;
        while (left <= right) {
            while (left < pos && array[left] <= array[pos])
                left++;

            while (right >= 0 && array[right] > array[pos])
                right--;

            if (left >= right)
                break;

            swap(array, left, right);
        }
        swap(array, left, pos);

        return left;
    }


    private static int partitionWithLowAndHigh(int[] arrays, int left, int right) {
        int last = right;
        right--;
        while (left <= right) {

            while (left < last && arrays[left] <= arrays[last])
                left++;
            while (right >= 0 && arrays[right] > arrays[last])
                right--;

            if (left >= right)
                break;
            swap(arrays, left, right);
        }
        swap(arrays, left, last);
        return left;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static String returnLongestNonRepeatSubString(String s) {
        String a = "12345abca";
        String b = "45ac";
        String max = a.length() > b.length() ? a : b;
        String min = a.length() > b.length() ? b : a;

        for (int i = 0; i < min.length(); i++) {
            for (int m = 0, n = min.length() - i; n < min.length() + 1; n++, m++) {
                String minSub = min.substring(m, n);
                if (max.contains(minSub)) {
                    return minSub;
                }
            }
        }
        return "";
    }

    // 查找最大子串
    public static String Max(String s1, String s2) {
        String max = (s1.length() > s2.length()) ? s1 : s2;//max=s2
        String min = max.equals(s1) ? s2 : s1;//min=s1

        for (int i = 0; i < min.length(); i++) {
            // 从后面一个字符 一个字符截取。
//            for (int m = 0, n = min.length() - i; n != min.length() + 1; m++, n++) {
            for (int m = 0, n = min.length() - i; n < min.length() + 1; m++, n++) {
                System.out.println("m=" + m + " " + "n=" + n);
                System.out.println(min.substring(m, n));
                String sub = min.substring(m, n);
                if (max.contains(sub)) {
                    return sub;
                }
            }
        }
        return null;
    }

    //Given the list {12, 3, 8, 7, 5, 6, 13, 10, 11, 4}, the longest increasing subsequence is {3, 5, 6, 10, 11}, with cardinality 5.
    public static void LongestIncreasingSubsequence() {
        // 动态规划
    }

    /**
     * V[]存储硬币面值集合，n第几个硬币，m金额
     * F(n,m)=0; n=0或m=0
     * F(n,m)=F(n-1,m); n>0且0<m<V[n] 金额<硬币面值
     * F(n,m)=Min(F(n-1,m),F(n,m-V[n])+1); n>0并且m>=V[n]
     *
     * @param coins
     * @param money
     * @return
     */
    public static int getLeastCoins(int[] coins, int money) {
        int[][] dp = new int[coins.length + 1][money + 1];
        // 从n=1,m=1开始
        for (int n = 0; n < coins.length; n++) {
            for (int m = 0; m <= money; m++) {
                // 面值
                int val = coins[n];
                //当有硬币面值>=目标金额
                if (m >= val) {
                    int calValue = dp[n + 1][m - coins[n]] + 1;
                    dp[n + 1][m] = dp[n][m] != 0 ? Math.min(dp[n][m], calValue) : calValue;
                } else {
                    // 面值<目标金额
                    dp[n + 1][m] = dp[n][m];
                }
            }
        }
        return dp[coins.length][money];
    }

//    public static int[] getLeastCoins(int[] coins, int money) {
//        int[] result = new int[money + 1];
//        result[0] = 0; //数额0只需0个便可凑齐
//        for (int i = 1; i < result.length; i++)
//            result[i] = 10000; //初始化时各个数额需要最大上限个硬币
//        for (int i = 1; i <= money; i++) {
//            for (int coin : coins) {
//                if (coin <= i) {
//                    int min = 10000; //存储中间结果的变量，初始化为最大硬币上限
//                    min = result[i - coin] + 1;
//                    if (result[i] > min)
//                        result[i] = min;
//                }
//            }
//        }
//        return result;
//    }


}
