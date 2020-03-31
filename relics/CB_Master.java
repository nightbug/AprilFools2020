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

public class CB_Master extends CustomRelic implements ClickableRelic {

    public static final String ID = Sanctuary.makeID(CB_Master.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("null.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("null.png"));

    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static AbstractRelic qqq;
    public AbstractRelic q34;


    public CB_Master() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        this.counter = 0;

        AbstractRelic yes = AbstractDungeon.player.relics.get(0);
        AbstractRelic no = AbstractDungeon.player.getRelic(ID);
        moveRelic(no, yes);

        nukerelics();



    }

    private void nukerelics(){
        for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
            AbstractRelic q = AbstractDungeon.player.relics.get(i);
            if (!(q.relicId.equals(CB_Master.ID))) {
                AbstractDungeon.effectsQueue.add(0, new LoseRelicLater(q));
                this.counter += 1;
            }
        }
    }
    private static void moveRelic(AbstractRelic r1, AbstractRelic r2) {
        /*  55 */
        float oldX = r1.currentX;
        /*  56 */
        float oldHBX = r1.hb.x;
        /*  57 */
        r1.currentX = r2.currentX;
        /*  58 */
        r1.hb.x = r2.hb.x;
        /*  59 */
        r2.hb.x = oldHBX;
        /*  60 */
        r2.currentX = oldX;
        /*     */
    }



    @Override
    public void onRightClick() {

        while (this.counter != 0){
            q34 = shop();
            this.counter -= 1;
        }


    }

    private static AbstractRelic shop() {

        Random rng = AbstractDungeon.relicRng;
        ArrayList<AbstractRelic> tmp = new ArrayList<AbstractRelic>();

        for(AbstractRelic r : RelicLibrary.starterList) {
            tmp.add(r.makeCopy());
        }
        for(AbstractRelic r : RelicLibrary.commonList) {
            tmp.add(r.makeCopy());
        }
        for(AbstractRelic r : RelicLibrary.uncommonList) {
            tmp.add(r.makeCopy());
        }
        for(AbstractRelic r : RelicLibrary.rareList) {
                tmp.add(r.makeCopy());
        }
        for(AbstractRelic r : RelicLibrary.specialList) {
            tmp.add(r.makeCopy());
        }
        for(AbstractRelic r : RelicLibrary.bossList) {
            tmp.add(r.makeCopy());
        }
        AbstractRelic shop = tmp.get(rng.random(tmp.size() - 1));

        while(shop.relicId.equals(BottledFlame.ID)
                        || shop.relicId.equals(BottledLightning.ID)
                        || shop.relicId.equals(BottledTornado.ID)
                        || shop.relicId.equals(Astrolabe.ID)
                        || shop.relicId.equals(TinyHouse.ID)
                        || shop.relicId.equals(Orrery.ID)
                        || shop.relicId.equals(PandorasBox.ID)
                        || shop.relicId.equals("Kintsugi")
                        || shop.relicId.equals("Thesaurus")
                        || shop instanceof CustomBottleRelic
                        || shop.relicId.equals(EmptyCage.ID)
                        || shop.relicId.equals(CallingBell.ID)
                        || shop.relicId.equals(CB_Master.ID)){

            shop = tmp.get(rng.random(tmp.size() - 1));

        }

        AbstractDungeon.effectsQueue.add(0, new ObtainRelicLater(shop));
        return shop;

    }

}
