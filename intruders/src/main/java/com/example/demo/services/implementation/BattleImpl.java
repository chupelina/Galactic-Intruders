package com.example.demo.services.implementation;

import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.serviceModels.ShipModel;
import com.example.demo.services.Battle;
import com.example.demo.services.ShipService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BattleImpl implements Battle {
    private final ShipService shipService;

    public BattleImpl(ShipService shipService) {
        this.shipService = shipService;
    }

    @Override
    public boolean startBattle(String data, List<ShipModel> army, PlanetResourceModelInfo planetModelInfo) {
        List<ShipModel> collectArmy = army.stream().filter(shipModel -> shipModel.getGoneToBattle() > 0).collect(Collectors.toList());
        List<ShipModel> enemy = shipService.createEnemy();
        List<ShipModel> collectEnemies = enemy.stream().filter(c -> c.getGoneToBattle() > 0).collect(Collectors.toList());
        while (collectEnemies.size()!=0 && collectArmy.size()!=0) {
            for (ShipModel collectEnemy : collectEnemies) {
                for (ShipModel shipModel : collectArmy) {
                    attack(collectEnemy, shipModel);
                }
            }
            for (ShipModel shipModel : collectArmy) {
                for (ShipModel collectEnemy : collectEnemies) {
                    attack(shipModel, collectEnemy);
                }
            }
            collectArmy = army.stream().filter(shipModel -> shipModel.getGoneToBattle() > 0).collect(Collectors.toList());
            collectEnemies = enemy.stream().filter(c -> c.getGoneToBattle() > 0).collect(Collectors.toList());
        }
        if(collectArmy.size()==0){
            shipService.decreaseArmy(data, planetModelInfo);
            return false;
        }else{
            return true;
        }
    }
    private boolean attack(ShipModel ship, ShipModel enemy ){
        enemy.setHealth(enemy.getHealth() - ship.getAttack());
        if(enemy.getHealth()<0){
            enemy.setGoneToBattle(enemy.getGoneToBattle()-1);
            if(enemy.getGoneToBattle()==0){
                return true;
            }
        }
        return false;
    }
}

