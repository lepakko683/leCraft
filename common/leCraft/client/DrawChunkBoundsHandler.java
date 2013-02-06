package leCraft.client;

import java.awt.Event;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class DrawChunkBoundsHandler {
	
	public static void drawChunkBounds(double x, double y, double z, RenderEngine ren)
	{
		Tessellator drawer = Tessellator.instance;
		
			int tex1 = ren.getTexture("/ElementCraft2/res/borderEffect.png");
			GL11.glDepthMask(false);
			GL11.glPushMatrix();
			//GL11.glDisable(GL11.GL_LIGHTING);
			//drawer.addTranslation((float)x, (float)y, (float)z);
			GL11.glColor3f(1.0f, 0.5f, 0.1f);
			//GL11.glBegin(GL11.GL_LINES);
			//GL11.glVertex2f(200f, 200f);
			//GL11.glVertex2f(400f, 400f);
			//GL11.glEnd();
			GL11.glTranslated(0, 0, 0);
			System.out.println("X: " + x + "Y: " + y + "Z: " + z);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
			
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex1);
		
			drawer.startDrawingQuads();
			drawer.addVertexWithUV(-1.0D, 1.0D, 0F, 0, 1);
			drawer.addVertexWithUV(1.0D, 1.0D, 0F, 1, 1);
			drawer.addVertexWithUV(1.0D, -1.0D, 0F, 1, 0);
			drawer.addVertexWithUV(-1.0D, -1.0D, 0F, 0, 0);
			
			drawer.draw();
			GL11.glPopMatrix();
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			//GL11.glEnable(GL11.GL_LIGHTING);
			
			GL11.glDepthMask(true);
		
	}
}
