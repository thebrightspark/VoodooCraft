package mdc.voodoocraft.handlers;

import mdc.voodoocraft.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    private static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID.toLowerCase());
    private static int packedID = 0;

    /**
     * register a packet and it's handler using the simple network
     * implementation
     * 
     * @param handler the message handler
     * @param message the message
     * @param side the receiving {@link Side}
     */
    public static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> handler, Class<REQ> message, Side side) {
        INSTANCE.registerMessage(handler, message, packedID++, side);
    }

}
