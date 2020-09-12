package org.filip;

import org.slf4j.*;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RandomLoadApplier {

    private final Logger LOGGER = LoggerFactory.getLogger(RandomLoadApplier.class);

    private int[] loads = new int[] {0, 1000, 5000, 10000};
    private Random random;

    public RandomLoadApplier() {
        random = new Random();
    }

    public void applyALoad() {
        int upperLimit = loads.length;
        int timeToSleep = loads[random.nextInt(upperLimit)];

        LOGGER.info("Applying a load of {} seconds", timeToSleep);
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException("interrupted exception");
        }
    }
}
