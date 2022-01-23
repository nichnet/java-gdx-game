package com.engine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Constants {

	public static final int GRID_SIZE = 32;//pixels
	public static final int ANIM_DEFAULT_SPEED = 1;
	public static final int MILLIS_IN_SEC = 1000;
	public static final int VIEW_DISTANCE = 10;
	
	public static void printInputStream(InputStream is) {

        try (InputStreamReader streamReader =
                    new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
	public static String generateId() {
		return UUID.randomUUID().toString();
	}
}
