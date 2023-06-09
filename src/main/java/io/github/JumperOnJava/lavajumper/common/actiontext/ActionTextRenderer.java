package io.github.JumperOnJava.lavajumper.common.actiontext;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;

import java.util.LinkedList;
import java.util.List;

public class ActionTextRenderer {
    private static List<ActionText> text = new LinkedList<>();
    private static List<ActionText> addText = new LinkedList<>();
    static TextRenderer getTextRenderer(){return MinecraftClient.getInstance().textRenderer;};
    static {
        HudRenderCallback.EVENT.register((context,delta)->{
            text.addAll(addText);
            addText.clear();
            List<ActionText> removeText=new LinkedList<>();
            var iterator = text.listIterator(text.size());
            int i=0,j=0;
            while(iterator.hasPrevious())
            {
                var line=iterator.previous();
                if(line.time < 0)
                {
                    removeText.add(text.get(i));
                    continue;
                }
                else
                {
                    line.time-=delta;
                }
                if(ColorHelper.Argb.getAlpha(line.color)!=0){
                    renderActionText(context, line.text, -j, line.color);
                    j++;
                }
                i++;
            }
            text.removeAll(removeText);
        });
    }
    public static void addText(ActionText text)
    {
        ActionTextRenderer.addText.add(text);
    }
    public static void renderActionText(DrawContext context, String text, int offset, int color)
    {
        context.drawCenteredTextWithShadow(
                MinecraftClient.getInstance().textRenderer,
                text,
                MinecraftClient.getInstance().getWindow().getScaledWidth()/2,
                (int) (MinecraftClient.getInstance().getWindow().getScaledHeight()/2*1.65 + offset*8),
                color);
    }
    public static void renderUpperRight(DrawContext context, int offset, String text,int color)
    {

        var textWidth = getTextRenderer().getWidth(text);
        var x = MinecraftClient.getInstance().getWindow().getScaledWidth() - textWidth - 2;
        var y = 2;
        renderText(context,x,y+8*offset,text,color);
    }
    public static void renderText(DrawContext context,int posX,int posY,String text,int color)
    {
        context.fill(posX-1,posY,posX+ getTextRenderer().getWidth(text),posY+8, ColorHelper.Argb.getArgb(64,0,0,0));
        if(getTextRenderer()== null)
        {
            return;
        }
        context.drawText(getTextRenderer(),text,posX,posY,color,true);
    }
    public static void sendChatMessage(Object... messages)
    {
        String fullMessage="";
        for(var message : messages)
            fullMessage+=(message==null)?"null": message +" ";
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal(fullMessage));
    }
}
