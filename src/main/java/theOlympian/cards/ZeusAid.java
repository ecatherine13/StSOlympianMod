package theOlympian.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoxiousFumesPower;
import theOlympian.DefaultMod;
import theOlympian.characters.TheDefault;
import theOlympian.powers.CloudedJudgementPower;
import theOlympian.powers.HeavenlyFurorPower;
import theOlympian.powers.JoltedPlayerPower;
import theOlympian.powers.LightningPower;

import static theOlympian.DefaultMod.makeCardPath;

public class ZeusAid extends AbstractDynamicCard {

    // TEXT DECLARATION

    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.
    public static final String ID = DefaultMod.makeID(ZeusAid.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int LIGHTNING_DAMAGE = 8;
    private static final int UPGRADE_PLUS_LIGHTNING = 4;


    // /STAT DECLARATION/


    public ZeusAid() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        this.baseMagicNumber = this.magicNumber = LIGHTNING_DAMAGE;

        this.exhaust = true;
        this.selfRetain = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Apply three turns of Lightning buff to player
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new LightningPower(p, p, 3, magicNumber), magicNumber));

        // Heavenly Furor (doesn't remove)
        if (p.hasPower(HeavenlyFurorPower.POWER_ID))
        {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new LightningBolt(), p.getPower(HeavenlyFurorPower.POWER_ID).amount));
        }

        // Clouded Judgement (doesn't remove)
        if (p.hasPower(CloudedJudgementPower.POWER_ID))
        {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, p.getPower(CloudedJudgementPower.POWER_ID).amount));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_LIGHTNING);
            initializeDescription();
        }
    }
}
