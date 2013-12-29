package com.github.mcfeudalages.kingdoms.adapter;

import java.lang.reflect.Type;
import java.util.Map;

import com.github.mcfeudalages.kingdoms.RegionAccess;
import com.github.mcfeudalages.kingdoms.entity.RegionMap;
import com.massivecraft.mcore.ps.PS;
import com.massivecraft.mcore.xlib.gson.JsonDeserializationContext;
import com.massivecraft.mcore.xlib.gson.JsonDeserializer;
import com.massivecraft.mcore.xlib.gson.JsonElement;
import com.massivecraft.mcore.xlib.gson.JsonParseException;
import com.massivecraft.mcore.xlib.gson.JsonSerializationContext;
import com.massivecraft.mcore.xlib.gson.JsonSerializer;

public class RegionMapAdapter implements JsonSerializer<RegionMap>, JsonDeserializer<RegionMap>{
	
	private static RegionMapAdapter i = new RegionMapAdapter();
	public static RegionMapAdapter get() { return i; }


	@SuppressWarnings("unchecked")
	@Override
	public RegionMap deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		return new RegionMap((Map<PS, RegionAccess>) context.deserialize(json, RegionMap.MAP_TYPE));
	}
	
	@Override
	public JsonElement serialize(RegionMap src, Type typeOfSrc,
			JsonSerializationContext context) {
		return context.serialize(src.getMap(), RegionMap.MAP_TYPE);
	}
}
