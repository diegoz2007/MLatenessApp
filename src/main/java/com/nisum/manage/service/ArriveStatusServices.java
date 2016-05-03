package com.nisum.manage.service;

import com.nisum.manage.persistence.ArriveStatus;

import java.text.ParseException;
import java.util.List;

/**
 * Created by dpinto on 26-04-2016.
 */
public interface ArriveStatusServices {

    public List<ArriveStatus> findAll(String from, String to, String email) throws ParseException;

    List<ArriveStatus> findAll();

    public void save(ArriveStatus entity);

    public void  deleteForPurge();

    public List<ArriveStatus> findAllToSendMail() throws ParseException;

}
