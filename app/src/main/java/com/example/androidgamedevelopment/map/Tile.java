package com.example.androidgamedevelopment.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.androidgamedevelopment.graphics.SpriteSheet;

public abstract class Tile {
    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }


    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {

        switch(TileType.values()[idxTileType]) {

            case WATER_TILE:
                return new WaterTile(spriteSheet, mapLocationRect);
            case LAVA_TILE:
                return new LavaTile(spriteSheet, mapLocationRect);
            case GROUND_TILE:
                return new GroundTile(spriteSheet, mapLocationRect);
            case GRASS_TILE:
                return new GrassTile(spriteSheet, mapLocationRect);
            case TREE_TILE:
                return new TreeTile(spriteSheet, mapLocationRect);
            default:
                return null;
        }

    }

    public abstract void draw(Canvas canvas);
}
