package com.oocl.web.sampleWebApp;

import com.oocl.web.sampleWebApp.SingleEntity;
import com.oocl.web.sampleWebApp.SingleEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SingleEntityTest {
    @Autowired
    private SingleEntityRepository singleEntityRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void should_throws_exception_when_name_lenth_too_long(){
        //Given A single entity obj with name too long
        SingleEntity singleEntityWithLongName =new SingleEntity();
        singleEntityWithLongName.name = "NameWithTooManyCharacter";
        singleEntityWithLongName.id = 1L;
        //When save into repository should throw exception
        AssertHelper.assertThrows(Exception.class, () ->{
            singleEntityRepository.save(singleEntityWithLongName);
            entityManager.flush();
        });
    }

    @Test
    public void should_test_single_entity(){
        //Given a single entity obj and saved into repository
        SingleEntity singleEntity = new SingleEntity();
        singleEntity.name = "HI";
        singleEntity.id = 2L;
        singleEntityRepository.save(singleEntity);
        entityManager.flush();

        // When get back single entity from repository
        entityManager.clear();
        SingleEntity singleEntityFromRepo = singleEntityRepository.getOne(2L);


        // Then assert equal should return true
        assertEquals("HI", singleEntityFromRepo.name);
    }
}
