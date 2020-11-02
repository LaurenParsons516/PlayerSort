package com.lauren.playersort;

import java.sql.SQLOutput;
import java.util.NoSuchElementException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        int maxAge = 15;
        Player[] players = new Player[] {
                new Player("Bob", r.nextInt(maxAge)),
                new Player("Phil", r.nextInt(maxAge)),
                new Player("Jeff", r.nextInt(maxAge)),
                new Player("Joe", r.nextInt(maxAge)),
                new Player("Bill", r.nextInt(maxAge)),
                new Player("Aaron", r.nextInt(maxAge)),
                new Player("Max", r.nextInt(maxAge)),
                new Player("Sam", r.nextInt(maxAge)),
                new Player("Eric", r.nextInt(maxAge)),
                new Player("Remington", r.nextInt(maxAge)),
        };
        mergeSort(players, 0, players.length - 1);
        try {
            Player answer = binSearch(players, maxAge/2);
            System.out.println("Player: " + answer.getName());
            System.out.println("Age: " + answer.getAge());
        } catch (NoSuchElementException e) {
            System.out.println("Searched age not found.");
        }

    }
    static void mergeSort(Player[] ar, int begin, int end)
    {
        if(begin != end)
        {
            int begin1 = begin;
            int end1 = begin + ((end - begin)/2);
            int begin2 = end1 + 1;
            int end2 = end;
            mergeSort(ar, begin1, end1);
            mergeSort(ar, begin2, end2);
            merge(ar, begin1, end1, begin2, end2);
        }
    }

    static void merge(Player[] ar, int begin1, int end1, int begin2, int end2)
    {
        Player[] temp = new Player[end2 - begin1 + 1];
        int pos1 = begin1;
        int pos2 = begin2;
        for(int i = 0; i < temp.length; i++)
        {
            if(pos1 <= end1 && pos2 <= end2)
            {
                if(ar[pos1].getAge() < ar[pos2].getAge())
                {
                    temp[i] = ar[pos1];
                    pos1++;
                }
                else
                {
                    temp[i] = ar[pos2];
                    pos2++;
                }
            }
            else
            {
                //either pos1 or pos2 is off the end of their list and the other guy is the
                //default winner
                if(pos1 > end1)
                {
                    temp[i] = ar[pos2];
                    pos2++;
                }
                else
                {
                    temp[i] = ar[pos1];
                    pos1++;
                }
            }
        } //end of for loop

        //copy temp back over ar from begin1 to end2
        int posInTemp = 0;
        for(int i = begin1; i <= end2; i++)
        {
            ar[i] = temp[posInTemp];
            posInTemp++;
        }
    }
    static void displayArray(Player[] players)
    {
        for (Player player : players) {
            System.out.println("Player: " + player.getName());
            System.out.println("Age: " + player.getAge());
            System.out.println();
        }
    }
    static Player binSearch(Player[] players, int searchedNum){
        int beginning = 0;
        int end = players.length - 1;
        int middle;

        do {
            middle = (end + beginning) / 2;
            if (players[middle].getAge() == searchedNum) {
                return players[middle];
            } else if (searchedNum < players[middle].getAge()) {
                end = middle - 1;
            } else {
                beginning = middle + 1;
            }
        } while (end <= beginning);

        throw new NoSuchElementException();
    }
}
