package com.engine.player;

import com.engine.world.LivingEntity;

public class Player {

	private String id;
	private String username; 
	private String entityId;
	private FriendList friendList;
	private BlockList blockList;
	
	public Player(String id, String username, String entityId) {
		this.id = id;
		this.username = username;
		this.entityId = entityId;

		this.friendList = new FriendList();
		this.blockList = new BlockList();
	}

	public String getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEntityId() {
		return this.entityId;
	}
	
	private LivingEntity getLivingEntity() {
		return null;//TODO return living entity of Player.
	}
	
	public FriendList getFriendList() {
		return this.friendList;
	}
	
	public BlockList getBlockList() {
		return this.blockList;
	}
}
