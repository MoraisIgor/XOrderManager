# XOrderManager

```
dependencies {
    implementation 'com.github.moraisigor:order:0.10.1'
}
```

## Example

Your need extends from `OrderActivity`.

```kotlin
class YourActivity : OrderActivity() {

}
```

Create Order.

```kotlin
val order = create("My Order")
```

Add items to Order.

```kotlin
order.addItem(sku, name, unitPrice, quantity, unitOfMeasure)
```

Place order.

```kotlin
place(order)
```

Pay order.

```kotlin
```
