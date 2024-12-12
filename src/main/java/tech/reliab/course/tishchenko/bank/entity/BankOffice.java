package tech.reliab.course.tishchenko.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import tech.reliab.course.tishchenko.bank.enums.BankOfficeStatusEnum;

import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankOffice {
    private int id;
    private String name;
    private String address;
    private BankOfficeStatusEnum status;
    private boolean canPlaceAtm;
    private int numberOfAtms;
    private boolean canIssueLoans;
    private boolean canDispenseCash;
    private boolean canAcceptDeposits;
    private double cashAmount;
    private double rentCost;

    public BankOffice(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = BankOfficeStatusEnum.WORKING;
        this.canPlaceAtm = true;
        this.canIssueLoans = true;
        this.numberOfAtms = 0;
        this.canDispenseCash = true;
        this.canAcceptDeposits = true;
        this.cashAmount = generateCashAmount();
        this.rentCost = generateRentCost();
    }

    private double generateRentCost() {
        Random rand = new Random();
        return rand.nextDouble() * 1000; // сумма до 1000000
    }

    private double generateCashAmount() {
        Random rand = new Random();
        return rand.nextDouble() * 10000; // сумма до 1000000
    }


    public String toRusBoolean(boolean expression) {
        return expression ? "Да" : "Нет";
    }

    public String toRusAtmStatus (BankOfficeStatusEnum status) {
        if (status.equals(BankOfficeStatusEnum.WORKING))
            return "Работает";
        else
            return "Не работает";
    }

    @Override
    public String toString() {
        return "\nОфис \n" +
                "Название: " + name + '\n' +
                "Адрес: " + address + '\n' +
                "Статус: " + toRusAtmStatus(status) + '\n' +
                "Можно ли разместить банкомат: " + toRusBoolean(canPlaceAtm) + '\n' +
                "Число банкоматов: " + numberOfAtms + '\n' +
                "Возможность оформления кредита: " + toRusBoolean(canIssueLoans) + '\n' +
                "Возможность выдачи наличных: " + toRusBoolean(canDispenseCash) + '\n' +
                "Возможность внести деньги: " + toRusBoolean(canAcceptDeposits) + '\n' +
                "Количество денег в офисе: " + String.format("%.2f",cashAmount) + '\n' +
                "Стоимость аренды: " + String.format("%.2f", rentCost);
    }
}
