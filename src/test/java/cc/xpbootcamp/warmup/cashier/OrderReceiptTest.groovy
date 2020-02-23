package cc.xpbootcamp.warmup.cashier

import spock.lang.Specification
import java.time.LocalDate

class OrderReceiptTest extends Specification {

    def "should print 7 line when get bill given no line order"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17")).lineItemList(new ArrayList<LineItem>()).build()
        def orderReceipt = new OrderReceipt(order);
        when:
        def bill = orderReceipt.printReceipt()
        def resultLine = bill.split("\n")
        then:
        resultLine.size() == 7
    }

    def "should print2020年2月17日，星期一 when get bill receipt given date 2020-02-17"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17"))
                .lineItemList(new ArrayList<LineItem>()).build()
        def orderReceipt = new OrderReceipt(order);
        when:
        def bill = orderReceipt.printReceipt()
        def resultLine = bill.split("\n")
        then:
        resultLine[2] == "2020年2月17日, 星期一"
    }

    def "should print====老王超市，值得信赖==== when get bill receipt given order"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17")).lineItemList(new ArrayList<LineItem>()).build()
        def orderReceipt = new OrderReceipt(order);
        when:
        def bill = orderReceipt.printReceipt()
        def resultLine = bill.split("\n")
        then:
        resultLine[0] == "====老王超市，值得信赖===="
    }

    def "should print税额: 0 when get bill receipt given order"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17")).lineItemList(new ArrayList<LineItem>()).build()
        def orderReceipt = new OrderReceipt(order);
        when:
        def bill = orderReceipt.printReceipt()
        def resultLine = bill.split("\n")
        then:
        resultLine[5] == "税额: 0.0"
    }

    def "should print总价: 0 when get bill receipt given order"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17")).lineItemList(new ArrayList<LineItem>()).build()
        def orderReceipt = new OrderReceipt(order);
        when:
        def bill = orderReceipt.printReceipt()
        def resultLine = bill.split("\n")
        then:
        resultLine[6] == "总价: 0.0"
    }

    def "should_print_line_item_and_sales_tax_information"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17"))
                .lineItemList([new LineItem("milk", 10.0, 2),
                               new LineItem("biscuits", 5.0, 5),
                               new LineItem("chocolate", 20.0, 1)]).build()
        def orderReceipt = new OrderReceipt(order);
        when:
        def bill = orderReceipt.printReceipt()
        def resultLine = bill.split("\n")
        then:
        resultLine.size() == 10
        resultLine[7] == "--------------------------"
        resultLine[8] == "税额: 6.5"
        resultLine[9] == "总价: 71.5"
    }

    def "should_print_line_item_info when getBill given 3 line item"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-17"))
                .lineItemList([new LineItem("milk", 10.0, 2),
                               new LineItem("biscuits", 5.0, 5),
                               new LineItem("chocolate", 20.0, 1)]).build()
        def orderReceipt = new OrderReceipt(order);
        when:
        def bill = orderReceipt.printReceipt()
        def resultLine = bill.split("\n")
        then:
        resultLine.size() == 10
        resultLine[3] == "milk,10.0*2,20.0"
        resultLine[4] == "biscuits,5.0*5,25.0"
        resultLine[5] == "chocolate,20.0*1,20.0"
    }

    def "should discount with98% when getBill given order in 星期三"() {
        given:
        def order = Order.builder().date(LocalDate.parse("2020-02-19"))
                .lineItemList([new LineItem("milk", 10.0, 2),
                               new LineItem("biscuits", 5.0, 5),
                               new LineItem("chocolate", 20.0, 1)]).build()
        def orderReceipt = new OrderReceipt(order);
        when:
        def bill = orderReceipt.printReceipt()
        def resultLine = bill.split("\n")
        then:
        resultLine.size() == 11
        resultLine[3] == "milk,10.0*2,20.0"
        resultLine[4] == "biscuits,5.0*5,25.0"
        resultLine[5] == "chocolate,20.0*1,20.0"

        resultLine[7] == "--------------------------"
        resultLine[8] == "税额: 6.5"
        resultLine[9] == "折扣: 1.430"
        resultLine[10] == "总价: 70.070"
    }
}
