package com.Itayventura.Notifier.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee_message")
public class EmployeeMessage extends Message {

    @JoinColumn(name = "employee_id")
    @ManyToOne
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


}

