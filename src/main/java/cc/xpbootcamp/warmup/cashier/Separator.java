package cc.xpbootcamp.warmup.cashier;


public enum Separator {
  LINE_BREAK("\r\n"),
  SPACES("\t"),
  COMMA(","),
  DOTTED_LINE("--------------------------");

  private String value;

  public String getValue() {
    return value;
  }

  Separator(String value) {
    this.value = value;
  }
}
