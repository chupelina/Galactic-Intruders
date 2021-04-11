package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.serviceModels.ShipModel;
import com.example.demo.services.Battle;
import com.example.demo.services.ShipService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
public class BattleRestController {
  private final Battle battle;
  private final ShipService shipService;

    public BattleRestController(Battle battle, ShipService shipService) {
        this.battle = battle;
        this.shipService = shipService;
    }


    @PostMapping("/api/battle/{data}")
    public  String startBattle(@PathVariable String data, HttpSession session, RedirectAttributes redirectAttributes){
        List<ShipModel> army = shipService.getArmy(
                (PlanetResourceModelInfo) session.getAttribute("planetModelInfo"));
        Arrays.stream(data.split("-")).forEach(c->{
            String[] currentShip = c.split(":");
            for (ShipModel shipModel : army) {
                if(currentShip[0].equals(shipModel.getName())){
                    int gone = Integer.parseInt(currentShip[2]);
                    if(shipModel.getCount()<gone){
                        throw new UnsupportedOperationException();
                    }
                    shipModel.setGoneToBattle(gone);
                }
            }
        });
        boolean winner = battle.startBattle(data, army,  (PlanetResourceModelInfo) session.getAttribute("planetModelInfo"));
        redirectAttributes.addFlashAttribute("winner", winner);
        return "redirect:/battle";
    }
}
