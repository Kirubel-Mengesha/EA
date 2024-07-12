package client;

import generated.AddRequest;
import generated.AddResponse;
import generated.SubtractRequest;
import generated.SubtractResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CalculatorClient extends WebServiceGatewaySupport {

    public AddResponse add(int number1, int number2) {
        AddRequest request = new AddRequest();
        request.setNumber1(number1);
        request.setNumber2(number2);

        AddResponse response = (AddResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws", request,
                        new SoapActionCallback("http://springtraining/calculator/AddRequest"));

        return response;
    }

    public SubtractResponse subtract(int number1, int number2) {
        SubtractRequest request = new SubtractRequest();
        request.setNumber1(number1);
        request.setNumber2(number2);

        SubtractResponse response = (SubtractResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws", request,
                        new SoapActionCallback("http://springtraining/calculator/SubtractRequest"));

        return response;
    }
}

