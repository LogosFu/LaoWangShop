package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
public class Order {

  private String cName;
  private String addr;
  private LocalDate date;
  private List<LineItem> lineItemList;


  public Order(String cName, String addr, LocalDate date, List<LineItem> lineItemList) {
    this.cName = cName;
    this.addr = addr;
    this.lineItemList = lineItemList;
    this.date = date;
  }

  public double getSalesTx() {
    return lineItemList.stream().map(LineItem::getSalesTax).reduce(0d, Double::sum);
  }

  public double getTotalPrice() {
    return lineItemList.stream().map(item -> item.totalAmount() + item.getSalesTax())
        .reduce(0d, Double::sum);
  }

}
