import Interfaces.IRentACarCompany;
import Models.RentACarCompany;

import java.io.FileNotFoundException;

public class RentACarApplication {
    public static void main(String[] args) {
        try {
            IRentACarCompany rentACarCompany = new RentACarCompany();
            rentACarCompany.work();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }
}
