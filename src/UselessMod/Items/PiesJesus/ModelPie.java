

package UselessMod.Items.PiesJesus;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPie extends ModelBiped
{
		ModelRenderer pieL;
		ModelRenderer pieR;
	    ModelRenderer Shape1;
	    ModelRenderer Shape2;
	    ModelRenderer Shape3;
	    ModelRenderer Shape4;
	    ModelRenderer Shape5;
	    ModelRenderer Shape6;
	    ModelRenderer Shape7;
	    ModelRenderer Shape8;
	    ModelRenderer Shape9;
	    ModelRenderer nada;
	    float altura=-10;
	  public ModelPie()
	  {
		  super();
		  
	    textureWidth = 64;
	    textureHeight = 32;
	    nada = new ModelRenderer(this, 0, 0);
	    pieL=Left(pieL,altura,0,0,0,-0.1f);
	   	Rigth();
	      this.bipedLeftLeg=pieL;
	      this.bipedRightLeg=pieR;
	      this.bipedBody=nada;
	      this.bipedCloak=nada;
	      this.bipedEars=nada;
	      this.bipedHead=nada;
	      this.bipedHeadwear=nada;
	      this.bipedLeftArm=nada;
	      this.bipedRightArm=nada;

	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
		 super.render(entity, f, f1, f2, f3, f4, f5);
		 setRotationAngles(f, f1, f2, f3, f4, f5,entity);
	  }
	  
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
  }
  public ModelRenderer Left(ModelRenderer mod,float altura,float a,float b,float c,float margen){
	  mod = new ModelRenderer(this, 0, 0);
	    
      Shape1 = new ModelRenderer(this, 26, 0);
      Shape1.addBox(0F+margen, altura, 0F, 5, 3, 3);
      Shape1.setRotationPoint(0F, 21F, -6F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(0F+margen, altura, 0F, 5, 9, 7);
      Shape2.setRotationPoint(0F, 15F, -3F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      
      Shape3 = new ModelRenderer(this, 13, 17);
      Shape3.addBox(0F+margen, altura, 0F, 1, 3, 2);
      Shape3.setRotationPoint(2F, 21F, -8F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      
      Shape4 = new ModelRenderer(this, 34, 9);
      Shape4.addBox(0F+a+margen, altura, 0F, 1, 3, 1);
      Shape4.setRotationPoint(4F, 21F, -7F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      
      Shape5 = new ModelRenderer(this, 26, 9);
      Shape5.addBox(0F+b+margen, altura, 0F, 1, 3, 2);
      Shape5.setRotationPoint(3F, 21F, -8F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      
      Shape6 = new ModelRenderer(this, 0, 17);
      Shape6.addBox(0F+c+margen, altura, 0F, 2, 3, 3);
      Shape6.setRotationPoint(0F, 21F, -9F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);

      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 44, 0);
      Shape7.addBox(margen, altura, 0F, 1, 1, 1);
      Shape7.setRotationPoint(0F, 14F, 0F);
      Shape7.setTextureSize(64, 32);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      
      Shape8 = new ModelRenderer(this, 44, 0);
      Shape8.addBox(margen, altura, 0F, 1, 1, 1);
      Shape8.setRotationPoint(4F, 14F, -2F);
      Shape8.setTextureSize(64, 32);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      
      Shape9 = new ModelRenderer(this, 44, 0);
      Shape9.addBox(margen, altura, 0F, 1, 1, 1);
      Shape9.setRotationPoint(2F, 14F, 3F);
      Shape9.setTextureSize(64, 32);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);


  
     mod.addChild(Shape1);
	  mod.addChild(Shape2);
	  mod.addChild(Shape3);
	  mod.addChild(Shape4);
	  mod.addChild(Shape5);
	  mod.addChild(Shape6);
	  return mod;
  }
  public void Rigth(){
	  

	  float x4=-4,x5=-2,x6=3,margen=-4.9f;
	  pieR=Left(pieR,altura,x4,x5,x6,margen);

  }
}

