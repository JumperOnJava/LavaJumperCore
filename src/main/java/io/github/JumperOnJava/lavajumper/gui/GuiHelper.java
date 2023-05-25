package io.github.JumperOnJava.lavajumper.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.lang3.NotImplementedException;

import static net.minecraft.client.gui.DrawableHelper.fill;

public class GuiHelper {
    public static Vec3d transformCoords(MatrixStack matrixStack,Vec3d vec3d){
        return transformCoords(matrixStack,vec3d.x,vec3d.y,vec3d.z);
    }
    public static Vec3d transformCoords(MatrixStack matrixStack,double x,double y, double z){
        /*var tv = matrixStack.peek().getPositionMatrix().(new Vector4f((float) x, (float) y, (float) z,1));
        return new Vec3d(tv.x,tv.y,tv.z);*/
        throw new NotImplementedException();
    }
    public static Vec3d transformCoords(MatrixStack matrixStack,double x,double y){
        return transformCoords(matrixStack,x,y,0);
    }
    public static void renderSides(MatrixStack matrices, int x, int y, int width, int height)
    {
        y-=1;
        height+=1;
        fill(matrices, x-7, y-7, x + width+7, y + height+7, 0xFF000000);
        fill(matrices, x-6, y-6, x + width+6, y + height+6, 0xFFC6C6C6);
        fill(matrices, x-6, y-6, x + width+4, y + height+4, 0xFFFFFFFF);
        fill(matrices, x-4, y-4, x + width+6, y + height+6, 0xFF555555);
        fill(matrices, x-4, y-4, x + width+4, y + height+4, 0xFFC6C6C6);
        /*RenderSystem.enableBlend();
        fill(matrices,x,y,x+width,y+height,0x3F000000 | (int)(Math.pow(x + width + y + height,5f)%Integer.MAX_VALUE));*/
    }
    public static TestScreen TestScreen(int color){
        return new TestScreen(color);
    }
    public static class TestScreen extends Screen {
        int color;
        public TestScreen(int color) {
            super(Text.empty());
            this.color=color;
        }
        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            super.render(matrices, mouseX, mouseY, delta);
            fill(matrices,0,0,width,height,color);
        }
    }
}
