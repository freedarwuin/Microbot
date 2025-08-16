![image](https://github.com/user-attachments/assets/7c08e053-c84f-41f8-bc97-f55130100419)

# Microbot
Microbot es un cliente de Old School RuneScape de código abierto basado en Runelite. Utiliza un sistema de plugins que permite habilitar *scripts*. Aquí tienes un canal de YouTube mostrando algunos de los scripts:

## YouTube

[![image](https://github.com/user-attachments/assets/f15ec853-9b92-474e-a269-9a984e8bb792)](https://www.youtube.com/channel/UCEj_7N5OPJkdDi0VTMOJOpw)

## Discord

[![Discord Banner 1](https://discord.com/api/guilds/1087718903985221642/widget.png?style=banner1)](https://discord.gg/zaGrfqFEWE)

Si tienes alguna pregunta, únete a nuestro servidor de [Discord](https://discord.gg/zaGrfqFEWE).

## ☕ Cómprame un café

Si disfrutas de mi trabajo *open source* y quieres apoyarme, considera invitarme un café. ¡Tu apoyo me ayuda a mantenerme despierto y motivado para seguir mejorando y creando proyectos increíbles!

[![Buy Me a Coffee](https://img.shields.io/badge/Buy%20Me%20a%20Coffee-donate-yellow)](https://www.paypal.com/paypalme/MicrobotBE?country.x=BE)

![image](https://github.com/user-attachments/assets/c510631d-5ecf-4968-a916-2942f9b754f8)

**BTC Address:** bc1q4c63nc5jt9wem87cy7llsk2ur5psjnqhltt2kf  
**LTC Address:** ltc1qgk0dkchfd8tf7jvtj5708vheq82k2wyqucrqs7  
**ETC Address:** 0xf8A6d6Fae32319A93341aE45F1ED87DA2Aa04132  
**DOGE Address:** DNHQDHKn7MKdMQRZyoSrJ68Lnd1D9bjbTn

¡Gracias por tu apoyo! 😊

# Quiero jugar

## Cuenta que no sea de Jagex

Aquí tienes un video de YouTube sobre cómo configurar Microbot desde cero para **CUENTAS QUE NO SON DE JAGEX**:

https://www.youtube.com/watch?v=EbtdZnxq5iw

## Cuenta Jagex

Sigue la wiki de Runelite para configurar cuentas de Jagex:  
https://github.com/runelite/runelite/wiki/Using-Jagex-Accounts

Después de configurarlo, sigue estos dos pasos:

1. Inicia sesión con el *Jagex Launcher* por primera vez. Esto creará un token para tu cuenta. Cierra todo una vez que hayas iniciado sesión correctamente con el *Jagex Launcher*.
2. Abre el **microbot.jar** de Microbot y esto debería mostrarte la cuenta de Jagex para iniciar sesión.

# Quiero desarrollar

## ¿Primera vez ejecutando el proyecto como desarrollador?

Asegúrate de seguir esta guía si es tu primera vez ejecutando el proyecto:

[https://github.com/runelite/runelite/wiki/Building-with-IntelliJ-IDEA](https://github.com/chsami/microbot/wiki/Building-with-IntelliJ-IDEA)

## Microbot ChatGPT Chatbot

[![image](https://github.com/user-attachments/assets/92adb50f-1500-44c0-a069-ff976cccd317)](https://chatgpt.com/g/g-LM0fGeeXB-microbot-documentation)

Usa este chatbot de IA para aprender cómo escribir *scripts* en [Microbot GPT](https://chatgpt.com/g/g-LM0fGeeXB-microbot-documentation)

## Estructura del proyecto

Dentro del plugin de Microbot encontrarás una carpeta **util** que contiene todas las clases de utilidades que facilitan la interacción con el juego.

Las clases de utilidades tienen el prefijo **Rs2**. Por ejemplo: para el jugador es **Rs2Player**, para NPCs es **Rs2Npc**, etc.

Si no encuentras algo específico en una clase de utilidades, siempre puedes llamar al objeto **Microbot**, el cual tiene acceso a todos los objetos que expone Runelite. Por ejemplo, para obtener la ubicación de un jugador puedes hacer:

```java
Microbot.getClient().getLocalPlayer().getWorldLocation()
```

![img.png](img.png)

## ExampleScript

Hay un script de ejemplo que puedes usar para experimentar con la API.

![img_1.png](img_1.png)

### ¿Cómo luce el script de ejemplo?

```java
public class ExampleScript extends Script {
public static double version = 1.0;

    public boolean run(ExampleConfig config) {
        Microbot.enableAutoRunOn = false;
        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            if (!super.run()) return;
            try {
                /*
                 * Clases importantes:
                 * Inventory
                 * Rs2GameObject
                 * Rs2GroundObject
                 * Rs2NPC
                 * Rs2Bank
                 * etc...
                 */

                long startTime = System.currentTimeMillis();
                
                //TU CÓDIGO VIENE AQUÍ
                Rs2Npc.attack("guard");
                
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Tiempo total por ciclo " + totalTime);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }, 0, 2000, TimeUnit.MILLISECONDS);
        return true;
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }
}
```

### Explicación de los scripts

Todos nuestros scripts constan de:

- **Config**: configuraciones para un script específico.
- **Overlay**: superposición visual para un script específico.
- **Plugin**: maneja el código para iniciar y detener el script.
- **Script**: contiene todo el código que Microbot debe ejecutar.

Dentro de la inicialización (*startup*) de un plugin podemos llamar al código del script de la siguiente manera:

```java
@Override
protected void startUp() throws AWTException {
if (overlayManager != null) {
overlayManager.add(exampleOverlay);
}
//LLAMADA A TU SCRIPT.RUN
exampleScript.run(config);
}
```

## Créditos

Créditos a Runelite por hacer todo esto posible ❤️

https://github.com/runelite/runelite

### Licencia

RuneLite está licenciado bajo la licencia BSD 2-clause. Consulta el encabezado de la licencia en el archivo correspondiente para estar seguro.
