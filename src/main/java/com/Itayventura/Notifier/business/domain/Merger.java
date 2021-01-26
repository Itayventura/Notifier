package com.Itayventura.Notifier.business.domain;

import com.Itayventura.Notifier.data.entity.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Merger {

    public static List<Message> mergeIterators(Iterator<? extends Message> it1,
                                               Iterator<? extends Message> it2){
        List<Message> messages = new ArrayList<>();

        Message m1 = it1.hasNext()? it1.next(): null;
        Message m2 = it2.hasNext()? it2.next(): null;

        while (m1 != null && m2 != null){
            if (m2.getLocalDateTime().isAfter(m1.getLocalDateTime())){
                messages.add(m1);
                m1 = it1.hasNext()? it1.next():null;
            } else{
                messages.add(m2);
                m2 = it2.hasNext()? it2.next():null;
            }
        }

        if (m1 != null){
            messages.add(m1);
        }
        if (m2 != null){
            messages.add(m2);
        }

        it1.forEachRemaining(messages::add);
        it2.forEachRemaining(messages::add);

        return messages;
    }
}
