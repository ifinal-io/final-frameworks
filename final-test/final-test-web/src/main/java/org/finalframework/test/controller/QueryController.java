package org.finalframework.test.controller;

import org.finalframework.data.query.Query;
import org.finalframework.test.dao.mapper.PersonMapper;
import org.finalframework.test.query.QPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author likly
 * @version 1.0
 * @date 2019-11-23 16:22:54
 * @since 1.0
 */
@RestController
@RequestMapping("/query")
public class QueryController {
    public static final Logger logger = LoggerFactory.getLogger(QueryController.class);

    @Resource
    private PersonMapper personMapper;

    @GetMapping
    public Object query() {
        Query query = new Query();
        query.where(QPerson.created.date().eq("2019-11-23"));
//        PersonQuery query = new PersonQuery();
//        query
//                .addCreatedEqual("2019-11-23")
//                .addCreatedEqual("")
//                .addIdEqual(1L)
//                .addCreatedDateEqual(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        return personMapper.select(query);
    }
}
