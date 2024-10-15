package net.superscary.blockcore.api.helper;

import net.neoforged.fml.util.thread.SidedThreadGroups;

public class Side {

    public static boolean isClient () {
        return Thread.currentThread().getThreadGroup() != SidedThreadGroups.SERVER;
    }

    public static boolean isServer () {
        return Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER;
    }

}
