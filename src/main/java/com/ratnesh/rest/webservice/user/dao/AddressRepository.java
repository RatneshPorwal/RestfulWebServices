package com.ratnesh.rest.webservice.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ratnesh.rest.webservice.user.bean.MyAddress;
import com.ratnesh.rest.webservice.user.bean.MyUser;

@Repository
public interface AddressRepository extends JpaRepository<MyAddress, Integer> {

}
