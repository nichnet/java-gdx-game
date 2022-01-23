package com.engine.assets.language;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.badlogic.gdx.Gdx;
import com.engine.assets.ResourceManagerBase;
import com.engine.assets.asset.AssetsManager;
import com.engine.util.Logger;
import com.engine.util.Settings;

public class LanguageManager extends ResourceManagerBase {
	
	private static LanguageManager instance;
	private HashMap<String, Language> languages;

	@Override
	public void load() {
		String path = "lang";
		this.languages = new HashMap<String, Language>();
		
		String[] files = AssetsManager.getInstance().getResourceFolderFiles(path, ".json");
		
		if(files == null || files.length == 0) {
			Logger.warning(String.format("No language packs found in: \"%s\".", path));
			return;
		}
	
		
		for(String file : files) {
			InputStream in = Gdx.files.internal(file).read();
			
			if(in == null) {
				Logger.error(String.format("Failed to load language pack: \"%s\".", file));
				continue;
			}

			JSONParser parser = new JSONParser();

			try {
				JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(in));
				
				if(obj == null) {
					Logger.error(String.format("Failed to load language pack: \"%s\", because the file is malformed.", file));
					continue;
				}

				String id = (String) getProperty(obj, "id");
				String name = (String) getProperty(obj, "name");
				JSONObject data = (JSONObject) getProperty(obj, "data");

				Iterator keys = data.keySet().iterator();
				
				while(keys.hasNext()) {
					String key = (String) keys.next();
					String value = (String) data.get(key);
					
					put(id, name);
					getLanguage(id).put(key, value);
				}
				
				in.close();
			} catch (IOException e) {
				Logger.error(String.format("Failed to load language pack: \"%s\", because the file could not be opened or located.", file));
			} catch (ParseException e) {
				Logger.error(String.format("Failed to load language pack: \"%s\", because the file is malformed.", file));
			}
		}
	}
	
	private void put(String name, String localizedName) {
		if(this.languages.containsKey(name)) {
			return;
		}
		
		this.languages.put(name, new Language(name, localizedName));
	}

	public String get(String name) {
		if(this.getCurrentLanguage() == null) {
			return name;
		}
		
		return this.getCurrentLanguage().get(name);
	}
	
	private Language getCurrentLanguage() {
		return this.languages.get(Settings.getInstance().getSelectedLanguage());
	}
	
	private Language getLanguage(String id) {
		return this.languages.get(id);
	}

	public static LanguageManager getInstance() {
		if(instance == null) {
			instance = new LanguageManager();
		}
		
		return instance;
	}

	public int getSize() {
		return this.languages.size();
	}

	public Language[] all() {
		return this.languages.values().toArray(new Language[] { });
	}
}
