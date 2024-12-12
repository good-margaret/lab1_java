package tech.reliab.course.tishchenko.bank.service;

import tech.reliab.course.tishchenko.bank.entity.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService extends BaseOperationsService<Bank>{
    Bank create(String bankName);

    void addOffice(Bank bank);

    void addAtm(Bank bank);

    void addEmployee(Bank bank);

    void addClient(Bank bank);

    void removeOffice(Bank bank);

    void removeAtm(Bank bank);

    void removeEmployee(Bank bank);

    void removeClient(Bank bank);
}