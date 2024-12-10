package tech.reliab.course.tishchenko.bank.service;

import tech.reliab.course.tishchenko.bank.entity.Bank;
import tech.reliab.course.tishchenko.bank.entity.BankOffice;

public interface BankOfficeService extends BaseOperationsService<BankOffice>{
    BankOffice create(String name, String address, Bank bank);
    void update(int id, String name);
}