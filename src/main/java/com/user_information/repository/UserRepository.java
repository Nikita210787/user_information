package com.user_information.repository;

import com.user_information.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
// JPARepository extends PagingAndSortingRepository , PagingAndSortingRepository extens CrudRepository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(Integer id);
    @Query("SELECT u FROM User u")
    List<User> findAll();


}
