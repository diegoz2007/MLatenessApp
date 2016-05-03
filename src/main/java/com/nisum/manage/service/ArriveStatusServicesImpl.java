package com.nisum.manage.service;

import com.nisum.manage.persistence.ArriveStatus;
import com.nisum.manage.persistence.repositories.ArriveStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.List;

/**
 * Created by dpinto on 26-04-2016.
 */
public class ArriveStatusServicesImpl implements ArriveStatusServices {


    @Autowired
    ArriveStatusRepository asRepository;


    @Override
    public List<ArriveStatus> findAll(String from, String to, String email) throws ParseException {
        return asRepository.findAll(from,to,email);
    }

    @Override
    public List<ArriveStatus> findAll(){
        return asRepository.findAll();
    }

    @Override
    public void save(ArriveStatus entity){
        asRepository.save(entity);
    }

    @Override
    public void deleteForPurge() {
        asRepository.deleteForPurge();
    }

    @Override
    public List<ArriveStatus> findAllToSendMail() throws ParseException {
        return asRepository.findAllToSendMail();
    }
}
