package com.gvnavin.infra.explorecdk.stage;

import com.gvnavin.infra.explorecdk.input.Accounts;
import com.gvnavin.infra.explorecdk.constants.Constants;
import com.gvnavin.infra.explorecdk.constants.Regions;
import com.gvnavin.infra.explorecdk.constants.Stages;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StageProps;

public class StageFactory {

    @NotNull
    public static StageProps getStageProps(String wave, String stage) {
        return StageProps.builder()
                .env(getEnv(wave, stage))
                .build();
    }

    @NotNull
    private static Environment getEnv(String wave, String stage) {
        return Environment.builder()
                .account(getAccountNumber(wave, stage))
                .region(Regions.WAVE_1_REGION)
                .build();
    }

    private static String getAccountNumber(String wave, String stage) {
        if (wave.equalsIgnoreCase(Constants.WAVE_1_ID)) {
            if (stage.equalsIgnoreCase(Stages.STAGE_DEV)) {
                return Accounts.WAVE_1_ACCOUNT_DEV;
            } else if (stage.equalsIgnoreCase(Stages.STAGE_BETA)) {
                return Accounts.WAVE_1_ACCOUNT_BETA;
            } else if (stage.equalsIgnoreCase(Stages.STAGE_GAMMA)) {
                return Accounts.WAVE_1_ACCOUNT_GAMMA;
            } else if (stage.equalsIgnoreCase(Stages.STAGE_PROD)) {
                return Accounts.WAVE_1_ACCOUNT_PROD;
            }
        }
        return "";
    }

}
