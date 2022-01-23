package com.lakshay.jobsportal.repository;

import com.lakshay.jobsportal.model.Role;
import com.lakshay.jobsportal.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



import java.util.List;
import java.util.Optional;
import java.util.UUID;



public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String Username);


    Optional<Users> findByMobile(String mobile);

    Optional<Users> findByEmail(String email);
    List<Users> findAllByRole(Role role);


    @Query("update Users u set u.username= ?1, u.mobile = ?2, u.email = ?3  where u.userId = ?4")
    @Modifying
    void setUserInfoWithoutPassById(String Username, String mobile,String email, long UserId);


    @Query("update Users u set u.username= ?1, u.mobile = ?2, u.email = ?3, u.password = ?4 where u.userId = ?5")
    @Modifying
    void setUserInfoById(String Username, String mobile,String email,String password, long UserId);

}