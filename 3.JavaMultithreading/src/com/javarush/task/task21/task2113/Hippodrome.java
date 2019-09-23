package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    private List<Horse> horses;
    static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() throws InterruptedException {
        for (int i=0; i<100; i++) {
            move();
            print();
            Thread.sleep(100);
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
//        for (int i=0; i<10; i++) {
//            System.out.println();
//        }
    }

    public Horse getWinner() {
        Horse winner = null;
        double maxDistance = 0;
        for (Horse horse : horses) {
            double tempDistance = horse.getDistance();
            if (tempDistance > maxDistance) {
                winner = horse;
                maxDistance = tempDistance;
            }
        }

        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public static void main(String[] args) throws InterruptedException {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Klaudia", 3, 0));
        horses.add(new Horse("Maxim", 3, 0));
        horses.add(new Horse("Konrad", 3, 0));
        horses.add(new Horse("Paulina", 3, 0));
        horses.add(new Horse("Mariush", 3, 0));
        horses.add(new Horse("Kasha", 3, 0));
        horses.add(new Horse("Pawel", 3, 0));
        horses.add(new Horse("Vera", 3, 0));
        horses.add(new Horse("Sergey", 3, 0));

        Hippodrome.game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }
    
}
