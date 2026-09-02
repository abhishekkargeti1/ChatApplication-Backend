package com.chatapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.entities.Room;
import com.chatapp.repositories.RoomRepository;

@Service
public class RoomService {
	@Autowired
	private RoomRepository repository;
	
	public Room getRoomById(String roomId) {
		return repository.findByRoomId(roomId);
	}
	
	public Room createNewRoom(Room room) {
		return repository.save(room);
	}

}
