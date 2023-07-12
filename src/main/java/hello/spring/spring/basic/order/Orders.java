package hello.spring.spring.basic.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Orders {

    private Long id;
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;
    private int totalPrice;

    public Orders(Long memberId, String itemName, int itemPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return getItemPrice() == orders.getItemPrice() && getDiscountPrice() == orders.getDiscountPrice() && Objects.equals(getMemberId(), orders.getMemberId()) && Objects.equals(getItemName(), orders.getItemName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberId(), getItemName(), getItemPrice(), getDiscountPrice());
    }
}
