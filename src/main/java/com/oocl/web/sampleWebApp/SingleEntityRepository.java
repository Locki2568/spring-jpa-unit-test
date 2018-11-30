package com.oocl.web.sampleWebApp;


import org.springframework.data.jpa.repository.JpaRepository;

public interface SingleEntityRepository extends JpaRepository<SingleEntity, Long> {
}
