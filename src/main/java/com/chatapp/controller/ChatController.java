package com.chatapp.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.chatapp.entities.Messages;
import com.chatapp.entities.Room;
import com.chatapp.payload.MessageRequest;
import com.chatapp.repositories.RoomRepository;

@Controller
@CrossOrigin("http://localhost:5173")
public class ChatController {

	@Autowired
	private RoomRepository repository;

	public ChatController(RoomRepository repository) {
		super();
		this.repository = repository;
	}

	// sending message

	@MessageMapping("/sendMessage/{roomId}")// app/sendMessage/{roomId}
	@SendTo("/topic/room/{roomId}")// subscribe 
	public Messages sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest request)throws Exception {
		Room room = repository.findByRoomId(request.getRoomId());

		Messages message = new Messages();
		message.setContent(request.getContent());
		message.setSender(request.getSender());
		message.setTimeStamp(LocalDateTime.now());

		if (room != null) {
			room.getMessages().add(message);
			repository.save(room);
		} else {
			throw new RuntimeException("Room Not Found");
		}
		return message;
	}

}
