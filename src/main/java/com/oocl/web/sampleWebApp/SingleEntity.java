package com.oocl.web.sampleWebApp;

import javax.persistence.*;

@Entity
public class SingleEntity {
    @Id
    public Long id;
    @Column(length = 10)
    public String name;

    public SingleEntity(){
    }
}
