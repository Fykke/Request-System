package com.maksim.requestsystem.repositories;

import com.maksim.requestsystem.models.Requests;
import org.springframework.data.repository.CrudRepository;

public interface RequestsRepository extends CrudRepository<Requests, Long> {

}