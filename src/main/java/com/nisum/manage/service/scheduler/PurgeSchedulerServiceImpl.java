package com.nisum.manage.service.scheduler;

import com.nisum.manage.service.ArriveStatusServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

/**
 * Created by dpinto on 27-04-2016.
 */
public class PurgeSchedulerServiceImpl implements PurgeSchedulerService {

    private static Logger logger = Logger.getLogger(PurgeSchedulerServiceImpl.class.toString());

    @Autowired
    private ArriveStatusServices asServices;

 /*   @Value("${valor.cantidad}")
    private static long fixDelay;*/

    @Override
    /*@Scheduled(fixedDelay = 80000)*/
    public void serviceMethodToPurge() {
        logger.info("Method scheduled for executed at every .. AND WILL DELETE THE DATA OLDER THAN A MONTH ");

        asServices.deleteForPurge();

    }
}
