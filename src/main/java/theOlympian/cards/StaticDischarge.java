package theOlympian.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theOlympian.DefaultMod;
import theOlympian.characters.TheDefault;
import theOlympian.powers.JoltedEnemyPower;
import theOlympian.powers.LightningPower;

import java.util.Iterator;

import static theOlympian.DefaultMod.makeCardPath;

public class StaticDischarge extends AbstractDynamicCard {

    // TEXT DECLARATION

    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.
    public static final String ID = DefaultMod.makeID(StaticDischarge.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.NONE;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;

    private static final int JOLTED = 12; // Jolted
    private static final int UPGRADE_PLUS_JOLTED = 3; // Jolted

    // /STAT DECLARATION/

    public StaticDischarge() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = JOLTED;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Apply BLOCK Jolted to all enemies
        Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();

        while (var1.hasNext())
        {
            AbstractMonster m_n = (AbstractMonster)var1.next();
            if (!m_n.isDead && !m_n.isDying) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(m_n, p, new JoltedEnemyPower(m_n, p, magicNumber), magicNumber));
            }
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_JOLTED);
            initializeDescription();
        }
    }
}
