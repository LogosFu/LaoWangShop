package cc.xpbootcamp.warmup.cashier

import spock.lang.Specification
import java.time.LocalDate

class OrderTest extends Specification {

    def "should print 7 line when get bill given no line order"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17")).lineItemList(new ArrayList<LineItem>()).build()
        when:
        def bill = order.getBill()
        def resultLine = bill.split(Separator.LINE_BREAK.getValue())
        then:
        resultLine.size() == 7
    }

    def "should print2020年2月17日，星期一 when get bill receipt given date 2020-02-17"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17")).lineItemList(new ArrayList<LineItem>()).build()
        when:
        def bill = order.getBill()
        def resultLine = bill.split(Separator.LINE_BREAK.getValue())
        then:
        resultLine[2] == "2020年2月17日，星期一"
    }

    def "should print====老王超市，值得信赖==== when get bill receipt given order"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17")).lineItemList(new ArrayList<LineItem>()).build()
        when:
        def bill = order.getBill()
        def resultLine = bill.split(Separator.LINE_BREAK.getValue())
        then:
        resultLine[0] == "====老王超市，值得信赖===="
    }

    def "should print税额: 0 when get bill receipt given order"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17")).lineItemList(new ArrayList<LineItem>()).build()
        when:
        def bill = order.getBill()
        def resultLine = bill.split(Separator.LINE_BREAK.getValue())
        then:
        resultLine[5] == "税额:\t0.0"
    }

    def "should print总价: 0 when get bill receipt given order"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17")).lineItemList(new ArrayList<LineItem>()).build()
        when:
        def bill = order.getBill()
        def resultLine = bill.split(Separator.LINE_BREAK.getValue())
        then:
        resultLine[6] == "总价:\t0.0"
    }

    def "should_print_line_item_and_sales_tax_information"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17"))
                .lineItemList([new LineItem("milk", 10.0, 2),
                               new LineItem("biscuits", 5.0, 5),
                               new LineItem("chocolate", 20.0, 1)]).build()
        when:
        def bill = order.getBill()
        def resultLine = bill.split(Separator.LINE_BREAK.getValue())
        then:
        resultLine.size() == 10
        resultLine[7] == Separator.DOTTED_LINE.getValue()
        resultLine[8] == "税额:\t6.5"
        resultLine[9] == "总价:\t71.5"
    }

    def "should_print_line_item_info when getBill given 3 line item"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17"))
                .lineItemList([new LineItem("milk", 10.0, 2),
                               new LineItem("biscuits", 5.0, 5),
                               new LineItem("chocolate", 20.0, 1)]).build()
        when:
        def bill = order.getBill()
        def resultLine = bill.split(Separator.LINE_BREAK.getValue())
        then:
        resultLine.size() == 10
        resultLine[4] == "milk,10.0*2,20.0"
        resultLine[5] == "biscuits,5.0*5,25.0"
        resultLine[6] == "chocolate,20.0*1,20.0"
    }

    def "should discount with98% when getBill given order in 星期三"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-19"))
                .lineItemList([new LineItem("milk", 10.0, 2),
                               new LineItem("biscuits", 5.0, 5),
                               new LineItem("chocolate", 20.0, 1)]).build()
        when:
        def bill = order.getBill()
        def resultLine = bill.split(Separator.LINE_BREAK.getValue())
        then:
        resultLine.size() == 11
        resultLine[4] == "milk,10.0*2,20.0"
        resultLine[5] == "biscuits,5.0*5,25.0"
        resultLine[6] == "chocolate,20.0*1,20.0"

        resultLine[7] == Separator.DOTTED_LINE.getValue()
        resultLine[8] == "税额:\t6.5"
        resultLine[9] == "折扣:\t1.43"
        resultLine[10] == "总价:\t70.07"
    }


//    @Test
//    void should_print_customer_information_on_order() {
//        Order order = new Order("Mr X", "Chicago, 60601", LocalDate.now(), new ArrayList<LineItem>());
//        OrderReceipt receipt = new OrderReceipt(order);
//        String output = receipt.printReceipt();
//        final String[] resultLine = output.split(Separator.LINE_BREAK.getValue());
//        assertThat(resultLine.length).isEqualTo(7);
//        assertThat(resultLine[0]).isEqualTo("====老王超市，值得信赖====");
//    }
//
//    @Test
//    void should_print_2020_2_17__monday_when_print_receipt_given_that_date() {
//        Order order = new Order("Mr X", "Chicago, 60601", LocalDate.parse("2020-02-17"), new ArrayList<LineItem>());
//        OrderReceipt receipt = new OrderReceipt(order);
//        String output = receipt.printReceipt();
//        assertThat(output).contains("====老王超市，值得信赖====");
//        assertThat(output).contains("2020年2月17日，星期一");
//    }
//
//    @Test
//    public void should_print_line_item_and_sales_tax_information() {
//        List<LineItem> lineItems = new ArrayList<LineItem>() {{
//            add(new LineItem("milk", 10.0, 2));
//            add(new LineItem("biscuits", 5.0, 5));
//            add(new LineItem("chocolate", 20.0, 1));
//        }};
//        OrderReceipt receipt = new OrderReceipt(new Order(null, null, LocalDate.now(), lineItems));
//
//        String output = receipt.printReceipt();
//        assertThat(output).contains("milk\t10.0\t2\t20.0");
//        assertThat(output).contains("biscuits\t5.0\t5\t25.0");
//        assertThat(output).contains("chocolate\t20.0\t1\t20.0");
////        assertThat(output).contains("Sales Tax\t6.5");
////        assertThat(output).contains("Total Amount\t71.5");
//    }
}
