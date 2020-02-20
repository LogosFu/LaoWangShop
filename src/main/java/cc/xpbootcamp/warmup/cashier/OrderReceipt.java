package cc.xpbootcamp.warmup.cashier;

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

  public OrderReceipt(Order order) {
    this.order = order;
  }

  public String printReceipt() {
    return buildOrderHeader()
        + Separator.LINE_BREAK.getValue()
        + buildLineItemInfo()
        + Separator.DOTTED_LINE.getValue()
        + Separator.LINE_BREAK.getValue()
        + buildOrderFooter();
  }

  private String buildOrderHeader() {
    return "====老王超市，值得信赖===="
        + Separator.LINE_BREAK.getValue()
        + Separator.LINE_BREAK.getValue()
        + order.getDate().format(DateTimeFormatter.ofPattern("yyyy年M月dd日, EEEE", Locale.CHINESE))
        + Separator.LINE_BREAK.getValue();
  }

  private String buildLineItemInfo() {
    return order.getLineItemList().stream()
        .map(this::getLineInfo)
        .collect(Collectors.joining());
  }
  private String buildOrderFooter() {
    return "税额:" + Separator.SPACES.getValue() + order.getSalesTx()
        + Separator.LINE_BREAK.getValue()
        + (order.discountStatus() ? ("折扣:" + Separator.SPACES.getValue() + order.getDiscountPrice()
        + Separator.LINE_BREAK.getValue())
        : "")
        + "总价:" + Separator.SPACES.getValue() + order.getSellingPrice();
  }

  private String getLineInfo(LineItem lineItem) {
    return lineItem.getDesc()
        + Separator.COMMA.getValue()
        + lineItem.getPrice()
        + "*"
        + lineItem.getQuantity()
        + Separator.COMMA.getValue()
        + lineItem.totalAmount()
        + Separator.LINE_BREAK.getValue();
  }
}