package hollowsoft.order

import android.util.Log

/**
 * @author Igor Morais
 */
class Logger {
    companion object {
        fun info(tag: String, message: String) = Log.i(tag, message)

        fun warn(tag: String, message: String) = Log.w(tag, message)

        fun error(tag: String, message: String) = Log.e(tag, message)
    }
}
