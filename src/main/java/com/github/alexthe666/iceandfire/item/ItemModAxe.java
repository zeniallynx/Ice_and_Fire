package com.github.alexthe666.iceandfire.item;

import java.util.List;

import javax.annotation.Nullable;

import com.github.alexthe666.citadel.server.entity.EntityPropertiesHandler;
import com.github.alexthe666.citadel.server.item.CustomToolMaterial;
import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.EntityDeathWorm;
import com.github.alexthe666.iceandfire.entity.props.FrozenEntityProperties;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemModAxe extends AxeItem {

    private final CustomToolMaterial toolMaterial;

    public ItemModAxe(CustomToolMaterial toolmaterial, String gameName) {
        super(toolmaterial, 5.0F, -3.0F, (new Item.Properties()).group(IceAndFire.TAB_ITEMS));
        this.toolMaterial = toolmaterial;
        this.setRegistryName(IceAndFire.MODID, gameName);
    }


    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (toolMaterial == IafItemRegistry.SILVER_TOOL_MATERIAL) {
            if (target.getCreatureAttribute() == CreatureAttribute.UNDEAD) {
                target.attackEntityFrom(DamageSource.MAGIC, func_234675_d_() + 3.0F);
            }
        }
        if (this.toolMaterial == IafItemRegistry.MYRMEX_CHITIN_TOOL_MATERIAL) {
            if (target.getCreatureAttribute() != CreatureAttribute.ARTHROPOD) {
                target.attackEntityFrom(DamageSource.GENERIC, func_234675_d_() + 5.0F);
            }
            if (target instanceof EntityDeathWorm) {
                target.attackEntityFrom(DamageSource.GENERIC, func_234675_d_() + 5.0F);
            }
        }
        if (toolMaterial == IafItemRegistry.DRAGONSTEEL_FIRE_TOOL_MATERIAL) {
            target.setFire(15);
            target.func_233627_a_( 1F, attacker.getPosX() - target.getPosX(), attacker.getPosZ() - target.getPosZ());
        }
        if (toolMaterial == IafItemRegistry.DRAGONSTEEL_ICE_TOOL_MATERIAL) {
            FrozenEntityProperties frozenProps = EntityPropertiesHandler.INSTANCE.getProperties(target, FrozenEntityProperties.class);
            frozenProps.setFrozenFor(300);
            target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 300, 2));
            target.func_233627_a_( 1F, attacker.getPosX() - target.getPosX(), attacker.getPosZ() - target.getPosZ());
        }
        if (toolMaterial == IafItemRegistry.DRAGONSTEEL_LIGHTNING_TOOL_MATERIAL) {
            boolean flag = true;
            if(attacker instanceof PlayerEntity){
                if(((PlayerEntity)attacker).swingProgress > 0.2){
                    flag = false;
                }
            }
            if(!attacker.world.isRemote && flag){
                LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(target.world);
                lightningboltentity.func_233576_c_(target.getPositionVec());
                if(!target.world.isRemote){
                    target.world.addEntity(lightningboltentity);
                }
            }
            target.func_233627_a_( 1F, attacker.getPosX() - target.getPosX(), attacker.getPosZ() - target.getPosZ());
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (this == IafItemRegistry.SILVER_AXE) {
            tooltip.add(new TranslationTextComponent("silvertools.hurt").func_240699_a_(TextFormatting.GREEN));
        }
        if (toolMaterial == IafItemRegistry.DRAGONSTEEL_FIRE_TOOL_MATERIAL) {
            tooltip.add(new TranslationTextComponent("dragon_sword_fire.hurt2").func_240699_a_(TextFormatting.DARK_RED));
        }
        if (toolMaterial == IafItemRegistry.DRAGONSTEEL_ICE_TOOL_MATERIAL) {
            tooltip.add(new TranslationTextComponent("dragon_sword_ice.hurt2").func_240699_a_(TextFormatting.AQUA));
        }
        if (toolMaterial == IafItemRegistry.DRAGONSTEEL_LIGHTNING_TOOL_MATERIAL) {
            tooltip.add(new TranslationTextComponent("dragon_sword_lightning.hurt2").func_240699_a_(TextFormatting.DARK_PURPLE));
        }
    }

    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }
}
