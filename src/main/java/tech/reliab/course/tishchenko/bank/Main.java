package tech.reliab.course.tishchenko.bank;

import tech.reliab.course.tishchenko.bank.entity.*;
import tech.reliab.course.tishchenko.bank.service.*;
import tech.reliab.course.tishchenko.bank.service.impl.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        User user = userService.create("Петров Петр Петрович", LocalDate.now(), "Программист");

        BankService bankService = new BankServiceImpl(userService);
        Bank bank = bankService.create("Сбербанк");

        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankService);
        BankOffice bankOffice = bankOfficeService.create("Офис", "Некрасова, 36",  bank);

        EmployeeService employeeService = new EmployeeServiceImpl(bankService);
        Employee employee = employeeService.create("Кассир Кассирович Кассиров", LocalDate.now(),
                "Кассир", bank, bankOffice,  30000);

        BankAtmService bankAtmService = new BankAtmServiceImpl(bankService);
        BankAtm bankAtm = bankAtmService.create("Банкомат", "Мичурина, 52", bank, employee);

        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(userService, bankService);
        PaymentAccount paymentAccount = paymentAccountService.create(user, bank);

        CreditAccountService creditAccountService = new CreditAccountServiceImpl(userService, bankService);
        CreditAccount creditAccount = creditAccountService.create(user, bank, LocalDate.now(), 8,
                500000, 14, employee, paymentAccount);

        System.out.println(bank);
        System.out.println(user);
        System.out.println(bankOffice);
        System.out.println(employee);
        System.out.println(bankAtm);
        System.out.println(paymentAccount);
        System.out.println(creditAccount);
//        // Создаем список банков
//        List<Bank> banks = new ArrayList<>();
//
//        // Создаем банки и добавляем их в список
//        for (int i = 1; i <= 3; i++) {
//            Bank bank = create()
//            banks.add(bank);
//        }
//
//        // Создаем пользователей
//        User user = new User("U001", "Иванов Иван", new LocalDate(), "ИП Иванов", 6000, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
//
//        // Создаем офисы и банкоматы для каждого банка
//        for (Bank bank : banks) {
//            // Создаем офисы
//            List<BankOffice> offices = new ArrayList<>();
//            for (int j = 1; j <= 2; j++) { // Пример: 2 офиса
//                BankOffice office = new BankOffice(
//                        "O" + j,
//                        "Офис " + j,
//                        "Адрес Офиса " + j,
//                        "работает",
//                        true, // Может размещать банкоматы
//                        0, // Начальное количество банкоматов
//                        true, // Может выдавать кредиты
//                        true, // Можно снимать деньги
//                        true, // Можно вносить деньги
//                        1500.00, // Количество денег в офисе
//                        0, // Стоимость аренды
//                        new ArrayList<>(),
//                        new ArrayList<>()
//                );
//                offices.add(office);
//
//                // Создаем банкоматы для этого офиса
//                for (int k = 1; k <= 5; k++) { // Пример: 5 банкоматов
//                    BankAtm atm = new BankAtm(
//                            "ATM" + k + "_" + j,
//                            "Банкомат " + k + " в офисе " + j,
//                            "Адрес банкомата " + k + " в офисе " + j,
//                            "работает",
//                            bank,
//                            "Расположение банкомата",
//                            "Сотрудник " + k,
//                            true,
//                            true,
//                            100000.00,
//                            200.00
//                    );
//                    office.addAtm(atm);
//                }
//            }
//            bank.setOffices(offices); // Устанавливаем офисы в банке
//        }
//
//        // Запрашиваем кредит
//        try {
//            double requestedAmount = 10000; // Запрашиваемая сумма кредита
//            CreditService.requestCredit(user, banks, requestedAmount);
//        } catch (CreditRequestException e) {
//            System.err.println("Ошибка при получении кредита: " + e.getMessage());
//        }
//
//        // Вывод информации о пользователе и его кредитах
//        System.out.println("Пользователь: " + user);
//        System.out.println("Кредиты пользователя: " + user.getCreditAccounts());
//    }
    }
}