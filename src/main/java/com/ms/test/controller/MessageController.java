package com.ms.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.test.beans.Message;
import com.ms.test.repository.MessageRepository;

/**
 * MessageController class
 * 
 * @author Manish
 *
 */
@RestController
@RequestMapping(path = "/messages")
public class MessageController {
	@Autowired
	private MessageRepository messageRepository;

	/**
	 * Service get message by message-id
	 * 
	 * @param messageid
	 * @return
	 */
	@GetMapping(value = "/getmessagebyid/{messageid}")
	@ResponseBody
	public Message getMessageById(@PathVariable("messageid") int messageid) {
		Message result = null;
		Optional<Message> message = messageRepository.findById(messageid);
		if (message.isPresent()) result = message.get();
		return result;
	}

	/**
	 * Service get all messages
	 * 
	 * @return
	 */
	@GetMapping(value = "/allmessages")
	@ResponseBody
	public List<Message> getAllMessages() {
		return (List<Message>) messageRepository.findAll();
	}

	/**
	 * Service create/update message
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/saveupdatemessage", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Message saveUpdateMessage(@RequestBody Message message) throws Exception {
		// Check message rights
		checkMessageRights(message.getMessageid());
		// New Or update need to add user-id from session
		message.setMessageuserid(UserController.sessionUserId);
		return messageRepository.save(message);
	}

	/**
	 * Service delete message
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "/deletemessage", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String deleteMessage(@RequestBody Message message) throws Exception {
		// Check message rights
		checkMessageRights(message.getMessageid());
		messageRepository.delete(message);
		return "Message Deleted";
	}

	/**
	 * Method check message right
	 * 
	 * @param messageId
	 * @return
	 * @throws Exception
	 */
	private boolean checkMessageRights(int messageId) throws Exception {
		if (messageId < 1)
			return true;

		Message oldMessage = getMessageById(messageId);
		if (oldMessage != null && oldMessage.getMessageuserid() != UserController.sessionUserId) {
			throw new Exception("Current user is not allow to edit or delete other's users messages.");
		}
		return true;
	}

}
