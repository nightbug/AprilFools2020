package Sanctuary.relics;

import Sanctuary.Sanctuary;
import Sanctuary.util.TextureLoader;
import Sanctuary.vfx.general.LoseRelicLater;
import Sanctuary.vfx.general.ObtainRelicLater;
import Sanctuary.vfx.general.ObtainRelicLater2;
import basemod.BaseMod;
import basemod.abstracts.CustomBottleRelic;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;

import java.util.ArrayList;

import static Sanctuary.Sanctuary.makeRelicOutlinePath;
import static Sanctuary.Sanctuary.makeRelicPath;

public class CB_Recursive extends CustomRelic implements ClickableRelic {

    public static final String ID = Sanctuary.makeID(CB_Recursive.class.getSimpleName());
    public static Texture IMG = TextureLoader.getTexture(makeRelicPath("null.png"));
    public static Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("null.png"));

    public boolean open = false;


    public CB_Recursive() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {

        if(this.counter == 0){
            return DESCRIPTIONS[0];
        }
        else if(this.counter > 0 && this.counter <= 4){
            return DESCRIPTIONS[4];
        }
        else if(this.counter > 4 && this.counter <= 15){
            return DESCRIPTIONS[5];
        }
        else if(this.counter == 16){
            return DESCRIPTIONS[1] + DESCRIPTIONS[2];
        }
        else if(this.counter != 20)
        {
            String description = DESCRIPTIONS[1] + DESCRIPTIONS[3];
            int i = 17;
            while (i != this.counter + 1){
                description += DESCRIPTIONS[3];
            }
            description += DESCRIPTIONS[2];

            return description;
        }
        else if (this.counter == 20){
            open = true;
            this.img = TextureLoader.getTexture(makeRelicPath("box.png"));
            this.outlineImg = TextureLoader.getTexture(makeRelicOutlinePath("box.png"));
            this.flavorText = "A stack of empty boxes.";
            return DESCRIPTIONS[6];
        }

        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        this.counter = 0;

    }


    @Override
    public void onRightClick() {

        if(!open) {
            this.counter += 1;
            getUpdatedDescription();
        }


    }

}