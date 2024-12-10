package tech.reliab.course.tishchenko.bank.service.impl;

import tech.reliab.course.tishchenko.bank.entity.Bank;
import tech.reliab.course.tishchenko.bank.entity.BankOffice;
import tech.reliab.course.tishchenko.bank.entity.Employee;
import tech.reliab.course.tishchenko.bank.service.BankService;
import tech.reliab.course.tishchenko.bank.service.EmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

public class EmployeeServiceImpl implements EmployeeService {
    private static int employeesCount = 0;

    private final BankService bankService;

    private List<Employee> employees = new ArrayList<>();

    public EmployeeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * Создание нового сотрудника банка.
     *
     * @param fullName     Полное имя сотрудника.
     * @param birthDate    Дата рождения сотрудника.
     * @param position     Должность сотрудника.
     * @param bank         Банк, в котором работает сотрудник.
     * @param bankOffice   Офис, в котором работает сотрудник.
     * @param salary       Зарплата сотрудника.
     * @return Созданный сотрудник банка.
     */
    public Employee create(String fullName, LocalDate birthDate, String position, Bank bank,
                                   BankOffice bankOffice, double salary) {
        Employee employee = new Employee(employeesCount++, fullName, birthDate, position, bank,
                bankOffice, salary);
        employee.setId(employeesCount++);
        employees.add(employee);
        bankService.addEmployee(bank);
        return employee;
    }

    /**
     * Чтение сотрудника по его идентификатору.
     *
     * @param id Идентификатор сотрудника.
     * @return Сотрудник, если он найден, иначе - пустой Optional.
     */
    public Optional<Employee> getById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    /**
     * Чтение всех сотрудников.
     *
     * @return Список всех сотрудников.
     */
    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }

    /**
     * Обновление информации о сотруднике по его идентификатору.
     *
     * @param id   Идентификатор сотрудника.
     * @param name Новое имя сотрудника.
     */
    public void update(int id, String name) {
        Employee employee = getByIdIfPossible(id);
        employee.setFullName(name);
    }

    /**
     * Удаление сотрудника по его идентификатору.
     *
     * @param id Идентификатор сотрудника.
     */
    public void delete(int id) {
        employees.remove(getByIdIfPossible(id));
    }

    /**
     * Получение сотрудника по его идентификатору, если он существует.
     *
     * @param id Идентификатор сотрудника.
     * @return Сотрудник, если он найден.
     * @throws NoSuchElementException Если сотрудник не найден.
     */
    public Employee getByIdIfPossible(int id) {
        return getById(id).orElseThrow(() -> new NoSuchElementException("Employee was not found"));
    }
}
