package com.enjoy.hackerrank.arrays;

/**
 * @author Michael
 * @create 12/10/2020 9:45 AM
 * sum the value of every hourglass
 */
public class DSArray {

    public static void main(String[] args) {
//        int[][] array = new int[][]{
//                {1, 1, 1, 0, 0, 0},
//                {0, 1, 0, 0, 0, 0},
//                {1, 1, 1, 0, 0, 0},
//                {0, 0, 2, 4, 4, 0},
//                {0, 0, 0, 2, 0, 0},
//                {0, 0, 1, 2, 4, 0}
//        };
//        int[][] arr = new int[][]{
//                {-1, -1, 0, -9, -2, -2},
//                {-2, -1, -6, -8, -2, -5},
//                {-1, -1, -1, -2, -3, -4},
//                {-1, -9, -2, -4, -4, -5},
//                {-7, -3, -3, -2, -9, -9},
//                {-1, -3, -1, -2, -4, -5}
//        };

        int[][] arr = new int[][]{
                {-1, -1, 0, -9, -2, -2},
                {-2, -1, -6, -8, -2, -5},
                {-1, -1, -1, -2, -3, -4},
                {-1, -9, -2, -4, -4, -5},
                {-7, -3, -3, -2, -9, -9},
                {-1, -3, -1, -2, -4, -5}
        };

        int maxResult=getHourglass(arr, 0, 0);
        for (int i = 1; i < arr.length-2; i++) {
            for (int j = 1; j < arr[i].length-2; j++) {
                int result = getHourglass(arr, i, j);
                maxResult=result>maxResult?result:maxResult;
            }
        }

        System.out.println(maxResult);

//       getHourglass2(arr);


    }

    private static void getHourglass2(int[][] arr) {
        int maxResult = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int result = 0;
            for (int j = 0; j < arr[i].length - 2; j++) {
                int top = arr[i][j] + arr[i][j + 1] + arr[i][j + 2];
                int middle = arr[i + 1][j + 1] ;
                int bottom = arr[i + 2][j] + arr[i + 2][j + 1] + arr[i + 2][j + 2];
                result = top + middle + bottom;
                if (j == 0 && i == 0) maxResult = result;
                maxResult = result > maxResult ? result : maxResult;
            }
        }
        System.out.println(maxResult);
    }

    public static int getHourglass(int[][] arr,int x, int y){
        return  arr[x][y] + arr[x][y + 1] + arr[x][y + 2]+
                arr[x + 1][y + 1]+
                arr[x + 2][y] + arr[x + 2][y + 1] + arr[x + 2][y + 2];
    }

}
