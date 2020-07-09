package com.bootcamp.expenseTracker.requestController;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
     
}
