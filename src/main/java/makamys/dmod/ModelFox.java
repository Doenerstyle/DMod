package makamys.dmod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.MathHelper;

public class ModelFox extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer rightEar;
    public ModelRenderer leftEar;
    public ModelRenderer nose;
    public ModelRenderer torso;
    public ModelRenderer rightBackLeg;
    public ModelRenderer leftBackLeg;
    public ModelRenderer rightFrontLeg;
    public ModelRenderer leftFrontLeg;
    public ModelRenderer tail;
    private float legPitchModifier;

    public ModelFox(){
    	this.textureWidth = 48;
        this.textureHeight = 32;
        this.head = new ModelRenderer(this, 1, 5);
        this.head.addBox(-3.0F, -2.0F, -5.0F, 8, 6, 6, 0.0F);
        this.head.setRotationPoint(-1.0F, 16.5F, -3.0F);
        this.rightEar = new ModelRenderer(this, 8, 1);
        this.rightEar.addBox(-3.0F, -4.0F, -4.0F, 2, 2, 1, 0.0F);
        this.leftEar = new ModelRenderer(this, 15, 1);
        this.leftEar.addBox(3.0F, -4.0F, -4.0F, 2, 2, 1, 0.0F);
        this.nose = new ModelRenderer(this, 6, 18);
        this.nose.addBox(-1.0F, 2.01F, -8.0F, 4, 2, 3, 0.0F);
        this.head.addChild(this.rightEar);
        this.head.addChild(this.leftEar);
        this.head.addChild(this.nose);
        this.torso = new ModelRenderer(this, 24, 15);
        this.torso.addBox(-3.0F, 3.999F, -3.5F, 6, 11, 6, 0.0F);
        this.torso.setRotationPoint(0.0F, 16.0F, -6.0F);
        float f = 0.001F;
        this.rightBackLeg = new ModelRenderer(this, 13, 24);
        this.rightBackLeg.addBox(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
        this.rightBackLeg.setRotationPoint(-5.0F, 17.5F, 7.0F);
        this.leftBackLeg = new ModelRenderer(this, 4, 24);
        this.leftBackLeg.addBox(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
        this.leftBackLeg.setRotationPoint(-1.0F, 17.5F, 7.0F);
        this.rightFrontLeg = new ModelRenderer(this, 13, 24);
        this.rightFrontLeg.addBox(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
        this.rightFrontLeg.setRotationPoint(-5.0F, 17.5F, 0.0F);
        this.leftFrontLeg = new ModelRenderer(this, 4, 24);
        this.leftFrontLeg.addBox(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
        this.leftFrontLeg.setRotationPoint(-1.0F, 17.5F, 0.0F);
        this.tail = new ModelRenderer(this, 30, 0);
        this.tail.addBox(2.0F, 0.0F, -1.0F, 4, 9, 5, 0.0F);
        this.tail.setRotationPoint(-4.0F, 15.0F, -1.0F);
        this.torso.addChild(this.tail);
    }
    
    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase p_78086_1_, float f, float g, float h)
    {
        FoxEntity foxEntity = (FoxEntity)p_78086_1_;

        /*if (entitywolf.isAngry())
        {
            this.wolfTail.rotateAngleY = 0.0F;
        }
        else
        {
            this.wolfTail.rotateAngleY = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
        }

        if (entitywolf.isSitting())
        {
            this.wolfMane.setRotationPoint(-1.0F, 16.0F, -3.0F);
            this.wolfMane.rotateAngleX = ((float)Math.PI * 2F / 5F);
            this.wolfMane.rotateAngleY = 0.0F;
            this.wolfBody.setRotationPoint(0.0F, 18.0F, 0.0F);
            this.wolfBody.rotateAngleX = ((float)Math.PI / 4F);
            this.wolfTail.setRotationPoint(-1.0F, 21.0F, 6.0F);
            this.wolfLeg1.setRotationPoint(-2.5F, 22.0F, 2.0F);
            this.wolfLeg1.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.wolfLeg2.setRotationPoint(0.5F, 22.0F, 2.0F);
            this.wolfLeg2.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.wolfLeg3.rotateAngleX = 5.811947F;
            this.wolfLeg3.setRotationPoint(-2.49F, 17.0F, -4.0F);
            this.wolfLeg4.rotateAngleX = 5.811947F;
            this.wolfLeg4.setRotationPoint(0.51F, 17.0F, -4.0F);
        }
        else
        {
            this.wolfBody.setRotationPoint(0.0F, 14.0F, 2.0F);
            this.wolfBody.rotateAngleX = ((float)Math.PI / 2F);
            this.wolfMane.setRotationPoint(-1.0F, 14.0F, -3.0F);
            this.wolfMane.rotateAngleX = this.wolfBody.rotateAngleX;
            this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
            this.wolfLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
            this.wolfLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
            this.wolfLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
            this.wolfLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
            this.wolfLeg1.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
            this.wolfLeg2.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
            this.wolfLeg3.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
            this.wolfLeg4.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
        }

        this.wolfHeadMain.rotateAngleZ = entitywolf.getInterestedAngle(p_78086_4_) + entitywolf.getShakeAngle(p_78086_4_, 0.0F);
        this.wolfMane.rotateAngleZ = entitywolf.getShakeAngle(p_78086_4_, -0.08F);
        this.wolfBody.rotateAngleZ = entitywolf.getShakeAngle(p_78086_4_, -0.16F);
        this.wolfTail.rotateAngleZ = entitywolf.getShakeAngle(p_78086_4_, -0.2F);*/
        
        this.torso.rotateAngleX = 1.5707964F;
        this.tail.rotateAngleX = -0.05235988F;
        this.rightBackLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.leftBackLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.rightFrontLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.leftFrontLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.head.setRotationPoint(-1.0F, 16.5F, -3.0F);
        this.head.rotateAngleY = 0.0F;
        this.head.rotateAngleZ = foxEntity.getHeadRoll(h);
        this.rightBackLeg.showModel = true;
        this.leftBackLeg.showModel = true;
        this.rightFrontLeg.showModel = true;
        this.leftFrontLeg.showModel = true;
        this.torso.setRotationPoint(0.0F, 16.0F, -6.0F);
        this.torso.rotateAngleZ = 0.0F;
        this.rightBackLeg.setRotationPoint(-5.0F, 17.5F, 7.0F);
        this.leftBackLeg.setRotationPoint(-1.0F, 17.5F, 7.0F);
        if (foxEntity.isInSneakingPose()) {
           this.torso.rotateAngleX = 1.6755161F;
           float i = foxEntity.getBodyRotationHeightOffset(h);
           this.torso.setRotationPoint(0.0F, 16.0F + foxEntity.getBodyRotationHeightOffset(h), -6.0F);
           this.head.setRotationPoint(-1.0F, 16.5F + i, -3.0F);
           this.head.rotateAngleY = 0.0F;
        } else if (foxEntity.isPlayerSleeping()) {
           this.torso.rotateAngleZ = -1.5707964F;
           this.torso.setRotationPoint(0.0F, 21.0F, -6.0F);
           this.tail.rotateAngleX = -2.6179938F;
           if (this.isChild) {
              this.tail.rotateAngleX = -2.1816616F;
              this.torso.setRotationPoint(0.0F, 21.0F, -2.0F);
           }

           this.head.setRotationPoint(1.0F, 19.49F, -3.0F);
           this.head.rotateAngleX = 0.0F;
           this.head.rotateAngleY = -2.0943952F;
           this.head.rotateAngleZ = 0.0F;
           this.rightBackLeg.showModel = false;
           this.leftBackLeg.showModel = false;
           this.rightFrontLeg.showModel = false;
           this.leftFrontLeg.showModel = false;
        } else if (foxEntity.isSitting()) {
           this.torso.rotateAngleX = 0.5235988F;
           this.torso.setRotationPoint(0.0F, 9.0F, -3.0F);
           this.tail.rotateAngleX = 0.7853982F;
           this.tail.setRotationPoint(-4.0F, 15.0F, -2.0F);
           this.head.setRotationPoint(-1.0F, 10.0F, -0.25F);
           this.head.rotateAngleX = 0.0F;
           this.head.rotateAngleY = 0.0F;
           if (this.isChild) {
              this.head.setRotationPoint(-1.0F, 13.0F, -3.75F);
           }

           this.rightBackLeg.rotateAngleX = -1.3089969F;
           this.rightBackLeg.setRotationPoint(-5.0F, 21.5F, 6.75F);
           this.leftBackLeg.rotateAngleX = -1.3089969F;
           this.leftBackLeg.setRotationPoint(-1.0F, 21.5F, 6.75F);
           this.rightFrontLeg.rotateAngleX = -0.2617994F;
           this.leftFrontLeg.rotateAngleX = -0.2617994F;
        }
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

        /*if (this.isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 5.0F * p_78088_7_, 2.0F * p_78088_7_);
            this.head.render(p_78088_7_);
            this.bill.render(p_78088_7_);
            this.chin.render(p_78088_7_);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
            this.body.render(p_78088_7_);
            this.rightLeg.render(p_78088_7_);
            this.leftLeg.render(p_78088_7_);
            this.rightWing.render(p_78088_7_);
            this.leftWing.render(p_78088_7_);
            GL11.glPopMatrix();
        }
        else
        {*/
            this.head.render(p_78088_7_);
            this.torso.render(p_78088_7_);
            this.rightBackLeg.render(p_78088_7_);
            this.leftBackLeg.render(p_78088_7_);
            this.rightFrontLeg.render(p_78088_7_);
            this.leftFrontLeg.render(p_78088_7_);
        //}
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float h, float i, float j, float p_78087_6_, Entity p_78087_7_)
    {
    	/*
        this.head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.bill.rotateAngleX = this.head.rotateAngleX;
        this.bill.rotateAngleY = this.head.rotateAngleY;
        this.chin.rotateAngleX = this.head.rotateAngleX;
        this.chin.rotateAngleY = this.head.rotateAngleY;
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.rightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.leftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.rightWing.rotateAngleZ = p_78087_3_;
        this.leftWing.rotateAngleZ = -p_78087_3_;*/
    	
    	FoxEntity foxEntity = (FoxEntity)p_78087_7_;
    	
        if (!foxEntity.isPlayerSleeping() && !foxEntity.isWalking() && !foxEntity.isInSneakingPose()) {
            this.head.rotateAngleX = j * 0.017453292F;
            this.head.rotateAngleY = i * 0.017453292F;
         }

         if (foxEntity.isPlayerSleeping()) {
            this.head.rotateAngleX = 0.0F;
            this.head.rotateAngleY = -2.0943952F;
            this.head.rotateAngleZ = MathHelper.cos(h * 0.027F) / 22.0F;
         }

         float l;
         if (foxEntity.isInSneakingPose()) {
            l = MathHelper.cos(h) * 0.01F;
            this.torso.rotateAngleY = l;
            this.rightBackLeg.rotateAngleZ = l;
            this.leftBackLeg.rotateAngleZ = l;
            this.rightFrontLeg.rotateAngleZ = l / 2.0F;
            this.leftFrontLeg.rotateAngleZ = l / 2.0F;
         }

         if (foxEntity.isWalking()) {
            l = 0.1F;
            this.legPitchModifier += 0.67F;
            this.rightBackLeg.rotateAngleX = MathHelper.cos(this.legPitchModifier * 0.4662F) * 0.1F;
            this.leftBackLeg.rotateAngleX = MathHelper.cos(this.legPitchModifier * 0.4662F + 3.1415927F) * 0.1F;
            this.rightFrontLeg.rotateAngleX = MathHelper.cos(this.legPitchModifier * 0.4662F + 3.1415927F) * 0.1F;
            this.leftFrontLeg.rotateAngleX = MathHelper.cos(this.legPitchModifier * 0.4662F) * 0.1F;
         }
    }
}