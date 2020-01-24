package hollowsoft.order

import androidx.appcompat.app.AppCompatActivity
import cielo.orders.domain.CancellationRequest
import cielo.orders.domain.CheckoutRequest
import cielo.orders.domain.Order
import cielo.sdk.order.OrderManager
import cielo.sdk.order.ServiceBindListener
import cielo.sdk.order.cancellation.CancellationListener
import cielo.sdk.order.payment.PaymentListener
import java.util.*
import kotlin.concurrent.schedule

/**
 * @author Igor Morais
 */
open class OrderActivity : AppCompatActivity(), ServiceBindListener {
    companion object {
        const val TAG = "OrderManager"

        const val DELAY = 1500L
    }

    private var bound = false

    private val manager by lazy { OrderManager(Manifest(this).credential(), this) }

    override fun onStart() {
        super.onStart()

        manager.bind(this, this)
    }

    override fun onStop() {
        super.onStop()

        manager.unbind()
    }

    fun create(name: String) = manager.createDraftOrder(name)

    fun place(order: Order?) : Boolean {
        order?.let { return manager.placeOrder(it) ?: false }
        return false
    }

    fun pay(request: CheckoutRequest, listener: PaymentListener) = manager.checkoutOrder(request, listener)

    fun cancel(request: CancellationRequest, listener: CancellationListener) = manager.cancelOrder(request, listener)

    override fun onServiceBound() {
        Logger.info(TAG, "onServiceBound")

        bound = true
    }

    override fun onServiceUnbound() {
        Logger.info(TAG, "onServiceUnbound")

        bound = false
    }

    override fun onServiceBoundError(e: Throwable) {
        Logger.error(TAG, "onServiceBoundError ${e.message}")

        Timer().schedule(DELAY) {
            manager.bind(this@OrderActivity, this@OrderActivity)
        }
    }
}
