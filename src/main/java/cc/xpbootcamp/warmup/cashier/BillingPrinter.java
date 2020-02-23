package cc.xpbootcamp.warmup.cashier;

import lombok.Builder;

@Builder
public class BillingPrinter {

  private String header;
  private String date;
  private String content;
  private String summary;
  private static final String FORMAT = "%s%n"
      + "%n"
      + "%s%n"
      + "%s"
      + "%n"
      + "--------------------------%n"
      + "%s";

  public BillingPrinter(String header, String date, String content, String summary) {
    this.header = header;
    this.date = date;
    this.content = content;
    this.summary = summary;
  }

  public String printBilling() {
    return String
        .format(FORMAT, header, date, content,
            summary);
  }
}
