package leCraft.lib;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderEngine;
import cpw.mods.fml.client.FMLClientHandler;

public class EC2textures {
	
	
	public static EC2textures instance = new EC2textures();
	Minecraft mc = FMLClientHandler.instance().getClient();
	RenderEngine r = mc.renderEngine;
	
	public int getTextureREff(){
		return r.getTexture("/ElementCraft/res/borderEffect.png");
	}
}
