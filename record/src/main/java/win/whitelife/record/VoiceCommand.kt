package win.whitelife.record

/**
 * @author wuzefeng
 * 2018/5/15
 */
class VoiceCommand {

    companion object {

        const val COMMAND="COMMAND"

        const val COMMAND_FILE="COMMAND_FILE"

        const val COMMAND_START_RECORD=1001

        const val COMMAND_STOP_RECORD=1002

        const val COMMAND_RELEASE_RECORD=1003

        const val COMMAND_START_PLAY=2001

        const val COMMAND_PAUSE_PLAY=2002

        const val COMMAND_STOP_PLAY=2003

        const val COMMAND_RELEASE_PLAY=2004

        const val COMMAND_INTENT_FILEPATH="intent_file_path"


    }
}