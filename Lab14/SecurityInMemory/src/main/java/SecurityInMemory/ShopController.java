package SecurityInMemory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

    @GetMapping("/shop")
    public String shop() {
        return "Welcome to the shop!";
    }

    @GetMapping("/orders")
    public String orders() {
        return "Here are your orders!";
    }

    @GetMapping("/payments")
    public String payments() {
        return "Payment information!";
    }
}
