package com.Itayventura.Notifier.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "employee_message")
public class EmployeeMessage extends Message {

    @JoinColumn(name = "employee_id")
    @ManyToOne
    @Getter
    @Setter
    private Employee employee;

    public EmployeeMessage(int messageId, String content, Employee sender, Employee employee){
        super(messageId, content, sender);
        this.employee = employee;
    }


    public EmployeeMessage(){
        super();
    }

    @Override
    public String getType() {
        return "Employee";
    }


    @Override
    public boolean equals(Object object){
        if(object instanceof EmployeeMessage){
            EmployeeMessage employeeMessage = (EmployeeMessage) object;
            return super.equals(employeeMessage) && this.employee.equals(employeeMessage.getEmployee());

        }
        return false;
    }


}

