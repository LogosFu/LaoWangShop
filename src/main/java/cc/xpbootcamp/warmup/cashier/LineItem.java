package cc.xpbootcamp.warmup.cashier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Builder
@Value(staticConstructor = "of")
@AllArgsConstructor
@Getter
public class LineItem {

  private String desc;
  private double price;
  private int quantity;

  double totalAmount() {
    return price * quantity;
  }



  double getSalesTax() {
    return totalAmount() * .10;
  }
}