package tech.reliab.course.tishchenko.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tech.reliab.course.tishchenko.bank.enums.BankAtmStatusEnum;

import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAtm {
    private int id;
    private String name;
    private String address;
    private BankAtmStatusEnum status;
    private Bank bank;
    private boolean locatedInOffice;
    private Employee servicingEmployee;
    private boolean canDispenseCash;
    private boolean canAcceptDeposits;
    private double cashAmount;
    private double maintenanceCost;

    public BankAtm(int id, String name, String address, Bank bank, Employee employee) {
         this.id = id;
         this.name = name;
         this.address = address;
         this.status = BankAtmStatusEnum.WORKING;
         this.locatedInOffice = true;
         this.servicingEmployee = employee;
         this.canDispenseCash= true;
         this.canAcceptDeposits = true;
         this.cashAmount = bank.getTotalCash() / bank.getNumberOfATMs();
         this.maintenanceCost = generateMaintenanceCost();
    }

    private double generateMaintenanceCost() {
        Random rand = new Random();
        return rand.nextDouble() * 1000; // сумма до 1000000
    }


    public String toRusBoolean(boolean expression) {
        return expression ? "Да" : "Нет";
    }

    @Override
    public String toString() {
        return "\nБанкомат\n" +
                "Название: " + name + '\n' +
                "Адрес: " + address + '\n' +
                "Статус: " + status + '\n' +
                "Название банка: " + (bank != null ? bank.getName() : "Не указано") + '\n' +
                "Расположение в офисе: " + toRusBoolean(locatedInOffice) + '\n' +
                "Обслуживающий работник: " + (servicingEmployee != null ? servicingEmployee.getFullName() : "Не указано") + '\n' +
                "Возможность выдачи наличных: " + toRusBoolean(canDispenseCash) + '\n' +
                "Возможность ввести деньги: " + toRusBoolean(canAcceptDeposits) + '\n' +
                "Количество наличных денег: " + String.format("%.2f", cashAmount) + '\n' +
                "Стоимость обслуживания банкомата: " + String.format("%.2f", maintenanceCost);
    }
}
