import com.VendingMachine.controller.VendingMachineController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
//        VendingMachineController vendingMachineController = new VendingMachineController();
//
//        vendingMachineController.run();
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.VendingMachine");
        appContext.refresh();

        VendingMachineController controller = appContext.getBean("vendingMachineController", VendingMachineController.class);
        controller.run();



    }
}
