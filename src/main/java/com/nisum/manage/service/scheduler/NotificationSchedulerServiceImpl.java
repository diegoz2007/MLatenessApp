package com.nisum.manage.service.scheduler;

import com.nisum.manage.persistence.ArriveStatus;
import com.nisum.manage.service.ArriveStatusServices;
import com.nisum.manage.util.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by dpinto on 26-04-2016.
 */
public class NotificationSchedulerServiceImpl implements NotificationSchedulerService {

    private static Logger logger= Logger.getLogger(NotificationSchedulerServiceImpl.class.toString());

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private ArriveStatusServices asServices;

    @Override
    /*@Scheduled(fixedDelay = 120000)*/
    public void serviceMethodToNotification() {

        logger.info("Method scheduled for executed at every ..  AND WILL SEND AN EMAIL TO THE EMPLOYEE WITH LATENEES.");

        Date d=new Date();

        try {
            List<ArriveStatus> basicDBObjects = asServices.findAllToSendMail();

            if(basicDBObjects==null || basicDBObjects.size()==0 )
                   return;

            emailUtils.sendMail("donotreply-saschile@nisum.com",recipientList(basicDBObjects),"Please do not be late!","<h1>Please do not be late, it will be deducted from your paycheck! :)</h1>");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String[] recipientList(List<ArriveStatus> basicDBObjects){
        String [] recipients=new String[basicDBObjects.size()];
        int i=0;
        for(ArriveStatus as:basicDBObjects) {
            recipients[i] = as.getEmail();
            i++;
        }
        return recipients;
    }

}
