package bank.service;

import bank.dao.IAccountChangeEventDAO;
import bank.domain.AccountChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class EventSaver {
    @Autowired
    private IAccountChangeEventDAO dao;

    @EventListener
    @Async
    public void onEvent(AccountChangeEvent event) {
        System.out.println("saving account change event :" + event);
        dao.save(event);
    }
}
