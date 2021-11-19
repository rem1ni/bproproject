package com.backend.bprobackend.service;

import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@EnableScheduling
@Transactional
@Service
public class PayDetailService {
    @Autowired
    UserRepos userRepository;
    String timetopay="31";

    @Scheduled(fixedDelay = 60000)
    public void PayService1(){
        String time=new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        Long count= userRepository.count();
        Long i =1l;
        if (Objects.equals(timetopay,time)) {
            while (i <= count) {
                User users = userRepository.getById(i);
                if(users.getAccount()>-500.00D)
                {
                users.setAccount(users.getAccount() - users.getMinutes() * users.getContract().getSum());
                users.setMinutes(0d);
                userRepository.save(users);
                    i++;
                System.out.println("yes");}
                else
                {System.out.println("false");
                    i++;}
            }
        }
        System.out.println(timetopay+" "+time);
    }
}
