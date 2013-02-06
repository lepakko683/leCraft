package leCraft.common.event;

import java.util.ArrayList;

import net.minecraftforge.event.Event;

public class DrawChunkBoundsEvent extends Event {
	
	public ArrayList bounds = new ArrayList();
	
	public DrawChunkBoundsEvent(float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
	{
		bounds.add((float)minX);
		bounds.add((float)minY);
		bounds.add((float)minZ);
		bounds.add((float)maxX);
		bounds.add((float)maxY);
		bounds.add((float)maxZ);
	}

}
