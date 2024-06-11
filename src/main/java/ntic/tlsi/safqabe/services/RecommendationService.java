package ntic.tlsi.safqabe.services;

import ntic.tlsi.safqabe.entities.RecommendationItemBasedModel;
import org.springframework.stereotype.Service;
import py4j.GatewayServer;
import java.util.List;

@Service
public class RecommendationService {
    private RecommendationItemBasedModel model;

    public void setModel(RecommendationItemBasedModel model) {
        this.model = model;
    }

    public List<String> predict(String productName) {
        return model.giveRec(productName);
    }

    public static void main(String[] args) {
        RecommendationService app = new RecommendationService();
        GatewayServer gatewayServer = new GatewayServer(app);
        gatewayServer.start();
        System.out.println("Gateway Server Started");
    }
}

