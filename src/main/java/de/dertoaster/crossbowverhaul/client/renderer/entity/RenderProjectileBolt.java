package de.dertoaster.crossbowverhaul.client.renderer.entity;

import de.dertoaster.crossbowverhaul.CrossbowverhaulMod;
import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBolt;
import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBoltExplosive;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderProjectileBolt<T extends ProjectileBolt> extends ArrowRenderer<T> {

	private static final ResourceLocation TEXTURE_BOLT_IRON = ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "textures/entity/projectiles/bolt_iron.png");
	private static final ResourceLocation TEXTURE_BOLT_GOLD = ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "textures/entity/projectiles/bolt_gold.png");
	private static final ResourceLocation TEXTURE_BOLT_DIAMOND = ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "textures/entity/projectiles/bolt_diamond.png");
	private static final ResourceLocation TEXTURE_BOLT_NETHERITE = ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "textures/entity/projectiles/bolt_netherite.png");
	
	private static final ResourceLocation TEXTURE_BOLT_EXPLOSIVE = ResourceLocation.fromNamespaceAndPath(CrossbowverhaulMod.MODID, "textures/entity/projectiles/bolt_explosive.png");
	
	public RenderProjectileBolt(EntityRendererProvider.Context renderManager) {
		super(renderManager);
	}

	@Override
	public ResourceLocation getTextureLocation(T boltEntity) {
		if(boltEntity instanceof ProjectileBoltExplosive) {
			return TEXTURE_BOLT_EXPLOSIVE;
		}
		switch(Tiers.values()[boltEntity.getSynchedTierID()]) {
		case DIAMOND:
			return TEXTURE_BOLT_DIAMOND;
		case GOLD:
			return TEXTURE_BOLT_GOLD;
		case NETHERITE:
			return TEXTURE_BOLT_NETHERITE;
		default:
			return TEXTURE_BOLT_IRON;
		}
	}

}
