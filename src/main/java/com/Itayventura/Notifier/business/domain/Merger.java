package com.Itayventura.Notifier.business.domain;

import com.Itayventura.Notifier.data.entity.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Merger {

    public static List<Message> mergeIterators(Iterator<? extends Message> it1,
                                               Iterator<? extends Message> it2){
        List<Message> messages = new ArrayList<>();
        if (!it1.hasNext()){
            it2.forEachRemaining(messages::add);
            return messages;
        }
        if (!it2.hasNext()){
            it1.forEachRemaining(messages::add);
            return messages;
        }

        Message m1 = it1.next();
        Message m2 = it2.next();
        while (it1.hasNext() && it2.hasNext()){
            if (m2.getLocalDateTime().isAfter(m1.getLocalDateTime())){
                messages.add(m1);
                m1 = it1.next();
            } else{
                messages.add(m2);
                m2 = it2.next();
            }
        }

        while (m1 != null && m2 != null){
            if (m2.getLocalDateTime().isAfter(m1.getLocalDateTime())){
                messages.add(m1);
                if (it1.hasNext()){
                    m1 = it1.next();
                } else{
                    messages.add(m2);
                    break;
                }
            } else{
                messages.add(m2);
                if (it2.hasNext()) {
                    m2 = it2.next();
                } else{
                    messages.add(m1);
                    break;
                }
            }
        }



        while(it1.hasNext()){
            it1.forEachRemaining(messages::add);
        }
        while(it2.hasNext()){
            it2.forEachRemaining(messages::add);
        }
        return messages;
    }
}
