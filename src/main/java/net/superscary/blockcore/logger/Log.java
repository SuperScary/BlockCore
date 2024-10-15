package net.superscary.blockcore.logger;

import net.superscary.blockcore.api.helper.Side;
import net.superscary.blockcore.core.BlockCore;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
public class Log {

    private static final String LOGGER_PREFIX = BlockCore.NAME + ":";
    private static final String SERVER_SUFFIX = "S";
    private static final String CLIENT_SUFFIX = "C";

    private static final Logger SERVER = LogManager.getFormatterLogger(LOGGER_PREFIX + SERVER_SUFFIX);
    private static final Logger CLIENT = LogManager.getFormatterLogger(LOGGER_PREFIX + CLIENT_SUFFIX);

    private static final String BLOCK_UPDATE = "Block Update of %s @ ( %s ). State %s -> %s";

    private static final String DEFAULT_EXCEPTION_MESSAGE = "Exception: ";

    private static boolean craftingLogEnabled;
    private static boolean debugLogEnabled = true;

    private Log () {

    }

    private static Logger getLogger () {
        return Side.isServer() ? SERVER : CLIENT;
    }

    public static boolean isLogEnabled () {
        return true;
    }

    public static void log (Level level, String message, Object... params) {
        if (Log.isLogEnabled()) {
            final String formattedMessage = String.format(message, params);
            final Logger logger = getLogger();
            logger.log(level, formattedMessage);
        }
    }

    private static void log (Level level, Throwable exception, String message, Object... params) {
        if (Log.isLogEnabled()) {
            final String formattedMessage = String.format(message, params);
            final Logger logger = getLogger();
            logger.log(level, formattedMessage, exception);
        }
    }

    public static void info (String format, Object... params) {
        log(Level.INFO, format, params);
    }

    public static void info (Throwable exception) {
        log(Level.INFO, exception, DEFAULT_EXCEPTION_MESSAGE);
    }

    public static void info (Throwable exception, String message) {
        log(Level.INFO, exception, message);
    }

    public static void warn (String format, Object... params) {
        log(Level.WARN, format, params);
    }

    public static void warn (Throwable exception) {
        log(Level.WARN, exception, DEFAULT_EXCEPTION_MESSAGE);
    }

    public static void warn (Throwable exception, String message) {
        log(Level.WARN, exception, message);
    }

    public static void error (String format, Object... params) {
        log(Level.ERROR, format, params);
    }

    public static void error (Throwable exception) {
        log(Level.ERROR, exception, DEFAULT_EXCEPTION_MESSAGE);
    }

    public static void error (Throwable exception, String message) {
        log(Level.ERROR, exception, message);
    }

    public static void debug (String format, Object... data) {
        if (Log.isDebugLogEnabled()) {
            log(Level.DEBUG, format, data);
        }
    }

    public static boolean isDebugLogEnabled () {
        return debugLogEnabled;
    }

    public static void setDebugLogEnabled (boolean newValue) {
        debugLogEnabled = newValue;
    }

    public static boolean isCraftingLogEnabled () {
        return craftingLogEnabled;
    }

    public static void setCraftingLogEnabled (boolean newValue) {
        craftingLogEnabled = newValue;
    }

    public static void crafting (String message, Object... params) {
        if (Log.isCraftingLogEnabled()) {
            log(Level.INFO, message, params);
        }
    }

    /*public static void blockUpdate (BlockPos pos, BlockState currentState, BlockState newState, KineticBaseBlockEntity blockEntity) {
        info(BLOCK_UPDATE, blockEntity.getClass().getName(), pos, currentState, newState);
    }*/

    public static boolean isCraftingDebugLogEnabled () {
        return isCraftingLogEnabled() && isDebugLogEnabled();
    }

    public static void craftingDebug (String message, Object... params) {
        if (Log.isCraftingDebugLogEnabled()) {
            log(Level.DEBUG, message, params);
        }
    }

}
