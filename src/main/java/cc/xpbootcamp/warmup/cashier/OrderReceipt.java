package cc.xpbootcamp.warmup.cashier;

import static java.lang.String.*;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part of order. It computes
 * the total order amount (amount of individual lineItems + total sales tax) and prints it.
 */
public class OrderReceipt {

  private Order order;
  private static final String HEADER = "====老王超市，值得信赖====";

  public OrderReceipt(Order order) {
    this.order = order;
  }

  public String printReceipt() {

    return BillingPrinter.builder()
        .header(HEADER)
        .date(formateDate())
        .content(formatContent())
        .summary(formatSummary())
        .build().printBilling();
  }

  private String formateDate() {
    return order.getDate().format(DateTimeFormatter.ofPattern("yyyy年M月dd日, EEEE", Locale.CHINESE));
  }

  private String formatContent() {
    return order.getLineItemList().stream()
        .map(this::getLineInfo)
        .collect(Collectors.joining());
  }

  private String formatSummary() {
    if (order.discountStatus()) {
      return format("税额: %s%n折扣: %s%n总价: %s", order.countSalesTx(), order.countDiscountPrice(),
          order.countSellingPrice());
    } else {
      return format("税额: %s%n总价: %s", order.countSalesTx(), order.countSellingPrice());
    }
  }

  private String getLineInfo(LineItem lineItem) {
    return String
        .format("%s,%s*%s,%s%n", lineItem.getDesc(), lineItem.getPrice(), lineItem.getQuantity(),
            lineItem.totalAmount());

  }
}