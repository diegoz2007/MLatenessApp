package com.nisum.manage.persistence.repositories;

import com.nisum.manage.persistence.ArriveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dpinto on 22-04-2016.
 */
@Repository
public class ArriveStatusRepositoryImpl implements ArriveStatusRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<ArriveStatus> findAllToSendMail() throws ParseException {

        Date now = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String stringNow=dateFormat.format(now);

        dateFormat.setLenient(false);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date to=null;
        Date from = null;

        try {

            to=dateFormat.parse(stringNow);
            from = dateFormat.parse(stringNow);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        Query query = new Query();
        Criteria c=Criteria.where("punctuality").is(Boolean.valueOf(false));
        query.addCriteria(Criteria.where("date").gte(from).lte(to));
        query.addCriteria(c);

        return mongoTemplate.find(query, ArriveStatus.class);
    }
    /////////////////////////////////////////////////////////////

    public List<ArriveStatus> findAll(String f, String t, String email) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        dateFormat.setLenient(false);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date to = null;
        Date from = null;

        try {

            to = dateFormat.parse(t);
            from = dateFormat.parse(f);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        Query query = new Query();
        Criteria c=Criteria.where("email").regex(email);
        query.addCriteria(Criteria.where("date").gte(from).lte(to));
        query.addCriteria(c);


        List<ArriveStatus> basicDBObjects = mongoTemplate.find(query, ArriveStatus.class);
        return basicDBObjects;
    }

    @Override
    public List<ArriveStatus> findAll() {
        return mongoTemplate.findAll(ArriveStatus.class,"arriveStatus");
    }

    @Override
    public void deleteForPurge() {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date previousMonth = cal.getTime();

        Query query = new Query();
        query.addCriteria(Criteria.where("date").lte(previousMonth));

        mongoTemplate.remove(query, ArriveStatus.class);


    }

    @Override
    public void save(ArriveStatus entity) {

        mongoTemplate.save((Object)entity);
    }
}
