package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Builder;
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

  String getBill() {

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
        + formatDate()
        + Separator.LINE_BREAK.getValue();
  }

  private String buildOrderFooter() {
    return isWednesday() ? discountTotalInfo() : normalTotalInfo();
  }

  private boolean isWednesday() {
    return date.getDayOfWeek().getValue() == 3;
  }

  private String discountTotalInfo() {
    return "税额:" + Separator.SPACES.getValue() + getSalesTx()
        + Separator.LINE_BREAK.getValue()
        + "折扣:" + Separator.SPACES.getValue() + getTotalPrice() * 0.02
        + Separator.LINE_BREAK.getValue()
        + "总价:" + Separator.SPACES.getValue() + getTotalPrice() * 0.98;
  }

  private String normalTotalInfo() {
    return "税额:" + Separator.SPACES.getValue() + getSalesTx()
        + Separator.LINE_BREAK.getValue()
        + "总价:" + Separator.SPACES.getValue() + getTotalPrice();
  }

  private String buildLineItemInfo() {

    return lineItemList.stream().map(LineItem::getLineInfo)
        .collect(Collectors.joining());
  }

  private double getSalesTx() {
    return lineItemList.stream().map(LineItem::getSalesTax).reduce(0d, Double::sum);
  }

  private double getTotalPrice() {
    return lineItemList.stream().map(item -> item.totalAmount() + item.getSalesTax())
        .reduce(0d, Double::sum);
  }

  private String formatDate() {
    return date.format(DateTimeFormatter.ofPattern("yyyy年M月dd日")) + "，星期" + getWeekOfDay();
  }

  private String getWeekOfDay() {
    String[][] strArray = {{"MONDAY", "一"}, {"TUESDAY", "二"}, {"WEDNESDAY", "三"}, {"THURSDAY", "四"},
        {"FRIDAY", "五"}, {"SATURDAY", "六"}, {"SUNDAY", "日"}};
    final Optional<String[]> matchDay = Arrays.stream(strArray)
        .filter(string -> String.valueOf(date.getDayOfWeek()).equals(string[0])).findAny();
    return matchDay.map(strings -> strings[1]).orElseThrow(RuntimeException::new);
  }
}
