package com.engine.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.engine.util.Constants;
import com.engine.util.Logger;
import com.engine.world.npc.Man;
import com.game.Game;

public class World {

	private String name;
	private int width;
	private int height;
	
	//hash maps of all currently spawned things in the world.
	private HashMap<String, Tile> tiles;
	private HashMap<String, Object> objects;
	private HashMap<String, Item> items;
	private HashMap<String, Entity> entities;
	private HashMap<String, LivingEntity> livingEntities;
	private HashMap<String, PlayerCharacter> playerCharacters;
	
	/**
	 * Returns an array of currently "renderable" tiles. 
	 * Renderable tiles are determined by their visibility (if they are within view distance of the current player's character).
	 * @return array of tiles
	 */
	public Tile[] getRenderableTiles() {
		HashSet<Tile> renderable = new HashSet<Tile>();
		
		for(Tile tile : tiles.values()) {
			if(tile.isVisible()) {
				renderable.add(tile);
			}
		}

		return renderable.toArray(new Tile[] {});
	}
	
	/**
	 * Returns an array of currently "renderable" entities. 
	 * Renderable entities are determined by their visibility (if they are within view distance of the current player's character).
	 * @return array of entities
	 */
	public Entity[] getRenderableEntities() {
		HashSet<Entity> renderable = new HashSet<Entity>();
		
		for(Entity entity : entities.values()) {
			if(entity.isVisible()) {
				renderable.add(entity);
			}
		}
		return renderable.toArray(new Entity[] {});
	}
	
	/**
	 * Returns an array of currently "renderable" living entities. 
	 * Renderable living entities are determined by their visibility (if they are within view distance of the current player's character).
	 * @return array of living entities
	 */
	public LivingEntity[] getRenderableLivingEntities() {
		HashSet<LivingEntity> renderable = new HashSet<LivingEntity>();
		
		for(LivingEntity livingEntity : livingEntities.values()) {
			if(livingEntity.isVisible()) {
				renderable.add(livingEntity);
			}
		}
		
		return renderable.toArray(new LivingEntity[] {});
	}
	
	/**
	 * Returns an array of currently "renderable" items. 
	 * Renderable items are determined by their visibility (if they are within view distance of the current player's character).
	 * @return array of items
	 */
	public Item[] getRenderableItems() {
		HashSet<Item> renderable = new HashSet<Item>();
		
		for(Item item : items.values()) {
			if(item.isVisible()) {
				renderable.add(item);
			}
		}

		return renderable.toArray(new Item[] {});
	}
	
	/**
	 * Returns an array of currently "renderable" objects. 
	 * Renderable objects are determined by their visibility (if they are within view distance of the current player's character).
	 * @return array of objects
	 */
	public Object[] getRenderableObjects() {
		HashSet<Object> renderable = new HashSet<Object>();
		
		for(Object obj : objects.values()) {
			if(obj.isVisible()) {
				renderable.add(obj);
			}
		}
		
		return renderable.toArray(new Object[] {});
	}
	
	/**
	 * Returns an array of currently "renderable" players. 
	 * Renderable players are determined by their visibility (if they are within view distance of the current player's character).
	 * @return array of players
	 */
	public PlayerCharacter[] getRenderablePlayers() {
		HashSet<PlayerCharacter> renderable = new HashSet<PlayerCharacter>();
		
		for(PlayerCharacter player : playerCharacters.values()) {
			if(player.isVisible()) {
				renderable.add(player);
			}
		}

		return renderable.toArray(new PlayerCharacter[] {});
	}
	
	public World(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
		
		//initialize the world.
		initialize();
	}
	
	/**
	 * Initializes the world data.
	 */
	private void initialize() {
		this.tiles = new HashMap<>();

		this.objects = new HashMap<>();
		this.items = new HashMap<>();
		this.entities = new HashMap<>();
		this.livingEntities = new HashMap<>();
		this.playerCharacters = new HashMap<>();
		
		//load in test world data manually. 
		testWorldData();
	}
	
	/**
	 * Test data.
	 */
	private void testWorldData() {
		spawnObject("log_pile", new Position(8,5));
		spawnObject("camp_fire", new Position(9,5));
		spawnObject("tree", new Position(11,6)); 
		spawnObject("tree", new Position(11,4));
		spawnObject("barrel", new Position(11,7));
		spawnObject("barrel", new Position(11,3));
		spawnObject("grave_stone", new Position(5,3));
		spawnObject("sign", new Position(4,7));
		spawnObject("chair", new Position(6,7));
		spawnObject("obelisk", new Position(7,9));
		spawnObject("stool", new Position(4,10));
		spawnObject("table", new Position(5,10));
		spawnObject("stool", new Position(6,10));
		spawnObject("stool", new Position(5,11));
		spawnObject("stool", new Position(5,9));
		spawnObject("well", new Position(9,10));
		spawnObject("bed_sack", new Position(11,10));
		spawnObject("bed_wooden", new Position(13,10));
		
		spawnPlayer(Constants.generateId(), new Position(5,5), true);
		spawnLivingEntity("man",new Position(8,8));
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Position position = new Position(x, y);
				
				Random r = new Random();
				
				int i = r.nextInt(100);
				
				
				if(((x == 2 || x == width - 2) && y >= 2) || 
						((y == 2 || y == height - 2) && x >= 2)) {
					spawnObject("tree", position);
				}
				
				String type = "grass";

				if(x < 2 || x >= width - 2 || y < 2 || y >= height - 2) {
					type = "water";
				}
				
				if(i < 2) {
					if(type.equals("grass")) {
						spawnObject("weed", position);
					}
				}
				
				
				
				Tile tile = new Tile(Constants.generateId(), type, position);
				tiles.put(tile.getId(), tile);
			}	
		}
	}
	
	/**
	 * Spawns a "Living Entity" into the world.
	 * @param The type of Living Entity to spawn.
	 * @param The spawn position.
	 */
	private void spawnLivingEntity(String type, Position position) {
		//TODO perhaps use an enum for the "type"
		LivingEntity entity = null;
		String id = Constants.generateId();
		
		if(type.equals("man")) {
			entity = new Man(id, type, position);
		} else {
			entity = new LivingEntity(id, type, position);
		}
		
		livingEntities.put(id, entity);
	}

	/**
	 * 	 
	 * Spawns an "Item" into the world.
	 * @param The type of Item to spawn.
	 * @param The quantity to spawn.
	 * @param The spawn position.
	 */
	public void spawnItem(String type, int quantity, Position position) {
		Item item = new Item(Constants.generateId(), type, position, quantity);
		items.put(item.getId(), item);
	}
	
	/**
	 * 	 
	 * Spawns an "Object" into the world.
	 * @param The type of Object to spawn.
	 * @param The spawn position.
	 */
	public void spawnObject(String type, Position position) {
		//ensure an object doesnt exist here yet
		Object existing = getObjectAtPosition(position);
		
		if(existing == null) {			
			Object obj = new Object(Constants.generateId(), type, position);
			objects.put(obj.getId(), obj);
		} else {
			Logger.log(String.format("Cannot spawn object at %s, because an object already exists here.", position.toString()));
		}
	}

	/**
	 * @param The player Id.
	 * @param The spawn position.
	 * @param Whether the player character is local or remote.
	 */
	public void spawnPlayer(String id, Position position, boolean local) {
		PlayerCharacter playerCharacter = new PlayerCharacter(id, position);
		playerCharacters.put(playerCharacter.getId(), playerCharacter);
		//TODO set playerCharacter is local player as true.
		if(local) {
			Game.getInstance().getCamera().attachToEntity(playerCharacter);
		}
	}
	
	/**
	 * @return The local player.
	 */
	public PlayerCharacter getLocalPlayerCharacter() {
		for(PlayerCharacter character : playerCharacters.values()) {
			if(character.isLocalPlayer()) {
				return character;
			}
		}
		
		return null;
	}
	
	/**
	 * @param The expected position.
	 * @return the object at the specified position of the world.
	 */
	private Object getObjectAtPosition(Position position) {
		for(Object object : objects.values()) {
			if(object.getPosition().equals(position)) {
				return object;
			}
		}
		
		return null;
	}
	
	/**
	 * @param The expected position.
	 * @return the tile at the specified position of the world.
	 */
	public Tile getTile(Position position) {
		if(!isPositionValid(position)) {
			return null;
		}

		for(Tile tile : tiles.values()) {
			if(tile.getPosition().equals(position)){
				return tile;
			}
		}
		
		return null;
	}
	
	/**
	 * @param The expected Id.
	 * @return the tile with the specified Id.
	 */
	public Tile getTile(String id) {
		for(Tile tile : tiles.values()) {
			if(tile.getId().equals(id)){
				return tile;
			}
		}
	
		return null;
	}
	
	/**
	 * Ticks (updates) all items in the world.
	 */
	public void tick() {
		tickItems();
		tickObjects();
		tickTiles();
		tickEntities();
		tickLivingEntities();
		tickPlayerCharacters();
	}
	
	/**
	 * Ticks entities.
	 */
	private void tickEntities() {
		for(Entity entity : entities.values()) {
			entity.tick();
		}
		
	}
	
	/**
	 * Ticks player characters.
	 */
	private void tickPlayerCharacters() {
		for(PlayerCharacter playerCharacter : playerCharacters.values()) {
			playerCharacter.tick();
		}
	}

	/**
	 * ticks living entities.
	 */
	private void tickLivingEntities() {
		for(LivingEntity livingEntity : livingEntities.values()) {
			livingEntity.tick();
		}
	}
	
	/**
	 * ticks tiles.
	 */
	private void tickTiles() {
		for(Tile tile: tiles.values()) {
			tile.tick();
		}
	}
	
	/**
	 * ticks objects.
	 */
	private void tickObjects() {
		for(Object obj : objects.values()) {
			obj.tick();
		}
	}
	
	/**
	 * ticks items.
	 */
	private void tickItems() {
		for(Item item : items.values()) {
			item.tick();
		}
	}

	
	/**
	 * @param The expected position.
	 * @return Returns if the position is valid in the world.
	 */
	public boolean isPositionValid(Position position) { 
		//TODO may be out of bounds if its more <= or >= , may just need to be < or >
		return (position.getX() <= getWidth() && 
				position.getX() >= 0 && 
				
				position.getY() <= getHeight() && 
				position.getY() >= 0);//&& 
				
				//position.getZ() <= getMaxDepth() && 
				//position.getZ() >= 0);
	}
	
	/**
	 * The name of the world.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return Returns the height of the world.
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * @return Returns the width of the world.
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * @return Returns the total size of the world.
	 */
	public int getSize() {
		return (this.width * this.height);
	}

	public ObjectBase[] getAllObjects() {
		List<ObjectBase> all = new ArrayList<>();

		all.addAll(tiles.values());
		all.addAll(objects.values());
		all.addAll(items.values());
		all.addAll(playerCharacters.values());
		all.addAll(entities.values());
		all.addAll(livingEntities.values());
		
		return all.toArray(new ObjectBase[] {});
	}
}
