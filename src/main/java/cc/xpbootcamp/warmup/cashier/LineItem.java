package cc.xpbootcamp.warmup.cashier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value(staticConstructor = "of")
@AllArgsConstructor
public class LineItem {

  private String desc;
  private double price;
  private int qty;

  double totalAmount() {
    return price * qty;
  }

  String getLineInfo() {
    return desc
        + Separator.COMMA.getValue()
        + price
        + "*"
        + qty
        + Separator.COMMA.getValue()
        + totalAmount()
        + Separator.LINE_BREAK.getValue();
  }

  double getSalesTax() {
    return totalAmount() * .10;
  }
}