package leCraft.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockRendererMelter implements ISimpleBlockRenderingHandler {
	
	public static BlockRendererMelter instance = new BlockRendererMelter();
	
	public int renderid=0;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		
		renderer.renderStandardBlock(block, x, y, z);
        Tessellator var5 = Tessellator.instance;
        var5.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        float var6 = 1.0F;
        int var7 = block.colorMultiplier(world, x, y, z);
        float var8 = (float)(var7 >> 16 & 255) / 255.0F;
        float var9 = (float)(var7 >> 8 & 255) / 255.0F;
        float var10 = (float)(var7 & 255) / 255.0F;
        float var12;

        if (EntityRenderer.anaglyphEnable)
        {
            float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
            var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
            float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
            var8 = var11;
            var9 = var12;
            var10 = var13;
        }

        var5.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
        short var16 = 1;
        var12 = 0.125F;
        float wall = 0.0625F;
        float wall2 = 0.9375F;
        renderer.renderSouthFace(block, (double)((float)x - 1+2*var12-0.0006F), (double)y, (double)z, var16);
        renderer.renderNorthFace(block, (double)((float)x + 1-2*var12+0.0006F), (double)y, (double)z, var16);
        renderer.renderWestFace(block, (double)x, (double)y, (double)((float)z-1+2*var12-0.0006F), var16);
        renderer.renderEastFace(block, (double)x, (double)y, (double)((float)z+1-2*var12+0.0006F), var16);
                
        short var17 = 2;
        renderer.renderTopFace(block, (double)x, (double)((float)y - 1.0F + 0.25F), (double)z, var17);
        renderer.renderBottomFace(block, (double)x, (double)((float)y + 1.0F - wall2), (double)z, 4);
        int var14 = world.getBlockMetadata(x, y, z);

        if (var14 == 0)
        {
            short var15 = 4;

            renderer.renderTopFace(block, (double)x, (double)((float)y - 1.0F+3*wall), (double)z, var15);
        }else if(var14 == 1)
        {
        	short var15 = 0;
        	float texY = 0.7F;
            renderer.renderTopFace(block, (double)x, (double)((float)y - 1.0F+3*wall+texY), (double)z, var15);

        }
        

        return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		if(this.renderid==0){
			renderid=RenderingRegistry.getNextAvailableRenderId();
		}
		return renderid;
	}

}
