package com.engine.skills.skill;

import com.engine.assets.language.LanguageManager;

public abstract class SkillBase {

	private String id;
	private String name;
	private String description;

	public SkillBase(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public final String getId() {
		return this.id;
	}
	
	public final String getAssetDescription() {
		return this.description;
	}
	
	public final String getName() {
		return this.name;
	}

	public final String getLocalizedName() {
		return LanguageManager.getInstance().get(getName());
	}
	
	public final String getLocalizedDescription() {
		return LanguageManager.getInstance().get(String.format("%s_desc", this.getName()));
	}	
}
