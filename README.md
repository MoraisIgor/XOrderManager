# XOrderManager

```
dependencies {
    implementation 'com.github.moraisigor:order:0.11.0'
}
```


























## Example

```kotlin
class YourActivity : OrderActivity() {
    companion object {
        const val ID = "999999999999999"
        const val NAME = "Order"
        const val PRICE = 1L
        const val AMOUNT = 1
        const val UNITY = "Unity"
    }
    
    private val order by lazy { create("My Order") }
    
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        
        setContentView(R.layout.layout)
        
        buttonAdd.setOnClickListener {
            order?.addItem(ID, NAME, PRICE, AMOUNT, UNITY)
        }
        
        buttonFinish.setOnClickListener {
            place(order)
        }
        
        buttonPay.setOnClickListener {
            order?.let {
                val request = CheckoutRequest.Builder()
                    .orderId(it.id)
                    .build()
                    
                pay(request, object : PaymentListener {
                    override fun onStart() {
                    
                    }
                    
                    override fun onCancel() {
                    
                    }
                    
                    override fun onPayment(order: Order) {
                    
                    }
                    
                    override fun onError(error: PaymentError) {
                    
                    }
                })
            }
        }
    }
}
```
