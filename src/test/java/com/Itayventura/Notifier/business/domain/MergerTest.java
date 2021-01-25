package com.Itayventura.Notifier.business.domain;

import com.Itayventura.Notifier.data.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
public class MergerTest {

    public static Iterator<TeamMessage> teamMessageIterator;
    public static Iterator<TeamMessage> emptyTeamMessagesIterator;
    public static Iterator<EmployeeMessage> employeeMessagesIterator;
    public static Iterator<EmployeeMessage> emptyEmployeeMessagesIterator;

    @Before
    public void setUpClass() throws InterruptedException {
        List<TeamMessage> teamMessages = new ArrayList<>();
        List<EmployeeMessage> employeeMessages = new ArrayList<>();

        TeamMessage t1 = new TeamMessage(1, "c", new Employee(), new Team());
        TimeUnit.MILLISECONDS.sleep(50);

        EmployeeMessage e2 = new EmployeeMessage(2, "c", new Employee(), new Employee());
        TimeUnit.MILLISECONDS.sleep(50);

        TeamMessage t3 = new TeamMessage(3, "c", new Employee(), new Team());
        TimeUnit.MILLISECONDS.sleep(50);

        EmployeeMessage e4 = new EmployeeMessage(4, "c", new Employee(), new Employee());
        TimeUnit.MILLISECONDS.sleep(50);

        EmployeeMessage e5 = new EmployeeMessage(5, "c", new Employee(), new Employee());
        TimeUnit.MILLISECONDS.sleep(50);

        EmployeeMessage e6 = new EmployeeMessage(6, "c", new Employee(), new Employee());
        TimeUnit.MILLISECONDS.sleep(50);

        TeamMessage t7 = new TeamMessage(7, "c", new Employee(), new Team());
        TimeUnit.MILLISECONDS.sleep(50);

        teamMessages.add(t1);
        teamMessages.add(t3);
        teamMessages.add(t7);
        teamMessageIterator = teamMessages.iterator();

        employeeMessages.add(e2);
        employeeMessages.add(e4);
        employeeMessages.add(e5);
        employeeMessages.add(e6);
        employeeMessagesIterator = employeeMessages.iterator();

        emptyEmployeeMessagesIterator = new ArrayList<EmployeeMessage>().iterator();
        emptyTeamMessagesIterator = new ArrayList<TeamMessage>().iterator();

    }

    @Test
    public void mergeEmptyIterators() {
        List<Message> messages = Merger.mergeIterators(teamMessageIterator, emptyEmployeeMessagesIterator);
        Assert.assertEquals(3, messages.size());
        Assert.assertEquals(1, messages.get(0).getMessageId());
        Assert.assertEquals(3, messages.get(1).getMessageId());
        Assert.assertEquals(7, messages.get(2).getMessageId());

        messages = Merger.mergeIterators(emptyTeamMessagesIterator, employeeMessagesIterator);
        Assert.assertEquals(4, messages.size());
        Assert.assertEquals(2, messages.get(0).getMessageId());
        Assert.assertEquals(4, messages.get(1).getMessageId());
        Assert.assertEquals(5, messages.get(2).getMessageId());
        Assert.assertEquals(6, messages.get(3).getMessageId());

    }

    @Test
    public void mergeIterators(){
        List<Message> messages = Merger.mergeIterators(teamMessageIterator, employeeMessagesIterator);
        Assert.assertEquals(7, messages.size());
        Assert.assertEquals(1, messages.get(0).getMessageId());
        Assert.assertEquals(2, messages.get(1).getMessageId());
        Assert.assertEquals(3, messages.get(2).getMessageId());
        Assert.assertEquals(4, messages.get(3).getMessageId());
        Assert.assertEquals(5, messages.get(4).getMessageId());
        Assert.assertEquals(6, messages.get(5).getMessageId());
        Assert.assertEquals(7, messages.get(6).getMessageId());
    }
}