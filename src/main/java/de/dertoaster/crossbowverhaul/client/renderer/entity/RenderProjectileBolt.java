package de.dertoaster.crossbowverhaul.client.renderer.entity;

import de.dertoaster.crossbowverhaul.CrossbowverhaulMod;
import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBolt;
import de.dertoaster.crossbowverhaul.entity.projectile.ProjectileBoltExplosive;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderProjectileBolt<T extends ProjectileBolt> extends ArrowRenderer<T> {

	private static final ResourceLocation TEXTURE_BOLT_IRON = new ResourceLocation(CrossbowverhaulMod.MODID, "textures/entity/projectiles/bolt_iron.png");
	private static final ResourceLocation TEXTURE_BOLT_GOLD = new ResourceLocation(CrossbowverhaulMod.MODID, "textures/entity/projectiles/bolt_gold.png");
	private static final ResourceLocation TEXTURE_BOLT_DIAMOND = new ResourceLocation(CrossbowverhaulMod.MODID, "textures/entity/projectiles/bolt_diamond.png");
	private static final ResourceLocation TEXTURE_BOLT_NETHERITE = new ResourceLocation(CrossbowverhaulMod.MODID, "textures/entity/projectiles/bolt_netherite.png");
	
	private static final ResourceLocation TEXTURE_BOLT_EXPLOSIVE = new ResourceLocation(CrossbowverhaulMod.MODID, "textures/entity/projectiles/bolt_explosive.png");
	
	public RenderProjectileBolt(EntityRendererProvider.Context renderManager) {
		super(renderManager);
	}

	@Override
	public ResourceLocation getTextureLocation(T boltEntity) {
		if(boltEntity instanceof ProjectileBoltExplosive) {
			return TEXTURE_BOLT_EXPLOSIVE;
		}
		switch(boltEntity.getTier()) {
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
