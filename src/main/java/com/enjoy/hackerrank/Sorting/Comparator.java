package com.enjoy.hackerrank.Sorting;

import java.util.Arrays;

/**
 * @author Michael
 * @create 12/21/2020 12:26 PM
 */
public class Comparator {


    public static void main(String[] args) {

        Player[] players=new Player[]{
                new Player("Smith",20),
                new Player("Jones",15),
                new Player("Jones",20)
        };

        Arrays.sort(players, new Checker());

        System.out.println(Arrays.toString(players));

    }

    public static class Checker implements java.util.Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            if (o1.score > o2.score)
                return -1;
            else if (o1.score < o2.score)
                return 1;
            else
                return o1.name.compareTo(o2.name);


        }
    }

    public static class Player {
        public String name;// ascending sort
        public Integer score;// descending sort

        public Player(String name, Integer score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
