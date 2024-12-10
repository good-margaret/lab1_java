package tech.reliab.course.tishchenko.bank.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    private int id;
    private String name;
    private int numberOfOffices;
    private int numberOfATMs;
    private int numberOfEmployees;
    private int numberOfClients;
    private int rating;
    private double totalCash;
    private double interestRate;

    public Bank(int id, String name) {
        this.id = id;
        this.name = name;
        this.numberOfOffices = 0;
        this.numberOfATMs = 0;
        this.numberOfEmployees = 0;
        this.numberOfClients = 0;
        this.rating = generateRating();
        this.totalCash = generateTotalCash();
        this.interestRate = generateInterestRate();
    }

    private int generateRating() {
        Random rand = new Random();
        return rand.nextInt(101); // рейтинг от 0 до 100
    }

    private double generateTotalCash() {
        Random rand = new Random();
        return rand.nextDouble() * 1000000; // сумма до 1000000
    }

    private double generateInterestRate() {
        Random rand = new Random();
        double baseRate = rand.nextDouble() * 20;

        if (rating > 0)
            baseRate = baseRate * (100 - rating) / 100;

        return Math.min(baseRate, 20);
    }

    @Override
    public String toString(){
        return "\nБанк \n" +
                "Название банка: " + name +
                "\nКоличество офисов: " + numberOfOffices +
                "\nКоличество банкоматов: " + numberOfATMs +
                "\nКоличество сотрудников: " + numberOfEmployees +
                "\nКоличество клиентов: " + numberOfClients +
                "\nРейтинг: " + rating +
                "\nДенежная сумма: " + String.format("%.2f", totalCash) +
                "\nПроцентная ставка: " + String.format("%.2f",interestRate);
    }
}



