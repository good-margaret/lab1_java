package tech.reliab.course.tishchenko.bank.service;

import tech.reliab.course.tishchenko.bank.entity.Bank;
import tech.reliab.course.tishchenko.bank.entity.BankAtm;
import tech.reliab.course.tishchenko.bank.entity.Employee;

import java.util.List;

public interface BankAtmService extends BaseOperationsService<BankAtm> {
    void update(int id, String name);
    List<BankAtm> getAllBankAtmsByBank(Bank bank);
    BankAtm create(String name,
                          String address,
                          Bank bank,
                          Employee employee);
}
