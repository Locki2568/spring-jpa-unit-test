package com.oocl.web.sampleWebApp;

import com.oocl.web.sampleWebApp.SingleEntity;
import com.oocl.web.sampleWebApp.SingleEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SingleEntityTest {
    @Autowired
    private SingleEntityRepository singleEntityRepository;

    @Test
    public void should_test_single_entity(){
        //Given a single entity obj and saved into repository
        SingleEntity singleEntity = new SingleEntity();
        singleEntity.name = "HI";
        singleEntity.id = 1L;
        singleEntityRepository.save(singleEntity);

        // When get back single entity from repository
        SingleEntity singleEntityFromRepo = singleEntityRepository.getOne(1L);

        // Then assert equal should return true
        assertEquals("HI", singleEntityFromRepo.name);
    }
}
