package Sanctuary.vfx.general;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class LoseRelicLater extends AbstractGameEffect
{
    private AbstractRelic relic;

    public LoseRelicLater(AbstractRelic relic)
    {
        this.relic = relic;
        duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update()
    {
        AbstractDungeon.player.loseRelic(relic.relicId);
        isDone = true;
    }

    @Override
    public void render(SpriteBatch spriteBatch)
    {

    }

    @Override
    public void dispose()
    {

    }
}