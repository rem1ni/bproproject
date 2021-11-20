package com.backend.bprobackend.service;

import com.backend.bprobackend.model.Pay;
import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.PayRepos;
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
    @Autowired
    PayRepos payRepos;
    String timetopay="20";

    @Scheduled(fixedDelay = 3600000)
    public void PayService1(){
        String time=new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        Long count= userRepository.count();
        Long i =1l;
        if (Objects.equals(timetopay,time)) {
            while (i <= count) {
                User users = userRepository.getById(i);
                if(users.getAccount()>-500.00D && users.getMinutes()!=0)
                {
                    Double sum= users.getMinutes() * users.getContract().getSum();
                users.setAccount(users.getAccount() - sum);
                users.setMinutes(0d);
                userRepository.save(users);
                sum=-1*sum;
                    String timetopay=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Calendar.getInstance().getTime());
                    Pay pay = new Pay(i,sum,users.getAccount(),timetopay);
                    payRepos.save(pay);
                System.out.println(pay.getPay());
                    i++;
                }
                else
                {System.out.println("false");
                    i++;
                }
            }
        }
        System.out.println(timetopay+" "+time);
    }
}
