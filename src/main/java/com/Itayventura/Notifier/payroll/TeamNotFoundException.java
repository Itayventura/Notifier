package com.Itayventura.Notifier.payroll;

public class TeamNotFoundException extends  RuntimeException{
    public TeamNotFoundException(int id){
        super("could not find employee " + id);
    }
}
