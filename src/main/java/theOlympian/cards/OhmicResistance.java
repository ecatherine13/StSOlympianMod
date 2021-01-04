package theOlympian.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theOlympian.DefaultMod;
import theOlympian.characters.TheDefault;
import theOlympian.powers.JoltedEnemyPower;

import java.util.Iterator;

import static theOlympian.DefaultMod.makeCardPath;

public class OhmicResistance extends AbstractDynamicCard {

    // TEXT DECLARATION

    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.
    public static final String ID = DefaultMod.makeID(OhmicResistance.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 2;


    // /STAT DECLARATION/


    public OhmicResistance() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Flat 8 Block
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));

        boolean all_jolted = true;
        // Add block if ALL enemies have JoltedEnemyPower
        Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();

        while (var1.hasNext())
        {
            AbstractMonster m_n = (AbstractMonster)var1.next();
            if (!m_n.isDead && !m_n.hasPower(JoltedEnemyPower.POWER_ID))
            {
                all_jolted = false;
                break;
            }
        }

        if (all_jolted)
        {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
