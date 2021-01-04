package theOlympian.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theOlympian.DefaultMod;
import theOlympian.cards.ZeusAid;
import theOlympian.util.TextureLoader;

import static theOlympian.DefaultMod.makeRelicOutlinePath;
import static theOlympian.DefaultMod.makeRelicPath;

public class ThunderSignet extends CustomRelic {

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("ThunderSignet");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Thunder_Signet.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Thunder_Signet.png"));

    public ThunderSignet() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    // Stats
    private static final int TURNS = 8;

    // Flash at the start of Battle.
    @Override
    public void atBattleStartPreDraw() {
        flash();
        this.counter = 0;
    }

    @Override
    public void atTurnStart()
    {
        if (this.counter == TURNS) {
            flash();

            // Add card to hand
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new MakeTempCardInHandAction(new ZeusAid(), 1, false));

            // grayscale
            this.grayscale = true;
        }
        else
        {
            ++this.counter;
        }
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
