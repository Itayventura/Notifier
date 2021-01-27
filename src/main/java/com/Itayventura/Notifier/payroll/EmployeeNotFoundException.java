package com.Itayventura.Notifier.payroll;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(int id){
        super("could not find employee " + id);
    }

    public EmployeeNotFoundException(String firstName, String lastName){
        super("could not find employee " + firstName + " " + lastName);
    }
}
