package tech.reliab.course.tishchenko.bank.service;

import tech.reliab.course.tishchenko.bank.entity.Bank;
import tech.reliab.course.tishchenko.bank.entity.BankOffice;
import tech.reliab.course.tishchenko.bank.entity.Employee;

import java.time.LocalDate;

public interface EmployeeService extends BaseOperationsService<Employee>{
    Employee create(String fullName, LocalDate birthDate, String position, Bank bank,
                    BankOffice bankOffice, double salary);
    void update(int id, String name);
}
