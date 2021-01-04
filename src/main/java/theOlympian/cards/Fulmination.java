package theOlympian.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theOlympian.DefaultMod;
import theOlympian.characters.TheDefault;
import theOlympian.powers.FulminationPower;
import theOlympian.powers.JoltedEnemyPower;

import static theOlympian.DefaultMod.makeCardPath;

public class Fulmination extends AbstractDynamicCard {

    // TEXT DECLARATION

    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.
    public static final String ID = DefaultMod.makeID(Fulmination.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int JOLTED = 15;
    private static final int UPGRADE_PLUS_JOLTED = 3;

    // /STAT DECLARATION/


    public Fulmination() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = JOLTED;

        this.isEthereal = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        System.out.println(this.baseMagicNumber);
        System.out.println(this.magicNumber);

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new JoltedEnemyPower(m, p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FulminationPower(p, p, 1), 0));
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
