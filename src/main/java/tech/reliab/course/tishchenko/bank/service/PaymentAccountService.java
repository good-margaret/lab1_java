package tech.reliab.course.tishchenko.bank.service;

import tech.reliab.course.tishchenko.bank.entity.Bank;
import tech.reliab.course.tishchenko.bank.entity.PaymentAccount;
import tech.reliab.course.tishchenko.bank.entity.User;

public interface PaymentAccountService extends BaseOperationsService<PaymentAccount> {
    void update(int id, Bank bank);
    PaymentAccount create(User user, Bank bank);
}
