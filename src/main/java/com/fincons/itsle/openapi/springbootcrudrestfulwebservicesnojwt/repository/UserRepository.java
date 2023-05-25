package com.fincons.itsle.openapi.springbootcrudrestfulwebservicesnojwt.repository;


import com.fincons.itsle.openapi.springbootcrudrestfulwebservicesnojwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
