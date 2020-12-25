package com.enjoy.hackerrank.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Michael
 * @create 12/25/2020 3:03 PM
 * 二维数组:1 x-插入指定x,2 y-删除一个y，3 z-数组最后任意数值出现次数刚好是z则打印1，否则打印0
 */
public class FrequencyQueries {

    public static void main(String[] args) {

        List<List<Integer>> list = new ArrayList<List<Integer>>() {
            {
                add(new ArrayList<>(Arrays.asList(1, 3)));
                add(new ArrayList<>(Arrays.asList(2, 3)));
                add(new ArrayList<>(Arrays.asList(3, 2)));
                add(new ArrayList<>(Arrays.asList(1, 4)));
                add(new ArrayList<>(Arrays.asList(1, 5)));
                add(new ArrayList<>(Arrays.asList(1, 5)));
                add(new ArrayList<>(Arrays.asList(1, 4)));
                add(new ArrayList<>(Arrays.asList(3, 2)));
                add(new ArrayList<>(Arrays.asList(2, 4)));
                add(new ArrayList<>(Arrays.asList(3, 2)));


//                add(new ArrayList<>(Arrays.asList(3, 4)));
//                add(new ArrayList<>(Arrays.asList(1, 6)));
//                add(new ArrayList<>(Arrays.asList(3, 2)));
//                add(new ArrayList<>(Arrays.asList(1, 10)));
//                add(new ArrayList<>(Arrays.asList(1, 10)));
//                add(new ArrayList<>(Arrays.asList(1, 6)));
//                add(new ArrayList<>(Arrays.asList(2, 5)));
//                add(new ArrayList<>(Arrays.asList(3, 2)));
            }
        };

        List<Integer> list1 = freqQuery(list);
        System.out.println(list1);
    }


    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> result = new ArrayList<>();
        // <element,frequency>
        HashMap<Integer, Integer> fre = new HashMap<>();
        // <frequency,frequencyCount>
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (List<Integer> query : queries) {
            Integer command = query.get(0);
            Integer number = query.get(1);
            switch (command) {
                case 1:
                    // 包含则次数加1
                    if (fre.containsKey(number)) {
                        Integer count = fre.get(number);

                        // 次数减1
                        Integer freCount = countMap.get(count);
                        countMap.put(count, --freCount);
                        // 次数加1
                        fre.put(number, ++count);
                        // 新的次数加1
                        freCount = countMap.get(count);
                        if (freCount != null)
                            countMap.put(count, ++freCount);
                        else
                            countMap.put(count, 1);
                    } else {
                        //不包含初始化1
                        fre.put(number, 1);
                        Integer freCount = countMap.get(1);
                        if (freCount != null)
                            countMap.put(1, ++freCount);
                        else
                            countMap.put(1, 1);
                    }
                    break;
                case 2:
                    // 包含则次数减一
                    if (fre.containsKey(number)) {
                        Integer count = fre.get(number);

                        Integer freCount = countMap.get(count);
                        countMap.put(count, --freCount);

                        if ((--count) == 0)
                            fre.remove(number);
                        else
                            fre.put(number, count);
                        // 新的次数
                        Integer newCount = countMap.get(count);
                        if (newCount != null)
                            countMap.put(count, ++newCount);
                    }
                    break;
                case 3:
                    if (countMap.get(number) != null && countMap.get(number) > 0)
                        result.add(1);
                    else
                        result.add(0);
                    break;
            }
        }
        return result;
    }

}
