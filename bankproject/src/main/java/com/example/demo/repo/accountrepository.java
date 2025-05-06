//to create account we using save method of the jpa repository
package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.account;
//you will pass which entity and primary key
public interface accountrepository extends JpaRepository<account,Long> {

}
