package makamys.dmod.proxy;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import makamys.dmod.ConfigDMod;
import makamys.dmod.DMod;
import makamys.dmod.entity.EntityFox;
import makamys.dmod.future.entity.passive.EntityAnimalFuture;
import makamys.dmod.util.DUtil;
import makamys.dmod.util.EggHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.world.WorldEvent;

public class DProxyCommon {
	
	public Cache<EntityItem, EntityPlayer> itemDropperMap = CacheBuilder.newBuilder().maximumSize(1000).build();
	
	public void init() {
		EntityRegistry.registerModEntity(EntityFox.class, "fox", 0, DMod.instance, 64, 1, true);
        
        List<BiomeGenBase> foxBiomes = DUtil.getBiomesMatchingTag(BiomeDictionary.Type.CONIFEROUS);
    	DMod.LOGGER.debug("Fox spawn biomes: " + String.join(", ", foxBiomes.stream().map(b -> b.biomeName + " (" + b.getClass().getName() + ")").collect(Collectors.toList())));
        EntityRegistry.addSpawn(EntityFox.class, 8, 2, 4, EnumCreatureType.creature, foxBiomes.toArray(new BiomeGenBase[] {}));
        EggHelper.addEgg(EntityFox.class, 14005919, 13396256);
	}
	
	@SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
    	ConfigDMod.reload(true);
    }
    
    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
    	if(event.entity instanceof EntityAnimalFuture) {
    		event.distance = ((EntityAnimalFuture)event.entity).computeFallDistance(event.distance);
    	}
    }
    
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
    	if(event.source.getEntity() instanceof EntityFox) {
    		EntityFox fox = (EntityFox)event.source.getEntity();
    		int looting = fox.getLootingLevel();
    		EntityLivingBase victim = event.entityLiving;
    		if(fox.hasAbility(EntityFox.Ability.LOOTING_CHICKEN) && victim instanceof EntityChicken) {
    			int extraChicken = victim.getRNG().nextInt(1 + looting);
    			for(EntityItem entityItem : event.drops) {
    				Item item = entityItem.getEntityItem().getItem();
    				if(item == Items.cooked_chicken || item == Items.chicken) {
    					entityItem.getEntityItem().stackSize += extraChicken;
    				}
    			}
    		}
    	}
    }
    
    @SubscribeEvent
    public void onItemTossEvent(ItemTossEvent event) {
    	itemDropperMap.put(event.entityItem, event.player);
    }
	
}