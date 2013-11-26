package UselessMod.Items.MTele;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMisil extends ModelBase {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape9;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer aleta;
	ModelRenderer aleta2;
	ModelRenderer aleta3;
	ModelRenderer aleta4;

	public ModelMisil() {
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 12);
		Shape1.addBox(0F, 0F, 0F, 7, 7, 13);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 41, 12);
		Shape2.addBox(0F, 0F, 0F, 5, 5, 3);
		Shape2.setRotationPoint(1F, 1F, -3F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 53, 6);
		Shape3.addBox(0F, 0F, 0F, 3, 3, 2);
		Shape3.setRotationPoint(2F, 2F, -5F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 48, 29);
		Shape4.addBox(0F, 0F, 0F, 1, 1, 1);
		Shape4.setRotationPoint(3F, 3F, -6F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 43, 22);
		Shape9.addBox(0F, 0F, 0F, 5, 5, 1);
		Shape9.setRotationPoint(1F, 1F, 13F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 47, 9);
		Shape5.addBox(0F, 0F, 0F, 1, 1, 1);
		Shape5.setRotationPoint(3F, -1F, 5F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;

		aleta = new ModelRenderer(this, 42, 8);
		setRotation(aleta, 0F, 0F, 0F);

		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 42, 8);
		Shape6.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape6.setRotationPoint(3F, -2F, 6F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		aleta.addChild(Shape6);
		Shape7 = new ModelRenderer(this, 35, 6);
		Shape7.addBox(0F, 0F, 0F, 1, 3, 2);
		Shape7.setRotationPoint(3F, -3F, 7F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		aleta.addChild(Shape7);
		Shape8 = new ModelRenderer(this, 18, 0);
		Shape8.addBox(0F, 0F, 0F, 1, 4, 7);
		Shape8.setRotationPoint(3F, -4F, 9F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		aleta.addChild(Shape8);

		aleta2 = new ModelRenderer(this, 42, 8);
		aleta2.addChild(aleta);
		aleta2.setRotationPoint(0F, 0F, 0F);
		setRotation(aleta2, 0F, 0F, -(float) Math.PI / 2);
		aleta2.offsetX = 0;
		aleta2.offsetY = 7;
		aleta2.offsetZ = 0;

		aleta3 = new ModelRenderer(this, 42, 8);
		aleta3.addChild(aleta);
		aleta3.setRotationPoint(0F, 0F, 0F);
		setRotation(aleta3, 0F, 0F, (float) Math.PI);
		aleta3.offsetX = 7;
		aleta3.offsetY = 7;
		aleta3.offsetZ = 0;

		aleta4 = new ModelRenderer(this, 42, 8);
		aleta4.addChild(aleta);
		aleta4.setRotationPoint(0F, 0F, 0F);
		setRotation(aleta4, 0F, 0F, (float) Math.PI / 2);
		aleta4.offsetX = 7;
		aleta4.offsetY = 0;
		aleta4.offsetZ = 0;

		Shape10 = new ModelRenderer(this, 0, 0);
		Shape10.addBox(0F, 0F, 0F, 3, 3, 1);
		Shape10.setRotationPoint(2F, 2F, 14F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 10, 0);
		Shape11.addBox(0F, 0F, 0F, 1, 1, 1);
		Shape11.setRotationPoint(3F, 3F, 15F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape9.render(f5);
		aleta.render(f5);
		aleta2.render(f5);
		aleta3.render(f5);
		aleta4.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
	}

	private void setRotationAngles(Entity entity, float f, float f1, float f2,
			float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
