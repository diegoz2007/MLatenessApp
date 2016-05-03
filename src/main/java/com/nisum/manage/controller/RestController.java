package com.nisum.manage.controller;


import com.nisum.manage.persistence.ArriveStatus;
import com.nisum.manage.service.ArriveStatusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by dpinto on 21-04-2016.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/app")
public class RestController {

    private static Logger logger=Logger.getLogger(RestController.class.toString());


    @Autowired
    private ArriveStatusServices asServices;


    @RequestMapping(value = "/search/since={since}&until={until}/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<List<ArriveStatus>> listAllArriveStatus(@PathVariable("since") String since, @PathVariable("until") String until, @PathVariable("email") String email) {
        List<ArriveStatus> arriveStatus= null;
        try {
            arriveStatus = asServices.findAll(since,until,email);

        } catch (Exception e) {

            e.printStackTrace();
        }
        if(arriveStatus.isEmpty()){
            return new ResponseEntity<List<ArriveStatus>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ArriveStatus>>(arriveStatus, HttpStatus.OK);
    }

    ////////////////////////////////////////////////////
    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public ResponseEntity<Void> insertArriveStatus(@RequestBody ArriveStatus arriveStatus,    UriComponentsBuilder ucBuilder) {

        logger.info("Creating Status " + arriveStatus.getEmail());

        try {
            asServices.save(arriveStatus);

        } catch (Exception e) {

            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


}
