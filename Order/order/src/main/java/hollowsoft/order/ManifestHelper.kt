package hollowsoft.order

import android.content.Context
import android.content.pm.PackageManager
import cielo.orders.domain.Credentials

/**
 * @author Igor Morais
 */
class ManifestHelper(context: Context) {
    companion object {
        const val CLIENT_ID = "CLIENT_ID"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }

    private val manager by lazy { context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA) }

    fun credential() : Credentials {
        val id = manager.metaData.getString(CLIENT_ID) ?: throw IllegalArgumentException()
        val token = manager.metaData.getString(ACCESS_TOKEN) ?: throw IllegalArgumentException()

        return Credentials(id, token)
    }
}
