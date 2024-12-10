package tech.reliab.course.tishchenko.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAccount {
    private int id;
    private User user;
    private Bank bank;
    private double balance = 0;

    public PaymentAccount(int id, User user, Bank bank) {
        this.id = id;
        this.user = user;
        this.bank = bank;
        this.balance = 0;
    }

    @Override
    public String toString() {
        return "\nПлатежный счет" +
                "\nid: " + id +
                "\nКлиент: " + user +
                "\nНазвание банка" + bank +
                "\nБаланс счета: " + balance;
    }
}
