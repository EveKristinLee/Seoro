package com.seoro.seoro.domain.entity.ChatRoom;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class ChatRoomPhoto implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatRoomPhotoId;
    private String photo;
}
