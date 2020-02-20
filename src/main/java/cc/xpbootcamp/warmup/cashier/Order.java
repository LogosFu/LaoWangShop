package cc.xpbootcamp.warmup.cashier;

import static java.time.DayOfWeek.WEDNESDAY;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private String cName;
  private String addr;
  private LocalDate date;
  private List<LineItem> lineItemList;
  @Default
  private BigDecimal discountPrice = BigDecimal.ZERO;
  @Default
  private static final double DISCOUNT_RATE = 0.02;

  public Order(String cName, String addr, LocalDate date, List<LineItem> lineItemList) {
    this.cName = cName;
    this.addr = addr;
    this.lineItemList = lineItemList;
    this.date = date;
  }

  public BigDecimal getSalesTx() {
    return BigDecimal.valueOf(lineItemList.stream().mapToDouble(LineItem::getSalesTax).sum());
  }

  public BigDecimal getDiscountPrice() {
    if (discountStatus()) {
      discountPrice = this.getTotalPrice().multiply(BigDecimal.valueOf(0.02));
    }
    return discountPrice;
  }

  public BigDecimal getSellingPrice() {
    return getTotalPrice().subtract(getDiscountPrice());
  }

  public BigDecimal getTotalPrice() {
    return BigDecimal.valueOf(lineItemList.stream()
        .mapToDouble(item -> item.totalAmount() + item.getSalesTax())
        .sum());
  }

  public boolean discountStatus() {
    return date.getDayOfWeek() == WEDNESDAY;
  }
}
