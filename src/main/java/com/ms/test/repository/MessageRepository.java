package com.ms.test.repository;

import org.springframework.data.repository.CrudRepository;

import com.ms.test.beans.Message;

/**
 * MessageRepository
 * @author Manish
 *
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {

}