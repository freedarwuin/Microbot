package net.runelite.client.plugins.microbot.LT.Pizza;

import java.util.concurrent.TimeUnit;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.util.bank.Rs2Bank;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.keyboard.Rs2Keyboard;
import net.runelite.client.plugins.microbot.util.widget.Rs2Widget;

public class PizzaScript extends Script {
    private static final int REQUIRED_QUANTITY = 5;

    public PizzaScript() {
    }

    public boolean run() {
        Microbot.enableAutoRunOn = false;
        this.mainScheduledFuture = this.scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                if (!Microbot.isLoggedIn()) {
                    return;
                }

                if (!super.run()) {
                    return;
                }

                if (this.hasEnoughPizza()) {
                    System.out.println("Detectadas 5 tartas de verano, abriendo banco...");
                    this.openBankToDepositAndRestart();
                }

                if (!this.isInventoryReady() || !this.hasRequiredMaterials()) {
                    System.out.println("Apertura de banco para preparar inventario...");
                    this.manageBankAndWithdrawMaterials();
                }

                if (this.hasRequiredMaterials()) {
                    this.createBatchOfPizza();
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }

        }, 0L, 1000L, TimeUnit.MILLISECONDS);
        return true;
    }

    private boolean hasEnoughPizza() {
        return Rs2Inventory.hasItem(new Integer[]{2287, 5});
    }

    private boolean isInventoryReady() {
        return Rs2Inventory.hasItem(new Integer[]{1929, 5}) && Rs2Inventory.hasItem(new Integer[]{1933, 5}) && Rs2Inventory.hasItem(new Integer[]{1982, 5}) && Rs2Inventory.hasItem(new Integer[]{1985, 5});
    }

    private boolean hasRequiredMaterials() {
        return this.isInventoryReady();
    }

    private void manageBankAndWithdrawMaterials() {
        Rs2Bank.openBank();
        Rs2Bank.depositAll();
        this.sleepUntil(Rs2Inventory::isEmpty, 5000);
        Rs2Bank.withdrawX(1929, 5);
        this.sleepUntil(() -> Rs2Inventory.hasItem(1929), 5000);
        Rs2Bank.withdrawX(1933, 5);
        this.sleepUntil(() -> Rs2Inventory.hasItem(1933), 5000);
        Rs2Bank.withdrawX(1982, 5);
        this.sleepUntil(() -> Rs2Inventory.hasItem(1982), 5000);
        Rs2Bank.withdrawX(1985, 5);
        this.sleepUntil(() -> Rs2Inventory.hasItem(1985), 5000);
        Rs2Bank.closeBank();
    }

    private void createBatchOfPizza() {
        System.out.println("Creando un lote de 5 pizzas...");

        for(int i = 0; i < 5; ++i) {
            if (Rs2Inventory.hasItem(1929) && Rs2Inventory.hasItem(1933)) {
                this.combineTwoItems(1929, 1933);
                this.sleepUntil(() -> Rs2Inventory.hasItem(2283), 8000);
            }

            if (Rs2Inventory.hasItem(2283) && Rs2Inventory.hasItem(1982)) {
                this.combineTwoItems(2283, 1982);
                this.sleepUntil(() -> Rs2Inventory.hasItem(2285), 8000);
            }

            if (Rs2Inventory.hasItem(1985) && Rs2Inventory.hasItem(2285)) {
                this.combineTwoItems(1985, 2285);
                this.sleepUntil(() -> Rs2Inventory.hasItem(2287), 8000);
            }
        }

        this.openBankToDepositAndRestart();
    }

    private void combineTwoItems(int item1, int item2) {
        if (Rs2Inventory.hasItem(item1) && Rs2Inventory.hasItem(item2)) {
            Rs2Inventory.combineClosest(item1, item2);
            this.sleepUntil(() -> Rs2Widget.getWidget(270, 14) != null, 8000);
            this.selectPizzaBaseOption();
            this.confirmCombination();
        }

    }

    private void selectPizzaBaseOption() {
        int widgetGroupId = 270;
        int pizzaBaseChildId = 16;
        boolean menuAbierto = this.sleepUntil(() -> Rs2Widget.getWidget(widgetGroupId, pizzaBaseChildId) != null, 5000);
        if (!menuAbierto) {
            System.out.println("No se detectó el menú de selección de masa.");
        } else {
            System.out.println("Menú de selección de masa detectado, intentando seleccionar 'Pizza Base'...");
            if (Rs2Widget.clickWidget(widgetGroupId, pizzaBaseChildId)) {
                System.out.println("Se hizo clic en la opción 'Pizza Base'.");
                this.sleep(1000, 1500);
            } else {
                System.out.println("No se pudo hacer clic en la opción 'Pizza Base'.");
            }

        }
    }

    private void confirmCombination() {
        Rs2Keyboard.keyPress(32);
        this.sleep(3000, 3500);
        Rs2Keyboard.keyRelease(32);
        this.sleep(5000, 6000);
    }

    private void openBankToDepositAndRestart() {
        Rs2Bank.openBank();
        Rs2Bank.depositAll(2287);
        this.sleepUntil(() -> !Rs2Inventory.hasItem(2287), 300);
        this.manageBankAndWithdrawMaterials();
    }

    public void shutdown() {
        super.shutdown();
    }
}