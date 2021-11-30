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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@EnableScheduling
@Transactional
@Service
public class PayDetailService {
    @Autowired
    UserRepos userRepository;
    @Autowired
    PayRepos payRepos;
    String timetopay="30";

    @Scheduled(fixedDelay = 3600000)
    public void PayService1(){
        String time=new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        List<User> users=  userRepository.findAll();
        int i =0;
        Long count = userRepository.count();
        System.out.println(count);
        if (Objects.equals(timetopay,time)) {
            while (i < count) {
                User user= users.get(i);
                if (user.getAccount()>-500.00D && user.getMinutes()!=0) {
                    Double sum = user.getMinutes() * user.getContract().getSum();
                    user.setAccount(user.getAccount() - sum);
                    user.setMinutes(0d);
                    userRepository.save(user);
                    sum = -1 * sum;
                    String timetopay = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Calendar.getInstance().getTime());
                    Pay pay = new Pay(user.getId(), sum, user.getAccount(), timetopay);
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
