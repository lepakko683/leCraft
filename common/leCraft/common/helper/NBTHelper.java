package leCraft.common.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;


public class NBTHelper {
	private static void InitNBTHelper(ItemStack stack){
		if(stack.stackTagCompound == null){
			stack.setTagCompound(new NBTTagCompound());
		}
	}
	
	public static String getString(ItemStack stack, String key){
		InitNBTHelper(stack);
		
		if(!stack.stackTagCompound.hasKey(key)){
			setString(stack, key, "");
		}
		
		return stack.stackTagCompound.getString(key);
		
	}
	
	public static void setString(ItemStack stack, String key, String value){
		InitNBTHelper(stack);
		
		stack.stackTagCompound.setString(key, value);
	}
	
	public static boolean getBoolean(ItemStack stack, String key){
		InitNBTHelper(stack);
		
		if(!stack.stackTagCompound.hasKey(key)){
			setBoolean(stack, key, false);
		}
		
		return stack.stackTagCompound.getBoolean(key);
		
	}
	
	public static void setBoolean(ItemStack stack, String key, boolean value){
		InitNBTHelper(stack);
		
		stack.stackTagCompound.setBoolean(key, value);
	}
	
	public static short getShort(ItemStack stack, String key){
		InitNBTHelper(stack);
		
		if(!stack.stackTagCompound.hasKey(key)){
			setShort(stack, key, (short)0);
		}
		
		return stack.stackTagCompound.getShort(key);
		
	}
	
	public static void setShort(ItemStack stack, String key, short value){
		InitNBTHelper(stack);
		
		stack.stackTagCompound.setShort(key, value);
	}
	
	public static int getInt(ItemStack stack, String key){
		InitNBTHelper(stack);
		
		if(!stack.stackTagCompound.hasKey(key)){
			setInt(stack, key, 0);
		}
		
		return stack.stackTagCompound.getInteger(key);
		
	}
	
	public static void setInt(ItemStack stack, String key, int value){
		InitNBTHelper(stack);
		
		stack.stackTagCompound.setInteger(key, value);
	}
	
	public static byte getByte(ItemStack stack, String key){
		InitNBTHelper(stack);
		
		if(!stack.stackTagCompound.hasKey(key)){
			setByte(stack, key, (byte)0);
		}
		
		return stack.stackTagCompound.getByte(key);
		
	}
	
	public static void setByte(ItemStack stack, String key, byte value){
		InitNBTHelper(stack);
		
		stack.stackTagCompound.setByte(key, value);
	}
	
	public static long getLong(ItemStack stack, String key){
		InitNBTHelper(stack);
		
		if(!stack.stackTagCompound.hasKey(key)){
			setLong(stack, key, (long)0);
		}
		
		return stack.stackTagCompound.getLong(key);
		
	}
	
	public static void setLong(ItemStack stack, String key, long value){
		InitNBTHelper(stack);
		
		stack.stackTagCompound.setLong(key, value);
	}
	
	public static float getFloat(ItemStack stack, String key){
		InitNBTHelper(stack);
		
		if(!stack.stackTagCompound.hasKey(key)){
			setFloat(stack, key, 0f);
		}
		
		return stack.stackTagCompound.getFloat(key);
		
	}
	
	public static void setFloat(ItemStack stack, String key, float value){
		InitNBTHelper(stack);
		
		stack.stackTagCompound.setFloat(key, value);
	}
	
	public static double getDouble(ItemStack stack, String key){
		InitNBTHelper(stack);
		
		if(!stack.stackTagCompound.hasKey(key)){
			setDouble(stack, key, 0D);
		}
		
		return stack.stackTagCompound.getDouble(key);
		
	}
	
	public static void setDouble(ItemStack stack, String key, double value){
		InitNBTHelper(stack);
		
		stack.stackTagCompound.setDouble(key, value);
	}
	
	public static int[] getIntArray(ItemStack stack, String key){
		InitNBTHelper(stack);
		
		if(!stack.stackTagCompound.hasKey(key)){
			setIntArray(stack, key, null);
		}
		
		return stack.stackTagCompound.getIntArray(key);
		
	}
	
	public static void setIntArray(ItemStack stack, String key, int[] value){
		InitNBTHelper(stack);
		
		stack.stackTagCompound.setIntArray(key, value);
	}
	
	public static ItemStack getItemStack(ItemStack stack, String key){
		InitNBTHelper(stack);
		ItemStack eStack = null;
		
		
		if(!stack.stackTagCompound.hasKey(key)){
			setItemStack(stack, key, eStack);
		}
		int a[] = new int[3];
		a = stack.stackTagCompound.getIntArray("key");
		int iId=a[0], sS=a[1], dT=a[2];
		ItemStack retStack = new ItemStack(iId, sS, dT);
		
		return retStack;
	}
	
	public static void setItemStack(ItemStack stack, String key, ItemStack stack2){
		InitNBTHelper(stack);
		int[] store = new int[3];
		store[0]=stack2.itemID; store[1]=stack2.stackSize; store[2]=stack2.getItemDamage();
		
		stack.stackTagCompound.setIntArray(key, store);
	}
	
}
